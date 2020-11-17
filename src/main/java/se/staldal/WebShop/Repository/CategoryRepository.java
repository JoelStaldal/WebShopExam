package se.staldal.WebShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.staldal.WebShop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
