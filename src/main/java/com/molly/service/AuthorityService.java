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
    public Authority addAuthority(String authority,User user){
        Authority auth = new Authority();
        auth.setAuthority(authority);
        auth.setUser(user);
        authorityRepository.save(auth);
        return auth;
    }

    @Transactional
    public Authority save(Authority authority){
        return authorityRepository.save(authority);
    }

    @Transactional
    public List<Authority> findByUser(User user) {
        return authorityRepository.findByUser_Id(user.getId());
    }
}
