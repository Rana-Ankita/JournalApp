package com.ankita.rana.journalApp.repository;

import com.ankita.rana.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;


public List<User> getUserForSA(){
    Query query = new Query();
   // query.addCriteria(Criteria.where("userName").is("Ankita123"));
    //query.addCriteria(Criteria.where("email").exists(true));
    //query.addCriteria((Criteria.where("userName").nin("Rajat","Shanu")));
   // query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));
    query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
    query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

    //if we want to use OR conditions
   // Criteria criteria= new Criteria();
    /*query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
            Criteria.where("sentimentAnalysis").is(true)));*/
    List <User> users = mongoTemplate.find(query, User.class);
    return  users;
}
}
