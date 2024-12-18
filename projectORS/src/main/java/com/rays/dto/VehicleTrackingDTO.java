package com.rays.dto;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_VEHICLETRACKING")
public class VehicleTrackingDTO extends BaseDTO {

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAT")
	private double lat;	

	@Column(name = "LONGITUDE")
	private double longitude;

	@Column(name = "DATE")
	private Date date;

	@Column(name = "VEHICLEID_NAME", length = 20)
	private String vehicleIdName = null;

	@Column(name = "VEHICLEID_ID")
	private Long vehicleIdId;

	@Column(name = "ORG_NAME", length = 50)
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
