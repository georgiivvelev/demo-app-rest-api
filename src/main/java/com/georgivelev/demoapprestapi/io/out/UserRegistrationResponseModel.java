package com.georgivelev.demoapprestapi.io.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponseModel {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    @JsonIgnore
    private String password;
    private Set<String> userAuthorities;
}
