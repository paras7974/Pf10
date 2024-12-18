package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.LocationDTO;

@Repository
public class LocationDAOImpl extends BaseDAOImpl<LocationDTO> implements LocationDAOInt {

	@Override
	public Class<LocationDTO> getDTOClass() {
		return LocationDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(LocationDTO dto, CriteriaBuilder builder, Root<LocationDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getLocationName())) {

			whereCondition.add(builder.like(qRoot.get("locationName"), dto.getLocationName() + "%"));
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