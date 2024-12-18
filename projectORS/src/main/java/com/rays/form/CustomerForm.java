package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.CustomerDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class CustomerForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@Size(max = 20,message = "this field is alphabate only")
	@NotEmpty(message = "Please enter Name")
	private String name;

	@NotNull(message = "Please enter phone Number")
	@Pattern(regexp = "(^$|^[6-9]\\d{9}$)", message = "Invalid input for phone Number")
	//@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid input for phone Number")

	private String phoneNumber;

	@NotEmpty(message = "Please enter registration Date")
	@ValidDate(message = "Invalid date format or value")
	private String dateOfBirth;

	private String genderName;

	@NotEmpty(message = "Please enter genderId")
	@ValidLong(message = "Invalid input for gender id", allowEmpty = true)
	@Min(value = 1, message = "genderId should be greater than 0")
	private String genderId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	@Override
	public BaseDTO getDto() {
		CustomerDTO dto = initDTO(new CustomerDTO());

		dto.setName(name);

		if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(dateOfBirth);
				dto.setDateOfBirth(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (phoneNumber != null && !phoneNumber.isEmpty()) {
			dto.setPhoneNumber(Long.valueOf(phoneNumber));
		}

		if (genderId != null && !genderId.isEmpty()) {
			dto.setGenderId(Long.valueOf(genderId));
		}

		dto.setGenderName(genderName);

		return dto;
	}
}
