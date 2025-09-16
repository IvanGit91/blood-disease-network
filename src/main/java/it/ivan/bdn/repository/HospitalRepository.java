package it.ivan.bdn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.ivan.bdn.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>
{
	Hospital findByCodeAndName(String code, String name);

	Hospital findByCode(String code);
}