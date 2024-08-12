package com.example.capstone1jpa.Controller;

import com.example.capstone1jpa.Model.User;
import com.example.capstone1jpa.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getUsers());

    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("is added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid@RequestBody User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=userService.updateUser(id,user);
        if(isUpdated){
            return ResponseEntity.status(200).body("coffee updated");

        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteUser(@PathVariable Integer id){
        boolean isDeleted=userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(400).body("IS deleted");
        }
        return ResponseEntity.status(200).body("not found");
    }



    @GetMapping("/prime/{userId}")
    public ResponseEntity primeUser(@PathVariable int userId){
        boolean isPrime=userService.registerUserToPrime(userId);
        if(isPrime){
            return ResponseEntity.status(200).body("User successfully registered to Prime.");
        }
        return ResponseEntity.status(400).body("user not found");
    }
}
