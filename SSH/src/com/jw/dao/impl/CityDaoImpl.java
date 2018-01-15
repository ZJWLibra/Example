package com.jw.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jw.dao.CityDao;
import com.jw.model.City;

@Repository
public class CityDaoImpl implements CityDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public City get(Integer id) {
		return hibernateTemplate.get(City.class, id);
	}

}
