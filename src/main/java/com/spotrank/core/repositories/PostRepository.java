package com.spotrank.core.repositories;

import com.spotrank.core.models.Post;
import com.spotrank.core.models.Spot;
import com.spotrank.core.models.Team;
import com.spotrank.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByUserAndSpot(User user, Spot spot);
}
