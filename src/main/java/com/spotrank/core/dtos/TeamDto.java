package com.spotrank.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamDto {
    private String id;
    private String name;
    private String adminId;
    private Set<Long> membersId = new HashSet<>(0);

}
