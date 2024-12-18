package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DiseaseDTO;

public class DiseaseForm extends BaseForm {

	public static final int FEVER = 1;
	public static final int COLD = 2;
	public static final int CANCER = 3;

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

		DiseaseDTO dto = initDTO(new DiseaseDTO());
		dto.setDescription(description);
		System.out.println(dto.getDescription() + "___________");
		dto.setName(name);
		return dto;

	}

}
