package com.rungroop.web.mapper;

import com.rungroop.web.dto.RegistrationDto;
import com.rungroop.web.models.Role;
import com.rungroop.web.models.UserEntity;

import java.util.Arrays;

public class UserMapper {

    public static UserEntity mapToUserEntity(RegistrationDto registrationDto, Role role){
        return UserEntity.builder()
                .email(registrationDto.getEmail())
                .roles(Arrays.asList(role))
                .password(registrationDto.getPassword())
                .username(registrationDto.getUsername())
                .build();
    }

}
