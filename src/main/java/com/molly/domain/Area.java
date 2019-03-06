package com.molly.domain;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="areas")
public class Area {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "area_id_seq")
    @SequenceGenerator(name = "area_id_seq", sequenceName = "area_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "area_name")
    private String areaName;
//    @Column(name = "area_zipcode")
//    private Integer areaZipcode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
    private List<Building> building;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
//    private List<Food> food;

    //get and set method for area
    //id
    public Long getId(){
        return id;
    }
    //areaName
    public String getAreaName(){
        return areaName;
    }

    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

}

