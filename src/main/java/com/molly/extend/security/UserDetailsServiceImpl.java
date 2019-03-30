package com.molly.service;

import com.molly.domain.Authority;
import com.molly.domain.User;
import com.molly.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private  UserService userService;

    @Autowired
    private AuthorityService authorityService;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(username+"is trying to log in from spring security");
        User domainUser = null;
//        userService.findByUsername(username);
//        1, null 2, user 3 execption
        try {
            domainUser =  userService.findByUsername(username);
        }catch (Exception repositoryProblem){//catch (NotFoundException|NullPointerException repositoryProblem)
           //try find user in another way
            logger.debug("catch AuthenticationServiceException from AuthenticationProvide");
        }
//        domainUser 1, user 2. null
        if (domainUser == null){
            throw new UsernameNotFoundException("Username not found in system");
        }
        List<Authority> authorities = authorityService.findByUser(domainUser);
        domainUser.setAuthorities(authorities);
        return domainUser;
    }



//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username){
//        logger.debug(username+"is trying to log in from spring security");
//        User domainUser = null;
//        try{
//            domainUser = userService
//        }
//        User user =userRepository.findByUsername(username);
//
//        if(user==null){
//            throw new UsernameNotFoundException(username);
//        }
//        return new MyUserPrincipal(user);
//    }
//
//    @Autowired
//    private MyUserDetailService userDetailService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//        throws Exception{
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Bean

}
