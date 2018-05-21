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
    public ResponseEntity calculateRatingCriterions(Integer dimensionId, ArrayList<RatingCriterion> ratingCriterion) {

        if (!ratingCriterion.isEmpty()) {
            Double weight = 1.0;
            int m = 0;
            Double geometricMean[] = new Double[ratingCriterion.size()];
            String weights = "";
            for (RatingCriterion aRatingCriterion : ratingCriterion) {
                weights = weights.concat(aRatingCriterion.getWeights().concat(","));
            }
            String[] weightCriterionArray = weights.split(",");
            Double weightCriterion[][] = new Double[ratingCriterion.size()][ratingCriterion.size()];
            int countCriterion = 0;
            Double sumGeometric = 0.0;
            for (int i = 0; i < ratingCriterion.size(); i++) {
                for (int j = 0; j < ratingCriterion.size(); j++) {
                    weightCriterion[i][j] = Double.valueOf(weightCriterionArray[countCriterion]);
                    countCriterion++;
                }
            }

            for (Double[] aWeightCriterion : weightCriterion) {
                for (int j = 0; j < weightCriterion.length + 1; j++) {
                    if (j != weightCriterion.length) {
                        weight *= aWeightCriterion[j];
                    } else {
                        Double degree = (double) 1 / ratingCriterion.size();
                        Double rank = Math.pow(weight, degree);
                        geometricMean[m] = rank;
                        m++;
                        weight = 1.0;
                    }
                }
            }

            for (Double aGeometricMean : geometricMean) {
                sumGeometric += aGeometricMean;
            }

            Double[][] NW = new Double[ratingCriterion.size()][1];

            for (int b = 0; b < NW.length; b++) {
                NW[b][0] = geometricMean[b] / sumGeometric;
            }

            Double[][] multipleMatrix = matrixMultiplication(weightCriterion, NW);
            Double lamdaSum = 0.0;

            for (int i = 0; i < multipleMatrix.length; i++) {
                Double lamda = multipleMatrix[i][0] / NW[i][0];
                lamdaSum += lamda;
            }

            Double lamda = lamdaSum / multipleMatrix.length;
            Double CI = (lamda - ratingCriterion.size()) / (ratingCriterion.size() - 1);
            Double RI = 0.0;

            switch (ratingCriterion.size()) {
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

            Double CR = CI / RI;

            if (CR >= getNumberLogicalConsistency(ratingCriterion.size()) || CR <= 0) {
                return new ResponseEntity("The matrix is not consistent", HttpStatus.CONFLICT);
            } else {
                for (int i = 0; i < ratingCriterion.size(); i++) {
                    ratingCriterion.get(i).setRating(NW[i][0]);
                    ratingCriterion.get(i).setDimensionId(dimensionId);
                    addOrUpdate(ratingCriterion.get(i));
                }
                return new ResponseEntity(ratingCriterion, HttpStatus.OK);
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

    private Double getNumberLogicalConsistency(Integer countCriterions) {
        Double numberConsistency;
        if (countCriterions <= 3) {
            numberConsistency = 0.05;
        } else if (countCriterions <= 5) {
            numberConsistency = 0.08;
        } else {
            numberConsistency = 0.1;
        }
        return numberConsistency;
    }
}