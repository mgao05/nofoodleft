package com.molly.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
//anotation,
@Entity
@Table(name="users")
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
}

