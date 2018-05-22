package services.impl;

import models.CriterionsSet;
import models.Expert;
import models.ExpertsToCriterionSet;
import models.helpers.ExpertsToCriterionSetPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExpertsToCriterionSetRepository;
import services.ExpertsToCriterionSetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExpertsToCriterionSetServiceImpl implements ExpertsToCriterionSetService {

    @Autowired
    private ExpertsToCriterionSetRepository expertsToCriterionSetRepository;

    @Transactional
    public ExpertsToCriterionSet addOrUpdate(ExpertsToCriterionSet obj) {
        return expertsToCriterionSetRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<ExpertsToCriterionSet> getAll() {
        return expertsToCriterionSetRepository.findAll();
    }

    @Transactional
    public void delete(ExpertsToCriterionSetPK id) {
        expertsToCriterionSetRepository.delete(id);
    }

    @Transactional
    public ExpertsToCriterionSet get(ExpertsToCriterionSetPK id) {
        return expertsToCriterionSetRepository.findOne(id);
    }

    @Transactional
    public List<Expert> getExpertsByCriterionSetId(Integer criterionSetId) {
        return expertsToCriterionSetRepository.getExpertsByCriterionSetId(criterionSetId);
    }

    @Transactional
    public List<CriterionsSet> getCriterionSetsByExpertId(Integer expertId) {
        return expertsToCriterionSetRepository.getCriterionSetsByExpertId(expertId);
    }
}
