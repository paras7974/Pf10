package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.InventoryDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class InventoryForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@Size(max = 20, message = "please input in 20 characters only")
	@NotEmpty(message = "Please enter supplier Name")
	private String supplierName;

	@NotEmpty(message = "Please enter quantity")
	@Pattern(regexp = "^(?:1000|[1-9]\\d{0,2})$", message = "Quantity must be a number between 1 and 1000")
    @Pattern(regexp = "[0-9]+", message = "Quantity must be a number")
	@Size(max = 10, message = "this field contain 10 digit only")
	private String quantity;

	@NotEmpty(message = "Please enter last Date Updated")
	@ValidDate(message = "Invalid date format or value")
	private String lastUpdatedDate;

	private String productName;

	@NotEmpty(message = "Please enter productId")
	@ValidLong(message = "Invalid input for product id", allowEmpty = true)
	@Min(value = 1, message = "productId should be greater than 0")
	private String productId;

	// Getters and setters
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public BaseDTO getDto() {
		InventoryDTO dto = initDTO(new InventoryDTO());

		dto.setSupplierName(supplierName);

		if (lastUpdatedDate != null && !lastUpdatedDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(lastUpdatedDate);
				dto.setLastUpdatedDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (quantity != null && !quantity.isEmpty()) {
			dto.setQuantity(Long.valueOf(quantity));
		}

		if (productId != null && !productId.isEmpty()) {
			dto.setProductId(Long.valueOf(productId));
		}

		dto.setProductName(productName);

		return dto;
	}

}
