package it.ivan.bdn.repository;

import it.ivan.bdn.model.Who2016;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Who2016Repository extends JpaRepository<Who2016, Long>
{

}