package services.impl;

import models.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExpertRepository;
import services.ExpertService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService{

    @Autowired
    private ExpertRepository expertRepository;

    @Transactional
    public Expert addOrUpdate(Expert obj) {
        return expertRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<Expert> getAll() {
        return expertRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        expertRepository.delete(id);
    }

    @Transactional
    public Expert get(Integer id) {
        return expertRepository.findOne(id);
    }

    @Transactional
    public Expert getUserByEmail(String email){
        return expertRepository.findByEmail(email);
    }
}
