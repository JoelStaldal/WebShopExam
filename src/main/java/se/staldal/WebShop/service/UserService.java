package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.Repository.UserRepository;
import se.staldal.WebShop.model.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public boolean userExists(String name) {
        return userRepository.existsByName(name);
    }
}
