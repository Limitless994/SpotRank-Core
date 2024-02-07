package com.spotrank.core.services.impl;

import com.spotrank.core.mappers.PostMapper;
import com.spotrank.core.mappers.UserMapper;
import com.spotrank.core.models.Image;
import com.spotrank.core.dtos.ImageDto;
import com.spotrank.core.models.ImagePost;
import com.spotrank.core.repositories.ImagePostRepository;
import com.spotrank.core.repositories.ImageRepository;
import com.spotrank.core.services.ImageService;
import com.spotrank.core.services.PostService;
import com.spotrank.core.services.UserService;
import com.spotrank.core.utils.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImagePostRepository repository;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @Override
    public ImageDto upload(MultipartFile file, Long postId, Long userId) throws IOException {
        return null;
    }

    @Override
    public ImagePost getImageDetails(String name) throws IOException {
        return null;
    }

    @Override
    public byte[] getImage(String name) throws IOException {
        return new byte[0];
    }
     /*   @Override
    public ImageDto upload(MultipartFile file, Long postId, Long userId) throws IOException {

     repository.save(ImagePost.builder()
                .name(file.getOriginalFilename())
                .user(UserMapper.INSTANCE.toUserEntity(userService.findById(userId)))
                .type(file.getContentType())
                .file(ImageUtility.compressImage(file.getBytes())).build());
        return new ImageDto("Image uploaded successfully: " +
                        file.getOriginalFilename());
    }

   @Override
    public ImagePost getImageDetails(String name) throws IOException {
        final Optional<ImagePost> dbImage = repository.findByName(name);
        return ImagePost.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .file(ImageUtility.decompressImage(dbImage.get().getFile())).build();
    }

    @Override
    public byte[] getImage(String name) throws IOException {
        final Optional<ImagePost> dbImage = repository.findByName(name);
        return ImageUtility.decompressImage(dbImage.get().getFile());
    }*/
}
