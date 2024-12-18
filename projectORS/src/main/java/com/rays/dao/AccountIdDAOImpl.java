package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.AccountIdDTO;

@Repository
public class AccountIdDAOImpl extends BaseDAOImpl<AccountIdDTO> implements AccountIdDAOInt {

	@Override
	public Class<AccountIdDTO> getDTOClass() {
		return AccountIdDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(AccountIdDTO dto, CriteriaBuilder builder, Root<AccountIdDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getAccountIdName())) {

			whereCondition.add(builder.like(qRoot.get("accountIdName"), dto.getAccountIdName() + "%"));
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