package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.DiseaseDAOInt;
import com.rays.dto.DiseaseDTO;

@Service
@Transactional
public class DiseaseServiceImpl extends BaseServiceImpl<DiseaseDTO, DiseaseDAOInt> implements DiseaseServiceInt {

	private static Logger log = LoggerFactory.getLogger(DiseaseServiceImpl.class);

	@Transactional(readOnly = true)
	public DiseaseDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
