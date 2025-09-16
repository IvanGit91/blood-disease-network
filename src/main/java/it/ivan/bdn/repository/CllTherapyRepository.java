package it.ivan.bdn.repository;

import it.ivan.bdn.model.CllTherapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CllTherapyRepository extends JpaRepository<CllTherapy, Long>
{

}