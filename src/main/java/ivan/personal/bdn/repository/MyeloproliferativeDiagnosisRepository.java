package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.MyeloproliferativeDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyeloproliferativeDiagnosisRepository extends JpaRepository<MyeloproliferativeDiagnosis, Long> {

}