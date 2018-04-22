package services;

import models.Expert;

public interface ExpertService extends CRUDService<Expert, Integer> {
    Expert getUserByEmail(String email);
    Expert getByEmailAndPassword(String email, String password);
}
