package com.georgivelev.demoapprestapi.appconfig;

import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import com.georgivelev.demoapprestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements ApplicationRunner {
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UserDto superAdmin = UserDto.builder()
                .email("superadmin@superadmin.com")
                .password("password12@#")
                .firstName("super")
                .lastName("admin")
                .dateOfBirth(new Date(System.currentTimeMillis()))
                .build();

        userService.registerUser(superAdmin);
        userService.addAuthorities(superAdmin, Set.of(UserAuthorities.SUPER_ADMIN));
    }
}
