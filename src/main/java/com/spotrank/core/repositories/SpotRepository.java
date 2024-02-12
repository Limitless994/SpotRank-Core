package com.spotrank.core.repositories;

import com.spotrank.core.models.City;
import com.spotrank.core.models.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    Spot findByCityAndName(City city, String spotName);
}