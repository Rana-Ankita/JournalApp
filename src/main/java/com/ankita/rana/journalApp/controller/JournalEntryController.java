package com.ankita.rana.journalApp.controller;

import com.ankita.rana.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/journal")
public class JournalEntryController {

   // private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();
   /* private Map<String, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping()
    public boolean createEntry(@RequestBody JournalEntry journalEntry){
     journalEntryMap.put(journalEntry.getId(), journalEntry);
     return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntryMap.get(myId);
    }

    @DeleteMapping("id/{id}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long id){
        return journalEntryMap.remove(id);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry journalEntry){
        return journalEntryMap.put(id, journalEntry);
    }*/
}
