package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CategoryDTO;

@Repository
public class CategoryDAOImpl extends BaseDAOImpl<CategoryDTO> implements CategoryDAOInt {

	@Override
	public Class<CategoryDTO> getDTOClass() {
		return CategoryDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CategoryDTO dto, CriteriaBuilder builder, Root<CategoryDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getCategoryName())) {

			whereCondition.add(builder.like(qRoot.get("categoryName"), dto.getCategoryName() + "%"));
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