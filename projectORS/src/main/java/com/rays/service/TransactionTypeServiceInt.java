package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.TransactionTypeDTO;

public interface TransactionTypeServiceInt extends BaseServiceInt<TransactionTypeDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public TransactionTypeDTO findByName(String name, UserContext userContext);

}
