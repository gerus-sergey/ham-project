package services;

import models.Dimension;

import java.util.ArrayList;

public interface DimensionService extends CRUDService<Dimension, Integer>{
    ArrayList<Dimension> getDimensionByExpertId(Integer expertId);
}
