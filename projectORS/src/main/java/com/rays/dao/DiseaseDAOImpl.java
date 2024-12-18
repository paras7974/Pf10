package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.DiseaseDTO;

@Repository
public class DiseaseDAOImpl extends BaseDAOImpl<DiseaseDTO> implements DiseaseDAOInt {

	@Override
	public Class<DiseaseDTO> getDTOClass() {
		return DiseaseDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(DiseaseDTO dto, CriteriaBuilder builder, Root<DiseaseDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getDiseaseName())) {

			whereCondition.add(builder.like(qRoot.get("diseaseName"), dto.getDiseaseName() + "%"));
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