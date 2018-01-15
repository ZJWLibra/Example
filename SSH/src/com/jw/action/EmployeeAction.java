package com.jw.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jw.common.EasyUIResult;
import com.jw.common.Pager;
import com.jw.common.SSHResult;
import com.jw.model.City;
import com.jw.model.Department;
import com.jw.model.District;
import com.jw.model.Employee;
import com.jw.model.Province;
import com.jw.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("ssh")
@Namespace("/employee")
public class EmployeeAction extends ActionSupport {
	private static final long serialVersionUID = -3524805005433992265L;

	@Autowired
	private EmployeeService employeeService;

	private Integer page;
	private Integer rows;

	private EasyUIResult<Employee> easyUIResult;
	private SSHResult sshResult;

	// 部门List
	private List<Department> departmentList;
	// 省List
	private List<Province> provinceList;
	// 市List
	private List<City> cityList;
	// 区List
	private List<District> districtList;
	// 接收员工参数
	private Employee employee;

	private File employeeExcel;
	private String employeeExcelFileName;

	@Action(value = "list", results = { @Result(name = "json", type = "json", params = { "root", "easyUIResult" }) })
	public String list() {
		Pager pager = new Pager();
		pager.setPageNo(page);
		pager.setPageSize(rows);

		easyUIResult = employeeService.List(pager, new Employee());
		return "json";
	}

	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	@Action(value = "listDepartmentAll", results = {
			@Result(name = "json", type = "json", params = { "root", "departmentList" }) })
	public String listDepartmentAll() {
		departmentList = employeeService.listDepartmentAll();
		return "json";
	}

	/**
	 * 查询所有省
	 * 
	 * @return
	 */
	@Action(value = "listProvince", results = {
			@Result(name = "json", type = "json", params = { "root", "provinceList" }) })
	public String listProvince() {
		provinceList = employeeService.listProvince();
		return "json";
	}

	/**
	 * 根据省id查询对应的市
	 * 
	 * @return
	 */
	@Action(value = "listCityByProvinceId", results = {
			@Result(name = "json", type = "json", params = { "root", "cityList" }) })
	public String listCityByProvinceId() {
		cityList = employeeService.listCityByProvinceId(employee.getProvince().getId());
		return "json";
	}

	/**
	 * 根据市id查询对应的区
	 * 
	 * @return
	 */
	@Action(value = "listDistrictByCityId", results = {
			@Result(name = "json", type = "json", params = { "root", "districtList" }) })
	public String listDistrictByCityId() {
		districtList = employeeService.listDistrictByCityId(employee.getCity().getId());
		return "json";
	}

	@Action(value = "insert", results = { @Result(name = "json", type = "json", params = { "root", "sshResult" }) })
	public String insert() {
		sshResult = employeeService.insert(employee);
		return "json";
	}

	/**
	 * 导出excel
	 * 
	 * @return
	 */
	@Action(value = "exportExcel")
	public String exportExcel() {
		ServletOutputStream outputStream = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition",
					"attchment;filename=" + new String("员工列表.xls".getBytes(), "ISO-8859-1"));
			outputStream = response.getOutputStream();

			employeeService.exportExcel(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return NONE;
	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 */
	@Action(value = "importExcel", results = { @Result(name = "list", location = "/WEB-INF/views/employee/index.jsp") })
	public String importExcel() {
		if (employeeExcelFileName != null) {
			String suffix = employeeExcelFileName.substring(employeeExcelFileName.lastIndexOf(".") + 1);
			if (suffix.equals("xls")) {
				employeeService.importExcel(employeeExcel);
			}
		}
		
		return "list";
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public EasyUIResult<Employee> getEasyUIResult() {
		return easyUIResult;
	}

	public void setEasyUIResult(EasyUIResult<Employee> easyUIResult) {
		this.easyUIResult = easyUIResult;
	}

	public SSHResult getSshResult() {
		return sshResult;
	}

	public void setSshResult(SSHResult sshResult) {
		this.sshResult = sshResult;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<District> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<District> districtList) {
		this.districtList = districtList;
	}

	public File getEmployeeExcel() {
		return employeeExcel;
	}

	public void setEmployeeExcel(File employeeExcel) {
		this.employeeExcel = employeeExcel;
	}

	public String getEmployeeExcelFileName() {
		return employeeExcelFileName;
	}

	public void setEmployeeExcelFileName(String employeeExcelFileName) {
		this.employeeExcelFileName = employeeExcelFileName;
	}

}
