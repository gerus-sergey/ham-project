package repositories;

import models.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativesRepository extends JpaRepository<Alternative, Integer> {
}
