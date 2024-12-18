package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.VehicleIdDAOInt;
import com.rays.dto.VehicleIdDTO;

@Service
@Transactional
public class VehicleIdServiceImpl extends BaseServiceImpl<VehicleIdDTO, VehicleIdDAOInt> implements VehicleIdServiceInt {

	private static Logger log = LoggerFactory.getLogger(VehicleIdServiceImpl.class);

	@Transactional(readOnly = true)
	public VehicleIdDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
