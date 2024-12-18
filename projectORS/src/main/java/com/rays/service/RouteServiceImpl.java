package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.RouteDAOInt;
import com.rays.dto.RouteDTO;

@Service
@Transactional
public class RouteServiceImpl extends BaseServiceImpl<RouteDTO, RouteDAOInt> implements RouteServiceInt {

	@Autowired
	RouteDAOInt routeDAO;

}