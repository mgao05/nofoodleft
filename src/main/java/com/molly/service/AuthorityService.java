package com.molly.service;

import com.molly.domain.Authority;
import com.molly.domain.User;
import com.molly.repository.AuthorityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public void addAuthority(String authority){
        Authority auth = new Authority();
        auth.setAuthority(authority);
        authorityRepository.save(auth);
    }
    //todo store createuser, input a user and output an authority method
    @Transactional
    public List<Authority> findByUser(User user) {
        return authorityRepository.findByUser_Id(user.getId());
    }
}
