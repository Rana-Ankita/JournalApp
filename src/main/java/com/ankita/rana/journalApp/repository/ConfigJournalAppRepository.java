package com.ankita.rana.journalApp.repository;

import com.ankita.rana.journalApp.entity.ConfigJournalAppEntity;
import com.ankita.rana.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
   // User findByUserName(String UserName);

   // void deleteByUserName(String userName);

}
