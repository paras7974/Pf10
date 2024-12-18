package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.OrderDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class OrderForm extends BaseForm {

	@NotNull(message = "Please enter quantity")
	@Pattern(regexp = "^(?:1000|[1-9]\\d{0,2})$", message = "Quantity must be a number between 1 and 1000")
	private String quantity;

	@NotNull(message = "Please enter order Date")
	@ValidDate(message = "Invalid date format or value")
	private String orderDate;

	@NotEmpty(message = "Please enter product name")
	@Size(max = 20, message = "Product name must be up to 20 characters")
	@Pattern(regexp = "^[A-Z][a-zA-Z]{0,19}$", message = "Invalid product name")
	private String productName;

	private String customerName;

	@NotEmpty(message = "Please enter customerId")
	@ValidLong(message = "Invalid input for customer id", allowEmpty = true)
	@Min(value = 1, message = "customerId should be greater than 0")
	private String customerId;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public BaseDTO getDto() {
		OrderDTO dto = initDTO(new OrderDTO());

		if (quantity != null && !quantity.isEmpty()) {
			dto.setQuantity(Long.valueOf(quantity)); // Convert String to Long
		}
		if (orderDate != null && !orderDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(orderDate);
				dto.setOrderDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (customerId != null && !customerId.isEmpty()) {
			try {
				dto.setCustomerId(Long.valueOf(customerId)); // Convert String to Long
			} catch (NumberFormatException e) {
				// Handle conversion error if productId is not a valid Long
				e.printStackTrace();
			}
		}
		dto.setCustomerName(customerName);

		dto.setProductName(productName);

		return dto;
	}

}
