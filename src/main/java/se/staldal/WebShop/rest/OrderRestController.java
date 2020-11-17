package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Status;
import se.staldal.WebShop.service.OrderService;

import java.util.Optional;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @PatchMapping("/{id}/completed")
    public String markCompleted(@PathVariable("id") Long id) {
        Optional<Order> optionalOrder = orderService.get(id);

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
