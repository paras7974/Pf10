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
import com.rays.dto.CategoryDTO;
import com.rays.dto.ProjectDTO;

@Repository
public class ProjectDAOImpl extends BaseDAOImpl<ProjectDTO> implements ProjectDAOInt {

	@Override
	public Class<ProjectDTO> getDTOClass() {
		return ProjectDTO.class;
	}

	@Autowired
	CategoryDAOInt categoryDao;

	@Override
	protected void populate(ProjectDTO dto, UserContext userContext) {
		if (dto.getCategoryId() != null && dto.getCategoryId() > 0) {
			CategoryDTO categoryDto = categoryDao.findByPK(dto.getCategoryId(), userContext);
			dto.setCategoryName(categoryDto.getName());
			System.out.println(dto.getCategoryName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(ProjectDTO dto, CriteriaBuilder builder, Root<ProjectDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getVersion())) {
			whereCondition.add(builder.equal(qRoot.get("version"), dto.getVersion()));
		}

		if (isNotNull(dto.getOpenDate())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getOpenDate();

			// Define start and end dates for the search day
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(searchDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0); // Start of the day
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date startDate = calendar.getTime();

			calendar.set(Calendar.HOUR_OF_DAY, 23); // End of the day
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date endDate = calendar.getTime();

			// Create predicate for date range
			Predicate datePredicate = builder.between(qRoot.get("openDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getCategoryId())) {
			whereCondition.add(builder.equal(qRoot.get("categoryId"), dto.getCategoryId()));
		}

		if (!isEmptyString(dto.getCategoryName())) {
			whereCondition.add(builder.like(qRoot.get("categoryName"), dto.getCategoryName() + "%"));
		}

		return whereCondition;
	}
}
