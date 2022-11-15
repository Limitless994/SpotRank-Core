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

    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;

    public User toEntity(PasswordEncoder encoder) {
        User user = new User();
        user.setAge(this.age);
        user.setEmail(this.email);
        user.setName(this.name);
        user.setPassword(encoder.encode(this.password));
        user.setSurname(this.surname);
        return user;
    }
}
