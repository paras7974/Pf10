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
import com.rays.dto.IssueDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidAlphabetic1;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class IssueForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid titel")
	@Size(max = 30, message = "this field is 20 characters only")
	@NotEmpty(message = "Please enter titel")
	private String title;

	@Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid product name. Only alphabetic characters are allowed.")
	@NotEmpty(message = "Please enter description")
	@Size(max = 100, message = "Description must be up to 100 charecters")
	
	private String description;

	@NotEmpty(message = "Please enter open Date")
	//@ValidDate(message = "Invalid date format or value")
	private String openDate;

	@NotEmpty(message = "Please enter assign to")
	private String assignTo;

	private String statusName;

	@NotEmpty(message = "Please enter statusId")
	@ValidLong(message = "Invalid input for status id", allowEmpty = true)
	@Min(value = 1, message = "statusId should be greater than 0")
	private String statusId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	@Override
	public BaseDTO getDto() {
		IssueDTO dto = initDTO(new IssueDTO());

		dto.setTitle(title);
		
		dto.setDescription(description);

		if (openDate != null && !openDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(openDate);
				dto.setOpenDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		dto.setAssignTo(assignTo);

		if (statusId != null && !statusId.isEmpty()) {
			dto.setStatusId(Long.valueOf(statusId));
		}

		dto.setStatusName(statusName);

		return dto;
	}
}
