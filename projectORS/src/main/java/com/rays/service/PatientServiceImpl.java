package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.PatientDAOInt;
import com.rays.dto.PatientDTO;

@Service
@Transactional
public class PatientServiceImpl extends BaseServiceImpl<PatientDTO, PatientDAOInt>
		implements PatientServiceInt {

	@Autowired
	PatientDAOInt patientDAO;

}
