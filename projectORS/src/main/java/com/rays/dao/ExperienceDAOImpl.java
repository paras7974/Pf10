package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.ExperienceDTO;

@Repository
public class ExperienceDAOImpl  extends BaseDAOImpl<ExperienceDTO> implements ExperienceDAOInt {

	@Override
	public Class<ExperienceDTO> getDTOClass() {
		return ExperienceDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ExperienceDTO dto, CriteriaBuilder builder, Root<ExperienceDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getExperienceName())) {

			whereCondition.add(builder.like(qRoot.get("experienceName"), dto.getExperienceName() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {

			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (!isZeroNumber(dto.getId())) {

			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		return whereCondition;
	}
}
