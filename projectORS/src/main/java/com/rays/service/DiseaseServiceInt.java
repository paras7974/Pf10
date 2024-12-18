package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.DiseaseDTO;

public interface DiseaseServiceInt extends BaseServiceInt<DiseaseDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public DiseaseDTO findByName(String name, UserContext userContext);

}
