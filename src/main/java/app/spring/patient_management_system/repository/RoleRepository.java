package app.spring.patient_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.spring.patient_management_system.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRole(String string);

}
