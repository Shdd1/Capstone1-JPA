package com.example.capstone1jpa.Controller;

import com.example.capstone1jpa.Model.MerchantStock;
import com.example.capstone1jpa.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());

    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("is added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id,@Valid@RequestBody MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=merchantStockService.updateMerchantStock(id,merchantStock);
        if(isUpdated){
            return ResponseEntity.status(200).body("coffee updated");

        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        boolean isDeleted=merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(400).body("IS deleted");
        }
        return ResponseEntity.status(200).body("not found");
    }
    //  Q11
    @PutMapping("/update/{id}/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable Integer id,@PathVariable int productId,@PathVariable int merchantId,@PathVariable int additionalStock){
        boolean isUpdated=merchantStockService.merchantAddStocks(id,productId,merchantId,additionalStock);
        if(isUpdated){
            return ResponseEntity.status(200).body("IS Updated");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    //Q12
    @PutMapping("/update/{userid}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userid,@PathVariable int productId,@PathVariable int merchantId) {
        int buy = merchantStockService.buyProduct(userid, productId, merchantId);
        return switch (buy) {
            case 1 -> ResponseEntity.status(400).body("Bad request: the balance less than price");
            case 2 -> ResponseEntity.status(400).body("Bad request: dont have stock ");
            case 3 -> ResponseEntity.status(400).body("Bad request: Must be customer to buy");
            default -> ResponseEntity.status(200).body("is updated");
        };

    }
    //***************Discount*****************************************8
    @PutMapping("/discount/{productId}/{merchantId}/{userId}")
    public ResponseEntity discount(@PathVariable int productId,@PathVariable int merchantId,@PathVariable int userId){
        boolean discount=merchantStockService.discount(productId,merchantId,userId);
        if(discount){
            return ResponseEntity.status(200).body("Successful discount");
        }
        return ResponseEntity.status(400).body("Not found");
    }
    @PutMapping("/status/{productId}/{merchantId}")
    public ResponseEntity productStatus(@PathVariable int productId,@PathVariable int merchantId ){
        boolean status= merchantStockService.productStatus(productId,merchantId);
        if(status){
            return ResponseEntity.status(200).body("change status of product");

        }
        return ResponseEntity.status(400).body("Order Not Found");

    }


}
