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
import com.rays.dto.TransportationDTO;
import com.rays.validation.ValidAlphabetic1;
import com.rays.validation.ValidCost;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class TransportationForm extends BaseForm {
  
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid product name. Only alphabetic characters are allowed.")
	@NotEmpty(message = "Please enter description")
	@Size(max = 100, message = "Description must be up to 100 charecters")
	private String description;

	@NotNull(message = "Please enter cost")
	//@ValidCost(message = "Invalid cost value")
	@Pattern(regexp = "^(1000|[1-9]\\d{3,4}|100000)$", message = "Invalid cost value")
	private String cost;

	@NotNull(message = "Please enter date")
	//@ValidDate(message = "Invalid date format or value")
	private String date;

	private String modeName;

	@NotNull(message = "Please enter diseaseId")
	@ValidLong(message = "Invalid input for id", allowEmpty = true)
	@Min(value = 1, message = "modeId should be greater than 0")
	private String modeId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getModeId() {
		return modeId;
	}

	public void setModeId(String modeId) {
		this.modeId = modeId;
	}

	@Override
	public BaseDTO getDto() {
		TransportationDTO dto = initDTO(new TransportationDTO());

		dto.setDescription(description);
		if (date != null && !date.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(date);
				dto.setDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (cost != null && !cost.isEmpty()) {
			dto.setCost(Long.valueOf(cost));
		}

		if (modeId != null && !modeId.isEmpty()) {
			dto.setModeId(Long.valueOf(modeId));
		}

		dto.setModeName(modeName);

		return dto;
	}

}
