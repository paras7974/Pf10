package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.DepartmentDTO;

public interface DepartmentServiceInt extends BaseServiceInt<DepartmentDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public DepartmentDTO findByName(String name, UserContext userContext);

}
