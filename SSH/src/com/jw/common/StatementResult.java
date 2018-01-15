package com.jw.common;

import java.util.List;

public class StatementResult {
	private String[] title;
	// 柱状图数据
	private Integer[] columnarData;
	// 饼图数据
	private List<PieData> pieData;

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}

	public Integer[] getColumnarData() {
		return columnarData;
	}

	public void setColumnarData(Integer[] columnarData) {
		this.columnarData = columnarData;
	}

	public List<PieData> getPieData() {
		return pieData;
	}

	public void setPieData(List<PieData> pieData) {
		this.pieData = pieData;
	}

}
