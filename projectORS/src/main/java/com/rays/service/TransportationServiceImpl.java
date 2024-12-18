package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.TransportationDAOInt;
import com.rays.dto.TransportationDTO;

@Service
@Transactional
public class TransportationServiceImpl extends BaseServiceImpl<TransportationDTO, TransportationDAOInt> implements TransportationServiceInt {

	@Autowired
	TransportationDAOInt transportationDAO;

}
