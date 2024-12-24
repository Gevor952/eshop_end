package am.itspace.eshop_s.service;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.entity.Product;
import am.itspace.eshop_s.service.security.CurrentUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    public Product save(CurrentUser currentUser, Product product, MultipartFile multipartFile);

    public Product save(Product product);


    public List<Product> findAll();

    public List<Product> findByCategory(Category category);

    public Product findById(int id);

    public void delete(int id);

}
