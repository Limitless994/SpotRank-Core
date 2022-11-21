package com.spotrank.core.controllers;


import com.spotrank.core.models.Image;
import com.spotrank.core.dtos.ImageDto;
import com.spotrank.core.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin()
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload/image")
    public ResponseEntity<ImageDto> uplaodImage(@RequestParam("image") MultipartFile file, Long postId, Long userId) throws IOException {
        return new ResponseEntity<>(imageService.upload(file, postId, userId), HttpStatus.OK);
    }

    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {
        return imageService.getImageDetails(name);
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
        return new ResponseEntity<>(imageService.getImage(name), HttpStatus.OK);
    }
}