package com.jw.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("ssh")
@Namespace("/")
public class PageAction extends ActionSupport {
	private static final long serialVersionUID = 6791736586201221139L;

	@Action(value = "index", results = {@Result(name = "index", location = "/WEB-INF/views/index.jsp")})
	public String toIndex() {
		return "index";
	}
	
	@Action(value = "toDepartmentIndex", results = {@Result(name = "index", location = "/WEB-INF/views/department/index.jsp")})
	public String toDepartmentIndex() {
		return "index";
	}
	
	@Action(value = "toEmployeeIndex", results = {@Result(name = "index", location = "/WEB-INF/views/employee/index.jsp")})
	public String toEmployeeIndex() {
		return "index";
	}
	
	@Action(value = "toColumnarIndex", results = {@Result(name = "index", location = "/WEB-INF/views/statement/columnarIndex.jsp")})
	public String toColumnarIndex() {
		return "index";
	}
	
	@Action(value = "toPieIndex", results = {@Result(name = "index", location = "/WEB-INF/views/statement/pieIndex.jsp")})
	public String toPieIndex() {
		return "index";
	}
}
