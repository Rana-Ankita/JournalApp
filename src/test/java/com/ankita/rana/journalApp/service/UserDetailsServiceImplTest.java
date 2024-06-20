package com.ankita.rana.journalApp.service;

import com.ankita.rana.journalApp.entity.User;
import com.ankita.rana.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@SpringBootTest
public class UserDetailsServiceImplTest {

    //we removed @SpringBootTest. @Autowired because we don't want to use ApplicationContext
    //@Autowired
    @InjectMocks
    UserDetailsServiceImpl userDetailsService;


    //@MockBean
    @Mock
    UserRepository userRepository;

    /*Previously we were using @Autowired so it was creating automatically beans for use now we removed that
    so we need to initialize mock object*/

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ankita").password("Ankita").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("Ankita");
        assertNotNull(userDetails);

    }

}
