package com.rays.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public void initialize(ValidDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {
        if (dateStr == null) {
            return true; // Let @NotNull handle null case
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            Date parsedDate = sdf.parse(dateStr);
            Date currentDate = new Date();
            // Check if the parsed date is after the current date
            if (parsedDate.after(currentDate)) {
                return false;
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
