package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.AccountIdDAOInt;
import com.rays.dto.AccountIdDTO;

@Service
@Transactional
public class AccountIdServiceImpl extends BaseServiceImpl<AccountIdDTO, AccountIdDAOInt>
		implements AccountIdServiceInt {

	private static Logger log = LoggerFactory.getLogger(AccountIdServiceImpl.class);

	@Transactional(readOnly = true)
	public AccountIdDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
