package repositories;

import models.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AlternativesRepository extends JpaRepository<Alternative, Integer> {
}
