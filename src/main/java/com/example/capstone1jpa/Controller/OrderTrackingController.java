package com.example.capstone1jpa.Controller;

import com.example.capstone1jpa.Model.OrderTracking;
import com.example.capstone1jpa.Service.OrderTrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/track")
@RequiredArgsConstructor
public class OrderTrackingController {
    private final OrderTrackingService orderTrackingService;
    @GetMapping("/get")
    public ResponseEntity getorderTracking(){
        return ResponseEntity.status(200).body(orderTrackingService.getOrderTrackingService());

    }
    @PostMapping("/add")
        public ResponseEntity addorderTracking(@Valid @RequestBody OrderTracking orderTracking, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        orderTrackingService.addOrderTrackingService(orderTracking);
        return ResponseEntity.status(200).body("is added");


    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateorderTracking(@PathVariable Integer id,@Valid@RequestBody OrderTracking orderTracking,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=orderTrackingService.updateOrderTrackingService(id,orderTracking);
        if(isUpdated){
            return ResponseEntity.status(200).body("coffee updated");

        }
        return ResponseEntity.status(400).body("Not found");
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteorderTracking(@PathVariable Integer id){
        boolean isDeleted=orderTrackingService.deleteOrderTrackingService(id);
        if(isDeleted){
            return ResponseEntity.status(400).body("IS deleted");
        }
        return ResponseEntity.status(200).body("not found");
    }
//
    @GetMapping("/search/{id}")
    public ResponseEntity SearchTrackShipment(@PathVariable Integer id){
       return ResponseEntity.status(200).body(orderTrackingService.searchOrderTracking(id));
    }
    //
    @PutMapping("/updatestat/{adminId}/{orderId}")
    public ResponseEntity updateOrderStatus(@PathVariable Integer adminId,@PathVariable Integer orderId){
        boolean IsUpdate=orderTrackingService.updateOrderStatus(adminId,orderId);
        if(IsUpdate){
            return ResponseEntity.status(200).body("is updated");
        }
        return ResponseEntity.status(400).body("not found");
    }
//
    @GetMapping("/get/{trackID}/{userId}")
    public ResponseEntity chaeckStatus(@PathVariable int trackID,@PathVariable int userId){
        boolean status=orderTrackingService.checkStatus(trackID,userId);
        if(status){
            return ResponseEntity.status(200).body(orderTrackingService.checkStatus(trackID,userId));
        }
      return ResponseEntity.status(400).body("Not found");
    }

}
