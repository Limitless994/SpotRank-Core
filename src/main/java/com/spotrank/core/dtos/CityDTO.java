package com.spotrank.core.dtos;

import lombok.Data;

@Data
public class CityDTO {
    private Long cityId;
    private String name;
    private CountryDTO country;
}