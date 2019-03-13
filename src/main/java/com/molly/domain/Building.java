package com.molly.domain;

import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;
@Entity
@Table(name="buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "building_id_seq")
    @SequenceGenerator(name="building_id_seq", sequenceName = "building_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "building_address")
    private String buildingAddress;

    @JoinColumn(name = "area_id")
    @ManyToOne
    private Area area;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
//    private List<Food> food;

    //get and set methods for class building
    //id
    public Long getId(){
        return id;
    }

    //building name
    public String getBuildingName(){
        return buildingName;
    }
    public void setBuildingName(String building){
        this.buildingName = building;
    }

    //area
    public Area getArea(){
        return area;
    }

    public void setArea(Area a){
        this.area = a;
    }

    //address
    public String getBuildingAddress(){
        return buildingAddress;
    }

    public void setBuildingAddress(String address){
        this.buildingAddress = address;
    }

}

