package com.jw.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.jw.model.City;
import com.jw.model.Department;
import com.jw.model.District;
import com.jw.model.Employee;
import com.jw.model.Province;

public interface EmployeeService extends BaseService<Employee> {
	List<Department> listDepartmentAll();
	
	List<Province> listProvince();
	
	List<City> listCityByProvinceId(Integer id);
	
	List<District> listDistrictByCityId(Integer id);

	void exportExcel(ServletOutputStream outputStream);

	void importExcel(File employeeExcel);
}
