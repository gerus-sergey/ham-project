package services.impl;

import models.Criterion;
import repositories.CriterionRepository;
import services.CriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CriterionServiceImpl implements CriterionService {

    @Autowired
    private CriterionRepository criterionRepository;

    @Transactional
    public Criterion addOrUpdate(Criterion obj) {
        return criterionRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<Criterion> getAll() {
        return criterionRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        criterionRepository.delete(id);
    }

    @Transactional
    public Criterion get(Integer id) {
        return criterionRepository.findOne(id);
    }

    @Transactional
    public ArrayList<Criterion> getCriterionByExpertId(Integer expertId) {
        return criterionRepository.getCriteriaByExpertId(expertId);
    }
}
