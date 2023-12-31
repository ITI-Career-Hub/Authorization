package gov.iti.career.hub.authroizationserver.security.services;

import gov.iti.career.hub.authroizationserver.repositories.UserRepository;
import gov.iti.career.hub.authroizationserver.security.entities.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(SecurityUser::new)
            .orElseThrow(() ->
                    new UsernameNotFoundException("Username not found" + username)
            );
    }
}
