package it.ivan.bdn.repository;

import it.ivan.bdn.model.TherapyLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapyLineRepository extends JpaRepository<TherapyLine, Long>
{

}