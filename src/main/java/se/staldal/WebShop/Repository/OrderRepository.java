package se.staldal.WebShop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Status;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByStatus(Status status);
    List<Order> findAllByUserId(Long id);
}
