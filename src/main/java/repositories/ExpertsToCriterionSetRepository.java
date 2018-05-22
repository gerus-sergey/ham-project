package repositories;

import models.CriterionsSet;
import models.Expert;
import models.ExpertsToCriterionSet;
import models.helpers.ExpertsToCriterionSetPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertsToCriterionSetRepository extends JpaRepository<ExpertsToCriterionSet, ExpertsToCriterionSetPK> {

    @Query("SELECT ecs.PK.expert FROM ExpertsToCriterionSet ecs WHERE ecs.PK.criterionsSet.id = ?1")
    List<Expert> getExpertsByCriterionSetId(Integer criterionSetId);

    @Query("SELECT ecs.PK.criterionsSet FROM ExpertsToCriterionSet ecs WHERE ecs.PK.expert.id = ?1")
    List<CriterionsSet> getCriterionSetsByExpertId(Integer expertId);
}
