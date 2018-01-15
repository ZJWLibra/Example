package com.jw.service;

import com.jw.common.StatementResult;
import com.jw.model.Department;

public interface DepartmentService extends BaseService<Department> {
	StatementResult departmentColumnar();
	
	StatementResult departmentPie();
}
