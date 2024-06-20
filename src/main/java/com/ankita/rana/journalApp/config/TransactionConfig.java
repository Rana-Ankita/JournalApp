package com.ankita.rana.journalApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    public PlatformTransactionManager testing(MongoDatabaseFactory dbfactory){
        return new MongoTransactionManager(dbfactory);
    }
}*/
