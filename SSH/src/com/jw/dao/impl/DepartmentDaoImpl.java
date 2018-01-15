package com.jw.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jw.common.Pager;
import com.jw.dao.DepartmentDao;
import com.jw.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void insert(Department t) {
		hibernateTemplate.save(t);
	}

	@Override
	public void update(Department t) {
		hibernateTemplate.update(t);
	}

	@Override
	public void delete(Department t) {
		hibernateTemplate.delete(t);
	}

	@Override
	public Department get(Integer id) {
		return hibernateTemplate.get(Department.class, id);
	}

	@Override
	public List<Department> list(Pager pager, Department t) {
		StringBuffer hql = new StringBuffer("from Department where 1=1 ");
		
		if (t != null && t.getName() != null && !t.getName().equals("")) {
			hql.append("and name like '%"+t.getName()+"%'");
		}
		
		List<Department> list = hibernateTemplate.execute(new HibernateCallback<List<Department>>() {
			@Override
			public List<Department> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql.toString());
				if (pager != null && pager.getPageNo() != null && !pager.getPageNo().equals("") && pager.getPageSize() != null && !pager.getPageSize().equals("")) {
					query.setFirstResult(pager.getBeginIndex());
					query.setMaxResults(pager.getPageSize());
				}
				return query.list();
			}
		});
		return list;
	}

	@Override
	public Long count(Department t) {
		StringBuffer hql = new StringBuffer("select count(*) from Department where 1=1 ");
		
		if (t != null && t.getName() != null && !t.getName().equals("")) {
			hql.append("and name like '%"+t.getName()+"%'");
		}
		
		Long count = hibernateTemplate.execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql.toString());
				return (Long) query.uniqueResult();
			}
		});
		return count;
	}

}
