package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.TherapyLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapyLineRepository extends JpaRepository<TherapyLine, Long> {

}