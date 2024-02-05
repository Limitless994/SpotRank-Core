package com.spotrank.core.services.impl;

import com.spotrank.core.mappers.PostMapper;
import com.spotrank.core.mappers.UserMapper;
import com.spotrank.core.models.Image;
import com.spotrank.core.dtos.ImageDto;
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
    ImageRepository repository;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Override
    public ImageDto upload(MultipartFile file, Long postId, Long userId) throws IOException {

        repository.save(Image.builder()
                .name(file.getOriginalFilename())
                .post(PostMapper.INSTANCE.toPostEntity(postService.findById(postId)))
                .user(UserMapper.INSTANCE.toUserEntity(userService.findById(userId)))
                .type(file.getContentType())
                .file(ImageUtility.compressImage(file.getBytes())).build());
        return new ImageDto("Image uploaded successfully: " +
                        file.getOriginalFilename());
    }

    @Override
    public Image getImageDetails(String name) throws IOException {
        final Optional<Image> dbImage = repository.findByName(name);
        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .file(ImageUtility.decompressImage(dbImage.get().getFile())).build();
    }

    @Override
    public byte[] getImage(String name) throws IOException {
        final Optional<Image> dbImage = repository.findByName(name);
        return ImageUtility.decompressImage(dbImage.get().getFile());
    }
}
