package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.StatusDTO;

public interface StatusServiceInt extends BaseServiceInt<StatusDTO> {

	/**
	 * 
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public StatusDTO findByName(String name, UserContext userContext);

}
