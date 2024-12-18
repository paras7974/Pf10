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
import com.rays.dto.OwnerDTO;
import com.rays.dto.VehicleIdDTO;

@Repository
public class OwnerDAOImpl extends BaseDAOImpl<OwnerDTO> implements OwnerDAOInt {

	@Override
	public Class<OwnerDTO> getDTOClass() {
		return OwnerDTO.class;
	}

	@Autowired
	VehicleIdDAOInt vehicleIdDao;

	@Override
	protected void populate(OwnerDTO dto, UserContext userContext) {
		if (dto.getVehicleIdId() != null && dto.getVehicleIdId() > 0) {
			VehicleIdDTO vehicleIdDto = vehicleIdDao.findByPK(dto.getVehicleIdId(), userContext);
			dto.setVehicleIdName(vehicleIdDto.getName());
			System.out.println(dto.getVehicleIdName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(OwnerDTO dto, CriteriaBuilder builder, Root<OwnerDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isZeroNumber(dto.getInsuranceAmount())) {
			whereCondition.add(builder.equal(qRoot.get("insuranceAmount"), dto.getInsuranceAmount()));
		}

		if (isNotNull(dto.getDob())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getDob();

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
			Predicate datePredicate = builder.between(qRoot.get("dob"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getVehicleIdId())) {
			whereCondition.add(builder.equal(qRoot.get("vehicleIdId"), dto.getVehicleIdId()));
		}

		if (!isEmptyString(dto.getVehicleIdName())) {
			whereCondition.add(builder.like(qRoot.get("vehicleIdName"), dto.getVehicleIdName() + "%"));
		}

		return whereCondition;
	}
}
