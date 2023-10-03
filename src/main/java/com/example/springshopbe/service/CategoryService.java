package com.example.springshopbe.service;

import com.example.springshopbe.domain.Category;
import com.example.springshopbe.exception.CatrgoryException;
import com.example.springshopbe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;



    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    public Category update(Long id, Category entity) {
        Optional<Category> existed = categoryRepository.findById(id);

        if (existed.isEmpty()){
           throw new CatrgoryException("Catrgory is " +id+ " does not exist");
        }
        try {
                Category existecCategory = existed.get();
                existecCategory.setName(entity.getName());
                existecCategory.setStatus(entity.getStatus());

            return categoryRepository.save(existecCategory);

        }catch (Exception e){
            throw new CatrgoryException("catrgory is updated fail");
        }
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category findById(Long id) {
        Optional<Category> found = categoryRepository.findById(id);
        if (found.isEmpty()){
            throw new CatrgoryException("Category with id " +id+ " does not exist");
        }
        return found.get();
    }

    public void deleteById(Long id ) {
        Category existed = findById(id);
        categoryRepository.delete(existed);
    }
}
