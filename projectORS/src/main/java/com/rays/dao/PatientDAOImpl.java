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
import com.rays.dto.DiseaseDTO;
import com.rays.dto.PatientDTO;

@Repository
public class PatientDAOImpl extends BaseDAOImpl<PatientDTO> implements PatientDAOInt {

	@Override
	public Class<PatientDTO> getDTOClass() {
		return PatientDTO.class;
	}

	@Autowired
	DiseaseDAOInt diseaseDao;

	@Override
	protected void populate(PatientDTO dto, UserContext userContext) {
		if (dto.getDiseaseId() != null && dto.getDiseaseId() > 0) {
			DiseaseDTO diseaseDto = diseaseDao.findByPK(dto.getDiseaseId(), userContext);
			dto.setDiseaseName(diseaseDto.getName());
			System.out.println(dto.getDiseaseName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(PatientDTO dto, CriteriaBuilder builder, Root<PatientDTO> qRoot) {
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

		if (isNotNull(dto.getDateOfVisit())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getDateOfVisit();

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
			Predicate datePredicate = builder.between(qRoot.get("dateOfVisit"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getDiseaseId())) {
			whereCondition.add(builder.equal(qRoot.get("diseaseId"), dto.getDiseaseId()));
		}

		if (!isEmptyString(dto.getDiseaseName())) {
			whereCondition.add(builder.like(qRoot.get("diseaseName"), dto.getDiseaseName() + "%"));
		}

		return whereCondition;
	}

}