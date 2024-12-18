package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.RouteDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class RouteForm extends BaseForm {

//	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@Size(max = 30, message = "this field is 30 characters only")
//	@NotEmpty(message = "Please enter name")
	@ValidAlphabetic
	private String name;

//	@NotNull(message = "Please enter allowed speed")
//	@Min(value = 5, message = "Allowed speed should not be less than 5")
//	@Max(value = 170, message = "Allowed speed should not be more than 170")
	@ValidLong
	private String allowedSpeed;

	@NotNull(message = "Please enter start Date")
	@ValidDate(message = "Invalid date format or value")
	private String startDate;

	private String vehicleIdName;

	@NotNull(message = "Please enter vehicleId")
	@ValidLong(message = "Invalid input for id", allowEmpty = true)
	private String vehicleIdId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAllowedSpeed() {
		return allowedSpeed;
	}

	public void setAllowedSpeed(String allowedSpeed) {
		this.allowedSpeed = allowedSpeed;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getVehicleIdName() {
		return vehicleIdName;
	}

	public void setVehicleIdName(String vehicleIdName) {
		this.vehicleIdName = vehicleIdName;
	}

	public String getVehicleIdId() {
		return vehicleIdId;
	}

	public void setVehicleIdId(String vehicleIdId) {
		this.vehicleIdId = vehicleIdId;
	}

	@Override
	public BaseDTO getDto() {
		RouteDTO dto = initDTO(new RouteDTO());

		dto.setName(name);
		if (startDate != null && !startDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(startDate);
				dto.setStartDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (allowedSpeed != null && !allowedSpeed.isEmpty()) {
			dto.setAllowedSpeed(Long.valueOf(allowedSpeed));
		}

		if (vehicleIdId != null && !vehicleIdId.isEmpty()) {
			dto.setVehicleIdId(Long.valueOf(vehicleIdId));
		}

		dto.setVehicleIdName(vehicleIdName);

		return dto;
	}

}
