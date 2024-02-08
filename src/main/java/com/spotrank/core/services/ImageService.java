package com.spotrank.core.services;

import com.spotrank.core.models.Image;
import java.util.List;
import java.util.Optional;

public interface ImageService {
    Optional<Image> getImageById(Long id);
    Optional<Image> getImageByName(String name);
    List<Image> getAllImages();
    Image saveImage(Image image);
    void deleteImage(Long id);
}