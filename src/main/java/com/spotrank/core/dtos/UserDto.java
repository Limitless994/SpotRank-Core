package com.spotrank.core.dtos;

import com.spotrank.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
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
