package gov.iti.career.hub.authroizationserver.services;

import gov.iti.career.hub.authroizationserver.mappers.ClientMapper;
import gov.iti.career.hub.authroizationserver.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(mapper.toClient(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        var client = clientRepository
            .findById(Integer.valueOf(id))
            .orElseThrow(() -> new RuntimeException("Client Not Found"));
        return mapper.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client = clientRepository
            .findByClientId(clientId)
            .orElseThrow(() -> new RuntimeException("Client Not Found"));
        return mapper.toRegisteredClient(client);
    }

}
