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
import com.rays.dto.AccountIdDTO;
import com.rays.dto.TransactionTypeDTO;
import com.rays.dto.TransactionsDTO;

@Repository
public class TransactionsDAOImpl extends BaseDAOImpl<TransactionsDTO> implements TransactionsDAOInt {

	@Override
	public Class<TransactionsDTO> getDTOClass() {
		return TransactionsDTO.class;
	}

	@Autowired
	TransactionTypeDAOInt transactionTypeDao;

	@Autowired
	AccountIdDAOInt accountIdDao;

	@Override
	protected void populate(TransactionsDTO dto, UserContext userContext) {
		if (dto.getTransactionTypeId() != null && dto.getTransactionTypeId() > 0) {
			TransactionTypeDTO transactionTypeDto = transactionTypeDao.findByPK(dto.getTransactionTypeId(),
					userContext);
			dto.setTransactionTypeName(transactionTypeDto.getName());
			System.out.println(dto.getTransactionTypeName() + "PriorityName-------");
		}
		
		if (dto.getAccountIdId() != null && dto.getAccountIdId() > 0) {
			AccountIdDTO accountIdDto = accountIdDao.findByPK(dto.getAccountIdId(), userContext);
			dto.setAccountIdName(accountIdDto.getName());
			System.out.println(dto.getAccountIdName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(TransactionsDTO dto, CriteriaBuilder builder,
			Root<TransactionsDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getDescription())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (isNotNull(dto.getTransactionDate())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getTransactionDate();

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
			Predicate datePredicate = builder.between(qRoot.get("transactionDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getTransactionTypeId())) {
			whereCondition.add(builder.equal(qRoot.get("transactionTypeId"), dto.getTransactionTypeId()));
		}

		if (!isEmptyString(dto.getTransactionTypeName())) {
			whereCondition.add(builder.like(qRoot.get("transactionTypeName"), dto.getTransactionTypeName() + "%"));
		}

		if (!isZeroNumber(dto.getAccountIdId())) {
			whereCondition.add(builder.equal(qRoot.get("AccountIdId"), dto.getAccountIdId()));
		}

		if (!isEmptyString(dto.getAccountIdName())) {
			whereCondition.add(builder.like(qRoot.get("accountIdName"), dto.getAccountIdName() + "%"));
		}

		return whereCondition;
	}
}
