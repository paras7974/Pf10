package com.rays.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_ROUTE")
public class RouteDTO extends BaseDTO {

	@Column(name = "NAME")
	private String name;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "ALLOWED_SPEED")
	private Long allowedSpeed;

	@Column(name = "VEHICLEID_NAME", length = 20)
	private String vehicleIdName = null;

	@Column(name = "VEHICLEID_ID")
	private Long vehicleIdId;

	@Column(name = "ORG_NAME", length = 50)
	private String orgName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getAllowedSpeed() {
		return allowedSpeed;
	}

	public void setAllowedSpeed(Long allowedSpeed) {
		this.allowedSpeed = allowedSpeed;
	}

	public String getVehicleIdName() {
		return vehicleIdName;
	}

	public void setVehicleIdName(String vehicleIdName) {
		this.vehicleIdName = vehicleIdName;
	}

	public Long getVehicleIdId() {
		return vehicleIdId;
	}

	public void setVehicleIdId(Long vehicleIdId) {
		this.vehicleIdId = vehicleIdId;
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
		map.put("name", "asc");
		map.put("vehicleIdName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("modifiedBy", modifiedBy);
		return map;
	}

}
