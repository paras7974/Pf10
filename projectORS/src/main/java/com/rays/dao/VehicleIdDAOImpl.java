package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.VehicleIdDTO;

@Repository
public class VehicleIdDAOImpl extends BaseDAOImpl<VehicleIdDTO> implements VehicleIdDAOInt {

	@Override
	public Class<VehicleIdDTO> getDTOClass() {
		return VehicleIdDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(VehicleIdDTO dto, CriteriaBuilder builder, Root<VehicleIdDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getVehicleIdName())) {

			whereCondition.add(builder.like(qRoot.get("vehicleIdName"), dto.getVehicleIdName() + "%"));
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