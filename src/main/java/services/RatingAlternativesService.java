package services;

import models.RatingAlternatives;

import java.util.ArrayList;

public interface RatingAlternativesService {
    ArrayList<RatingAlternatives> calculateRatingAlternatives(Integer dimensionId, String criterionNames, String criterionWeights, String alternativesNames, String alternativesWeights);
}
