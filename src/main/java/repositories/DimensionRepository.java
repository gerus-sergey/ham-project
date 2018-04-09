package repositories;

import models.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DimensionRepository extends JpaRepository<Dimension, Integer> {
    ArrayList<Dimension> getDimensionByExpertId(Integer expertId);
}
