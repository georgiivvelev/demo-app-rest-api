package com.georgivelev.demoapprestapi.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String password;
    private Set<String> userAuthorities;
    private String token;
}
