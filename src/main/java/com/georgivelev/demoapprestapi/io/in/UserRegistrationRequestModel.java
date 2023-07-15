package com.georgivelev.demoapprestapi.io.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestModel {

    @Pattern(regexp = "^(?=.{2,}$)[^\\s].*$", message = "Invalid firstНame format. First name should be at least 2 non empty symbols")
    private String firstName;

    @Pattern(regexp = "^(?=.{2,}$)[^\\s].*$", message = "Invalid lastНame format. Last name should be at least 2 non empty symbols")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "User Email Cannot be Null")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9].*[0-9])(?=.*[^a-zA-Z0-9].*[^a-zA-Z0-9])[a-zA-Z0-9!@#$%^&*]{8,16}$",
            message = "Invalid password format. Password should be 8-16 symbols and should contains at least 2 numbers and at least 2 special symbols (!@#$%^&*)")
    private String password;
}
