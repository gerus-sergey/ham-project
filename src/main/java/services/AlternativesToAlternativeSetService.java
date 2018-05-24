package services;

import models.Alternative;
import models.AlternativesToAlternativeSet;
import models.helpers.AlternativesToAlternativeSetPK;

import java.util.List;

public interface AlternativesToAlternativeSetService extends CRUDService<AlternativesToAlternativeSet, AlternativesToAlternativeSetPK> {
    List<Alternative> getAlternativesByAlternativeSetId(Integer alternativesSetId);
}
