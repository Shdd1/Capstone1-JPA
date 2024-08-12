package com.example.capstone1jpa.Service;

import com.example.capstone1jpa.Model.MerchantStock;
import com.example.capstone1jpa.Model.Product;
import com.example.capstone1jpa.Model.User;
import com.example.capstone1jpa.Repository.MerchantRepository;
import com.example.capstone1jpa.Repository.MerchantStockRepository;
import com.example.capstone1jpa.Repository.ProductRepository;
import com.example.capstone1jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;



    public List<MerchantStock> getMerchantStock() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock) {

        merchantStockRepository.save(merchantStock);
    }

    public boolean updateMerchantStock(Integer id, MerchantStock merchantStock) {
        MerchantStock m = merchantStockRepository.getById(id);
        if (m == null) {
            return false;
        }
        m.setStock(merchantStock.getStock());
        merchantStockRepository.save(m);
        return true;
    }

    public boolean deleteMerchantStock(Integer id) {
        MerchantStock m = merchantStockRepository.getById(id);
        if (m == null) {
            return false;
        }
        merchantStockRepository.delete(m);
        return true;
    }

    //11- Create endpoint where merchant can add more stocks of product to a merchant Stock
    public boolean merchantAddStocks(Integer id, int productId, int merchantId, int additionalStock) {
        MerchantStock m = merchantStockRepository.getById(id);

        if (m.getMerchant_id() == merchantId && m.getProduct_id() == productId && additionalStock > 0) {
            m.setStock(additionalStock + m.getStock());
            merchantStockRepository.save(m);
            return true;

        }
        return false;
    }

    //12- Create endpoint where user can buy a product directly
    public int buyProduct(Integer userid, Integer productId, Integer merchantId) {
        MerchantStock m = merchantStockRepository.getById(merchantId);
        User u = userRepository.getById(userid);
        Product product=productRepository.getById(productId);
        if (u.getRole().equalsIgnoreCase("customer") ) {
            if (m.getStock() != 0) {
                if (u.getBalance() >= product.getPrice()) {
                    u.setBalance(u.getBalance() - product.getPrice());
                    m.setStock(m.getStock() - 1);
                    product.setSales(product.getSales() + 1);
                    merchantStockRepository.save(m);
                    userRepository.save(u);
                    productRepository.save(product);

                }else{return 1;}

            }else {return 2;}
        }else {return 3;}
        return 0;
    }

    //**************** Discount *************************************************
        public boolean discount(Integer productId, Integer merchantId, Integer userId) {
            MerchantStock m = merchantStockRepository.getById(merchantId);
            User u = userRepository.getById(userId);
            Product product=productRepository.getById(productId);
             if(u.getRole().equalsIgnoreCase("Customer")){
                u.setBalance(u.getBalance()-product.getPrice()*0.5);
                userRepository.save(u);
                return true;
             }
             return false;
         }

         //*****************************product Status******************************
         public boolean productStatus(int productId, int merchantId) {
             Product product = productRepository.getById(productId);
             MerchantStock m = merchantStockRepository.getById(merchantId);
             if (m.getStock() <= 0) {
                 product.setProductStatus("Unavailable");
                 productRepository.save(product);
                 return true;
             }
             if (m.getStock() > 0) {
                 product.setProductStatus("Available");
                 productRepository.save(product);
                 return true;
             }
             return false;
         }


}
