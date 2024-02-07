package com.spotrank.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("SPOT")
public class ImageSpot extends Image {
    @OneToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spotId")
    private Spot spot;

}