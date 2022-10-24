package agh.project.scs_forum.service;

import agh.project.scs_forum.model.Category;
import agh.project.scs_forum.repository.AdminRepository;
import agh.project.scs_forum.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    AdminRepository adminRepository;

    public CategoryService(CategoryRepository categoryRepository, AdminRepository adminRepository) {
        this.categoryRepository = categoryRepository;
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getCategoryById(long id) {
        return new ResponseEntity<>(categoryRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> getCategoryByName(String name) {
        return new ResponseEntity<>(categoryRepository.findByName(name), HttpStatus.OK);
    }

    public ResponseEntity<?> getCategoriesByModerators(String name) {
        return new ResponseEntity<>(categoryRepository.findByModeratedBy(name), HttpStatus.OK);
    }

    public ResponseEntity<?> createCategory(Category newCategory) {
        if (categoryRepository.existsByName(newCategory.getName())) {
            return new ResponseEntity<>("Category with that name already exists.", HttpStatus.FORBIDDEN);
        }
        if (!adminRepository.existsByUsername(newCategory.getModeratedBy())) {
            return new ResponseEntity<>("Given user is not admin or it does not exist.", HttpStatus.NOT_FOUND);
        }
        categoryRepository.save(newCategory);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    public ResponseEntity<?> editCategory(Category editedCategory, String categoryName) {
        Category category = categoryRepository.findByName(categoryName);

        if (category == null) {
            return new ResponseEntity<>("Category with that name does not exist.", HttpStatus.NOT_FOUND);
        }

        category.setName(editedCategory.getName());
        category.setModeratedBy(editedCategory.getModeratedBy());
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);

        if (category == null) {
            return new ResponseEntity<>("Category with that name already exists.", HttpStatus.FORBIDDEN);
        }

        categoryRepository.delete(category);
        return new ResponseEntity<>("Category " + name + " deleted successfully", HttpStatus.OK);
    }
}
