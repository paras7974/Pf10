package com.rays.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_TRANSACTION")
public class TransactionsDTO extends BaseDTO {

	@Column(name = "DESCRIPTION", length = 50)
	private String description;

	@Column(name = "TRANSACTIONTYPE_NAME", length = 20)
	private String transactionTypeName = null;

	@Column(name = "TRANSACTIONTYPE_ID")
	private Long transactionTypeId;

	@Column(name = "TRANSACTION_DATE")
	private Date transactionDate;

	@Column(name = "ACCOUNTID_NAME", length = 20)
	private String accountIdName = null;

	@Column(name = "ACCOUNTID_ID")
	private Long accountIdId;

	@Column(name = "ORG_NAME", length = 50)
	private String orgName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	public Long getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getAccountIdName() {
		return accountIdName;
	}

	public void setAccountIdName(String accountIdName) {
		this.accountIdName = accountIdName;
	}

	public Long getAccountIdId() {
		return accountIdId;
	}

	public void setAccountIdId(Long accountIdId) {
		this.accountIdId = accountIdId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
		map.put("description", "asc");
		map.put("accountIdName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("modifiedBy", modifiedBy);
		return map;
	}

}
