package repositories;

import models.Criterion;
import models.CriterionsToCriterionSet;
import models.helpers.CriterionsToCriterionSetPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CriterionsToCriterionSetRepository extends JpaRepository<CriterionsToCriterionSet, CriterionsToCriterionSetPK> {

    @Query("SELECT cs.PK.criterion FROM CriterionsToCriterionSet cs WHERE cs.PK.criterionsSet.id = ?1")
    List<Criterion> getCriterionsByCriterionSetId(Integer criterionSetId);
}
