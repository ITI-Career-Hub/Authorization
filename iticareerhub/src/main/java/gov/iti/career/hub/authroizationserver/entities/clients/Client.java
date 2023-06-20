package gov.iti.career.hub.authroizationserver.entities.clients;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;

    @Column(unique = true, nullable = false)
    private String secret;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "client_id")
            , inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<Scope> scope = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "client_id")
            , inverseJoinColumns = @JoinColumn(name = "authentication_method_id"))
    private Set<AuthenticationMethod> authenticationMethod = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "client_id")
            , inverseJoinColumns = @JoinColumn(name = "grant_type_id"))
    private Set<GrantType> grantType = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "redirect_uri_id", nullable = false)
    private RedirectURI redirectUri;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(id, client.id)) return false;
        if (!Objects.equals(clientId, client.clientId)) return false;
        if (!Objects.equals(secret, client.secret)) return false;
        if (!Objects.equals(scope, client.scope)) return false;
        if (!Objects.equals(authenticationMethod, client.authenticationMethod))
            return false;
        if (!Objects.equals(grantType, client.grantType)) return false;
        return Objects.equals(redirectUri, client.redirectUri);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (authenticationMethod != null ? authenticationMethod.hashCode() : 0);
        result = 31 * result + (grantType != null ? grantType.hashCode() : 0);
        result = 31 * result + (redirectUri != null ? redirectUri.hashCode() : 0);
        return result;
    }

}
