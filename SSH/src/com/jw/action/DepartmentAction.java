package com.jw.action;

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
import com.jw.model.Department;
import com.jw.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("ssh")
@Namespace("/department")
public class DepartmentAction extends ActionSupport {
	private static final long serialVersionUID = -3724180762435904307L;

	@Autowired
	private DepartmentService departmentService;

	private EasyUIResult<Department> easyUIResult;

	private Integer page; // 要查询的页数
	private Integer rows; // 每页显示的数目

	private Department department; // 查询参数封装

	private SSHResult sshResult;

	@Action(value = "list", results = { @Result(name = "json", type = "json", params = { "root", "easyUIResult" }) })
	public String list() {
		Pager pager = new Pager();
		pager.setPageNo(page);
		pager.setPageSize(rows);
		easyUIResult = departmentService.List(pager, department);
		return "json";
	}

	@Action(value = "insert", results = { @Result(name = "json", type = "json", params = { "root", "sshResult" }) })
	public String insert() {
		sshResult = departmentService.insert(department);
		return "json";
	}
	
	@Action(value = "update", results = { @Result(name = "json", type = "json", params = { "root", "sshResult" }) })
	public String update() {
		sshResult = departmentService.update(department);
		return "json";
	}

	public EasyUIResult<Department> getEasyUIResult() {
		return easyUIResult;
	}

	public void setEasyUIResult(EasyUIResult<Department> easyUIResult) {
		this.easyUIResult = easyUIResult;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public SSHResult getSshResult() {
		return sshResult;
	}

	public void setSshResult(SSHResult sshResult) {
		this.sshResult = sshResult;
	}

}
