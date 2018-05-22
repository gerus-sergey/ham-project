package services.impl;

import models.CriterionsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CriterionsSetRepository;
import services.CriterionsSetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CriterionsSetServiceImpl implements CriterionsSetService{

    @Autowired
    private CriterionsSetRepository criterionsSetRepository;

    @Transactional
    public CriterionsSet addOrUpdate(CriterionsSet obj) {
        return criterionsSetRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<CriterionsSet> getAll() {
        return criterionsSetRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        criterionsSetRepository.delete(id);
    }

    @Transactional
    public CriterionsSet get(Integer id) {
        return criterionsSetRepository.findOne(id);
    }
}
