package com.example.capstone1jpa.Controller;

import com.example.capstone1jpa.Model.Merchant;
import com.example.capstone1jpa.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(200).body(merchantService.getMerchant());

    }
    @PostMapping("/add")
        public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("is added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id,@Valid@RequestBody Merchant merchant,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=merchantService.updateMerchant(id,merchant);
        if(isUpdated){
            return ResponseEntity.status(200).body("coffee updated");

        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteMerchant(@PathVariable Integer id){
        boolean isDeleted=merchantService.deleteMerchant(id);
        if(isDeleted){
            return ResponseEntity.status(400).body("IS deleted");
        }
        return ResponseEntity.status(200).body("not found");
    }

}
