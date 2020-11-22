package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.model.Category;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.service.CategoryService;
import se.staldal.WebShop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("/all")
    public List<Product> allProducts() {
        return productService.getAll();
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product) {
        if(product.getName().trim().length() < 3 || product.getPrice() < 1) {
            return "unable to create product";
        }
        if(product.getCategory().getName().trim().length() < 3) {
            return "unable to create product";
        }
        Category category = product.getCategory();
        Optional<Category> optionalCategory = categoryService.getByName(category.getName());
        if(optionalCategory.isPresent()) {
            product.setCategory(optionalCategory.get());
        }
        productService.create(product);
        return "product created";
    }

}
