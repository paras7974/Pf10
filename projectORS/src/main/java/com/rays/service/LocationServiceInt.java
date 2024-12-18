package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.LocationDTO;

public interface LocationServiceInt extends BaseServiceInt<LocationDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public LocationDTO findByName(String name, UserContext userContext);

}
