package services;

import models.RatingCriterions;

import java.util.ArrayList;

public interface RatingCriterionsService {
    ArrayList<RatingCriterions> calculateRatingCriterions(Integer dimensionId, String criterionNames, String criterionWeights);
}
