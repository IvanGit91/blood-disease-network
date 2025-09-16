package it.ivan.bdn.repository;

import it.ivan.bdn.model.MyeloproliferativeDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyeloproliferativeDiagnosisRepository extends JpaRepository<MyeloproliferativeDiagnosis, Long>
{

}