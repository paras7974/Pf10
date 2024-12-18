package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.CustomerIdDTO;

public interface CustomerIdServiceInt extends BaseServiceInt<CustomerIdDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public CustomerIdDTO findByName(String name, UserContext userContext);

}
