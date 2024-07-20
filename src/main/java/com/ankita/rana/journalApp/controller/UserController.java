package com.ankita.rana.journalApp.controller;

import com.ankita.rana.journalApp.api.response.WeatherResponse;
import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.repository.UserRepository;
import com.ankita.rana.journalApp.service.UserService;
import com.ankita.rana.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
   UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeatherService weatherService;

    /*@GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }*/

   /* @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
       User userInDB = userService.findByUserName(userName);
       if(userInDB!=null){
           userInDB.setUserName(user.getUserName());
           userInDB.setPassword(user.getPassword());
           userService.saveEntry(userInDB);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
  @PutMapping
  public ResponseEntity<?> updateUser(@RequestBody User user){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();
       User userInDB = userService.findByUserName(userName);
      if(userInDB!=null){
           userInDB.setUserName(user.getUserName());
           userInDB.setPassword(user.getPassword());
           userService.saveNewUser(userInDB);
      }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      userRepository.deleteByUserName(authentication.getName());
      return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

   }

   //call external API
   @GetMapping
   public ResponseEntity<?> greeting(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       WeatherResponse weatherResponse = weatherService.getWeather();
       String greeting = "Today's wind speed feels like  :" +weatherResponse.getWind().getSpeed();
       return  new ResponseEntity<>("Hi" +authentication.getName() +greeting,HttpStatus.OK);

   }


}
