package com.dimine.util;

/**
 * 文件上传工具类
 * 
 * @author SSM
 * 
 */
public class FileUploadUntil {
	/**
	 * 获取附件类型的数据字典
	 * 
	 * @param filename
	 * @return
	 */
	public static String getAttchmenttype(String filename) {
		String atttype = filename.substring(filename.lastIndexOf("."))
				.toUpperCase();
		String fjlx = null;
		if (atttype.equals(".PDF")) {
			fjlx = "FJLX001";
		} else if (atttype.equals(".DOC") || atttype.equals(".DOCX")) {
			fjlx = "FJLX002";
		} else if (atttype.equals(".XLS") || atttype.equals(".XLSX")
				|| atttype.equals(".CSV")) {
			fjlx = "FJLX003";
		} else if (atttype.equals(".SWF")) {
			fjlx = "FJLX004";
		} else if (atttype.equals(".RM")) {
			fjlx = "FJLX005";
		} else if (atttype.equals(".SW")) {
			fjlx = "FJLX006";
		} else if (atttype.equals(".BMP") || atttype.equals(".JPG")
				|| atttype.equals(".JPEG") || atttype.equals(".PNG")
				|| atttype.equals(".GIF")) {
			fjlx = "FJLX008";
		}
		return fjlx;
	}

}
