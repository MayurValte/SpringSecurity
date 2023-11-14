package com.security.controller;

import com.security.model.User;
import com.security.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        logger.info("In getAllUsers Controller");
        return this.userServices.getAllUsers();
    }
    @GetMapping("/getUserByUserName")
    public User getUserByUsername(@RequestParam String username){
        logger.info("In getUserByUserName Controller");
        return this.userServices.getUserByUsername(username);
    }
    @PostMapping("/addUsers")
    public User addUser(@RequestBody User user){
        logger.info("In addUsers Controller");
        return this.userServices.addUser(user);
    }

}
