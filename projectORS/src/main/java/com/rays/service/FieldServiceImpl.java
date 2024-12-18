package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.FieldDAOInt;
import com.rays.dto.FieldDTO;

@Service
@Transactional
public class FieldServiceImpl extends BaseServiceImpl<FieldDTO, FieldDAOInt> implements FieldServiceInt {

	@Autowired
	FieldDAOInt fieldDAO;

}
