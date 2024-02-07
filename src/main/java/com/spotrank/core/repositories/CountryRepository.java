package com.spotrank.core.repositories;

import com.spotrank.core.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}