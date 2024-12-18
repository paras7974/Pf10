package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CustomerIdDTO;

@Repository
public class CustomerIdDAOImpl extends BaseDAOImpl<CustomerIdDTO> implements CustomerIdDAOInt {

	@Override
	public Class<CustomerIdDTO> getDTOClass() {
		return CustomerIdDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CustomerIdDTO dto, CriteriaBuilder builder, Root<CustomerIdDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getCustomerIdName())) {

			whereCondition.add(builder.like(qRoot.get("customerIdName"), dto.getCustomerIdName() + "%"));
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