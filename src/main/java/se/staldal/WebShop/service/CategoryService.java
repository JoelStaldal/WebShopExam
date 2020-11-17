package se.staldal.WebShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.staldal.WebShop.Repository.CategoryRepository;
import se.staldal.WebShop.model.Category;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void create(Category category) {
        categoryRepository.save(category);
    }
}
