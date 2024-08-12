package com.example.capstone1jpa.Service;

import com.example.capstone1jpa.Model.Category;
import com.example.capstone1jpa.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
       categoryRepository.save(category);
    }
     public boolean updateCategory(Integer id,Category category){
        Category category1=categoryRepository.getById(id);
         if(category1==null){
             return false;
         }
         category1.setName(category.getName());
             categoryRepository.save(category1);
         return true;
    }

    public boolean deleteCategory(Integer id){
        Category category1=categoryRepository.getById(id);
        if(category1==null){
            return false;
        }
        categoryRepository.delete(category1);
        return true;
    }


}
