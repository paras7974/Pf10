package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.PaymentDAOInt;
import com.rays.dto.PaymentDTO;

@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<PaymentDTO, PaymentDAOInt> implements PaymentServiceInt {

	private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Transactional(readOnly = true)
	public PaymentDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}