package it.ivan.bdn.repository;

import it.ivan.bdn.model.BoneMarrowFibrosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoneMarrowFibrosisRepository extends JpaRepository<BoneMarrowFibrosis, Long>
{

}