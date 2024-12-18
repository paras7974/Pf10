package com.rays.dto;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_FIELD")
public class FieldDTO extends BaseDTO {

	@Column(name = "LABELL")
	private String labell;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "TYPE_NAME", length = 20)
	private String typeName = null;

	@Column(name = "TYPE_ID")
	private Long typeId;

	@Column(name = "ORG_NAME", length = 50)
	private String orgName;

	public String getLabell() {
		return labell;
	}

	public void setLabell(String labell) {
		this.labell = labell;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
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
		return "labell";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return labell +"";
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "labell";
	}

	@Override
	public LinkedHashMap<String, String> orderBY() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("labell", "asc");
		map.put("typeName", "asc");
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> uniqueKeys() {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("labell", labell);
		return map;
	}

}
