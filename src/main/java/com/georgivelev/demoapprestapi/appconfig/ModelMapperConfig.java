package com.georgivelev.demoapprestapi.appconfig;

import com.georgivelev.demoapprestapi.entities.dtos.UserDto;
import com.georgivelev.demoapprestapi.entities.models.Authority;
import com.georgivelev.demoapprestapi.entities.models.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        Converter<Set<Authority>, Set<String>> authoritiesToStringConverter = mappingContext ->
                mappingContext.getSource().stream().map(a -> a.getName().name()).collect(Collectors.toSet());

        TypeMap<User, UserDto> userUserDtoTypeMap = modelMapper.createTypeMap(User.class, UserDto.class);
        userUserDtoTypeMap.addMappings(mapping ->
                mapping.using(authoritiesToStringConverter).map(User::getUserAuthorities, UserDto::setUserAuthorities));

        return modelMapper;
    }
}
