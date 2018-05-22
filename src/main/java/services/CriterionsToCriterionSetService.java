package services;

import models.Criterion;
import models.CriterionsToCriterionSet;
import models.helpers.CriterionsToCriterionSetPK;

import java.util.List;

public interface CriterionsToCriterionSetService extends CRUDService<CriterionsToCriterionSet, CriterionsToCriterionSetPK> {
    List<Criterion> getCriterionsByCriterionSetId(Integer criterionSetId);
}
