package com.spotrank.core.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScoreboardDTO {
    private Long id;
    private Long spotId;
    private List<PostDto> posts;
    private LocalDate startDate;
    private LocalDate endDate;
}
