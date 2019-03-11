package com.molly.service;

import com.molly.domain.User;
import com.molly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Transactional
    public List<User> findByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    @Transactional
    public List<User> findByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }

    @Transactional
    public User findByEmail(String userEmail){
        return userRepository.findByEmail(userEmail);
    }

}