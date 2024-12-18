package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.GenderDAOInt;
import com.rays.dto.GenderDTO;

@Service
@Transactional
public class GenderServiceImpl extends BaseServiceImpl<GenderDTO, GenderDAOInt> implements GenderServiceInt {

	private static Logger log = LoggerFactory.getLogger(GenderServiceImpl.class);

	@Transactional(readOnly = true)
	public GenderDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
