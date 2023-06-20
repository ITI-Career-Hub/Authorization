package gov.iti.career.hub.authroizationserver.repositories;

import gov.iti.career.hub.authroizationserver.entities.clients.AuthenticationMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthenticationMethodRepository extends JpaRepository<AuthenticationMethod, Integer> {

    @Query("SELECT a FROM AuthenticationMethod a WHERE a.name = :authenticationMethodName")
    Optional<AuthenticationMethod> findByAuthenticationMethodName(String authenticationMethodName);
}
