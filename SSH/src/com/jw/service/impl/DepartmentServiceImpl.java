package com.jw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.common.EasyUIResult;
import com.jw.common.Pager;
import com.jw.common.PieData;
import com.jw.common.SSHResult;
import com.jw.common.StatementResult;
import com.jw.dao.DepartmentDao;
import com.jw.model.Department;
import com.jw.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public SSHResult insert(Department t) {
		SSHResult result = new SSHResult();
		try {
			departmentDao.insert(t);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("error");
			result.setMsg("新增失败");
			return result;
		}
		
		result.setResult("success");
		return result;
	}

	@Override
	public SSHResult update(Department t) {
		SSHResult result = new SSHResult();
		try {
			departmentDao.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("error");
			result.setMsg("修改失败");
		}
		
		result.setResult("success");
		return result;
	}

	@Override
	public SSHResult delete(Department t) {
		SSHResult result = new SSHResult();
		try {
			departmentDao.delete(t);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("error");
			result.setMsg("删除失败");
		}
		
		result.setResult("success");
		return result;
	}

	@Override
	public Department get(Integer id) {
		try {
			return departmentDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EasyUIResult<Department> List(Pager pager, Department t) {
		List<Department> list;
		try {
			list = departmentDao.list(pager, t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Long count;
		try {
			count = departmentDao.count(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		EasyUIResult<Department> result = new EasyUIResult<>();
		result.setTotal(count);
		result.setRows(list);
		
		return result;
	}

	public StatementResult departmentColumnar() {
		StatementResult result = new StatementResult();
		
		List<Department> list;
		try {
			// 查询所有部门
			list = departmentDao.list(new Pager(), new Department());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		String[] title = new String[list.size()];
		Integer[] data = new Integer[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			// 将每个部门名称放入数组中
			title[i] = list.get(i).getName();
			// 将每个部门对应的人数放入数组中
			data[i] = list.get(i).getEmployees().size();
		}
		
		result.setTitle(title);
		result.setColumnarData(data);
		
		return result;
	}

	@Override
	public StatementResult departmentPie() {
		StatementResult result = new StatementResult();
		List<Department> list;
		try {
			list = departmentDao.list(new Pager(), new Department());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		String[] title = new String[list.size()];
		List<PieData> pieList = new ArrayList<>();
		PieData data;
		for (int i = 0; i < list.size(); i++) {
			title[i] = list.get(i).getName();
			data = new PieData();
			data.setName(title[i]);
			data.setValue(list.get(i).getEmployees().size());
			pieList.add(data);
		}
		
		result.setTitle(title);
		result.setPieData(pieList);
		
		return result;
	}

}
