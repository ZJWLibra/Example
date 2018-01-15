package com.jw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 区
 * 
 * @author Zeng
 *
 */
public class District implements Serializable {
	private static final long serialVersionUID = -7577685438105180765L;

	private Integer id;
	private String name;

	private City city;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(Set<Employee> employees) {
		Employees = employees;
	}

}
