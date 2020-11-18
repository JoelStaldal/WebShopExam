package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Status;
import se.staldal.WebShop.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/all")
    public List<Order> allOrders() {
        return orderService.getAll();
    }

    @GetMapping("/ongoing")
    public List<Order> ongoingOrders() {
        return orderService.getAllOngoing();
    }

    @GetMapping("/completed")
    public List<Order> completedOrders() {
        return orderService.getAllCompleted();
    }

    @PatchMapping("/{id}/completed")
    public String markCompleted(@PathVariable("id") Long id) {
        Optional<Order> optionalOrder = orderService.getById(id);

        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(Status.COMPLETED);
            orderService.update(order);
            return "updated";
        } else {
            return "order not found";
        }
    }
}
