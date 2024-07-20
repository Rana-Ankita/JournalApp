package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.entity.JournalEntry;
import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;




@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
       try {
           User user = userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved =  journalEntryRepository.save(journalEntry);
           user.getJournalEntryList().add(saved);
           userService.saveUser(user);
       } catch (Exception e) {
           log.error("Error is : ", e);
           throw new RuntimeException("An error occured while saving the entry" ,e);
       }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(String.valueOf(id));
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean remove = false;
        try {
            User user = userService.findByUserName(userName);
            remove = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
            if (remove) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(String.valueOf(id));
            }
        } catch(Exception e) {
            throw new RuntimeException("An error occured while deleting the entry.", e);
        }
        return remove;
    }

}


//Controller ->service -> Repository
