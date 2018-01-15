package com.jw.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jw.dao.DistrictDao;
import com.jw.model.District;

@Repository
public class DistrictDaoImpl implements DistrictDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public District get(Integer id) {
		return hibernateTemplate.get(District.class, id);
	}

}
