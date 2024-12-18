package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.CategoryDAOInt;
import com.rays.dto.CategoryDTO;

@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<CategoryDTO, CategoryDAOInt> implements CategoryServiceInt {

	private static Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Transactional(readOnly = true)
	public CategoryDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
