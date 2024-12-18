package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.CategoryDTO;

public interface CategoryServiceInt extends BaseServiceInt<CategoryDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public CategoryDTO findByName(String name, UserContext userContext);

}
