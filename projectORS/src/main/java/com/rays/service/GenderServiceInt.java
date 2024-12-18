package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.GenderDTO;

public interface GenderServiceInt extends BaseServiceInt<GenderDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public GenderDTO findByName(String name, UserContext userContext);

}
