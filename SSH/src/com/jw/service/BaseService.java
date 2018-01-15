package com.jw.service;

import com.jw.common.EasyUIResult;
import com.jw.common.Pager;
import com.jw.common.SSHResult;

public interface BaseService<T> {
	SSHResult insert(T t);
	
	SSHResult update(T t);
	
	SSHResult delete(T t);
	
	T get(Integer id);
	
	EasyUIResult<T> List(Pager pager, T t); // {"total" : "总记录数", "rows" : "list"}
}
