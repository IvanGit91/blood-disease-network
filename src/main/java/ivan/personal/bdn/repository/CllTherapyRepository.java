package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.CllTherapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CllTherapyRepository extends JpaRepository<CllTherapy, Long> {

}