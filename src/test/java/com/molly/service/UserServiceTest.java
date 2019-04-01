package com.molly.service;
import com.molly.config.AppConfig;
import com.molly.domain.User;
import com.molly.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private User newUser;

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
    public void findByIdTest(){
        userService.save(newUser);
        User testUser = userService.findById(newUser.getId());
        assertNotNull(testUser);
        assertEquals(newUser.getId(),testUser.getId());
    }

    @Test
    @Transactional
    public void findByFirstNameTest()throws NullPointerException, NotFoundException {
        userService.save(newUser);
        List<User> testUser = userService.findByFirstName(newUser.getFirstName());

        assertNotNull(testUser);
        User firstUser = testUser.get(0); //a list of testUser, so get the first one only which is class user
        assertEquals(newUser.getFirstName(),firstUser.getFirstName());
    }

    @Test
    @Transactional
    public void findByLastNameTest()throws NullPointerException, NotFoundException{
        userService.save(newUser);
        List<User> testUser = userService.findByLastName(newUser.getLastName());
        assertNotNull(testUser);
        User firstUser = testUser.get(0);
        assertEquals(newUser.getLastName(), firstUser.getLastName());
    }

    @Test
    @Transactional
    public void findByEmailTest()throws NullPointerException, NotFoundException{
       userService.save(newUser);
       User testUser = userService.findByEmail(newUser.getEmail());
       assertNotNull(testUser);
       assertEquals(newUser.getEmail(), testUser.getEmail());
    }

    @Test
    @Transactional
    public void createUserTest(){
        userService.createUser(newUser);
        userService.save(newUser);
        User testUser = userService.findById(newUser.getId());
        assertNotNull(testUser);
        assertEquals(newUser.getId(), testUser.getId());
    }

}

