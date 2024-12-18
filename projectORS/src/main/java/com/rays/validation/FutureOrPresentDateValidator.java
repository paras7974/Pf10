package com.rays.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FutureOrPresentDateValidator implements ConstraintValidator<FutureOrPresentDate, String> {

	@Override
	public void initialize(FutureOrPresentDate constraintAnnotation) {
	}

	@Override
	public boolean isValid(String dateStr, ConstraintValidatorContext context) {
		if (dateStr == null || dateStr.isEmpty()) {
			return true; // Use @NotNull to check for null values
		}

		try {
			LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			return !date.isBefore(LocalDate.now());
		} catch (DateTimeParseException e) {
			return false; // Invalid date format
		}
	}
}
