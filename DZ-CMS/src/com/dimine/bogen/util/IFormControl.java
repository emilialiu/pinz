package com.dimine.bogen.util;
/**
 * 代码生成表单信息生成接口类
 * @author Aaron 2014-12-12
 *
 */
public interface IFormControl {
	
	/**
	 * 
	 * @param type 表单类型
	 * @param fieldName 字段名
	 * @param description 字段描述
	 * @param validator 验证规则
	 * @param primaryKey 是否主键
	 * @param pageShow 是否在页面上显示
	 * @param isShowPK 主键是否在页面上显示
	 * @return HTML
	 */
	public String getFormControlType(String type,String fieldName,String filePath,String description,String validator,String primaryKey,String pageShow,String isShowPK);
	
	/**
	 * 
	 * @param type 数据字段类型
	 * @param fieldName 字段名称
	 * @param description 字段描述
	 * @return
	 */
	public String getQueryType(String isRequirement,String description);
	
	/**
	 * 
	 * @param description 字段描述信息
	 * @param validator 验证规则
	 * @return
	 */
	public String getValidateMsg(String description,String validator);

}
