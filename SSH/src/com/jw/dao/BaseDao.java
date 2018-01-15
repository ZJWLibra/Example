package com.jw.dao;

import java.util.List;

import com.jw.common.Pager;

public interface BaseDao<T> {
	void insert(T t) throws Exception;
	
	void update(T t) throws Exception;
	
	void delete(T t) throws Exception;
	
	T get(Integer id) throws Exception;
	
	List<T> list(Pager pager, T t) throws Exception;
	
	Long count(T t) throws Exception;
}
