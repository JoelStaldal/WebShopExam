package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.service.OrderService;

import java.util.Optional;

@RestController
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @GetMapping("/rest/order/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Long id) {
        return orderService.getOrder(id);
    }
}
