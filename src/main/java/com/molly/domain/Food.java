package com.molly.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "food_id_seq")
    @SequenceGenerator(name="food_id_seq", sequenceName = "food_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "food_type")
    private String foodType;
   // @Column(name = "image_id")
    //private Integer imageId;


//    @JoinColumn(name = "area_id")
//    @ManyToOne
//    private Area area;

    @JoinColumn(name = "building_id")
    @ManyToOne
    private Building building;

    //create get and set method for each column, excludes id
    //id, no set id method since id is already set
    public Long getId(){
        return id;
    }

    //food type
    public String getFoodType(){
        return foodType;
    }
    public void setFoodType(String foodType){
        this.foodType = foodType;
    }

    //building_id
    public Building getBuilding(){
        return building;
    }


}
