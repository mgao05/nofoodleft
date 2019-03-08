package com.molly.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.SEQUENCE;

//anotation,
@Entity
@Table(name="users")
public class User {
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

    //create get and set method for each column, excludes id
    // id
    public Long getId(){
        return id;
    }

    //username
    public String getUsername(){
        return username;
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

    //password
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }



}

