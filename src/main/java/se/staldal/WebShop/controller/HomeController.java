package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.service.ProductService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @RequestMapping("/home")
    public String showHomePage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "home";
    }


}
