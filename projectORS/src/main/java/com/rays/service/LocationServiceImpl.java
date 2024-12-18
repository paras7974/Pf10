package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.LocationDAOInt;
import com.rays.dto.LocationDTO;

@Service
@Transactional
public class LocationServiceImpl extends BaseServiceImpl<LocationDTO, LocationDAOInt> implements LocationServiceInt {

	private static Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

	@Transactional(readOnly = true)
	public LocationDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
