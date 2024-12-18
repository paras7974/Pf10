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
import com.rays.dto.CustomerIdDTO;
import com.rays.dto.LoansDTO;

@Repository
public class LoansDAOImpl extends BaseDAOImpl<LoansDTO> implements LoansDAOInt {

	@Override
	public Class<LoansDTO> getDTOClass() {
		return LoansDTO.class;
	}

	@Autowired
	CustomerIdDAOInt customerIdDao;

	@Override
	protected void populate(LoansDTO dto, UserContext userContext) {
		if (dto.getCustomerIdId() != null && dto.getCustomerIdId() > 0) {
			CustomerIdDTO customerIdDto = customerIdDao.findByPK(dto.getCustomerIdId(), userContext);
			dto.setCustomerIdName(customerIdDto.getName());
			System.out.println(dto.getCustomerIdName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(LoansDTO dto, CriteriaBuilder builder,
			Root<LoansDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isZeroNumber(dto.getLoanAmount())) {
			whereCondition.add(builder.equal(qRoot.get("loanAmount"), dto.getLoanAmount()));
		}
		
		if (!isZeroNumber(dto.getInterestRate())) {
			whereCondition.add(builder.equal(qRoot.get("interestRate"), dto.getInterestRate()));
		}

		if (isNotNull(dto.getLoanStartDate())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getLoanStartDate();

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
			Predicate datePredicate = builder.between(qRoot.get("loanStartDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getCustomerIdId())) {
			whereCondition.add(builder.equal(qRoot.get("customerIdId"), dto.getCustomerIdId()));
		}

		if (!isEmptyString(dto.getCustomerIdName())) {
			whereCondition.add(builder.like(qRoot.get("customerIdName"), dto.getCustomerIdName() + "%"));
		}

		return whereCondition;
	}

}