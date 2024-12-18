package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.ATMsDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidDouble;
import com.rays.validation.ValidLong;

public class ATMsForm extends BaseForm {

	@NotNull(message = "Please enter cash Available")
	@ValidDouble(message = "Invalid value for cash Available")
	private String cashAvailable;

	@NotNull(message = "Please enter last Restocked Date")
	@ValidDate(message = "Invalid date format or value")
	private String lastRestockedDate;

	private String locationName;

	@NotNull(message = "Please enter locationId")
	@ValidLong(message = "Invalid input for id", allowEmpty = true)
	@Min(value = 1, message = "locationId should be greater than 0")
	private String locationId;

	public String getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(String cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public String getLastRestockedDate() {
		return lastRestockedDate;
	}

	public void setLastRestockedDate(String lastRestockedDate) {
		this.lastRestockedDate = lastRestockedDate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public BaseDTO getDto() {
		ATMsDTO dto = initDTO(new ATMsDTO());

		if (lastRestockedDate != null && !lastRestockedDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(lastRestockedDate);
				dto.setLastRestockedDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (cashAvailable != null && !cashAvailable.isEmpty()) {
			dto.setCashAvailable(Double.parseDouble(cashAvailable));
		}

		if (locationId != null && !locationId.isEmpty()) {
			dto.setLocationId(Long.valueOf(locationId));
		}

		dto.setLocationName(locationName);

		return dto;
	}

}
