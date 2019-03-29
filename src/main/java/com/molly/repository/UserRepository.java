package com.molly.repository;

import com.molly.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User findByEmail(String userEmail);


}





