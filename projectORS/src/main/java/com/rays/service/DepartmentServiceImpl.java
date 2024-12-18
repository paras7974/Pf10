package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.DepartmentDAOInt;
import com.rays.dto.DepartmentDTO;

@Service
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentDTO, DepartmentDAOInt>
		implements DepartmentServiceInt {

	private static Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Transactional(readOnly = true)
	public DepartmentDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
