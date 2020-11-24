package se.staldal.WebShop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.staldal.WebShop.controller.dto.UserRegistrationDto;
import se.staldal.WebShop.model.User;

public interface UserSecurityService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
