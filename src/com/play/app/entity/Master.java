package com.play.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "masterType", "param_key" }))
@Entity
public class Master extends BaseEntity {

	@Column(length = 32, nullable = false)
	private String masterType;

	@Column(name = "param_key", length = 32, nullable = false)
	private String key;

	@Column(name = "param_value", length = 256, nullable = false)
	private String value;

	@Column(length = 256)
	private String description;

	@Column(nullable = false)
	private Boolean enabled;

	public String getMasterType() {
		return masterType;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}