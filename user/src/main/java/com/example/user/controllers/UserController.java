
package com.example.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.user.models.User;
import com.example.user.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200") 
public class UserController {

    @Autowired
    private UserService userRegisterService;

    @PostMapping("/registeruser")
    @CrossOrigin(origins = "http://localhost:4200")
    public User registerUser(@RequestBody User user) throws Exception {
        String currEmail = user.getEmail();
        System.out.println(currEmail);
        if (currEmail != null || !"".equals(currEmail)) {
            User userObj = userRegisterService.fetchUserByEmail(currEmail);
            if (userObj != null) {
                throw new Exception("User with " + currEmail + " already exists !!!");
            }
        }
        System.out.println("here");
        User userObj = null;
        userObj = userRegisterService.saveUser(user);

        return userObj;
    }

    @PostMapping("/loginuser")
    @CrossOrigin(origins = "http://localhost:4200")
    public User loginUser(@RequestBody User user) throws Exception {
        String currEmail = user.getEmail();
        String currPassword = user.getPassword();

        User userObj = null;
        if (currEmail != null && currPassword != null) {
            userObj = userRegisterService.fetchUserByEmailAndPassword(currEmail, currPassword);
        }
        if (userObj == null) {
            throw new Exception("User does not exists!!! Please enter valid credentials...");
        }
        return userObj;
    }

    @GetMapping("/gettotalusers")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Integer>> getTotalSlots() throws Exception {
        List<User> users = userRegisterService.getAllUsers();
        List<Integer> al = new ArrayList<>();
        al.add(users.size());
        return new ResponseEntity<List<Integer>>(al, HttpStatus.OK);
    }

    @GetMapping("/passagerlist")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRegisterService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/userProfileDetails/{email}")
    public ResponseEntity<List<User>> getProfileDetails(@PathVariable String email) {
        List<User> users = userRegisterService.fetchProfileByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<User> updateUserProfile(@RequestBody User user) {
        User updatedUser = userRegisterService.updateUserProfile(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/addNote")
    public ResponseEntity<String> addRating(@RequestParam String driverId, @RequestParam int rating,
            @RequestParam(required = false) String feedback) {
        // Add logic for adding notes/rating
        return new ResponseEntity<>("Rating added", HttpStatus.OK);
    }


    
}
