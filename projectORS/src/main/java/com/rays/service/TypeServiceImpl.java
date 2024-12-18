package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.TypeDAOInt;
import com.rays.dto.TypeDTO;

@Service
@Transactional
public class TypeServiceImpl extends BaseServiceImpl<TypeDTO, TypeDAOInt> implements TypeServiceInt {

	private static Logger log = LoggerFactory.getLogger(TypeServiceImpl.class);

	@Transactional(readOnly = true)
	public TypeDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
