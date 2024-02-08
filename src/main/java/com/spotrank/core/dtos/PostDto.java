package com.spotrank.core.dtos;

import com.spotrank.core.models.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    private String id;
    private Date creationDate;
    private Long userId;
    private Long spotId;
    private Image image;
}
