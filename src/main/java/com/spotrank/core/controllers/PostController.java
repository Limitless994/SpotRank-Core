package com.spotrank.core.controllers;

import com.spotrank.core.dtos.PostDto;
import com.spotrank.core.services.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    private static final Logger log = LogManager.getLogger(PostController.class);

    @Autowired
    private PostService postService;


    @GetMapping("{id}")
    public ResponseEntity<PostDto> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.postService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAll() {
        return new ResponseEntity<>(this.postService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto dto) {
        return new ResponseEntity<>(this.postService.create(dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if(postService.existsById(id)){
            this.postService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
