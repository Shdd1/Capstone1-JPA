package com.example.capstone1jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null unique")
    @NotNull(message = "product id must be not null")
    private Integer product_id ;

    @Column(columnDefinition = "int not null unique")
    @NotNull(message = "merchant id must be not null")
    private Integer merchant_id;

    @Column(columnDefinition = " int not null default 11")
    @NotNull(message = "stock must be not null")
    @Min(value = 11,message = "stock  have to be more than 10 at start) ")
    private int stock;
}
