package it.ivan.bdn.repository;

import it.ivan.bdn.model.MedicalRecordCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordCategoryRepository extends JpaRepository<MedicalRecordCategory, Long>
{

}