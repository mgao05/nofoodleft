package com.molly.domain;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "authority_id_seq")
    @SequenceGenerator(name="authority_id_seq", sequenceName = "authority_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "authority")
    private String authority;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    //get id
    public Long getId(){return id;}

   // authority
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority){
        this.authority = authority;
    }

    //user
    public User getUser(){return user;}
    public void setUser(User user){
        this.user = user;
    }

}
