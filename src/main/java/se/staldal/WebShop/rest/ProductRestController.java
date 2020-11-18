package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.staldal.WebShop.model.Product;
import se.staldal.WebShop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("/all")
    public List<Product> allProducts() {
        return productService.getAll();
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        productService.create(product);
        return product;
    }

}
