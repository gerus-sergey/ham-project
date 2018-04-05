package repositories;

import models.RatingCriterion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingCriterionRepository extends JpaRepository<RatingCriterion, Integer> {
}
