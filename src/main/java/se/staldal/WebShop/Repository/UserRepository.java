package se.staldal.WebShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.staldal.WebShop.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);
}
