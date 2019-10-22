// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ResourceBean.java

package com.dimine.taglib.entity;


public class ResourceBean
{

	private String name;
	private String code;
	private String parentId;
	private String extContent;

	public ResourceBean()
	{
		name = "";
		code = "";
		parentId = "";
		extContent = "";
	}

	public String getExtContent()
	{
		return extContent;
	}

	public void setExtContent(String extContent)
	{
		this.extContent = extContent;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public ResourceBean(String pname, String pcode)
	{
		name = "";
		code = "";
		parentId = "";
		extContent = "";
		name = pname;
		code = pcode;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
