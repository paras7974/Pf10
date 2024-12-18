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
import com.rays.dto.ModeDTO;
import com.rays.dto.TransportationDTO;

@Repository
public class TransportationDAOImpl extends BaseDAOImpl<TransportationDTO> implements TransportationDAOInt {

	@Override
	public Class<TransportationDTO> getDTOClass() {
		return TransportationDTO.class;
	}

	@Autowired
	ModeDAOInt modeDao;

	@Override
	protected void populate(TransportationDTO dto, UserContext userContext) {
		if (dto.getModeId() != null && dto.getModeId() > 0) {
			ModeDTO modeDto = modeDao.findByPK(dto.getModeId(), userContext);
			dto.setModeName(modeDto.getName());
			System.out.println(dto.getModeName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(TransportationDTO dto, CriteriaBuilder builder,
			Root<TransportationDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getDescription())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (!isZeroNumber(dto.getCost())) {
			whereCondition.add(builder.equal(qRoot.get("cost"), dto.getCost()));
		}

		if (isNotNull(dto.getDate())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getDate();

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
			Predicate datePredicate = builder.between(qRoot.get("date"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getModeId())) {
			whereCondition.add(builder.equal(qRoot.get("modeId"), dto.getModeId()));
		}

		if (!isEmptyString(dto.getModeName())) {
			whereCondition.add(builder.like(qRoot.get("modeName"), dto.getModeName() + "%"));
		}

		return whereCondition;
	}
}