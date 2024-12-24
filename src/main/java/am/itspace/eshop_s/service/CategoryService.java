package am.itspace.eshop_s.service;

import am.itspace.eshop_s.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Category save(Category category);

    public List<Category> findAll();

    public Optional<Category> findById(int id);

    public void deleteById(int id);

}
