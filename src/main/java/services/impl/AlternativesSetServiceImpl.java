package services.impl;

import models.AlternativesSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AlternativesSetRepository;
import services.AlternativesSetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlternativesSetServiceImpl implements AlternativesSetService {

    @Autowired
    private AlternativesSetRepository alternativesSetRepository;

    @Transactional
    public AlternativesSet addOrUpdate(AlternativesSet obj) {
        return alternativesSetRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<AlternativesSet> getAll() {
        return alternativesSetRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        alternativesSetRepository.delete(id);
    }

    @Transactional
    public AlternativesSet get(Integer id) {
        return alternativesSetRepository.findOne(id);
    }
}
