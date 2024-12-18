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
import com.rays.dto.CustomerDTO;
import com.rays.dto.GenderDTO;

@Repository
public class CustomerDAOImpl extends BaseDAOImpl<CustomerDTO> implements CustomerDAOInt {

	@Override
	public Class<CustomerDTO> getDTOClass() {
		return CustomerDTO.class;
	}

	@Autowired
	GenderDAOInt genderDao;

	@Override
	protected void populate(CustomerDTO dto, UserContext userContext) {
		if (dto.getGenderId() != null && dto.getGenderId() > 0) {
			GenderDTO genderDto = genderDao.findByPK(dto.getGenderId(), userContext);
			dto.setGenderName(genderDto.getName());
			System.out.println(dto.getGenderName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(CustomerDTO dto, CriteriaBuilder builder, Root<CustomerDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isZeroNumber(dto.getPhoneNumber())) {
			whereCondition.add(builder.equal(qRoot.get("phoneNumber"), dto.getPhoneNumber()));
		}

		if (isNotNull(dto.getDateOfBirth())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getDateOfBirth();

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
			Predicate datePredicate = builder.between(qRoot.get("dateOfBirth"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getGenderId())) {
			whereCondition.add(builder.equal(qRoot.get("genderId"), dto.getGenderId()));
		}

		if (!isEmptyString(dto.getGenderName())) {
			whereCondition.add(builder.like(qRoot.get("genderName"), dto.getGenderName() + "%"));
		}

		return whereCondition;

	}
}
