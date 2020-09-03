package pl.akademiakodu.ksiazki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.ksiazki.model.Book;
import pl.akademiakodu.ksiazki.model.Category;
import pl.akademiakodu.ksiazki.repository.CategoryRepository;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories")
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }


    @GetMapping("categories/{id}")
    public ResponseEntity<Category> showOne(@PathVariable Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
//            category.setId(id);
//            category.setCategoryName(categoryName);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("categories/{id}/books")
    public ResponseEntity<List<Book>> showBooksByCategory (@PathVariable Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            return new ResponseEntity<>(category.getBooks(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("categories")
    public Category createCategory(@RequestParam String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        return categoryRepository.save(category);
    }
    @PutMapping("categories/{id}")
    public ResponseEntity<Category> showOne(@PathVariable Integer id,
                                            @RequestParam String categoryName){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setCategoryName(categoryName);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}