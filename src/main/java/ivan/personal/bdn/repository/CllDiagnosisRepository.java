package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.CllDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CllDiagnosisRepository extends JpaRepository<CllDiagnosis, Long> {

}