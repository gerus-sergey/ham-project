package services.impl;

import models.Alternative;
import models.AlternativesToAlternativeSet;
import models.helpers.AlternativesToAlternativeSetPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AlternativesToAlternativeSetRepository;
import services.AlternativesToAlternativeSetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlternativesToAlternativeSetServiceImpl implements AlternativesToAlternativeSetService {

    @Autowired
    private AlternativesToAlternativeSetRepository alternativesToAlternativeSetRepository;

    @Transactional
    public AlternativesToAlternativeSet addOrUpdate(AlternativesToAlternativeSet obj) {
        return alternativesToAlternativeSetRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<AlternativesToAlternativeSet> getAll() {
        return alternativesToAlternativeSetRepository.findAll();
    }

    @Transactional
    public void delete(AlternativesToAlternativeSetPK id) {
        alternativesToAlternativeSetRepository.delete(id);
    }

    @Transactional
    public AlternativesToAlternativeSet get(AlternativesToAlternativeSetPK id) {
        return alternativesToAlternativeSetRepository.findOne(id);
    }

    @Transactional
    public List<Alternative> getAlternativesByAlternativeSetId(Integer alternativesSetId) {
        return alternativesToAlternativeSetRepository.getAlternativesByAlternativeSetId(alternativesSetId);
    }
}
