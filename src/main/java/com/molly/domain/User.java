package com.molly.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

//anotation,
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username",unique = true)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email",unique = true)
    @NotNull
    private String email;
    @Column(name = "password")
    private String password;
//    @Column(name = "age")
//    private Integer age;

   // @Column(name="account_non_expired")
    //todo create column and return this, add column and migrate

    //create get and set method for each column, excludes id
    // id
    public Long getId(){
        return id;
    }

    //username
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;//accountNonExpired
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username){
        this.username = username;
    }

    //first_name
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    //last_name
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    //email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Override //user detail method
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //password
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }



}

