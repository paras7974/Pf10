package com.rays.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAlphabeticValidator1 implements ConstraintValidator<ValidAlphabetic1, String> {

    @Override
    public void initialize(ValidAlphabetic1 constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation(); // Disable default message
            context.buildConstraintViolationWithTemplate("This field is required and cannot be empty")
                   .addConstraintViolation();
            return false;
        }
        
        boolean isValid = value.chars().allMatch(c -> Character.isLetter(c) || Character.isWhitespace(c));
        
        if (!isValid) {
            context.disableDefaultConstraintViolation(); // Disable default message
            context.buildConstraintViolationWithTemplate("Please enter valid characters)")
                   .addConstraintViolation();
        }
        
        return isValid;
    }
}