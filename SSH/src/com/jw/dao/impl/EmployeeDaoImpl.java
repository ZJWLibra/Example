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
import com.jw.dao.EmployeeDao;
import com.jw.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void insert(Employee t) throws Exception {
		hibernateTemplate.save(t);
	}

	@Override
	public void update(Employee t) throws Exception {
		hibernateTemplate.update(t);
	}

	@Override
	public void delete(Employee t) throws Exception {
		hibernateTemplate.delete(t);
	}

	@Override
	public Employee get(Integer id) throws Exception {
		return hibernateTemplate.get(Employee.class, id);
	}

	@Override
	public List<Employee> list(Pager pager, Employee t) throws Exception {
		StringBuffer hql = new StringBuffer("from Employee");
		
		List<Employee> list = hibernateTemplate.execute(new HibernateCallback<List<Employee>>() {
			@Override
			public List<Employee> doInHibernate(Session session) throws HibernateException {
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
	public Long count(Employee t) throws Exception {
		StringBuffer hql = new StringBuffer("select count(*) from Employee");
		
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
