package repositories;

import models.AlternativesSet;
import models.Expert;
import models.ExpertsToAlternativeSet;
import models.helpers.ExpertsToAlternativeSetPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertsToAlternativeSetRepository extends JpaRepository<ExpertsToAlternativeSet, ExpertsToAlternativeSetPK> {

    @Query("SELECT eas.PK.expert FROM ExpertsToAlternativeSet eas WHERE eas.PK.alternativesSet.id = ?1")
    List<Expert> getExpertsByAlternativeSetId(Integer alternativeSetId);

    @Query("SELECT eas.PK.alternativesSet FROM ExpertsToAlternativeSet eas WHERE eas.PK.expert.id = ?1")
    List<AlternativesSet> getAlternativesSetsByExpertId(Integer expertId);
}
