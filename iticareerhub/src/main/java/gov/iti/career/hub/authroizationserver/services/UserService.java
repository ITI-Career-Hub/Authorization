package gov.iti.career.hub.authroizationserver.services;

import gov.iti.career.hub.authroizationserver.entities.Role;
import gov.iti.career.hub.authroizationserver.entities.enums.RoleName;
import gov.iti.career.hub.authroizationserver.repositories.RoleRepository;
import gov.iti.career.hub.authroizationserver.repositories.UserRepository;
import gov.iti.career.hub.authroizationserver.entities.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> findAllUsers(){
        return userRepository
                .findAll();
    }
}
