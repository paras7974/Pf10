package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.ProductDTO;

public interface ProductServiceInt  extends BaseServiceInt<ProductDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public ProductDTO findByName(String name, UserContext userContext);

}