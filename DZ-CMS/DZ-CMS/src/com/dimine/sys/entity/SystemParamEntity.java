// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SystemParamEntity.java

package com.dimine.sys.entity;

import com.dimine.base.entity.BaseEntity;
public class SystemParamEntity extends BaseEntity
{

	public static final String IBATIS_KEY_SYSTEMPARAM_CODE_EXIST = "SystemParamEntity.codeexist";
	public static final String IBATIS_KEY_SYSTEMPARAM_GETVALUEBYCODE = "SystemParamEntity.getvaluebycode";
	private String deptID;
	private String paramcode;
	private String paramname;
	private String paramvalue;
	private String memo;
	private String deptname;

	public SystemParamEntity()
	{
		deptID = "";
		paramcode = "";
		paramname = "";
		paramvalue = "";
		memo = "";
	}

	public String getParamcode()
	{
		return paramcode;
	}

	public void setParamcode(String paramcode)
	{
		this.paramcode = paramcode;
	}

	public String getParamname()
	{
		return paramname;
	}

	public void setParamname(String paramname)
	{
		this.paramname = paramname;
	}

	public String getParamvalue()
	{
		return paramvalue;
	}

	public void setParamvalue(String paramvalue)
	{
		this.paramvalue = paramvalue;
	}

	

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptID()
	{
		return deptID;
	}

	public void setDeptID(String deptID)
	{
		this.deptID = deptID;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}
}
