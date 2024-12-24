package am.itspace.eshop_s.controller;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.entity.Product;
import am.itspace.eshop_s.service.CategoryService;
import am.itspace.eshop_s.service.ProductService;
import am.itspace.eshop_s.service.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final CategoryService categoryService;

    private final ProductService productService;

    @GetMapping
    public String productsPage(ModelMap model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/product/product";
    }


    @GetMapping(value = "/add")
    public String addProductPage(ModelMap modelMap) {
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories", categories);
        return "product/add";
    }

    @PostMapping(value = "/add")
    public String addProduct(@AuthenticationPrincipal CurrentUser currentUser, @ModelAttribute Product product, @RequestParam("img") MultipartFile img) {
        productService.save(currentUser, product, img);
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/delete")
    public String deleteProduct(@RequestParam("id") int id) {
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping(value = "/edit")
    public String editProductPage(@RequestParam("id") int id, ModelMap modelMap) {
        Product product = productService.findById(id);
        modelMap.addAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories", categories);
        return "/product/edit";
    }

    @PostMapping(value = "/edit")
    public String editProduct(@ModelAttribute Product product,
                              @RequestParam("img") MultipartFile img,
                              @AuthenticationPrincipal CurrentUser currentUser,
                              @RequestParam("i_name") String iName) {
        if (img == null || img.isEmpty()) {
            product.setPicName(iName);
            product.setUser(currentUser.getUser());
            productService.save(product);
        } else {
            productService.save(currentUser, product, img);
        }
        return "redirect:/product";
    }


}
