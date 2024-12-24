package am.itspace.eshop_s.repository;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

}
