package it.ivan.bdn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.ivan.bdn.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{

}
