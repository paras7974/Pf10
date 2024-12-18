package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.ExperienceDAOInt;
import com.rays.dto.ExperienceDTO;

@Service
@Transactional
public class ExperienceServiceImpl extends BaseServiceImpl<ExperienceDTO, ExperienceDAOInt> implements ExperienceServiceInt {

	private static Logger log = LoggerFactory.getLogger(ExperienceServiceImpl.class);

	@Transactional(readOnly = true)
	public ExperienceDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
