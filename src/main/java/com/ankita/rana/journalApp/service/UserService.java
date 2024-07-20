package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.entity.JournalEntry;
import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.repository.JournalEntryRepository;
import com.ankita.rana.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

 /*   public void saveEntry(User user){
        userRepository.save(user);
    }*/

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        }
        catch (Exception e) {
            logger.error("Error occured for {}", user.getUserName(), e);
            logger.info("Hi this is info method of slf4j");
            logger.warn("Hi this is warn method of slf4j");
            logger.debug("this is debug method of slf4j");
            logger.trace("this is trace method of slf4j");
            return false;
        }
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }


    public List<User> getAll(){
       return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(String.valueOf(id));
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(String.valueOf(id));
    }

    public User findByUserName(String userName){
       return userRepository.findByUserName(userName);
    }
}


//Controller ->service -> Repository
