package com.example.capstone1jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "varchar(20) not null unique")
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 4,message = "Name have to be more than 3 length long")
    private String name;

    @Column(columnDefinition = "float not null default 0")
    @NotNull(message = "price can not be null")
    @Positive(message = "price must be positive number")
    private double price;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "category ID can not be null")
    private Integer categoryID;

     @Column(columnDefinition = "int not null default 0")
    @NotNull(message = "Product sales must not be null")
    @Min(value = 0, message = "Product sales must be a positive number or zero")
    private Integer sales= 0;

    @Column(columnDefinition = "varchar(12) not null")
   // @Column(columnDefinition = "varchar(12) not null check(productStatus='New' or productStatus='Available' or productStatus='Unavailable' )")
    @NotEmpty(message = "Product Status can not be empty")
    @Pattern(regexp = "^(New|Available|Unavailable)$")
    private String productStatus;
}
