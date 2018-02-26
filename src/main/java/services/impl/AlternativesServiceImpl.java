package services.impl;

import models.Alternative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AlternativesRepository;
import services.AlternativesService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlternativesServiceImpl implements AlternativesService {

    @Autowired
    private AlternativesRepository alternativesRepository;

    @Transactional
    public Alternative addOrUpdate(Alternative obj) {
        return alternativesRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<Alternative> getAll() {
        return alternativesRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        alternativesRepository.delete(id);
    }

    @Transactional
    public Alternative get(Integer id) {
        return alternativesRepository.findOne(id);
    }
}
