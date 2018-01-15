package com.jw.dao;

import java.util.List;

import com.jw.model.Province;

public interface ProvinceDao {
	Province get(Integer id) throws Exception;
	
	List<Province> list() throws Exception;
}
