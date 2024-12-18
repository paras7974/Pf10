package com.rays.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CostValidator implements ConstraintValidator<ValidCost, String> {

    @Override
    public void initialize(ValidCost constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // allow empty value
        }
        try {
            long cost = Long.parseLong(value);
            return cost > 0 && cost <= 5000000;
        } catch (NumberFormatException e) {
            return false; // invalid if not a number
        }
    }
}
