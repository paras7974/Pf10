package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.SupplierDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class SupplierForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@Size(max = 20, message = "this field contain is 20 character only")
	@NotEmpty(message = "Please enter Name")
	private String name;

	@Pattern(regexp = "^(?:[1-9]|[1-9][0-9]|[1-4][0-9]{2}|500)$", message = "Payment Term must be a number between 1 and 500")
	@NotEmpty(message = "Please enter payment Term")
	private String paymentTerm;

	@NotEmpty(message = "Please enter registration Date")
	@ValidDate(message = "Invalid date format or value")
	private String registrationDate;

	//private String categoryName;

	@NotEmpty(message = "Please enter categoryId")
	@ValidLong(message = "Invalid input for category id", allowEmpty = true)
	@Min(value = 1, message = "categoryId should be greater than 0")
	private String categoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

//	public String getCategoryName() {
//		return categoryName;
//	}
//
//	public void setCategoryName(String categoryName) {
//		this.categoryName = categoryName;
//	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public BaseDTO getDto() {
		SupplierDTO dto = initDTO(new SupplierDTO());

		dto.setName(name);

		if (registrationDate != null && !registrationDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(registrationDate);
				dto.setRegistrationDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (paymentTerm != null && !paymentTerm.isEmpty()) {
			dto.setPaymentTerm(Long.valueOf(paymentTerm));
		}

		if (categoryId != null && !categoryId.isEmpty()) {
			dto.setCategoryId(Long.valueOf(categoryId));
		}

		//dto.setCategoryName(categoryName);

		return dto;
	}
}
