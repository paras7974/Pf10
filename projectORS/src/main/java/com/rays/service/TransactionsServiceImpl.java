package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.TransactionsDAOInt;
import com.rays.dto.TransactionsDTO;

@Service
@Transactional
public class TransactionsServiceImpl extends BaseServiceImpl<TransactionsDTO, TransactionsDAOInt> implements TransactionsServiceInt {

	@Autowired
	TransactionsDAOInt jobDAO;

}
