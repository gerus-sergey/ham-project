package services.impl;

import models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.RolesRepository;
import services.RolesService;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Transactional
    public Roles addOrUpdate(Roles obj) {
        return rolesRepository.saveAndFlush(obj);
    }

    @Transactional
    public List<Roles> getAll() {
        return rolesRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        rolesRepository.delete(id);
    }

    @Transactional
    public Roles get(Integer id) {
        return rolesRepository.findOne(id);
    }
}
