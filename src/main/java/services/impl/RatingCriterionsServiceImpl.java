package services.impl;

import models.Criterion;
import models.RatingCriterions;
import services.RatingCriterionsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RatingCriterionsServiceImpl implements RatingCriterionsService {

    @Override
    public ArrayList<RatingCriterions> calculateRatingCriterions(Integer dimensionId, String criterionNames, String criterionWeights) {

        if (!criterionNames.isEmpty() || !criterionWeights.isEmpty()) {
            String nameCriterion[] = criterionNames.split(",");
            ArrayList<RatingCriterions> ratingCriterions = new ArrayList<RatingCriterions>();
            Double weight = 1.0;
            int m = 0;
            Double geometricMean[] = new Double[nameCriterion.length];

            if (nameCriterion != null) {
                String weightCriterionArray[] = criterionWeights.split(",");
                Double weightCriterion[][] = new Double[nameCriterion.length][nameCriterion.length];
                int countCriterion = 0;
                Double sumGeometric = 0.0;
                Double normalizedWeight[] = new Double[nameCriterion.length];
                for (int i = 0; i < nameCriterion.length; i++) {
                    for (int j = 0; j < nameCriterion.length; j++) {
                        weightCriterion[i][j] = Double.valueOf(weightCriterionArray[countCriterion]);
                        countCriterion++;
                    }
                }

                for (int i = 0; i < weightCriterion.length; i++) {
                    for (int j = 0; j < weightCriterion.length + 1; j++) {
                        if (j != weightCriterion.length) {
                            weight *= weightCriterion[i][j];
                        } else {
                            Double degree = (double) 1 / nameCriterion.length;
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
                    Criterion criterion = new Criterion();
                    criterion.setCriterionName(nameCriterion[b]);
                    RatingCriterions ratingCriterion = new RatingCriterions();
                    ratingCriterion.setCriterion(criterion);
                    ratingCriterion.setDimensionId(dimensionId);
                    ratingCriterion.setRating(geometricMean[b] / sumGeometric);
                    ratingCriterions.add(b, ratingCriterion);
                }
                return ratingCriterions;
            }
        }
        return null;
    }
}