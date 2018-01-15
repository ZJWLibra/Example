package com.jw.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jw.common.StatementResult;
import com.jw.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("ssh")
@Namespace("/statement")
public class StatementAction extends ActionSupport {
	private static final long serialVersionUID = -7703879435680798960L;

	@Autowired
	private DepartmentService departmentService;

	private StatementResult statementResult;

	/**
	 * 每个部门对应人数柱状图
	 * 
	 * @return
	 */
	@Action(value = "departmentColumnar", results = {
			@Result(name = "json", type = "json", params = { "root", "statementResult" }) })
	public String departmentColumnar() {
		statementResult = departmentService.departmentColumnar();
		return "json";
	}
	
	/**
	 * 每个部门对应人数饼图
	 * 
	 * @return
	 */
	@Action(value = "departmentPie", results = {
			@Result(name = "json", type = "json", params = { "root", "statementResult" }) })
	public String departmentPie() {
		statementResult = departmentService.departmentPie();
		return "json";
	}

	public StatementResult getStatementResult() {
		return statementResult;
	}

	public void setStatementResult(StatementResult statementResult) {
		this.statementResult = statementResult;
	}

}
