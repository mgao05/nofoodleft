package com.molly.service;

import com.molly.config.AppConfig;
import com.molly.domain.User;
import com.molly.extend.security.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.molly.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class JwtTokenUtilTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    private User newUser;

    //annotation is important, here we need to put before, so newUser infor will be passed to testing
    @Before
    public void setup(){
        newUser = new User();
        newUser.setUsername("Molly");
        newUser.setEmail("gmran@qq.com");
        newUser.setFirstName("Mo");
        newUser.setLastName("lly");
        newUser.setPassword("password");
        newUser.setAccountExpired(false);
        newUser.setAccountLocked(false);
        newUser.setCredentialsExpired(false);
        newUser.setEnabled(true);
    }

    @Test
//    @Transactional ryo said no need to go back to database so don't need transactional
    public void getUsernameFromTokenTest(){
//        userService.save(newUser);  We don't need to fetch data from database later, so we don't have save it here. - Ryo
        String testToken = jwtTokenUtil.generateToken(newUser);
        String testUsername = jwtTokenUtil.getUsernameFromToken(testToken);
        assertNotNull(testUsername);
        assertEquals(newUser.getUsername(),testUsername);

    }

}
