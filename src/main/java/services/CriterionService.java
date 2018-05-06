package services;

import models.Criterion;

import java.util.ArrayList;

public interface CriterionService  extends CRUDService<Criterion, Integer> {
    ArrayList<Criterion> getCriterionByExpertId(Integer expertId);
}
