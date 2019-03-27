package com.molly.domain;


import org.springframework.security.core.GrantedAuthority;

//TODO authorities table migration
public class Authority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
