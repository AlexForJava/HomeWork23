package com.gmail.chernii.oleksii.service.user;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.exceptions.NotFoundUserException;

import java.util.List;

public interface UserService {
    void create(UserDto userDto);

    void update(UserDto userDto) throws NotFoundUserException;

    void deleteById(Long id);

    UserDto findUserById(Long id) throws NotFoundUserException;

    UserDto findUserByEmail(String email);

    List<UserDto> findAll();
}
