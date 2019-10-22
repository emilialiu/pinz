package com.dimine.${modelPath ?lower_case}.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * ${title}信息类
 * 
 * @author dimine ${sysdate}
 * 
 */
public class ${actionName ?cap_first}Entity extends BaseEntity {

	<#list filed as filed>
	/** ${filed.description} */
	private String ${filed.filedName ?lower_case} = "";
	</#list>

	<#list filed as filed>
	public String get${filed.filedName ?cap_first}() {
		return ${filed.filedName ?lower_case};
	}
	public void set${filed.filedName ?cap_first}(String ${filed.filedName ?lower_case}) {
		this.${filed.filedName ?lower_case} = ${filed.filedName ?lower_case};
	}
	</#list>

}
