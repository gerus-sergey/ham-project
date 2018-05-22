package services;

import models.CriterionsSet;
import models.Expert;
import models.ExpertsToCriterionSet;
import models.helpers.ExpertsToCriterionSetPK;

import java.util.List;

public interface ExpertsToCriterionSetService extends CRUDService<ExpertsToCriterionSet, ExpertsToCriterionSetPK> {
    List<Expert> getExpertsByCriterionSetId(Integer criterionSetId);

    List<CriterionsSet> getCriterionSetsByExpertId(Integer expertId);
}
