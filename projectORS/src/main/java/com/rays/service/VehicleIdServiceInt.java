package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.VehicleIdDTO;

public interface VehicleIdServiceInt extends BaseServiceInt<VehicleIdDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public VehicleIdDTO findByName(String name, UserContext userContext);

}
