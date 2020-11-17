package se.staldal.WebShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.staldal.WebShop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
