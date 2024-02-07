package com.spotrank.core.repositories;

import com.spotrank.core.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}