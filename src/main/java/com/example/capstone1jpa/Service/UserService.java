package com.example.capstone1jpa.Service;

import com.example.capstone1jpa.Model.User;
import com.example.capstone1jpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void addUser(User user){
      userRepository.save(user);
    }

    public boolean updateUser(Integer id,User user){
        User u=userRepository.getById(id);
        if(u==null){
            return false;
        }
       u.setUsername(user.getUsername());
        u.setRole(user.getRole());
        u.setPassword(user.getPassword());
        u.setIsPrime(user.getIsPrime());
        u.setEmail(user.getEmail());
        u.setBalance(user.getBalance());
        userRepository.save(u);
        return true;
    }
    public boolean deleteUser(Integer id){
        User u=userRepository.getById(id);
        if(u==null){
            return false;
        }
        userRepository.delete(u);
        return true;
    }

    //***************** register User To Prime ***************************************
    public boolean registerUserToPrime(Integer id) {
        User u=userRepository.getById(id);
            if (u.getIsPrime().equalsIgnoreCase("false")){
               u.setIsPrime("true");
               userRepository.save(u);
                return true;
            }

        return false;
    }

}
