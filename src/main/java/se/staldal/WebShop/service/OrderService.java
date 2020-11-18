package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.Repository.OrderRepository;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Status;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void create(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOngoing() {
        return orderRepository.findAllByStatus(Status.ONGOING);
    }

    public List<Order> getAllCompleted() {
        return orderRepository.findAllByStatus(Status.COMPLETED);
    }
}
