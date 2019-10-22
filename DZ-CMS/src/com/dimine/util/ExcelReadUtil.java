package com.dimine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;

/**
 * excel数据读取工具类
 * 
 * @author Administrator
 * 
 */

public class ExcelReadUtil {
	// 读取Excel
	public static Map<Integer, List<String>> readExcel(File file)
			throws IOException {
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		Workbook workbook = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			workbook = new XSSFWorkbook(is);
		} catch (POIXMLException ex) {
			try {
				is = new FileInputStream(file);
				workbook = new HSSFWorkbook(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 获取sheet数。
		int sheetnum = workbook.getNumberOfSheets();
		for (int n = 0; n < 1; n++) {
			// 获取Sheet
			Sheet sheet = workbook.getSheetAt(n);
			// 获取要读取的最大行数
			int rownum = sheet.getLastRowNum() + 1;
			if (rownum <= 1) {
				continue;
			}
			// 获取要读取的最大列数
			int cellnum;
			DecimalFormat df = new DecimalFormat("0.0000");
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < rownum; i++) {
				// 行
				Row row = sheet.getRow(i);
				if (row != null) {
					StringBuilder sb = new StringBuilder();
					cellnum = row.getLastCellNum();
					for (int j = 0; j < cellnum; j++) {
						Cell cell = row.getCell(j);
						if (cell == null) {
							sb.append(" ").append("$$$$$");
						}
						String cellValue;
						if (cell != null) {
							// 以下是判断数据的类型
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC: // 数字
								cellValue = df.format(cell
										.getNumericCellValue());
								break;

							case HSSFCell.CELL_TYPE_STRING: // 字符串
								cellValue = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
								cellValue = cell.getBooleanCellValue() + "";
								break;

							case HSSFCell.CELL_TYPE_FORMULA: // 公式
								try {
									cellValue = df.format(cell
											.getNumericCellValue());
								} catch (IllegalStateException e) {
									cellValue = "0.00";
								}
								break;

							case HSSFCell.CELL_TYPE_BLANK: // 空值
								cellValue = " ";
								break;

							case HSSFCell.CELL_TYPE_ERROR: // 故障
								cellValue = "非法字符";
								break;

							default:
								cellValue = "未知类型";
								break;
							}
							sb.append(cellValue).append("$$$$$");
						}
					}
					// 保存第i行的数据
					list.add(sb.toString());
				}
			}
			map.put(n, list);
		}
		return map;
	}
	
	/**
	 * 读取excel表格数据
	 * @param file excel文件
	 * @param _lastHeadRowIndex 最后一行的行头索引（就是），目的是为了固定表格的列数
	 * @return
	 * @throws IOException
	 */
	public static Map<Integer, List<String>> readExcel2(File file,int _lastHeadRowIndex)
			throws IOException {
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		Workbook workbook = null;
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			workbook = new XSSFWorkbook(is);
		} catch (POIXMLException ex) {
			try {
				is = new FileInputStream(file);
				workbook = new HSSFWorkbook(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 获取sheet数。
		int sheetnum = workbook.getNumberOfSheets();
		for (int n = 0; n < sheetnum; n++) {
			// 获取Sheet
			Sheet sheet = workbook.getSheetAt(n);
			// 获取要读取的最大行数
			int rownum = sheet.getLastRowNum() + 1;
			if (rownum <= 1) {
				continue;
			}
			// 获取要读取的最大列数
			int cellnum;
			cellnum = sheet.getRow(_lastHeadRowIndex-1).getLastCellNum();
			DecimalFormat df = new DecimalFormat("0.0000");
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < rownum; i++) {
				// 行
				Row row = sheet.getRow(i);
				if (row != null) {
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < cellnum; j++) {
						Cell cell = row.getCell(j);
						if (cell == null) {
							sb.append(" ").append("$$$$$");
						}
						String cellValue;
						if (cell != null) {
							// 以下是判断数据的类型
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC: // 数字
								cellValue = df.format(cell
										.getNumericCellValue());
								break;

							case HSSFCell.CELL_TYPE_STRING: // 字符串
								cellValue = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
								cellValue = cell.getBooleanCellValue() + "";
								break;

							case HSSFCell.CELL_TYPE_FORMULA: // 公式
								try {
									cellValue = df.format(cell
											.getNumericCellValue());
								} catch (IllegalStateException e) {
									cellValue = "0.00";
								}
								break;

							case HSSFCell.CELL_TYPE_BLANK: // 空值
								cellValue = " ";
								break;

							case HSSFCell.CELL_TYPE_ERROR: // 故障
								cellValue = "非法字符";
								break;

							default:
								cellValue = "未知类型";
								break;
							}
							sb.append(cellValue).append("$$$$$");
						}
					}
					// 保存第i行的数据
					list.add(sb.toString());
				}
			}
			map.put(n, list);
		}
		return map;
	}
	/**
	 * 根据列获取EXCEL的拼音，比如A，B 
	 * 1 A 2 B 26 Z 27 AA 28 AB
	 * @param i
	 * @return
	 */
	public static String excelPinyin(int i){
		String ab = null;
		int j = 0;
		if(i>26){
			if(i%26==0){
				j = i/26-1;
			}else{
				j = i/26;
			}
		}
	
		if(j == 0){
			ab = "";
		}else{
			System.out.println(j);
			switch(j){
				case 1 : ab="A";break;
				case 2 : ab="B";break;
				case 3 : ab="C";break;
				case 4 : ab="D";break;
				case 5 : ab="E";break;
				case 6 : ab="F";break;
				case 7 : ab="G";break;
				case 8 : ab="H";break;
				case 9 : ab="I";break;
				case 10 : ab="J";break;
				case 11 : ab="K";break;
				case 12 : ab="L";break;
				case 13 : ab="M";break;
				case 14 : ab="N";break;
				case 15 : ab="O";break;
				case 16 : ab="P";break;
				case 17 : ab="Q";break;
				case 18 : ab="R";break;
				case 19 : ab="S";break;
				case 20 : ab="T";break;
				case 21 : ab="U";break;
				case 22 : ab="V";break;
				case 23 : ab="W";break;
				case 24 : ab="X";break;
				case 25 : ab="Y";break;
				case 26 : ab="Z";break;
			}
		}
		int k = i-(i/26)*26;
		switch(k){
			case 0 : ab+="Z";break;
			case 1 : ab+="A";break;
			case 2 : ab+="B";break;
			case 3 : ab+="C";break;
			case 4 : ab+="D";break;
			case 5 : ab+="E";break;
			case 6 : ab+="F";break;
			case 7 : ab+="G";break;
			case 8 : ab+="H";break;
			case 9 : ab+="I";break;
			case 10 : ab+="J";break;
			case 11 : ab+="K";break;
			case 12 : ab+="L";break;
			case 13 : ab+="M";break;
			case 14 : ab+="N";break;
			case 15 : ab+="O";break;
			case 16 : ab+="P";break;
			case 17 : ab+="Q";break;
			case 18 : ab+="R";break;
			case 19 : ab+="S";break;
			case 20 : ab+="T";break;
			case 21 : ab+="U";break;
			case 22 : ab+="V";break;
			case 23 : ab+="W";break;
			case 24 : ab+="X";break;
			case 25 : ab+="Y";break;
			
	  }
		return ab;
	}
	public static void main(String[] args) {
		excelPinyin(54);
	}
}
