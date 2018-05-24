package repositories;

import models.Alternative;
import models.AlternativesToAlternativeSet;
import models.helpers.AlternativesToAlternativeSetPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlternativesToAlternativeSetRepository extends JpaRepository<AlternativesToAlternativeSet, AlternativesToAlternativeSetPK> {

    @Query("SELECT aas.PK.alternative FROM AlternativesToAlternativeSet aas WHERE aas.PK.alternativesSet.id = ?1")
    List<Alternative> getAlternativesByAlternativeSetId(Integer alternativesSetId);
}
