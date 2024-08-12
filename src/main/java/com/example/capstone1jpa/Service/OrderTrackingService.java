package com.example.capstone1jpa.Service;

import com.example.capstone1jpa.Model.MerchantStock;
import com.example.capstone1jpa.Model.OrderTracking;
import com.example.capstone1jpa.Model.User;
import com.example.capstone1jpa.Repository.OrderTrackingRepository;
import com.example.capstone1jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderTrackingService {
    private final OrderTrackingRepository orderTrackingRepository;
    private final UserRepository userRepository;

    public List<OrderTracking> getOrderTrackingService() {
        return orderTrackingRepository.findAll();
    }

    public void addOrderTrackingService(OrderTracking orderTracking) {

        orderTrackingRepository.save(orderTracking);
    }

    public boolean updateOrderTrackingService(Integer id, OrderTracking orderTracking) {
        OrderTracking o = orderTrackingRepository.getById(id);
        if (o == null) {
            return false;
        }
        o.setUserId(orderTracking.getUserId());
        o.setProduct_id(orderTracking.getProduct_id());
        o.setOrderStatus(orderTracking.getOrderStatus());
        o.setMerchant_id(orderTracking.getMerchant_id());
        o.setDelivery_date(orderTracking.getDelivery_date());
        orderTrackingRepository.save(o);
        return true;
    }

    public boolean deleteOrderTrackingService(Integer id) {
        OrderTracking m = orderTrackingRepository.getById(id);
        if (m == null) {
            return false;
        }
        orderTrackingRepository.delete(m);
        return true;
    }


    //************* Search to track shipment by id ***************
    public OrderTracking searchOrderTracking(Integer id) {
        OrderTracking o = orderTrackingRepository.getById(id);
        orderTrackingRepository.save(o);
        return o;
    }

    // *** Update order status by Admin ************
public boolean updateOrderStatus(Integer adminId,Integer orderTrackId) {
    User u = userRepository.getById(adminId);
    OrderTracking o = orderTrackingRepository.getById(orderTrackId);
    if (u.getRole().equalsIgnoreCase("Admin")) {
        if (o.getOrderStatus().equalsIgnoreCase("pending")) {
            o.setOrderStatus("processing");
            orderTrackingRepository.save(o);
        } else
            o.setOrderStatus("shipped");
        orderTrackingRepository.save(o);
        return true;
    }
    return false;
}

  //***** User checks the status of his shipment ********
    public boolean checkStatus(Integer trackId , Integer userId){
        OrderTracking o = orderTrackingRepository.getById(trackId);
        User u = userRepository.getById(userId);
         if(u==null||o==null){
                return false;
        }
         o.getOrderStatus();
         orderTrackingRepository.save(o);

        return true;

    }
}