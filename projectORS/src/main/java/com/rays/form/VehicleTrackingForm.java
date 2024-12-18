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
import com.rays.dto.VehicleTrackingDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidDouble;
import com.rays.validation.ValidLong;

public class VehicleTrackingForm extends BaseForm {

	//@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "Name contains alphabets only")
	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@Size(max = 20, message = "this field is 20 characters only")
	@NotEmpty(message = "Please enter name")
	private String name;

	@NotEmpty(message = "Please enter lat")
	@ValidDouble
	private String lat;

	@NotNull(message = "Please enter longitude")
	@ValidDouble
	private String longitude;

	@NotNull(message = "Please enter date")
	@ValidDate(message = "Invalid date format or value")
	private String date;

	private String vehicleIdName;

	@NotNull(message = "Please enter vehicleId")
	@ValidLong
	@Min(value = 1, message = "vehicleId should be greater than 0")
	private String vehicleIdId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		VehicleTrackingDTO dto = initDTO(new VehicleTrackingDTO());

		dto.setName(name);
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

		if (lat != null && !lat.isEmpty()) {
			dto.setLat(Double.parseDouble(lat));
		}

		if (longitude != null && !longitude.isEmpty()) {
			dto.setLongitude(Double.parseDouble(longitude));
		}

		if (vehicleIdId != null && !vehicleIdId.isEmpty()) {
			dto.setVehicleIdId(Long.valueOf(vehicleIdId));
		}

		dto.setVehicleIdName(vehicleIdName);

		return dto;
	}
}