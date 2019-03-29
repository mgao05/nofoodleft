package com.molly.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "account_expired")
    private boolean accountExpired;
    @Column(name = "account_locked")
    private boolean accountLocked;
    @Column(name = "credentials_expired")
    private boolean credentialsExpired;
    @Column(name = "enabled")
    private boolean enabled;


    @Transient    //database not adding
    @JsonIgnore   //controller response ignore json
    private Collection<?extends GrantedAuthority> authorities;

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
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;//accountNonExpired
    }

    @Override
    public boolean isEnabled() {
        return enabled;
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

    @Override //todo user detail method
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Collection authorities){this.authorities = authorities;}
    //password
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }


    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }




}

