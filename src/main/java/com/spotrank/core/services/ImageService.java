package com.spotrank.core.services;

import com.spotrank.core.models.Image;
import com.spotrank.core.dtos.ImageDto;
import com.spotrank.core.models.ImagePost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageDto upload(MultipartFile file, Long postId, Long userId) throws IOException;
    ImagePost getImageDetails(String name) throws IOException;
    byte[] getImage(String name) throws IOException;
}
