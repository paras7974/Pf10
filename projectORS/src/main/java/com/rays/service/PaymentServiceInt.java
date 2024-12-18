package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.PaymentDTO;

public interface PaymentServiceInt extends BaseServiceInt<PaymentDTO> {

	/**
	 * Finds Customers by name.
	 * 
	 * @param name
	 * @return
	 */
	public PaymentDTO findByName(String name, UserContext userContext);

}
