package com.jw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Department implements Serializable {
	private static final long serialVersionUID = -8279670903341932247L;

	private Integer id;
	private String name;

	private Set<Employee> employees = new HashSet<>();

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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
