package repositories;

import models.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CriterionRepository extends JpaRepository<Criterion, Integer> {
    ArrayList<Criterion> getCriteriaByExpertId(Integer expertId);
}
