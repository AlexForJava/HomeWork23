package com.gmail.chernii.oleksii.dto.validators;

import com.gmail.chernii.oleksii.dto.UserDto;
import com.gmail.chernii.oleksii.dto.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDto userDto = (UserDto) o;
        return userDto.getMatchingPassword().equals(userDto.getPassword());
    }
}
