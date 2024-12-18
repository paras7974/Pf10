package com.rays.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAlphabeticValidator implements ConstraintValidator<ValidAlphabetic, String> {

    @Override
    public void initialize(ValidAlphabetic constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation(); // Disable default message
            context.buildConstraintViolationWithTemplate("This field is required and can not be empty")
                   .addConstraintViolation();
            return false;
        }
        
       // boolean isValid = value.chars().allMatch(c -> Character.isLetter(c) || Character.isWhitespace(c));
        
       // boolean isValid = value.matches("[a-zA-Z]+\\s[a-zA-Z]+");
        boolean isValid = value.matches("^[A-Z][a-z]+ [A-Z][a-z]+$");
        
        
        if (!isValid) {
            context.disableDefaultConstraintViolation(); // Disable default message
            context.buildConstraintViolationWithTemplate("Please enter a valid name, (including your first name and last name)")
                   .addConstraintViolation();
        }
        
        return isValid;
    }
}
