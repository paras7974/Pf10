package com.rays.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VersionValidator implements ConstraintValidator<ValidVersion, int[]> {

    @Override
    public void initialize(ValidVersion constraintAnnotation) {
    }

    @Override
    public boolean isValid(int[] value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Consider using @NotEmpty for null checks
        }
        
        // Custom validation logic
        for (int i : value) {
            if (i < 0) {
                return false; // Example condition: no negative numbers allowed
            }
        }
        return true;
    }
}
