package am.itspace.eshop_s.repository;

import am.itspace.eshop_s.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
