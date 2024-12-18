package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.AccountIdDTO;

public interface AccountIdServiceInt extends BaseServiceInt<AccountIdDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public AccountIdDTO findByName(String name, UserContext userContext);

}
