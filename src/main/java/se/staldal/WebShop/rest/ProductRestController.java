package se.staldal.WebShop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getById(id);
        if(product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> allProducts() {
        List<Product> list = productService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        Category category = product.getCategory();
        Optional<Category> optionalCategory = categoryService.getByName(category.getName());
        if(optionalCategory.isPresent()) {
            product.setCategory(optionalCategory.get());
        }
        if(validProduct(product)) {
            productService.create(product);
            return new ResponseEntity<>("Product created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Unable to create product", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public boolean validProduct(Product product) {
        if(!productService.productExists(product.getName())
                && (product.getName().trim().length() >= 2
                && product.getPrice() >= 1
                && product.getCategory().getName().trim().length() >= 2)) {
            return true;
        } else {
            return false;
        }
    }
}
