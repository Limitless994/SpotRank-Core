package com.spotrank.core.repositories;

import com.spotrank.core.models.ImagePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagePostRepository extends JpaRepository<ImagePost, Long> {
    Optional<ImagePost> findByName(String name);
}
