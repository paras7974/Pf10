package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.ModeDTO;

@Repository
public class ModeDAOImpl extends BaseDAOImpl<ModeDTO> implements ModeDAOInt {

	@Override
	public Class<ModeDTO> getDTOClass() {
		return ModeDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ModeDTO dto, CriteriaBuilder builder, Root<ModeDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getModeName())) {

			whereCondition.add(builder.like(qRoot.get("modeName"), dto.getModeName() + "%"));
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