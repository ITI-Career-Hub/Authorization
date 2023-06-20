package gov.iti.career.hub.authroizationserver.repositories;

import gov.iti.career.hub.authroizationserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
