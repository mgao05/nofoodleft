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

}

