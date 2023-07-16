package com.georgivelev.demoapprestapi.services;

import com.georgivelev.demoapprestapi.dao.repositories.AuthorityRepository;
import com.georgivelev.demoapprestapi.dao.repositories.UserRepository;
import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.entities.models.Authority;
import com.georgivelev.demoapprestapi.entities.models.User;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.georgivelev.demoapprestapi.appconfig.ApplicationConstants.NO_SUCH_USER_WITH_PROVIDED_USERNAME;

@Service
@Data
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(NO_SUCH_USER_WITH_PROVIDED_USERNAME, username)));
    }

    @Override
    @Transactional
    public UserDto registerUser(UserDto userDto) {
        log.debug("Start registering new  User with username: {}", userDto);
        log.info("Start registering new  User with username: {}", userDto.getEmail());
        if (isUserExist(userDto.getEmail()))
            throw new RuntimeException(String.format("User with username:%s already exists", userDto.getEmail()));

        User student = modelMapper.map(userDto, User.class);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setUserAuthorities(new HashSet<>());
        User savedStudent = userRepository.save(student);

        log.debug("Start registering new  User with username: {}", userDto);
        log.info("Successfully registered user with username: {}", savedStudent.getUsername());
        return modelMapper.map(savedStudent, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto addAuthorities(UserDto userDto, Set<UserAuthorities> authorities) {
        log.debug("Start adding authorities {} to UserDto: {}", authorities, userDto);
        log.info("Start adding authorities {} to user: {}", authorities, userDto.getEmail());
        User existingUser = userRepository
                .findUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(String.format(NO_SUCH_USER_WITH_PROVIDED_USERNAME, userDto.getEmail())));

       authorities.stream()
                .map(a -> authorityRepository.findAuthorityByName(a).orElseGet(() -> new Authority(a)))
                .forEach(a -> existingUser.getUserAuthorities().add(a));

       User savedUser= userRepository.save(existingUser);
        log.debug("Successfully added authorities {} to UserDto: {}", authorities, savedUser.getEmail());
        log.info("Successfully added authorities {} to user: {}", authorities, savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public boolean isUserExist(String userName) {
        return userRepository.findUserByEmail(userName).isPresent();
    }
}
