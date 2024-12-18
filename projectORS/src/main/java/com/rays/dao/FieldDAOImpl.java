package com.rays.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.FieldDTO;
import com.rays.dto.SupplierDTO;
import com.rays.dto.TypeDTO;

@Repository
public class FieldDAOImpl extends BaseDAOImpl<FieldDTO> implements FieldDAOInt {

	@Override
	public Class<FieldDTO> getDTOClass() {
		return FieldDTO.class;
	}

	@Autowired
	TypeDAOInt typeDao;

	@Override
	protected void populate(FieldDTO dto, UserContext userContext) {
		if (dto.getTypeId() != null && dto.getTypeId() > 0) {
			TypeDTO typeDto = typeDao.findByPK(dto.getTypeId(), userContext);
			dto.setTypeName(typeDto.getName());
			System.out.println(dto.getTypeName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(FieldDTO dto, CriteriaBuilder builder, Root<FieldDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getLabell())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("labell"), dto.getLabell() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (!isEmptyString(dto.getActive())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("active"), dto.getActive() + "%"));
		}

		if (!isZeroNumber(dto.getTypeId())) {
			whereCondition.add(builder.equal(qRoot.get("typeId"), dto.getTypeId()));
		}

		if (!isEmptyString(dto.getTypeName())) {
			whereCondition.add(builder.like(qRoot.get("typeName"), dto.getTypeName() + "%"));
		}

		return whereCondition;
	}
}
