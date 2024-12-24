package am.itspace.eshop_s.service.Impl;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.entity.Product;
import am.itspace.eshop_s.repository.ProductRepository;
import am.itspace.eshop_s.service.ProductService;
import am.itspace.eshop_s.service.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${eshop_s.upload.path}")
    private String uploadPath;

    private final ProductRepository productRepository;

    @Override
    public Product save(CurrentUser currentUser, Product product, MultipartFile multipartFile) {
        String filename;
        if (!multipartFile.isEmpty()) {
            filename = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, filename);
            try {
                multipartFile.transferTo(file);

            }catch (Exception e){
                e.printStackTrace();
            }
            product.setPicName(filename);
        }
        product.setCreatedAt(new Date());
        product.setUser(currentUser.getUser());
        return productRepository.save(product);
    }

    @Override
    public Product save(Product product) {
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

}
