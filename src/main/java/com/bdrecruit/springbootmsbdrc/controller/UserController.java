package com.bdrecruit.springbootmsbdrc.controller;

import com.bdrecruit.springbootmsbdrc.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author m000878 2022-03-15 21:10
 * Service API
 */

@RestController

public class UserController {

    public UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() throws InterruptedException, ExecutionException{
        return "I'm running mate!";
    }

    @GetMapping("/get")
    public User getUser(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return userService.getUser(documentId);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() throws InterruptedException, ExecutionException{
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.createUser(user);
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException{
        return userService.updateUser(user);
    }
}
