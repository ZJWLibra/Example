package com.jw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 市
 * 
 * @author Zeng
 *
 */
public class City implements Serializable {
	private static final long serialVersionUID = -4547170436041559272L;

	private Integer id;
	private String name;

	private Province province;
	private Set<District> districts = new HashSet<>();
	private Set<Employee> Employees = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Set<District> getDistricts() {
		return districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

	public Set<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(Set<Employee> employees) {
		Employees = employees;
	}

}
