package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * EXCEL导入模板信息表信息类
 * 
 * @author dimine 2014-12-18 15:45:29
 * 
 */
public class Sys_modelinfoEntity extends BaseEntity {

	/** 模版ID */
	private String modelid ;
	/** 模版名称 */
	private String modelname ;
	/** 对应到oracle中表名称 */
	private String mtable ;
	/** excel模板名称(与本地一致) */
	private String exceltb ;
	/** 在服务器上保存的名称 */
	private String targetname ;

	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getMtable() {
		return mtable;
	}
	public void setMtable(String mtable) {
		this.mtable = mtable;
	}
	public String getExceltb() {
		return exceltb;
	}
	public void setExceltb(String exceltb) {
		this.exceltb = exceltb;
	}
	public String getTargetname() {
		return targetname;
	}
	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

}
