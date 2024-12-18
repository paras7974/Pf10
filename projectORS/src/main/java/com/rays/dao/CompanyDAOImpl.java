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
import com.rays.dto.CompanyDTO;
import com.rays.dto.ExperienceDTO;

@Repository
public class CompanyDAOImpl extends BaseDAOImpl<CompanyDTO> implements CompanyDAOInt {

	@Override
	public Class<CompanyDTO> getDTOClass() {
		return CompanyDTO.class;
	}

	@Autowired
	ExperienceDAOInt experienceDao;

	@Override
	protected void populate(CompanyDTO dto, UserContext userContext) {
		if (dto.getExperienceId() != null && dto.getExperienceId() > 0) {
			ExperienceDTO experienceDto = experienceDao.findByPK(dto.getExperienceId(), userContext);
			dto.setExperienceName(experienceDto.getName());
			System.out.println(dto.getExperienceName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(CompanyDTO dto, CriteriaBuilder builder, Root<CompanyDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isZeroNumber(dto.getMobile())) {
			whereCondition.add(builder.equal(qRoot.get("mobile"), dto.getMobile()));
		}

		if (isNotNull(dto.getDateOfJoining())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getDateOfJoining();

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
			Predicate datePredicate = builder.between(qRoot.get("dateOfJoining"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getExperienceId())) {
			whereCondition.add(builder.equal(qRoot.get("experienceId"), dto.getExperienceId()));
		}

		if (!isEmptyString(dto.getExperienceName())) {
			whereCondition.add(builder.like(qRoot.get("experienceName"), dto.getExperienceName() + "%"));
		}

		return whereCondition;
	}
	
}