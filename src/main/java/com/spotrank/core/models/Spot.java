package com.spotrank.core.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "spots")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    private String name;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(mappedBy = "spot", cascade = CascadeType.ALL, orphanRemoval = true)
    private ImageSpot thumbnail;


}
