package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.CustomerIdDAOInt;
import com.rays.dto.CustomerIdDTO;

@Service
@Transactional
public class CustomerIdServiceImpl extends BaseServiceImpl<CustomerIdDTO, CustomerIdDAOInt>
		implements CustomerIdServiceInt {

	private static Logger log = LoggerFactory.getLogger(CustomerIdServiceImpl.class);

	@Transactional(readOnly = true)
	public CustomerIdDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
