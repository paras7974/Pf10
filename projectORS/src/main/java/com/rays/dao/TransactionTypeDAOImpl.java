package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.TransactionTypeDTO;

@Repository
public class TransactionTypeDAOImpl extends BaseDAOImpl<TransactionTypeDTO> implements TransactionTypeDAOInt {

	@Override
	public Class<TransactionTypeDTO> getDTOClass() {
		return TransactionTypeDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(TransactionTypeDTO dto, CriteriaBuilder builder, Root<TransactionTypeDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getTransactionTypeName())) {

			whereCondition.add(builder.like(qRoot.get("transactionTypeName"), dto.getTransactionTypeName() + "%"));
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