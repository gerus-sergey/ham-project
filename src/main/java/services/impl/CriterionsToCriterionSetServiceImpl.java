package services.impl;

import models.Criterion;
import models.CriterionsToCriterionSet;
import models.helpers.CriterionsToCriterionSetPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CriterionsToCriterionSetRepository;
import services.CriterionsToCriterionSetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CriterionsToCriterionSetServiceImpl implements CriterionsToCriterionSetService {

    @Autowired
    private CriterionsToCriterionSetRepository criterionsToCriterionSetRepository;

    @Transactional
    public CriterionsToCriterionSet addOrUpdate(CriterionsToCriterionSet obj) {
        return criterionsToCriterionSetRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<CriterionsToCriterionSet> getAll() {
        return criterionsToCriterionSetRepository.findAll();
    }

    @Transactional
    public void delete(CriterionsToCriterionSetPK id) {
        criterionsToCriterionSetRepository.delete(id);
    }

    @Transactional
    public CriterionsToCriterionSet get(CriterionsToCriterionSetPK id) {
        return criterionsToCriterionSetRepository.findOne(id);
    }

    @Transactional
    public List<Criterion> getCriterionsByCriterionSetId(Integer criterionSetId){
        return criterionsToCriterionSetRepository.getCriterionsByCriterionSetId(criterionSetId);
    }
}
