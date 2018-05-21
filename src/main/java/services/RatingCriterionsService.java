package services;

import models.RatingCriterion;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface RatingCriterionsService extends CRUDService<RatingCriterion, Integer>{
    ResponseEntity calculateRatingCriterions(Integer dimensionId, ArrayList<RatingCriterion> ratingCriteria);
    ArrayList<RatingCriterion> getRatingCriterionByDimensionId(Integer dimensionId);
}
