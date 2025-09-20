package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.MedicalRecordCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordCategoryRepository extends JpaRepository<MedicalRecordCategory, Long> {

}