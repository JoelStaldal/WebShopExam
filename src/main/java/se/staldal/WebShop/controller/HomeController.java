package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.staldal.WebShop.model.Category;
import se.staldal.WebShop.model.Order;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.model.User;
import se.staldal.WebShop.service.CategoryService;
import se.staldal.WebShop.service.OrderService;
import se.staldal.WebShop.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String showHomePage(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().stream().findFirst().get().toString();
        if(role.equals("ROLE_ADMIN")) {
            model.addAttribute("isAdmin", true);
        }
        return "home";
    }

    @RequestMapping("/products")
    public String showProductPage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "home.products";
    }

    @RequestMapping("/search")
    public String searchByName(@RequestParam("name") String name, Model model) {
        List<Product> results = productService.getProductsContains(name);
        model.addAttribute("resultsFromSearch", results);
        return "home.search";
    }

    @RequestMapping("/category")
    public String showProductsByCategory(@RequestParam("id") Category category, Model model) {
        List<Product> products = category.getProducts();
        model.addAttribute("products", products);
        return "home.category";
    }

    @RequestMapping("/account")
    public String showAccountPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("sessionUser");
        if(user != null) {
            List<Order> orders = orderService.getAllByUserId(user.getId());
            model.addAttribute("orders", orders);
        }
        return "account";
    }

    @PostMapping("/account/order/details")
    public String showOrderDetails(@RequestParam("id") Order order, Model model) {
        model.addAttribute("order", order);
        return "account.order.details";
    }
}
