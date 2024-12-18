package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.StatusDAOInt;
import com.rays.dto.StatusDTO;

@Service
@Transactional
public class StatusServiceImpl extends BaseServiceImpl<StatusDTO, StatusDAOInt> implements StatusServiceInt {

	private static Logger log = LoggerFactory.getLogger(StatusServiceImpl.class);

	@Transactional(readOnly = true)
	public StatusDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
