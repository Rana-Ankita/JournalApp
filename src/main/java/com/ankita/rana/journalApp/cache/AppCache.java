package com.ankita.rana.journalApp.cache;

import com.ankita.rana.journalApp.entity.ConfigJournalAppEntity;
import com.ankita.rana.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
       List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
       for(ConfigJournalAppEntity ce: all){
           appCache.put(ce.getKey(), ce.getValue());
       }
    }
}
