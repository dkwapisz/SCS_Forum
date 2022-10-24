package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.Category;
import agh.project.scs_forum.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/id/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }

    @GetMapping("/moderator/{username}")
    public ResponseEntity<?> getCategoriesByModerators(@PathVariable String username) {
        return categoryService.getCategoriesByModerators(username);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{categoryName}")
    public ResponseEntity<?> editCategoryName(@RequestBody Category editedCategory, @PathVariable String categoryName) {
        return categoryService.editCategory(editedCategory, categoryName);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteCategoryByName(@PathVariable String name) {
        return categoryService.deleteCategoryByName(name);
    }
}
