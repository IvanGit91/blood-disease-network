package it.ivan.bdn.repository;

import it.ivan.bdn.model.MyeloproliferativeTherapy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyeloproliferativeTherapyRepository extends JpaRepository<MyeloproliferativeTherapy, Long>
{

}