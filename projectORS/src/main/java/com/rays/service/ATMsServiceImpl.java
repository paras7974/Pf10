package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.ATMsDAOInt;
import com.rays.dto.ATMsDTO;

@Service
@Transactional
public class ATMsServiceImpl extends BaseServiceImpl<ATMsDTO, ATMsDAOInt> implements ATMsServiceInt {

	@Autowired
	ATMsDAOInt atmsDAO;

}
