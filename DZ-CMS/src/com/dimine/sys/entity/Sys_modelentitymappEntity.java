package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 模板应用表信息类
 * 
 * @author dimine 2014-12-18 15:40:29
 * 
 */
public class Sys_modelentitymappEntity extends BaseEntity {

	/** 表名称 */
	private String tablename ;
	/** 实体类名 */
	private String entityclass ;
	/** 数据库 */
	private String dbkey ;
	/** 是否指定自动生成列值:1是；0否 */
	private String isauto ;

	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getEntityclass() {
		return entityclass;
	}
	public void setEntityclass(String entityclass) {
		this.entityclass = entityclass;
	}
	public String getDbkey() {
		return dbkey;
	}
	public void setDbkey(String dbkey) {
		this.dbkey = dbkey;
	}
	public String getIsauto() {
		return isauto;
	}
	public void setIsauto(String isauto) {
		this.isauto = isauto;
	}

}
