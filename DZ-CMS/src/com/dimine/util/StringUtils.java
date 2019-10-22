package com.dimine.util;

/**
 * 常用可简化的String类操作方法可在此添加
 * 减少在项目中重复出现的代码,方便维护
 * @author luqizhi001
 * 
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * 不具有实际意义的空字符串
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if ("".equals(s) || null == s || "null".equals(s)) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String s) {
		if (isEmpty(s)) {
			return false;
		}
		return true;
	}

	/**
	 * 验证字符串相同同时校验非空
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isSameStr(String s1, String s2) {
		if (isNotEmpty(s1) && isNotEmpty(s2) && s1.equals(s2)) {
			return true;
		}
		return false;
	}

	/**
	 * 数字不足位数左补0
	 * 
	 * @param str
	 * @param strLength
	 * @return
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}
	
	/**
	 * 判断值为数值类型的字符串是否相等
	 * 原因为数据库数值类型字段映射到实体类多为String类型，不好直接判断导致走错分支条件
	 * @param s1
	 * @param s2
	 * @return  regsvr32 -u E:\DimineX\diminex.dll
	regsvr32 J:\DimineX\diminex.dll
	 */
	public static boolean isSameDoubleStr(String s1,String s2){
		if(isNotEmpty(s1) && isNotEmpty(s2) && Double.parseDouble(s1)==Double.parseDouble(s2)){
			return true;
		}
		return false;
	}
	
}
