package com.spotrank.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserAuthenticationResponseDto {

    private String token;

    private String expirationDate;

    private Long id;

    private String name;

    private String email;

}
