package gov.iti.career.hub.authroizationserver.repositories;

import gov.iti.career.hub.authroizationserver.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}
