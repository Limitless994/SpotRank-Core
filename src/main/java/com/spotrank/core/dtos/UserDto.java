package com.spotrank.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private String id;

    private String name;

    private String surname;

    private Integer age;

    private String email;

    private String password;

}
