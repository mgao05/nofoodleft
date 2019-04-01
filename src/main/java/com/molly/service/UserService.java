package com.molly.service;

import com.molly.domain.Authority;
import com.molly.domain.User;
import com.molly.repository.AuthorityRepository;
import com.molly.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser){
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        userRepository.save(newUser);
        authorityService.addAuthority("REGISTERED_USER",newUser);
        return newUser;
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) throws UsernameNotFoundException,NullPointerException{
        if(username==null){
            throw new NullPointerException("");}
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("");}
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findByFirstName(String firstName) throws NotFoundException, NullPointerException{
        if(firstName==null){
            throw new NullPointerException("");
        }
        List<User> users = userRepository.findByFirstName(firstName);
        if(users==null){
            throw new NotFoundException("");
        }
        return users;
    }

    @Transactional(readOnly = true)
    public List<User> findByLastName(String lastName) throws NotFoundException, NullPointerException{
        if(lastName==null){
            throw new NullPointerException("");
        }
        List<User> users = userRepository.findByLastName(lastName);
        if(users==null){
            throw new NotFoundException("");
        }
        return users;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String userEmail)throws NotFoundException, NullPointerException{
        if(userEmail==null){
            throw new NullPointerException("");
        }
        User user = userRepository.findByEmail(userEmail);
        if(user == null){
            throw new NotFoundException("");
        }
        return user;
    }




}