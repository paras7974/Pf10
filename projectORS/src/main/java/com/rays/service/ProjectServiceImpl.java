package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.ProjectDAOInt;
import com.rays.dto.ProjectDTO;

@Service
@Transactional
public class ProjectServiceImpl extends BaseServiceImpl<ProjectDTO, ProjectDAOInt> implements ProjectServiceInt {

	@Autowired
	ProjectDAOInt projectDAO;

}
