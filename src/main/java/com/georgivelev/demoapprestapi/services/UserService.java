package com.georgivelev.demoapprestapi.services;

import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    UserDto registerUser(UserDto userDto);
    UserDto addAuthorities(UserDto userDto, Set<UserAuthorities> authorities);
}
