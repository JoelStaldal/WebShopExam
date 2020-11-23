package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Optional<Order>> getOrder(@PathVariable("id") Long id) {
        Optional<Order> order = orderService.getById(id);
        if(order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(order, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>>  allOrders() {
        List<Order> list = orderService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<Order>> ongoingOrders() {
        List<Order> list = orderService.getAllOngoing();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Order>> completedOrders() {
        List<Order> list = orderService.getAllCompleted();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<String> markCompleted(@PathVariable("id") Long id) {
        Optional<Order> optionalOrder = orderService.getById(id);

        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(Status.COMPLETED);
            orderService.update(order);
            return new ResponseEntity<>("Order " + id + " marked completed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }
}
