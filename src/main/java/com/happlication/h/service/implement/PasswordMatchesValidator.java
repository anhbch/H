package com.happlication.h.service.implement;


import com.happlication.h.security.PasswordMatches;
import com.happlication.h.service.DTO.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationDto userRegistrationDto = (UserRegistrationDto) o;
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getMatchingPassword());
    }
}
