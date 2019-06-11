package com.gmail.chernii.oleksii.service.user;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.enities.UserEntity;
import com.gmail.chernii.oleksii.exceptions.EmailExistsException;

public interface UserServiceRegistration {
    UserEntity registeredNewAccount(UserDto accountDto) throws EmailExistsException;
}
