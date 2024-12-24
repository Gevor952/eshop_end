package am.itspace.eshop_s.controller;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String categoryPage(ModelMap modelMap) {
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories", categories);
        return "category/category";
    }

    @GetMapping(value = "/add")
    public String addCategoryPage(){
        return "/category/add";
    }

    @PostMapping(value = "/add")
    public String addCategory(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/delete")
    public String deleteCategory(@RequestParam("id") int id){
        categoryService.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping(value = "/edit")
    public String editCategoryPage(@RequestParam("id") int id, ModelMap modelMap){
        Category category = categoryService.findById(id).get();
        modelMap.addAttribute("category", category);
        return "/category/edit";
    }

    @PostMapping(value = "/edit")
    public String editCategory(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/category";
    }
}
