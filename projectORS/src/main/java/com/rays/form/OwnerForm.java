package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.OwnerDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class OwnerForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@NotEmpty(message = "Please enter Name")
	private String name;

	@NotEmpty(message = "Please enter INSURANCEAMOUNT")
	@Pattern(regexp = "^(10000|[1-4]\\d{4}|500000)$", message = "Invalid input for INSURANCEAMOUNT")
	private String insuranceAmount;

	@NotEmpty(message = "Please enter DOB")
	@ValidDate(message = "Invalid date format or value")
	private String dob;

	private String vehicleIdName;

	@NotEmpty(message = "Please enter vehicleId")
	@ValidLong(message = "Invalid input for vehicleId", allowEmpty = true)
	@Min(value = 1, message = "vehicleId should be greater than 0")
	private String vehicleIdId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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
		OwnerDTO dto = initDTO(new OwnerDTO());

		dto.setName(name);

		if (dob != null && !dob.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(dob);
				dto.setDob(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (insuranceAmount != null && !insuranceAmount.isEmpty()) {
			dto.setInsuranceAmount(Long.valueOf(insuranceAmount));
		}

		if (vehicleIdId != null && !vehicleIdId.isEmpty()) {
			dto.setVehicleIdId(Long.valueOf(vehicleIdId));
		}

		dto.setVehicleIdName(vehicleIdName);

		return dto;
	}
}
