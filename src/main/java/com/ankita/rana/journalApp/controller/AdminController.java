package com.ankita.rana.journalApp.controller;

import com.ankita.rana.journalApp.cache.AppCache;
import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
       List<User> allUser = userService.getAll();
       if(allUser!=null && !allUser.isEmpty()){
           return new ResponseEntity<>(allUser, HttpStatus.OK);
       }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
