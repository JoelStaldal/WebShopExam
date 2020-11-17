package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.service.OrderService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/admin")
    public String showAdminPage(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "admin";
    }

    @RequestMapping("/orders/completed")
    public String showCompletedOrders(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "admin";
    }

    @RequestMapping("/orders/ongoing")
    public String showOngoingOrders() {
        return "";
    }

    @RequestMapping("/order/update")
    public String markCompleted() {
        return "";
    }

    @RequestMapping("/product/create")
    public String createProduct() {
        return "";
    }
}
