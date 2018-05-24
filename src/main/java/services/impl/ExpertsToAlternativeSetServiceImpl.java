package services.impl;

import models.AlternativesSet;
import models.Expert;
import models.ExpertsToAlternativeSet;
import models.helpers.ExpertsToAlternativeSetPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExpertsToAlternativeSetRepository;
import services.ExpertsToAlternativeSetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExpertsToAlternativeSetServiceImpl implements ExpertsToAlternativeSetService {

    @Autowired
    private ExpertsToAlternativeSetRepository expertsToAlternativeSetRepository;

    @Transactional
    public ExpertsToAlternativeSet addOrUpdate(ExpertsToAlternativeSet obj) {
        return expertsToAlternativeSetRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<ExpertsToAlternativeSet> getAll() {
        return expertsToAlternativeSetRepository.findAll();
    }

    @Transactional
    public void delete(ExpertsToAlternativeSetPK id) {
        expertsToAlternativeSetRepository.delete(id);
    }

    @Transactional
    public ExpertsToAlternativeSet get(ExpertsToAlternativeSetPK id) {
        return expertsToAlternativeSetRepository.findOne(id);
    }

    @Transactional
    public List<Expert> getExpertsByAlternativeSetId(Integer alternativeSetId) {
        return this.expertsToAlternativeSetRepository.getExpertsByAlternativeSetId(alternativeSetId);
    }

    @Transactional
    public List<AlternativesSet> getAlternativesSetsByExpertId(Integer expertId) {
        return this.expertsToAlternativeSetRepository.getAlternativesSetsByExpertId(expertId);
    }
}
