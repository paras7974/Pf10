package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.ExperienceDTO;

public class ExperienceForm extends BaseForm {

	public static final int ONEYEAR = 1;
	public static final int TWOYEAR = 2;
	public static final int THREEYEAR = 3;

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

		ExperienceDTO dto = initDTO(new ExperienceDTO());
		dto.setDescription(description);
		System.out.println(dto.getDescription() + "___________");
		dto.setName(name);
		return dto;

	}
}