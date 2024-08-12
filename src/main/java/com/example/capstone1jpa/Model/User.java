package com.example.capstone1jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition =" varchar(20) not null unique")
    @NotEmpty(message = "Username must not be null")
    private String username;

    @Column(columnDefinition = "varchar(20) not null ")
    @NotEmpty(message = "Password must not be null")
    @Size(min = 7, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must have characters and digits")
    private String password;

    @Column(columnDefinition ="varchar(20) not null unique" )
    @NotEmpty(message = "Email must not null")
    @Email(message = "Email must be valid")
    private String email;

    @Column(columnDefinition = "varchar(20) not null ")
    //@Column(columnDefinition = "varchar(20) not null Check(role='Admin' or role='Customer')")
    @NotEmpty(message = "Role must not be null")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    private String role;

    @Column(columnDefinition = "float not null default 0")
    @NotNull(message = "Balance must not be null")
    @Positive(message = "Balance must be positive")
    private double balance;

    @Column(columnDefinition = "varchar(5) not null")
   // @Column(columnDefinition = "varchar(5) not null Check(role='true' or role='false')")
    @NotNull(message = "can not be null")
    private String isPrime;

}
