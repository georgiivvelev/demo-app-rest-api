package com.georgivelev.demoapprestapi.controllers;

import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import com.georgivelev.demoapprestapi.io.in.UserRegistrationRequestModel;
import com.georgivelev.demoapprestapi.io.out.UserRegistrationResponseModel;
import com.georgivelev.demoapprestapi.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/users")
@Data
@AllArgsConstructor
@Slf4j
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @PostMapping(value = "/register/student", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRegistrationResponseModel> registerStudent(@RequestBody @Valid UserRegistrationRequestModel userRegistrationRequestModel) {
        UserDto registeredUser = userService.registerUser(modelMapper.map(userRegistrationRequestModel, UserDto.class));
        registeredUser = userService.addAuthorities(registeredUser, Set.of(UserAuthorities.STUDENT));
        return ResponseEntity.ok(modelMapper.map(registeredUser, UserRegistrationResponseModel.class));
    }

    @PostMapping(value = "/register/lector", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRegistrationResponseModel> registerLector(@RequestBody @Valid UserRegistrationRequestModel userRegistrationRequestModel) {
        UserDto registeredUser = userService.registerUser(modelMapper.map(userRegistrationRequestModel, UserDto.class));
        registeredUser = userService.addAuthorities(registeredUser, Set.of(UserAuthorities.LECTOR));
        return ResponseEntity.ok(modelMapper.map(registeredUser, UserRegistrationResponseModel.class));
    }

    @PostMapping(value = "/register/admin", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRegistrationResponseModel> registerAdmin(@RequestBody @Valid UserRegistrationRequestModel userRegistrationRequestModel) {
        UserDto registeredUser = userService.registerUser(modelMapper.map(userRegistrationRequestModel, UserDto.class));
        registeredUser = userService.addAuthorities(registeredUser, Set.of(UserAuthorities.ADMIN));
        return ResponseEntity.ok(modelMapper.map(registeredUser, UserRegistrationResponseModel.class));
    }

    @PostMapping(value = "/register/administration", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRegistrationResponseModel> registerAdministration(@RequestBody @Valid UserRegistrationRequestModel userRegistrationRequestModel) {
        UserDto registeredUser = userService.registerUser(modelMapper.map(userRegistrationRequestModel, UserDto.class));
        registeredUser = userService.addAuthorities(registeredUser, Set.of(UserAuthorities.ADMINISTRATION));
        return ResponseEntity.ok(modelMapper.map(registeredUser, UserRegistrationResponseModel.class));
    }
}
