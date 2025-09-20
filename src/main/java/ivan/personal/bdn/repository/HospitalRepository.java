package ivan.personal.bdn.repository;

import ivan.personal.bdn.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Hospital findByCodeAndName(String code, String name);

    Hospital findByCode(String code);
}