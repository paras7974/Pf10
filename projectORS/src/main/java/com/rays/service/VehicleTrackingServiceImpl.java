package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.VehicleTrackingDAOInt;
import com.rays.dto.VehicleTrackingDTO;

@Service
@Transactional
public class VehicleTrackingServiceImpl extends BaseServiceImpl<VehicleTrackingDTO, VehicleTrackingDAOInt>
		implements VehicleTrackingServiceInt {

	@Autowired
	VehicleTrackingDAOInt vehicleTrackingDAO;

}
