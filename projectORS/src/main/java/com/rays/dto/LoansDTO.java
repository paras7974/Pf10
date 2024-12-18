package com.rays.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_LOANS")
public class LoansDTO extends BaseDTO {

	@Column(name = "LOAN_AMOUNT")
	private Long loanAmount;

	@Column(name = "INTEREST_RATE")
	private double interestRate;

	@Column(name = "LOAN_START_DATE")
	private Date loanStartDate;

	@Column(name = "CUSTOMER_NAME", length = 20)
	private String customerIdName = null;

	@Column(name = "CUSTOMER_ID")
	private Long customerIdId;

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getCustomerIdName() {
		return customerIdName;
	}

	public void setCustomerIdName(String customerIdName) {
		this.customerIdName = customerIdName;
	}

	public Long getCustomerIdId() {
		return customerIdId;
	}

	public void setCustomerIdId(Long customerIdId) {
		this.customerIdId = customerIdId;
	}

	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "orgName";
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "orgName";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return "orgName";
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "orgName";
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("loanAmount", "asc");
		map.put("customerIdName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("modifiedBy", modifiedBy);
		return map;
	}

}
