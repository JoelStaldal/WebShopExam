package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.service.CategoryService;
import se.staldal.WebShop.service.ProductService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @RequestMapping("/home/products")
    public String showHomePage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "home.products";
    }

    @RequestMapping("/home/search")
    public String searchByName(@RequestParam("name") String name, Model model) {
        List<Product> results = productService.getProductsStartsWith(name);
        model.addAttribute("resultsFromSearch", results);
        return "home.search";
    }

}
