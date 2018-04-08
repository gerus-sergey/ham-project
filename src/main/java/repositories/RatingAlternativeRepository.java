package repositories;

import models.RatingAlternative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface RatingAlternativeRepository extends JpaRepository<RatingAlternative, Integer> {
    ArrayList<RatingAlternative> getRatingAlternativeByDimensionId(Integer dimensionId);
}
