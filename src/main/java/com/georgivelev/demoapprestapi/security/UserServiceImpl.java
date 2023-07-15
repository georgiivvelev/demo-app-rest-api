package com.georgivelev.demoapprestapi.security;

import com.georgivelev.demoapprestapi.dao.repositories.AuthorityRepository;
import com.georgivelev.demoapprestapi.dao.repositories.UserRepository;
import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.entities.models.Authority;
import com.georgivelev.demoapprestapi.entities.models.User;
import com.georgivelev.demoapprestapi.entities.models.UserAuthorities;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user with the provided username"));
    }

    @Override
    public UserDto registerStudent(UserDto userDto) {
        if (isUserExist(userDto.getEmail()))
            throw new RuntimeException(String.format("User with username:%s already exists", userDto.getEmail()));
        User student = modelMapper.map(userDto, User.class);
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        attachStudentAuthority(student);

        User savedStudent = userRepository.save(student);
        UserDto savedStudentDTO = modelMapper.map(savedStudent,UserDto.class);
        return savedStudentDTO;
    }

    private void attachStudentAuthority(User student) {
        Set<Authority> studentsAuthorities = student.getUserAuthorities() != null ? student.getUserAuthorities() : new HashSet<>();

        Optional<Authority> studentAuthority = authorityRepository.findAuthorityByName(UserAuthorities.STUDENT);
        studentsAuthorities.add(studentAuthority.orElseGet(() -> new Authority(UserAuthorities.STUDENT)));

        student.setUserAuthorities(studentsAuthorities);
    }

    public boolean isUserExist(String userName) {
        return userRepository.findUserByEmail(userName).isPresent();
    }
}
