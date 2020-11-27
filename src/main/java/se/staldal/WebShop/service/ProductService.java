package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.Repository.ProductRepository;
import se.staldal.WebShop.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void create(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProductsContains(String name) {
        return productRepository.findByNameContains(name);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public boolean productExists(String name) {
        return productRepository.existsByName(name);
    }
}
