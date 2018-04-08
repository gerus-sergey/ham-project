package repositories;

import models.RatingCriterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface RatingCriterionRepository extends JpaRepository<RatingCriterion, Integer> {
    ArrayList<RatingCriterion> getRatingCriterionByDimensionId(Integer dimensionId);
}
