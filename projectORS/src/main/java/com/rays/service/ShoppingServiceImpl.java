package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.ShoppingDAOInt;
import com.rays.dto.ShoppingDTO;

@Service
@Transactional
public class ShoppingServiceImpl extends BaseServiceImpl<ShoppingDTO, ShoppingDAOInt> implements ShoppingServiceInt {

	@Autowired
	ShoppingDAOInt shoppingDAO;
	
}
