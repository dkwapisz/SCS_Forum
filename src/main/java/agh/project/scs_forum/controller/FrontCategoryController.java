package agh.project.scs_forum.controller;

import java.util.List;
import java.util.Optional;

import agh.project.scs_forum.model.Category;
import agh.project.scs_forum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/front/category")
public class FrontCategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/all")
    public String viewHomePage(Model model) {
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("listCategories", listCategories);

        return "categories";
    }

    @RequestMapping("/new")
    public String showNewCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);

        return "new_category";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryRepository.save(category);

        return "redirect:/front/category/all";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCategoryForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_category");

        Optional<Category> category = categoryRepository.findById(id);
        mav.addObject("category", category.get());

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id) {
        categoryRepository.deleteById(id);

        return "redirect:/front/category/all";
    }
}