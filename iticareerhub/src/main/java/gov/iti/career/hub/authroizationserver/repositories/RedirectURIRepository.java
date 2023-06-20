package gov.iti.career.hub.authroizationserver.repositories;

import gov.iti.career.hub.authroizationserver.entities.clients.RedirectURI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RedirectURIRepository extends JpaRepository<RedirectURI, Integer> {

    @Query("SELECT r FROM RedirectURI r WHERE r.uri = :uri")
    Optional<RedirectURI> findByUri(String uri);
}