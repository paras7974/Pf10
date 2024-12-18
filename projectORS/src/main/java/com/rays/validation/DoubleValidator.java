package com.rays.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoubleValidator implements ConstraintValidator<ValidDouble, String> {

	@Override
	public void initialize(ValidDouble constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return true; // @NotEmpty or @NotNull will handle null or empty checks
		}
		try {
			double doubleValue = Double.parseDouble(value);
			// Check if the value contains a decimal point
			return value.contains(".");
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
//package com.rays.validation;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class DoubleValidator implements ConstraintValidator<ValidDouble, String> {
//
//    @Override
//    public void initialize(ValidDouble constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null || value.isEmpty()) {
//            return true; // @NotEmpty or @NotNull will handle null or empty checks
//        }
//        // Check if the value matches the pattern for the custom format
//        String regex = "^\\d+(\\.\\d+)*$";
//        return value.matches(regex);
//    }
//}
//
