package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.LoansDAOInt;
import com.rays.dto.LoansDTO;

@Service
@Transactional
public class LoansServiceImpl extends BaseServiceImpl<LoansDTO, LoansDAOInt> implements LoansServiceInt {

	@Autowired
	LoansDAOInt loansDAO;

}
