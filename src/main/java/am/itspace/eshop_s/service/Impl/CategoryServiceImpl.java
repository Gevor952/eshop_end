package am.itspace.eshop_s.service.Impl;

import am.itspace.eshop_s.entity.Category;
import am.itspace.eshop_s.repository.CategoryRepository;
import am.itspace.eshop_s.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
