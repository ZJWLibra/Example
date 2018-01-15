package com.jw.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jw.dao.ProvinceDao;
import com.jw.model.Province;

@Repository
public class ProvinceDaoImpl implements ProvinceDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Province get(Integer id) {
		return hibernateTemplate.get(Province.class, id);
	}

	@Override
	public List<Province> list() {
		StringBuffer hql = new StringBuffer("from Province");
		
		List<Province> list = hibernateTemplate.execute(new HibernateCallback<List<Province>>() {
			@Override
			public List<Province> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
		
		return list;
	}

}
