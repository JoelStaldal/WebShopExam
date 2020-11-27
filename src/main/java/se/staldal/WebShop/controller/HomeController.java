package se.staldal.WebShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.staldal.WebShop.model.Category;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.service.CategoryService;
import se.staldal.WebShop.service.ProductService;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping
    public String showHomePage(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
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

}
