package com.example.springshopbe.controller;

import com.example.springshopbe.domain.Category;
import com.example.springshopbe.dto.CategoryDto;
import com.example.springshopbe.service.CategoryService;
import com.example.springshopbe.service.MapValidationErrorService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    MapValidationErrorService mapValidationErrorService;
    @GetMapping
    public ResponseEntity<?> getCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getCategory(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC)
            Pageable pageable){
        return  new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategories(@Valid @RequestBody CategoryDto dto,
                                            BindingResult result){
       ResponseEntity<?> responseEntity = mapValidationErrorService.MapValidationFields(result);
       if(responseEntity != null){
           return responseEntity;
       }
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);

        entity = categoryService.save(entity);
        dto.setId(entity.getId());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategories(@PathVariable("id") Long id,
                                            @RequestBody CategoryDto dto){
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);

        entity = categoryService.update(id,entity);
        dto.setId(entity.getId());


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<?> getCategores(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>("Category with id " +id+" was deleted", HttpStatus.OK);
    }
}
