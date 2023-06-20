package gov.iti.career.hub.authroizationserver.config;

import gov.iti.career.hub.authroizationserver.entities.Authority;
import gov.iti.career.hub.authroizationserver.entities.Role;
import gov.iti.career.hub.authroizationserver.entities.clients.*;
import gov.iti.career.hub.authroizationserver.entities.enums.AuthorityName;
import gov.iti.career.hub.authroizationserver.entities.enums.AuthorizationGrantType;
import gov.iti.career.hub.authroizationserver.entities.enums.ClientAuthenticationMethod;
import gov.iti.career.hub.authroizationserver.entities.enums.RoleName;
import gov.iti.career.hub.authroizationserver.repositories.*;
import gov.iti.career.hub.authroizationserver.entities.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class JpaApplicationRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final GrantTypeRepository grantTypeRepository;
    private final AuthenticationMethodRepository authenticationMethodRepository;
    private final ScopeRepository scopeRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args){
        Authority read = Authority.builder().name(AuthorityName.READ).build();
        Authority write = Authority.builder().name(AuthorityName.WRITE).build();
        Authority execute = Authority.builder().name(AuthorityName.EXECUTE).build();

        Scope openid = Scope.builder()
                .name(OidcScopes.OPENID)
                .build();
        Scope profile = Scope.builder()
                .name(OidcScopes.PROFILE)
                .build();

        AuthenticationMethod clientSecretBasic = AuthenticationMethod.builder()
                .name(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue())
                .build();

        GrantType authorizationCode = GrantType.builder()
                .name(AuthorizationGrantType.AUTHORIZATION_CODE.getValue())
                .build();

        GrantType refreshToken = GrantType.builder()
                .name(AuthorizationGrantType.REFRESH_TOKEN.getValue())
                .build();


        Set<Scope> scopes = new HashSet<>();
        scopes.add(openid);
        scopes.add(profile);

        Set<AuthenticationMethod> authenticationMethods = new HashSet<>();
        authenticationMethods.add(clientSecretBasic);

        Set<GrantType> grantTypes = new HashSet<>();
        grantTypes.add(authorizationCode);
        grantTypes.add(refreshToken);

        RedirectURI redirectURI = RedirectURI.builder()
                .uri("http://localhost:8888/authorized")
                .build();



        Client client = Client.builder()
                .clientId("client")
                .secret("secret")
                .scope(scopes)
                .redirectUri(redirectURI)
                .authenticationMethod(authenticationMethods)
                .grantType(grantTypes)
                .build();

        Role admin = Role.builder()
                .name(RoleName.ADMIN)
                .authorities(Set.of(read, write, execute))
                .build();
        Role moderator = Role.builder()
                .name(RoleName.MODERATOR)
                .authorities(Set.of(read, execute))
                .build();

        User khaled = User.builder()
                .username("khaled")
                .password("password")
                .roles(Set.of(admin))
                .build();

        User kareem = User.builder()
                .username("kareem")
                .password("password")
                .roles(Set.of(moderator))
                .build();

        scopeRepository.save(openid);
        scopeRepository.save(profile);

        authenticationMethodRepository.save(clientSecretBasic);

        grantTypeRepository.save(authorizationCode);
        grantTypeRepository.save(refreshToken);

        clientRepository.save(client);

        userRepository.save(khaled);
        userRepository.save(kareem);
    }
}