package services.impl;

import models.RatingCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.RatingCriterionRepository;
import services.RatingCriterionsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingCriterionsServiceImpl implements RatingCriterionsService {

    @Override
    public ArrayList<RatingCriterion> calculateRatingCriterions(Integer dimensionId, ArrayList<RatingCriterion> ratingCriterion) {

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
            for (int b = 0; b < geometricMean.length; b++) {
                ratingCriterion.get(b).setRating(geometricMean[b] / sumGeometric * 100);
                ratingCriterion.get(b).setDimensionId(dimensionId);
                addOrUpdate(ratingCriterion.get(b));
            }
            return ratingCriterion;
        }
        return null;
    }

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
}