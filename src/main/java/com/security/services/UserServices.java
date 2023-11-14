package com.security.services;

import com.security.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    ArrayList<User> lists=new ArrayList<>();



    public UserServices() {
        lists.add(new User("abc","abc","abc","abc","abc@gmail.com","ADMIN"));
        lists.add(new User("xyz","xyz","XYZ","XYZ","xyz@gmail.com","NORMAL"));
    }

    public List<User> getAllUsers(){
        return this.lists;
    }

    public User getUserByUsername(String username){
        return this.lists.stream().filter((user) -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    public User addUser(User user){
        this.lists.add(user);
        return user;
    }

}
