package services.impl;

import models.RatingCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import repositories.RatingCriterionRepository;
import services.RatingCriterionsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingCriterionsServiceImpl implements RatingCriterionsService {

    @Autowired
    private RatingCriterionRepository ratingCriterionRepository;

    @Transactional
    public RatingCriterion addOrUpdate(RatingCriterion obj) {
        return ratingCriterionRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<RatingCriterion> getAll() {
        return ratingCriterionRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        ratingCriterionRepository.delete(id);
    }

    @Transactional
    public RatingCriterion get(Integer id) {
        return ratingCriterionRepository.findOne(id);
    }

    @Transactional
    public ArrayList<RatingCriterion> getRatingCriterionByDimensionId(Integer dimensionId) {
        return ratingCriterionRepository.getRatingCriterionByDimensionId(dimensionId);
    }

    @Override
    public ResponseEntity calculateRatingCriterions(Integer dimensionId, ArrayList<RatingCriterion> ratingCriterions) {

        if (!ratingCriterions.isEmpty()) {
            Double weightCriterions[][] = new Double[ratingCriterions.size()][ratingCriterions.size()];
            for (int i = 0; i < ratingCriterions.size(); i++) {
                String weightsCriterionArray[] = ratingCriterions.get(i).getWeights().split(",");
                if(weightsCriterionArray.length != ratingCriterions.size()) return new ResponseEntity("Number of criteria and weights doesn't match", HttpStatus.CONFLICT);
                for (int j = 0; j < ratingCriterions.size(); j++) {
                    weightCriterions[i][j] = Double.valueOf(weightsCriterionArray[j]);
                }
            }

            Double weight = 1.0;
            int m = 0;
            Double geometricMeans[] = new Double[ratingCriterions.size()];
            for (Double[] aWeightCriterion : weightCriterions) {
                for (int j = 0; j < weightCriterions.length + 1; j++) {
                    if (j != weightCriterions.length) {
                        weight *= aWeightCriterion[j];
                    } else {
                        Double degree = (double) 1 / ratingCriterions.size();
                        Double rank = Math.pow(weight, degree);
                        geometricMeans[m] = rank;
                        m++;
                        weight = 1.0;
                    }
                }
            }

            Double sumGeometric = 0.0;
            for (Double geometricMean : geometricMeans) {
                sumGeometric += geometricMean;
            }

            Double[][] NW = new Double[ratingCriterions.size()][1];
            for (int b = 0; b < NW.length; b++) {
                NW[b][0] = geometricMeans[b] / sumGeometric;
            }

            Double[][] multipleMatrix = matrixMultiplication(weightCriterions, NW);
            Double lambdaSum = 0.0;
            for (int i = 0; i < multipleMatrix.length; i++) {
                lambdaSum += multipleMatrix[i][0] / NW[i][0];
            }

            Double lambda = lambdaSum / multipleMatrix.length;
            Double CI = (lambda - ratingCriterions.size()) / (ratingCriterions.size() - 1);
            Double RI = 0.0;

            switch (ratingCriterions.size()) {
                case 2:
                    RI = 0.2;
                    break;
                case 3:
                    RI = 0.388;
                    break;
                case 4:
                    RI = 0.575;
                    break;
                case 5:
                    RI = 0.766;
                    break;
                case 6:
                    RI = 0.951;
                    break;
                case 7:
                    RI = 1.146;
                    break;
                case 8:
                    RI = 1.349;
                    break;
            }

            Double CR = CI / RI * 100;
            if (CR >= getNumberLogicalConsistency(ratingCriterions.size())) {
                System.out.print("CR = " + CR);
                return new ResponseEntity(String.format("%.2g%n", CR), HttpStatus.CONFLICT);
            } else {
                for (int i = 0; i < ratingCriterions.size(); i++) {
                    ratingCriterions.get(i).setRating(NW[i][0]);
                    ratingCriterions.get(i).setDimensionId(dimensionId);
                    addOrUpdate(ratingCriterions.get(i));
                }
                return new ResponseEntity(ratingCriterions, HttpStatus.OK);
            }
        }
        return null;
    }

    private Double[][] matrixMultiplication(Double[][] matrixOne, Double[][] matrixTwo) {
        int m = matrixOne.length;
        int n = matrixTwo[0].length;
        int o = matrixTwo.length;
        Double[][] res = new Double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = 0.0;
                for (int k = 0; k < o; k++) {
                    res[i][j] += matrixOne[i][k] * matrixTwo[k][j];
                }
            }
        }
        return res;
    }

    private Integer getNumberLogicalConsistency(Integer countCriterions) {
        Integer numberConsistency;
        if (countCriterions <= 3) {
            numberConsistency = 5;
        } else if (countCriterions <= 5) {
            numberConsistency = 8;
        } else {
            numberConsistency = 10;
        }
        return numberConsistency;
    }
}