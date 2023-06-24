package gov.iti.career.hub.authroizationserver.mappers;

import gov.iti.career.hub.authroizationserver.repositories.GrantTypeRepository;
import gov.iti.career.hub.authroizationserver.repositories.RedirectURIRepository;
import gov.iti.career.hub.authroizationserver.entities.clients.Client;
import gov.iti.career.hub.authroizationserver.entities.clients.Scope;
import gov.iti.career.hub.authroizationserver.repositories.AuthenticationMethodRepository;
import gov.iti.career.hub.authroizationserver.repositories.ScopeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClientMapper {
    private final ScopeRepository scopeRepository;
    private final AuthenticationMethodRepository authenticationMethodRepository;
    private final GrantTypeRepository grantTypeRepository;
    private final RedirectURIRepository redirectURIRepository;

    public Client toClient(RegisteredClient registeredClient){
        Client client = Client.builder()
                .clientId(registeredClient.getClientId())
                .secret(registeredClient.getClientSecret())

                .scope(registeredClient.getScopes()
                        .stream()
                        .map(scope -> scopeRepository.findByScopeName(scope)
                            .orElseThrow(() -> new RuntimeException("Scope Not Found"))
                        )
                        .collect(Collectors.toSet()))

                .authenticationMethod(registeredClient.getClientAuthenticationMethods()
                        .stream()
                        .map(clientAuthenticationMethod ->
                            authenticationMethodRepository.findByAuthenticationMethodName(clientAuthenticationMethod.getValue())
                            .orElseThrow(() -> new RuntimeException("Authentication Method Not Found"))
                        )
                        .collect(Collectors.toSet()))

                .grantType(registeredClient.getAuthorizationGrantTypes()
                        .stream()
                        .map(authorizationGrantType -> grantTypeRepository.findByName(authorizationGrantType.getValue())
                            .orElseThrow(() -> new RuntimeException("Grant Type Not Found")))
                        .collect(Collectors.toSet()))

                .redirectUri(registeredClient.getRedirectUris()
                        .stream()
                        .map(s -> redirectURIRepository.findByUri(s)
                            .orElseThrow(() -> new RuntimeException("Redirect URI Not Found")))
                        .findAny().get()
                )
                .build();
        return client;
    }

    public RegisteredClient toRegisteredClient(Client client){
        RegisteredClient registeredClient = RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .scopes(
                    e -> e.addAll(client.getScope()
                    .stream()
                    .map(Scope::getName)
                    .collect(Collectors.toSet()))
                )

                .redirectUri(client.getRedirectUri().getUri())

                .clientAuthenticationMethods(
                    methods-> methods.addAll(
                        client.getAuthenticationMethod().stream()
                        .map(
                            clientAuthenticationMethod ->
                                    new ClientAuthenticationMethod(clientAuthenticationMethod.getName())
                        )
                        .collect(Collectors.toSet())
                    )
                )

                .authorizationGrantTypes(
                    authorizationGrantTypes ->
                        authorizationGrantTypes.addAll(
                            client.getGrantType()
                            .stream()
                            .map(
                                authorizationGrantType ->
                                new AuthorizationGrantType(authorizationGrantType.getName())
                            )
                            .collect(Collectors.toSet())
                        )
                )

                .tokenSettings(
                    TokenSettings.builder()
                    .accessTokenTimeToLive(Duration.ofHours(24))
                    .build()
                )
                .build();
        return registeredClient;
    }

}
