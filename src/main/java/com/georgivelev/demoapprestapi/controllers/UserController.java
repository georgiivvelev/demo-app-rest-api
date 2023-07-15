package com.georgivelev.demoapprestapi.controllers;

import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.io.in.UserRegistrationRequestModel;
import com.georgivelev.demoapprestapi.io.out.UserRegistrationResponseModel;
import com.georgivelev.demoapprestapi.security.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/users")
@Data
@AllArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @PostMapping(value = "/register/student", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRegistrationResponseModel> registerStudent(@RequestBody @Valid UserRegistrationRequestModel userRegistrationRequestModel) {
        UserDto registeredUser = userService.registerStudent(modelMapper.map(userRegistrationRequestModel, UserDto.class));
        return ResponseEntity.ok(modelMapper.map(registeredUser, UserRegistrationResponseModel.class));
    }
}
