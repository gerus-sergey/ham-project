package repositories;

import models.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository  extends JpaRepository<Expert, Integer> {
}
