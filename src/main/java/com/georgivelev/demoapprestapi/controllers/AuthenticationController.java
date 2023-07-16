package com.georgivelev.demoapprestapi.controllers;

import com.georgivelev.demoapprestapi.io.in.UserAuthenticationRequestModel;
import com.georgivelev.demoapprestapi.io.out.UserAuthenticationResponseModel;
import com.georgivelev.demoapprestapi.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserAuthenticationResponseModel> authenticateUser(@RequestBody @Valid UserAuthenticationRequestModel authenticationRequestModel) {
        return ResponseEntity.ok(authenticationService.authenticateUser(authenticationRequestModel));
    }
}
