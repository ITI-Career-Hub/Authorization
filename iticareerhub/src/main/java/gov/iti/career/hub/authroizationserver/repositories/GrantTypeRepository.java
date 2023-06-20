package gov.iti.career.hub.authroizationserver.repositories;

import gov.iti.career.hub.authroizationserver.entities.clients.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GrantTypeRepository extends JpaRepository<GrantType, Integer> {

    @Query("SELECT g FROM GrantType g WHERE g.name = :name")
    Optional<GrantType> findByName(String name);
}
