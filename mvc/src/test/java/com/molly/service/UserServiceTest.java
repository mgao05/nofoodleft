package com.molly.service;
import com.molly.config.AppConfig;
import com.molly.domain.Authority;
import com.molly.domain.User;
import com.molly.repository.AuthorityRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.molly.service.AuthorityService;
import com.molly.service.UserService;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    private User newUser;
    private String originalPass;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private AuthorityService authorityService;

    @Before
    public void setup(){
        newUser = new User();
        newUser.setUsername("Molly");
        newUser.setEmail("gmran@qq.com");
        newUser.setFirstName("Mo");
        newUser.setLastName("lly");
        originalPass= "password";
        newUser.setPassword(originalPass);

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
        assertNotEquals(originalPass,newUser.getPassword());
//        authorityRepository.save(newAuthority);
        List<Authority> testAuthority = authorityRepository.findByUser_Id(newUser.getId());
        assertTrue(testAuthority.size()>0);
        for(Authority u: testAuthority){
            assertEquals(u.getAuthority(),"REGISTERED_USER");
        }
        //assertNotEquals(testUser.getPassword(),newUser.getPassword());

//        assertEquals(newUser.getId(), testUser.getId());
    }

}

