package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.ModeDAOInt;
import com.rays.dto.ModeDTO;

@Service
@Transactional
public class ModeServiceImpl extends BaseServiceImpl<ModeDTO, ModeDAOInt> implements ModeServiceInt {

	private static Logger log = LoggerFactory.getLogger(ModeServiceImpl.class);

	@Transactional(readOnly = true)
	public ModeDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
