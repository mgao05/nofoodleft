package com.molly.service;

import com.molly.domain.User;
import com.molly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(User newUser){
        String encodedPass = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPass);
        save(newUser);
        return newUser;
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Transactional(readOnly = true)
    public List<User> findByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public List<User> findByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String userEmail){
        return userRepository.findByEmail(userEmail);
    }

}