package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.TransactionTypeDAOInt;
import com.rays.dto.TransactionTypeDTO;

@Service
@Transactional
public class TransactionTypeServiceImpl extends BaseServiceImpl<TransactionTypeDTO, TransactionTypeDAOInt> implements TransactionTypeServiceInt {

	private static Logger log = LoggerFactory.getLogger(TransactionTypeServiceImpl.class);

	@Transactional(readOnly = true)
	public TransactionTypeDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
