package gov.iti.career.hub.authroizationserver.repositories;

import gov.iti.career.hub.authroizationserver.entities.clients.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScopeRepository extends JpaRepository<Scope, Integer> {

    @Query("SELECT s FROM Scope s WHERE s.name = :scopeName")
    Optional<Scope> findByScopeName(String scopeName);

}
