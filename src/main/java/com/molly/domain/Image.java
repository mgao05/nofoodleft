package com.molly.domain;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "image")
public class Image extends Object {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "image_id_seq")
    @SequenceGenerator(name ="image_id_seq", sequenceName = "image_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "extension")
    private String extension;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "food_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Food food;

    //get id
    public Long getId(){
        return id;
    }

    //get and set uuid
    public String getUuid(){ return uuid; }

    public void setUuid(String uuid){ this.uuid = uuid; }


    //get user_id
    public Long getUserId(){ return user.getId(); }

    //get food_id
    public Long getFoodId(){ return food.getId(); }

    //get extension
    public String getExtension(){
        return extension;
    }

    //generate s3Key
    public String getFileName(){
        return uuid+extension;
    }




}
