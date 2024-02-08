package com.spotrank.core.dtos;

import com.spotrank.core.models.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotDTO {
    private Long spotId;
    private String name;
    private double latitude;
    private double longitude;
    private Long cityId;
    private Image thumbnail;
}
