package com.jw.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 省
 * 
 * @author Zeng
 *
 */
public class Province implements Serializable {
	private static final long serialVersionUID = 6587163310306556185L;

	private Integer id;
	private String name;

	private Set<City> citys = new HashSet<>();

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

	public Set<City> getCitys() {
		return citys;
	}

	public void setCitys(Set<City> citys) {
		this.citys = citys;
	}

	public Set<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(Set<Employee> employees) {
		Employees = employees;
	}

}
