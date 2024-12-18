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
import com.rays.dto.IssueDTO;
import com.rays.dto.StatusDTO;

@Repository
public class IssueDAOImpl extends BaseDAOImpl<IssueDTO> implements IssueDAOInt {

	@Override
	public Class<IssueDTO> getDTOClass() {
		return IssueDTO.class;
	}

	@Autowired
	StatusDAOInt statusDao;

	@Override
	protected void populate(IssueDTO dto, UserContext userContext) {
		if (dto.getStatusId() != null && dto.getStatusId() > 0) {
			StatusDTO statusDto = statusDao.findByPK(dto.getStatusId(), userContext);
			dto.setStatusName(statusDto.getName());
			System.out.println(dto.getStatusName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(IssueDTO dto, CriteriaBuilder builder, Root<IssueDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getTitle())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("title"), dto.getTitle() + "%"));
		}
		
		if (!isEmptyString(dto.getAssignTo())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("assignTo"), dto.getAssignTo() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
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

		if (!isZeroNumber(dto.getStatusId())) {
			whereCondition.add(builder.equal(qRoot.get("statusId"), dto.getStatusId()));
		}

		if (!isEmptyString(dto.getStatusName())) {
			whereCondition.add(builder.like(qRoot.get("statusName"), dto.getStatusName() + "%"));
		}

		return whereCondition;
	}
}
