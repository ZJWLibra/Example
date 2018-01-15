package com.jw.common;

import java.util.List;

/**
 * easyui转json对象
 * 
 * @author Zeng
 *
 */
public class EasyUIResult<T> {
	private Long total;
	private List<T> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
