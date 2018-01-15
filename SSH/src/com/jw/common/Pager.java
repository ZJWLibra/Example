package com.jw.common;

public class Pager {
	// 当前页
	private Integer pageNo;
	// 要查询的数目
	private Integer pageSize;
	// 总记录数
	private Integer total;
	// 总页数
	private Integer pages;

	public Pager() {
	}

	public Pager(Integer pageNo, Integer pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPages() {
		int num = total % pageSize;
		return num == 0 ? total / pageSize : total / pageSize + 1;
	}

	public Integer getBeginIndex() {
		return (pageNo - 1) * pageSize;
	}
}
