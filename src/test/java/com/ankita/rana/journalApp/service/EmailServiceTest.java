package com.ankita.rana.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired EmailService emailService;

    @Test
    public void testSendmail(){
        emailService.sendEmail("ankitaranakaintura2022@gmail.com", "Test Java Mail Sender", "Hi, Please ignore");
    }
}
