package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.LocationDTO;

public class LocationForm extends BaseForm {

	public static final int INDORE = 1;
	public static final int DEWAS = 2;
	public static final int ASHOKNAGAR = 3;
	public static final int GUNA = 4;
	public static final int KHARGONE = 5;
	public static final int BHOPAL = 6;

	@NotEmpty(message = "please enter name")
	private String name;

	@NotEmpty(message = "please enter description")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BaseDTO getDto() {

		LocationDTO dto = initDTO(new LocationDTO());
		dto.setDescription(description);
		System.out.println(dto.getDescription() + "___________");
		dto.setName(name);
		return dto;

	}

}
