package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.ExperienceDTO;

public interface ExperienceServiceInt extends BaseServiceInt<ExperienceDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public ExperienceDTO findByName(String name, UserContext userContext);

}
