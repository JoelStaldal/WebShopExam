package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.staldal.WebShop.model.Category;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.model.Status;
import se.staldal.WebShop.service.CategoryService;
import se.staldal.WebShop.service.OrderService;
import se.staldal.WebShop.service.ProductService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @RequestMapping("/orders/completed")
    public String showCompletedOrders(Model model) {
        List<Order> orders = orderService.getAllCompleted();
        model.addAttribute("orders", orders);
        return "order.completed";
    }

    @RequestMapping("/orders/ongoing")
    public String showOngoingOrders(Model model) {
        List<Order> orders = orderService.getAllOngoing();
        model.addAttribute("orders", orders);
        return "order.ongoing";
    }

    @RequestMapping("/order/update")
    public String markCompleted(@RequestParam("id") Order order) {
        order.setStatus(Status.COMPLETED);
        orderService.update(order);
        return "redirect:/admin";
    }

    @RequestMapping("/product/new")
    public String showProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "product.new";
    }

    @RequestMapping("/product/create")
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "product.new";
    }
}
