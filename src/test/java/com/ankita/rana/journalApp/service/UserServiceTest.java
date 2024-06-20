package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Disabled
    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("Ankita123"));
        User user = userRepository.findByUserName("Ankita123");
        assertTrue(!user.getJournalEntryList().isEmpty());
    }


    //ParameterizedTest using @ValueSourcey
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"Ankita123",
    "Ankit123"})
    public void testFindByUserName2(String name){
        assertNotNull(userRepository.findByUserName(name), "failed for name :" +name);
    }

    //ParameterizedTest using @CsvSource
    @Disabled
    @ParameterizedTest
    @CsvSource({"1,1,2",
    "3,2,1",
    "3,33,3"})
    public void test(int a, int b, int result){
        assertEquals(result, a+b);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void saveNewUserTest(User user){
        assertTrue(userService.saveNewUser(user));
    }
}
