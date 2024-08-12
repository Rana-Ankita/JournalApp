package com.ankita.rana.journalApp.scheduler;


import com.ankita.rana.journalApp.cache.AppCache;
import com.ankita.rana.journalApp.entity.JournalEntry;
import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.enums.Sentiment;
import com.ankita.rana.journalApp.repository.UserRepositoryImpl;
import com.ankita.rana.journalApp.service.EmailService;
import com.ankita.rana.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    AppCache appCache;

    //we have six field in crone expression - sec--min--hour--dayOfTheMonth--Month--dayOfTheWeek
   // @Scheduled(cron = "0 0 9 * * SUN")
    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSaMail(){
       List<User> users = userRepository.getUserForSA();
       for(User user:users){
         List<JournalEntry> journalEntries =  user.getJournalEntryList();
         List<Sentiment> sentiments=journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now()
                 .minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
        // String entry =  String.join(" ", filteredList);
        //String sentiment = sentimentAnalysisService.getSentiment(entry);
      //  emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
           Map<Sentiment, Integer> sentimentCount = new HashMap<>();
           for(Sentiment sentiment : sentiments){
               if(sentiment != null){
                   sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) +1);
               }

               Sentiment mostFrequentSentiment = null;
               int maxCount = 0;
               for(Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()){
                   if(entry.getValue() > maxCount){
                       maxCount = entry.getValue();
                       mostFrequentSentiment = entry.getKey();
                   }
               }
               if(mostFrequentSentiment != null){
                   emailService.sendEmail(user.getEmail(), "Sentiment for the last 7 days", mostFrequentSentiment.toString());
               }
           }

       }
    }


    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }

}