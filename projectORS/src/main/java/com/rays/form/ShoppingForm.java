package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.ShoppingDTO;

public class ShoppingForm extends BaseForm {

	@NotEmpty(message = "please enter shoppingId")
	private String shoppingId;

	@NotEmpty(message = "please enter productType")
	private String productType;

	@NotEmpty(message = "please enter productName")
	private String productName;

	@NotEmpty(message = "please enter customerName")
	private String customerName;

	@NotNull(message = "please enter shoppingDate")
	private Date shoppingDate;

	private Long imageId;

	@NotNull(message = "please enter amount")
	private Long amount;

	private String paymentName = null;

	@NotNull(message = "please enter paymentId")
	@Min(1)
	private Long paymentId;

	public String getShoppingId() {
		return shoppingId;
	}

	public void setShoppingId(String shoppingId) {
		this.shoppingId = shoppingId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getShoppingDate() {
		return shoppingDate;
	}

	public void setShoppingDate(Date shoppingDate) {
		this.shoppingDate = shoppingDate;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getName() {
		return productName + " " + productType;
	}

	public void setName(String productName) {
		this.productName = productName;
	}

	@Override
	public BaseDTO getDto() {

		ShoppingDTO dto = initDTO(new ShoppingDTO());
		dto.setShoppingId(shoppingId);
		dto.setProductName(productName);
		dto.setProductType(productType);
		dto.setCustomerName(customerName);
		dto.setShoppingDate(shoppingDate);
		dto.setPaymentId(paymentId);
		dto.setPaymentName(paymentName);
		dto.setAmount(amount);
		dto.setImageId(imageId);

		return dto;
	}

}
