package com.example.capstone1jpa.Service;

import com.example.capstone1jpa.Model.Product;
import com.example.capstone1jpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getProduct(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        product.setProductStatus("New");
        productRepository.save(product);
    }
    public boolean updateProduct(Integer id,Product product){
        Product p=productRepository.getById(id);
        if(p==null){
            return false;
        }
       p.setSales(product.getSales());
        p.setProductStatus(product.getProductStatus());
        p.setPrice(product.getPrice());
        p.setName(product.getName());
        p.setCategoryID(product.getCategoryID());
        productRepository.save(p);
        return true;
    }
    public boolean deleteProduct(Integer id){
        Product p=productRepository.getById(id);
        if(p==null){
            return false;
        }
        productRepository.delete(p);
        return true;
    }



}
