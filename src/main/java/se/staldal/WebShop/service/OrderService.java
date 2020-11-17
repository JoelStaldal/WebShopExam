package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.Repository.OrderRepository;
import se.staldal.WebShop.model.Order;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void create(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> get(Long id) {
        return orderRepository.findById(id);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
