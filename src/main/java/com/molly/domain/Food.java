package com.molly.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "food_id_seq")
    @SequenceGenerator(name="food_id_seq", sequenceName = "food_id_seq", allocationSize = 1)
    private Long Id;
    @Column(name = "food_type")
    private String foodType;
    @Column(name = "image_id")
    private Integer imageId;


    @JoinColumn(name = "area_id")
    @ManyToOne
    private Area area;

    @JoinColumn(name = "building_id")
    @ManyToOne
    private Building building;

}
