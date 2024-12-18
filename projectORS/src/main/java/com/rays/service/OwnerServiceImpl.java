package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.OwnerDAOInt;
import com.rays.dto.OwnerDTO;

@Service
@Transactional
public class OwnerServiceImpl extends BaseServiceImpl<OwnerDTO, OwnerDAOInt> implements OwnerServiceInt {

	@Autowired
	OwnerDAOInt ownerDAO;

}
