package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CompanyDAOInt;
import com.rays.dto.CompanyDTO;

@Service
@Transactional
public class CompanyServiceImpl extends BaseServiceImpl<CompanyDTO, CompanyDAOInt> implements CompanyServiceInt {

	@Autowired
	CompanyDAOInt companyDAO;

}
