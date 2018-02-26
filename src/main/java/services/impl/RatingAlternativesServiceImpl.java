package services.impl;

import models.RatingAlternatives;
import services.RatingAlternativesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RatingAlternativesServiceImpl implements RatingAlternativesService {

    @Override
    public ArrayList<RatingAlternatives> calculateRatingAlternatives(Integer dimensionId, String criterionNames, String criterionWeights, String alternativesNames, String alternativesWeights) {

        String nameAlternatives[] = alternativesNames.split(",");
        if (nameAlternatives != null) {
            String weightAlternativesArray[] = alternativesWeights.split(",");
            String weightCriterionArray[] = criterionWeights.split(",");
            String nameCriterionParams[] = criterionNames.split(",");

            int countAlternatives = 0;
            Double weightAlternatives[][] = new Double[nameAlternatives.length][nameCriterionParams.length];

            for (int i = 0; i < nameAlternatives.length; i++) {
                for (int j = 0; j < nameCriterionParams.length; j++) {
                    weightAlternatives[i][j] = Double.valueOf(weightAlternativesArray[countAlternatives]);
                    countAlternatives++;
                }
            }

            Double rank = 1.0;
            Double geometricMean[] = new Double[nameAlternatives.length];
            int m = 0;

            for (int i = 0; i < nameAlternatives.length; i++) {
                for (int j = 0; j < nameCriterionParams.length + 1; j++) {
                    if (j != nameCriterionParams.length) {
                        rank *= Math.pow(weightAlternatives[i][j], Double.parseDouble(weightCriterionArray[j]));
                    } else {
                        geometricMean[m] = rank;
                        m++;
                        rank = 1.0;
                    }
                }
            }

            Double sumGeometric = 0.0;

            for (int a = 0; a < geometricMean.length; a++) {
                sumGeometric += geometricMean[a];
            }

            Long normalizedWeight[] = new Long[nameAlternatives.length];

            for (int b = 0; b < geometricMean.length; b++) {
                normalizedWeight[b] = Math.round((geometricMean[b] / sumGeometric) * 10000) / 100;
            }
        }
        return null;
    }
}