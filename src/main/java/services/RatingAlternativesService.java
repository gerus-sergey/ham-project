package services;

import models.RatingAlternative;

import java.util.ArrayList;

public interface RatingAlternativesService extends CRUDService<RatingAlternative, Integer>{
    ArrayList<RatingAlternative> calculateRatingAlternatives(Integer dimensionId, ArrayList<RatingAlternative> ratingAlternatives);
    ArrayList<RatingAlternative> getRatingAlternativeByDimensionId(Integer dimensionId);
}
