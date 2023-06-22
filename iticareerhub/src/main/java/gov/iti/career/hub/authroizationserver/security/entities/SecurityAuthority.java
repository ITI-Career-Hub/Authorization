package gov.iti.career.hub.authroizationserver.security.entities;

import gov.iti.career.hub.authroizationserver.entities.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Role role;

    @Override
    public String getAuthority() {
        return role.getName().name();
    }
}
