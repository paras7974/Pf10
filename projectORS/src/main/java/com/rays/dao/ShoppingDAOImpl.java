package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.common.attachment.AttachmentDAOInt;
import com.rays.common.attachment.AttachmentDTO;
import com.rays.dto.PaymentDTO;
import com.rays.dto.ShoppingDTO;

@Repository
public class ShoppingDAOImpl extends BaseDAOImpl<ShoppingDTO> implements ShoppingDAOInt {


	@Autowired
	public AttachmentDAOInt attachmentService;
	
	@Override
	public Class<ShoppingDTO> getDTOClass() {
		return ShoppingDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ShoppingDTO dto, CriteriaBuilder builder, Root<ShoppingDTO> qRoot) {

		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getProductType())) {

			whereCondition.add(builder.like(qRoot.get("productType"), dto.getProductType() + "%"));
		}

		if (!isEmptyString(dto.getProductName())) {

			whereCondition.add(builder.like(qRoot.get("productName"), dto.getProductName() + "%"));
		}

		if (!isEmptyString(dto.getCustomerName())) {

			whereCondition.add(builder.like(qRoot.get("customerName"), dto.getCustomerName() + "%"));
		}

		if (!isEmptyString(dto.getPaymentName())) {

			whereCondition.add(builder.like(qRoot.get("paymentName"), dto.getPaymentName() + "%"));
		}
		if (!isEmptyString(dto.getShoppingId())) {

			whereCondition.add(builder.equal(qRoot.get("shoppingId"), dto.getShoppingId()));
		}

		if (!isZeroNumber(dto.getPaymentId())) {

			whereCondition.add(builder.equal(qRoot.get("paymentId"), dto.getPaymentId()));
		}

		if (isNotNull(dto.getShoppingDate())) {

			whereCondition.add(builder.equal(qRoot.get("shoppingDate"), dto.getShoppingDate()));
		}

		return whereCondition;
	}

	@Autowired
	PaymentDAOInt paymentDao;

	@Override
	protected void populate(ShoppingDTO dto, UserContext userContext) {
		if (dto.getPaymentId() != null && dto.getPaymentId() > 0) {
			PaymentDTO paymentDto = paymentDao.findByPK(dto.getPaymentId(), userContext);
			dto.setPaymentName(paymentDto.getName());
			System.out.println(dto.getPaymentName() + "RoleName-------");
		}
			if (dto.getId() != null && dto.getId() > 0) {
				ShoppingDTO userData = findByPK(dto.getId(), userContext);
				dto.setImageId(userData.getImageId());
			}
		}

		public void delete(ShoppingDTO dto, UserContext userContext) {
			super.delete(dto, userContext);
			Long id = dto.getImageId();
			System.out.println(id);
			AttachmentDTO attachmentDto = attachmentService.findByPK(id, userContext);
			attachmentService.delete(attachmentDto, userContext);
		}

}
