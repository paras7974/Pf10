package com.rays.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLongValidator implements ConstraintValidator<ValidLong, String> {

    private boolean allowEmpty;
    private static final String DIGITS_ONLY_REGEX = "^[0-9]+$";

    @Override
    public void initialize(ValidLong constraintAnnotation) {
        allowEmpty = constraintAnnotation.allowEmpty();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return allowEmpty; // Return true if allowed to be empty
        }

        // Check if the value matches the digits-only pattern
        if (!value.matches(DIGITS_ONLY_REGEX)) {
            return false; // Invalid if it contains non-digit characters
        }

        try {
            Long.parseLong(value); // Check if it's a valid Long
            return true;
        } catch (NumberFormatException e) {
            return false; // Not a valid Long
        }
    }
}
