package com.gmail.chernii.oleksii.mappers;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.UserEntity;

public interface UserMapper {
    UserEntity create(UserDto userDto);

    UserEntity update(UserEntity entity, UserDto userDto);

    UserDto entityToDto(UserEntity entity);
}
