package com.molly.domain;

import javax.persistence.Entity;

//@Entity if there is entity, it means there is a table, so we don't need entity here
public class LoginInfo {
    private String username;
    private String password;

    public String getUsername(){return username;}

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
    return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
