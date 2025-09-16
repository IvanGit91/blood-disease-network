package it.ivan.bdn.repository;

import it.ivan.bdn.model.TreatmentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentResponseRepository extends JpaRepository<TreatmentResponse, Long>
{

}