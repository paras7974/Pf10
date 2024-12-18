package com.rays.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_SHOPPING")
public class ShoppingDTO extends BaseDTO {

	public static final String ACTIVE = "Activate";
	public static final String DEACTIVE = "deactivate";
	public static final String LOCKED = "locked";

	@Column(name = "SHOPPING_ID", length = 50)
	private String shoppingId;

	@Column(name = "PRODUCT_TYPE", length = 50)
	private String productType;

	@Column(name = "PRODUCT_NAME", length = 50)
	private String productName;

	@Column(name = "SHOPPING_DATE")
	private Date shoppingDate;

	@Column(name = "CUSTOMER_NAME", length = 50)
	private String customerName;

	@Column(name = "AMOUNT")
	private Long amount;

	@Column(name = "IMAGE_ID")
	private Long imageId;

	@Column(name = "PAYMENT_Name", length = 20)
	private String paymentName = null;

	@Column(name = "PAYMENT_ID")
	private Long paymentId;

	public String getName() {
		return productName + " " + productType;
	}

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

	public Date getShoppingDate() {
		return shoppingDate;
	}

	public void setShoppingDate(Date shoppingDate) {
		this.shoppingDate = shoppingDate;
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

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return productName;
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("productType", "asc");
		map.put("productName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("shoppingId", shoppingId);
		return map;
	}

	@Override
	public String getUniqueKey() {
		return "shoppingId";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return shoppingId;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Shopping Id";
	}

}
