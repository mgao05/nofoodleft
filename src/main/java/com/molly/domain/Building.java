package com.molly.domain;

import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;
@Entity
@Table(name="building")
public class Building {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "building_id_seq")
    @SequenceGenerator(name="building_id_seq", sequenceName = "building_id_seq", allocationSize = 1)
    private Long Id;
    @Column(name = "building_name")
    private String buildingName;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;

    @JoinColumn(name = "area_Id")
    @ManyToOne
    private Area area;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
    private List<Food> food;
}

