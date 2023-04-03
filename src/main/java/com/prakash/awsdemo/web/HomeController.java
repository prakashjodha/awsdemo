package com.prakash.awsdemo.web;

import com.prakash.awsdemo.dto.User;
import com.prakash.awsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    UserService service;
    @GetMapping("/user")
    public List<User> getUser(){
        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable long id){
        return service.getUserById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return service.getUserById(service.saveUser(user));
    }

}
