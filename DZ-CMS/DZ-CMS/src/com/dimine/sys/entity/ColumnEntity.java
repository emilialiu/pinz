package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;

public class ColumnEntity extends BaseEntity {

	private String codekey;
	private String codevalue;
	public String getCodekey() {
		return codekey;
	}
	public void setCodekey(String codekey) {
		this.codekey = codekey;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
}
