package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.CompanyDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidEmail;
import com.rays.validation.ValidLong;

public class CompanyForm extends BaseForm {

	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "Name contains alphabets only")
	@NotEmpty(message = "Please enter name")
	private String name;

	@NotEmpty(message = "Please enter email")
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", message = "Invalid login_Id")
	private String email;

	@NotNull(message = "Please enter mobile")
	@Pattern(regexp = "(^$|^[6-9]\\d{9}$)", message = "Invalid input for mobile")
	private String mobile;

	@NotNull(message = "Please enter date")
	@ValidDate(message = "Invalid date format or value")
	private String dateOfJoining;

	private String experienceName;

	@NotNull(message = "Please enter diseaseId")
	@ValidLong(message = "Invalid input for id")
	@Min(value = 1, message = "experienceId should be greater than 0")
	private String experienceId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getExperienceName() {
		return experienceName;
	}

	public void setExperienceName(String experienceName) {
		this.experienceName = experienceName;
	}

	public String getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(String experienceId) {
		this.experienceId = experienceId;
	}

	@Override
	public BaseDTO getDto() {
		CompanyDTO dto = initDTO(new CompanyDTO());

		dto.setName(name);
		if (dateOfJoining != null && !dateOfJoining.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(dateOfJoining);
				dto.setDateOfJoining(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (mobile != null && !mobile.isEmpty()) {
			dto.setMobile(Long.valueOf(mobile));
		}

		if (experienceId != null && !experienceId.isEmpty()) {
			dto.setExperienceId(Long.valueOf(experienceId));
		}

		dto.setEmail(email);
		dto.setExperienceName(experienceName);

		return dto;
	}

}
