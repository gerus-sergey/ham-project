package services;

import models.RatingCriterion;

import java.util.ArrayList;

public interface RatingCriterionsService extends CRUDService<RatingCriterion, Integer>{
    ArrayList<RatingCriterion> calculateRatingCriterions(Integer dimensionId, ArrayList<RatingCriterion> ratingCriteria);
}
