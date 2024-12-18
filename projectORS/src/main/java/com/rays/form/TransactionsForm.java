package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.TransactionsDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class TransactionsForm extends BaseForm {

	@NotEmpty(message = "Please enter description")
	private String description;

	@NotEmpty(message = "Please enter transaction Date")
	@ValidDate(message = "Invalid date format or value")
	private String transactionDate;

	private String transactionTypeName;

	@NotEmpty(message = "Please enter transactionTypeId")
	@ValidLong(message = "Invalid input for transaction Type id", allowEmpty = true)
	@Min(value = 1, message = "transactionTypeId should be greater than 0")
	private String transactionTypeId;

	private String accountIdName;

	@NotEmpty(message = "Please enter accountId")
	@ValidLong(message = "Invalid input for accountId", allowEmpty = true)
	@Min(value = 1, message = "accountId should be greater than 0")
	private String accountIdId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	public String getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getAccountIdName() {
		return accountIdName;
	}

	public void setAccountIdName(String accountIdName) {
		this.accountIdName = accountIdName;
	}

	public String getAccountIdId() {
		return accountIdId;
	}

	public void setAccountIdId(String accountIdId) {
		this.accountIdId = accountIdId;
	}

	@Override
	public BaseDTO getDto() {
		TransactionsDTO dto = initDTO(new TransactionsDTO());

		dto.setDescription(description);

		if (transactionDate != null && !transactionDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(transactionDate);
				dto.setTransactionDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (transactionTypeId != null && !transactionTypeId.isEmpty()) {
			dto.setTransactionTypeId(Long.valueOf(transactionTypeId));
		}

		dto.setTransactionTypeName(transactionTypeName);

		if (accountIdId != null && !accountIdId.isEmpty()) {
			dto.setAccountIdId(Long.valueOf(accountIdId));
		}

		dto.setAccountIdName(accountIdName);

		return dto;
	}

}
