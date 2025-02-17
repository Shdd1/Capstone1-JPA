package com.example.capstone1jpa.Controller;

import com.example.capstone1jpa.Model.Category;
import com.example.capstone1jpa.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("it is added");
    }

    @PutMapping("/update/{id}")
        public ResponseEntity updateCategroy(@PathVariable Integer id,@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=categoryService.updateCategory(id,category);
        if(isUpdated){
            return ResponseEntity.status(200).body("Is Updated");
        }
        return ResponseEntity.status(400).body("Not found");

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategroy(@PathVariable Integer id){
        boolean isDeleted=categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("is deleted");
        }
        return ResponseEntity.status(400).body("Not found");
    }

}
