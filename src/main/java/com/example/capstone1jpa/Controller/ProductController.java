package com.example.capstone1jpa.Controller;

import com.example.capstone1jpa.Model.Product;
import com.example.capstone1jpa.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/get")
    public ResponseEntity getProduct(){
        return ResponseEntity.status(200).body(productService.getProduct());

    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("is added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid@RequestBody Product product,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=productService.updateProduct(id,product);
        if(isUpdated){
            return ResponseEntity.status(200).body("coffee updated");

        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteProduct(@PathVariable Integer id){
        boolean isDeleted=productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(400).body("IS deleted");
        }
        return ResponseEntity.status(200).body("not found");
    }

}
