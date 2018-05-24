package services;

import models.AlternativesSet;
import models.Expert;
import models.ExpertsToAlternativeSet;
import models.helpers.ExpertsToAlternativeSetPK;

import java.util.List;

public interface ExpertsToAlternativeSetService extends CRUDService<ExpertsToAlternativeSet, ExpertsToAlternativeSetPK> {
    List<Expert> getExpertsByAlternativeSetId(Integer alternativeSetId);

    List<AlternativesSet> getAlternativesSetsByExpertId(Integer expertId);
}
