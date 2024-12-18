package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.TypeDTO;

public interface TypeServiceInt extends BaseServiceInt<TypeDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public TypeDTO findByName(String name, UserContext userContext);

}
