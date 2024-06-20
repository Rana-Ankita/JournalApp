package com.ankita.rana.journalApp.repository;

import com.ankita.rana.journalApp.entity.JournalEntry;
import com.ankita.rana.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String UserName);

    void deleteByUserName(String userName);

}
