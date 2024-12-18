package com.example.user.services;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.user.models.User;
import com.example.user.repository.UserRepository;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRegistrationRepo;

    public User saveUser(User user) {
        return userRegistrationRepo.save(user);
    }

    public User updateUserProfile(User user) {
        return userRegistrationRepo.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRegistrationRepo.findAll();
    }

    public User fetchUserByEmail(String email) {
        return userRegistrationRepo.findByEmail(email);
    }

    public User fetchUserByUsername(String username) {
        return userRegistrationRepo.findByUsername(username);
    }

    public User fetchUserByEmailAndPassword(String email, String password) {
        return userRegistrationRepo.findByEmailAndPassword(email, password);
    }

    public List<User> fetchProfileByEmail(String email) {
        return (List<User>) userRegistrationRepo.findProfileByEmail(email);
    }



}