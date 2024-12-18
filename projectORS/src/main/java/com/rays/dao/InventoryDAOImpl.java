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
import com.rays.dto.InventoryDTO;
import com.rays.dto.ProductDTO;

@Repository
public class InventoryDAOImpl extends BaseDAOImpl<InventoryDTO> implements InventoryDAOInt {

	@Override
	public Class<InventoryDTO> getDTOClass() {
		return InventoryDTO.class;
	}

	@Autowired
	ProductDAOInt productDao;

	@Override
	protected void populate(InventoryDTO dto, UserContext userContext) {
		if (dto.getProductId() != null && dto.getProductId() > 0) {
			ProductDTO productDto = productDao.findByPK(dto.getProductId(), userContext);
			dto.setProductName(productDto.getName());
			System.out.println(dto.getProductName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(InventoryDTO dto, CriteriaBuilder builder, Root<InventoryDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getSupplierName())) {
			// Use 'like' operator for partial matching
			whereCondition.add(builder.like(qRoot.get("supplierName"), dto.getSupplierName() + "%"));
		}

		if (!isZeroNumber(dto.getQuantity())) {
			whereCondition.add(builder.equal(qRoot.get("quantity"), dto.getQuantity()));
		}

		if (isNotNull(dto.getLastUpdatedDate())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getLastUpdatedDate();

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
			Predicate datePredicate = builder.between(qRoot.get("lastUpdatedDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getProductId())) {
			whereCondition.add(builder.equal(qRoot.get("productId"), dto.getProductId()));
		}

		if (!isEmptyString(dto.getProductName())) {
			whereCondition.add(builder.like(qRoot.get("productName"), dto.getProductName() + "%"));
		}

		return whereCondition;
	}

}