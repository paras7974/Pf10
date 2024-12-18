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
import com.rays.dto.EmployeeDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class EmployeeForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@Size(max = 30, message = "this field is 30 character only")
	private String name;

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid LastEmployerName")
	@Size(max = 30, message = "this field is 30 character only")
	private String lastEmployerName;

	@NotEmpty(message = "Please enter date of joining")
	@ValidDate(message = "Invalid date format or value")
	private String dateOfJoining;

	private String departmentName;

	@NotEmpty(message = "Please enter departmentId")
	@ValidLong(message = "Invalid input for department id", allowEmpty = true)
	@Min(value = 1, message = "departmentId should be greater than 0")
	private String departmentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastEmployerName() {
		return lastEmployerName;
	}

	public void setLastEmployerName(String lastEmployerName) {
		this.lastEmployerName = lastEmployerName;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public BaseDTO getDto() {
		EmployeeDTO dto = initDTO(new EmployeeDTO());

		dto.setName(name);
		dto.setLastEmployerName(lastEmployerName);

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

		if (departmentId != null && !departmentId.isEmpty()) {
			dto.setDepartmentId(Long.valueOf(departmentId));
		}

		dto.setDepartmentName(departmentName);

		return dto;
	}
}
