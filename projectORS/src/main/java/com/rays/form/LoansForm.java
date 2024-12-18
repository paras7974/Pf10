package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.LoansDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidDouble;
import com.rays.validation.ValidLong;

public class LoansForm extends BaseForm {

	@NotEmpty(message = "Please enter loan Amount")
	@Pattern(regexp = "^(50000|[5-9]\\d{4}|[1-9]\\d{5}|1000000)$", message = "Invalid input for loan amount. Please enter a value between 50000 and 1000000")
	private String loanAmount;

	@NotNull(message = "Please enter interest Rate")
	@ValidDouble(message = "Invalid value for interest Rate")
	private String interestRate;

	@NotNull(message = "Please enter loan Start Date")
	@ValidDate(message = "Invalid date format or value")
	private String loanStartDate;

	private String customerIdName;

	@NotNull(message = "Please enter customerId")
	@ValidLong(message = "Invalid input for id")
	@Min(value = 1, message = "customerId should be greater than 0")
	private String customerIdId;

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getCustomerIdName() {
		return customerIdName;
	}

	public void setCustomerIdName(String customerIdName) {
		this.customerIdName = customerIdName;
	}

	public String getCustomerIdId() {
		return customerIdId;
	}

	public void setCustomerIdId(String customerIdId) {
		this.customerIdId = customerIdId;
	}

	@Override
	public BaseDTO getDto() {
		LoansDTO dto = initDTO(new LoansDTO());

		if (loanStartDate != null && !loanStartDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(loanStartDate);
				dto.setLoanStartDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (loanAmount != null && !loanAmount.isEmpty()) {
			dto.setLoanAmount(Long.valueOf(loanAmount));
		}

		if (interestRate != null && !interestRate.isEmpty()) {
			dto.setInterestRate(Double.parseDouble(interestRate));
		}

		if (customerIdId != null && !customerIdId.isEmpty()) {
			dto.setCustomerIdId(Long.valueOf(customerIdId));
		}

		dto.setCustomerIdName(customerIdName);

		return dto;
	}

}
