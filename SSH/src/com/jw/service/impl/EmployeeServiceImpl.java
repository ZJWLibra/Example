package com.jw.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.common.EasyUIResult;
import com.jw.common.Pager;
import com.jw.common.SSHResult;
import com.jw.dao.CityDao;
import com.jw.dao.DepartmentDao;
import com.jw.dao.DistrictDao;
import com.jw.dao.EmployeeDao;
import com.jw.dao.ProvinceDao;
import com.jw.model.City;
import com.jw.model.Department;
import com.jw.model.District;
import com.jw.model.Employee;
import com.jw.model.Province;
import com.jw.service.EmployeeService;
import com.jw.util.ExcelUtil;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ProvinceDao provinceDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private DistrictDao districtDao;

	@Override
	public SSHResult insert(Employee t) {
		SSHResult result = new SSHResult();
		Department department = null;
		Province province = null;
		City city = null;
		District district = null;
		try {
			department = departmentDao.get(t.getDepartment().getId());
			province = provinceDao.get(t.getProvince().getId());
			city = cityDao.get(t.getCity().getId());
			district = districtDao.get(t.getDistrict().getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("error");
			result.setMsg("新增失败");
		}

		t.setDepartment(department);
		t.setProvince(province);
		t.setCity(city);
		t.setDistrict(district);

		try {
			employeeDao.insert(t);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult("error");
			result.setMsg("新增失败");
		}
		result.setResult("success");
		return result;
	}

	@Override
	public SSHResult update(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SSHResult delete(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EasyUIResult<Employee> List(Pager pager, Employee t) {
		List<Employee> list;
		try {
			list = employeeDao.list(pager, t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		Long count;
		try {
			count = employeeDao.count(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		EasyUIResult<Employee> easyUIResult = new EasyUIResult<>();
		easyUIResult.setRows(list);
		easyUIResult.setTotal(count);

		return easyUIResult;
	}

	@Override
	public List<Department> listDepartmentAll() {
		List<Department> list;
		try {
			list = departmentDao.list(new Pager(), new Department());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<Province> listProvince() {
		try {
			return provinceDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<City> listCityByProvinceId(Integer id) {
		Province province;
		try {
			province = provinceDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Set<City> citys = province.getCitys();
		return new ArrayList<City>(citys);
	}

	@Override
	public List<District> listDistrictByCityId(Integer id) {
		City city;
		try {
			city = cityDao.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Set<District> districts = city.getDistricts();
		return new ArrayList<District>(districts);
	}

	@Override
	public void exportExcel(ServletOutputStream outputStream) {
		List<Employee> list = null;
		try {
			list = employeeDao.list(new Pager(), new Employee());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] headers = { "姓名", "性别", "年龄", "生日", "所属部门", "所属省", "所属市", "所属区" };

		ExcelUtil.exportExcel("员工列表", "员工列表", headers, list, outputStream);
	}

	@Override
	public void importExcel(File employeeExcel) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(employeeExcel);
			// 读取工作簿
			Workbook workbook = WorkbookFactory.create(fis);
			// 读取Sheet
			Sheet sheet = workbook.getSheetAt(0);
			// 读取行
			Employee employee;
			if (sheet.getPhysicalNumberOfRows() > 2) {
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					employee = new Employee();
					Row row = sheet.getRow(i);
					// 把单元格中的值获取并存入员工对象中
					employee.setName(row.getCell(0).getStringCellValue());
					employee.setSex(row.getCell(1).getStringCellValue());
					employee.setAge((int) row.getCell(2).getNumericCellValue());
					double birthday = row.getCell(3).getNumericCellValue();
					employee.setBirthday(HSSFDateUtil.getJavaDate(birthday));
					
					// 持久化到数据库
					try {
						employeeDao.insert(employee);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
