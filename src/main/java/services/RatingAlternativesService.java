package services;

import models.RatingAlternative;

import java.util.ArrayList;

public interface RatingAlternativesService extends CRUDService<RatingAlternative, Integer>{
    ArrayList<RatingAlternative> calculateRatingAlternatives(Integer dimensionId, String criterionNames, String criterionWeights, String alternativesNames, String alternativesWeights);
}
