package services.impl;

import models.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DimensionRepository;
import services.DimensionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DimensionServiceImpl implements DimensionService {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Transactional
    public Dimension addOrUpdate(Dimension obj) {
        return dimensionRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<Dimension> getAll() {
        return dimensionRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        dimensionRepository.delete(id);
    }

    @Transactional
    public Dimension get(Integer id) {
        return dimensionRepository.findOne(id);
    }

    @Transactional
    public ArrayList<Dimension> getDimensionByExpertId(Integer expertId){
        return dimensionRepository.getDimensionByExpertId(expertId);
    }
}
