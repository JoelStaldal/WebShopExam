package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.model.Category;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.model.Status;
import se.staldal.WebShop.service.CategoryService;
import se.staldal.WebShop.service.OrderService;
import se.staldal.WebShop.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String showAdminPage(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        System.out.println("JSESSIONID=" + sessionId);
        return "admin";
    }

    @GetMapping("/orders/completed")
    public String showCompletedOrders(Model model) {
        List<Order> orders = orderService.getAllCompleted();
        model.addAttribute("orders", orders);
        return "order.completed";
    }

    @GetMapping("/orders/ongoing")
    public String showOngoingOrders(Model model) {
        List<Order> orders = orderService.getAllOngoing();
        model.addAttribute("orders", orders);
        return "order.ongoing";
    }

    @PostMapping("/order/update")
    public String markCompleted(@RequestParam("id") Order order) {
        order.setStatus(Status.COMPLETED);
        orderService.update(order);
        return "redirect:/admin";
    }

    @GetMapping("/product/new")
    public String showProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "product.new";
    }

    @PostMapping("/product/create")
    public String createProduct(@ModelAttribute("product") Product product) {
        if(product.getName().trim().length() >= 3 && !productService.productExists(product.getName())) {
            productService.create(product);
            return "redirect:/admin/product/new?successProduct";
        } else {
            return "redirect:/admin/product/new?errorProduct";
        }
    }

    @PostMapping("/category/create")
    public String createCategory(@RequestParam("name") String name) {
        if(name.trim().length() >= 3 && !categoryService.categoryExists(name)) {
            Category category = new Category(name);
            categoryService.create(category);
            return "redirect:/admin/product/new/?successCategory";
        } else {
            return "redirect:/admin/product/new/?errorCategory";
        }
    }

    @PostMapping("/order/details")
    public String showOrderDetails(@RequestParam("id") Order order, Model model) {
        model.addAttribute("order", order);
        return "order.details";
    }
}
