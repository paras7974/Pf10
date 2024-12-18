package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.DiseaseDTO;
import com.rays.dto.ModeDTO;

public interface ModeServiceInt extends BaseServiceInt<ModeDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public ModeDTO findByName(String name, UserContext userContext);

}
