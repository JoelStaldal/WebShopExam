package se.staldal.WebShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.staldal.WebShop.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
