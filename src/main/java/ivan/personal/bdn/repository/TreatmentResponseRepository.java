package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.TreatmentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentResponseRepository extends JpaRepository<TreatmentResponse, Long> {

}