package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.SupplierDAOInt;
import com.rays.dto.SupplierDTO;

@Service
@Transactional
public class SupplierServiceImpl extends BaseServiceImpl<SupplierDTO, SupplierDAOInt> implements SupplierServiceInt {

	@Autowired
	SupplierDAOInt supplierDAO;

}
