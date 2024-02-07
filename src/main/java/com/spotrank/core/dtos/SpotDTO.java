package com.spotrank.core.dtos;

import lombok.Data;

@Data
public class SpotDTO {
    private Long spotId;
    private String name;
    private double latitude;
    private double longitude;
    private CityDTO city;
}
