package com.gmail.chernii.oleksii.mappers.user;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserMapperImpl implements UserMapper{

    @Override
    public UserEntity create(UserDto userDto){
        UserEntity entity = new UserEntity()
                .setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setRoles(userDto.getRoles());
        entity.setUuid(UUID.randomUUID());
        return entity;
    }

    @Override
    public UserEntity update(UserEntity entity, UserDto dto){
        entity.setEmail(dto.getEmail())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setRoles(dto.getRoles());
        return entity;
    }

    @Override
    public UserDto entityToDto(UserEntity entity){
        UserDto dto = new UserDto()
                .setRoles(entity.getRoles())
                .setEmail(entity.getEmail())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setId(entity.getId());
        return dto;
    }
}
