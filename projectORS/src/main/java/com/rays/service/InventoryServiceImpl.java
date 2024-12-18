package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.InventoryDAOInt;
import com.rays.dto.InventoryDTO;

@Service
@Transactional
public class InventoryServiceImpl extends BaseServiceImpl<InventoryDTO, InventoryDAOInt> implements InventoryServiceInt {

	@Autowired
	InventoryDAOInt inventoryDAO;

}
