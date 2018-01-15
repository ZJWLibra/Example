package com.jw.util;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.jw.model.Employee;

public class ExcelUtil {

	/**
	 * 导出用户列表
	 * 
	 * @param sheetName
	 *            sheet名称
	 * @param titleName
	 *            标题
	 * @param headers
	 *            表格每一列的列名
	 * @param list
	 *            要导出的数据
	 * @param outputStream
	 */
	public static void exportExcel(String sheetName, String titleName, String[] headers, List<Employee> list,
			ServletOutputStream outputStream) {
		HSSFWorkbook workbook = null;
		try {
			// 创建工作簿
			workbook = new HSSFWorkbook();
			// 创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, headers.length - 1);// 起始行号，结束行号，起始列号，结束列号
			// 创建头标题行样式并创建字体
			HSSFCellStyle style1 = createCellStyle(workbook, (short) 16);
			// 创列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short) 13);

			// 创建工作表
			HSSFSheet sheet = workbook.createSheet(sheetName);
			// 加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			// 设置默认列宽
			sheet.setDefaultColumnWidth(20);

			// 创建行
			// 创建头标题行并写入头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			cell1.setCellStyle(style1);
			cell1.setCellValue(titleName);

			// 创建列标题并写入列标题
			HSSFRow row2 = sheet.createRow(1);
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell2 = row2.createCell(i);
				cell2.setCellStyle(style2);
				cell2.setCellValue(headers[i]);
			}

			// 创建单元格，写入用户数据到excel
			if (list != null && list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					HSSFRow row = sheet.createRow(j + 2);
					row.createCell(0).setCellValue(list.get(j).getName());
					row.createCell(1).setCellValue(list.get(j).getSex());
					row.createCell(2).setCellValue(list.get(j).getAge());
					Date birthady = list.get(j).getBirthday();
					row.createCell(3).setCellValue(DateUtil.dateToString(birthady, "yyyy/MM/dd"));
					row.createCell(4).setCellValue(list.get(j).getDepartment().getName());
					row.createCell(5).setCellValue(list.get(j).getProvince().getName());
					row.createCell(6).setCellValue(list.get(j).getCity().getName());
					row.createCell(7).setCellValue(list.get(j).getDistrict().getName());
				}
			}
			// 输出
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 创建单元格样式
	 * 
	 * @param workbook
	 *            工作簿
	 * @param fontSize
	 *            字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		// 水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 垂直居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建字体
		HSSFFont font = workbook.createFont();
		// 加粗字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontSize);
		// 在样式中加载字体
		style.setFont(font);
		return style;
	}
}
