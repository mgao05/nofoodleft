package com.molly.service;

import com.molly.config.AppConfig;
import com.molly.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserDetailsServiceImplTest {
    @Autowired
    private UserService userService;
    private User newUser;
    @Autowired
    private UserDetailsService userDetailsService;

    @Before
    public void setup(){
        newUser = new User();
        newUser.setUsername("Molly");
        newUser.setEmail("gmran@qq.com");
        newUser.setFirstName("Mo");
        newUser.setLastName("lly");
        newUser.setPassword("password");

    }

    @Test
    @Transactional
    public void loadUserByUsernameTest(){
    userService.save(newUser);
    UserDetails testUser = userDetailsService.loadUserByUsername(newUser.getUsername());
    assertNotNull(testUser);
    assertEquals(newUser.getUsername(),testUser.getUsername());
    }

}
