package com.example.capstone1jpa.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class OrderTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     @Column(columnDefinition = "int not null")
    @NotNull(message = "id can not be null")
    private Integer userId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "id can not be null")
    private Integer product_id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "merchant id must be not null")
    private Integer merchant_id;

    @Column(columnDefinition = "varchar(10) not null")
    @NotNull(message = "Order Status can not be null")
    @Pattern(regexp = "^(pending|processing|shipped)$", message = "Order Status must be either 'pending' or 'shipped'")
    private String OrderStatus;

     @Column(columnDefinition = "datetime ")
    @FutureOrPresent(message = "should be a date in the Future Or Present ")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date delivery_date;
}
