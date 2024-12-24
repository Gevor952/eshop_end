package am.itspace.eshop_s.controller;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.entity.Product;
import am.itspace.eshop_s.entity.UserType;
import am.itspace.eshop_s.service.CategoryService;
import am.itspace.eshop_s.service.ProductService;
import am.itspace.eshop_s.service.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Value("${eshop_s.upload.path}")
    private String uploadPath;

    @GetMapping(value = "/")
    public String MainPage(ModelMap modelMap) {
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories", categories);
        List<Product> products =productService.findAll();
        modelMap.addAttribute("products", products);
        return "index";
    }

    @GetMapping(value = "/vewByType")
    public String vewByType(ModelMap modelMap, @RequestParam("id") Integer id) {
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories", categories);
        Category category = categoryService.findById(id).get();
        List<Product> products = productService.findByCategory(category);
        modelMap.addAttribute("products", products);
        return "index";
    }


    @GetMapping(value = "/loginPage")
    public String loginPage(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null && currentUser.getUser() != null) {
            if (currentUser.getUser().getUserType() == UserType.ADMIN) {
                return "redirect:/admin/home";
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/getImg", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImg(@RequestParam("imgName") String imgName) {
        File file = new File(uploadPath + imgName);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file)) {
                return IOUtils.toByteArray(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
