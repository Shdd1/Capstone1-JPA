package com.example.capstone1jpa.Repository;

import com.example.capstone1jpa.Model.User;
import com.example.capstone1jpa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
