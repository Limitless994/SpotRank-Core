package com.spotrank.core.services.impl;

import com.spotrank.core.models.*;
import com.spotrank.core.repositories.ImageRepository;
import com.spotrank.core.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Optional<Image> getImageByName(String name) {
        return imageRepository.findByName(name);
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image saveImage(Image image) {
        imageRepository.save(image);
        return image;
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
