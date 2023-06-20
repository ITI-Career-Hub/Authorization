package gov.iti.career.hub.authroizationserver.security.entities;

import gov.iti.career.hub.authroizationserver.entities.clients.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

@AllArgsConstructor
public class SecurityClient extends RegisteredClient {
    private final Client client;


}
