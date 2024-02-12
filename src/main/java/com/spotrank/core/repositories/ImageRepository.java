package com.spotrank.core.repositories;

import com.spotrank.core.models.Image;
import com.spotrank.core.models.ImageParentType;
import com.spotrank.core.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    Image findByNameAndParentType(String name, ImageParentType parentType);}
