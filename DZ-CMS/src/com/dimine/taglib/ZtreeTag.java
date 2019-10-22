package com.dimine.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.dimine.sys.data.ResourceManager;
import com.dimine.base.taglib.AbstractTag;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.util.SpringUtil;
import com.dimine.taglib.entity.ZtreeEntity;
import com.dimine.taglib.service.ZtreeService;

public class ZtreeTag extends AbstractTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZtreeEntity bean = new ZtreeEntity();
	private ZtreeService<ZtreeEntity> ztreeService;
	
	private String table;
	private String ztreevalue;
	private String ztreetext;
	private String parameter;
	private String parentid;
	private String cssWidth;
	private String errorMessage;
	
	public ZtreeTag(){
		table = "";
		ztreevalue = "";
		ztreetext = "";
		parameter = "";
		parentid = "";
		cssWidth = "";
		errorMessage = "";
		
	}

	public int doStartTag() throws JspException{
		
		if(parameter==null||parameter.equals("")){
			parameter = "1 = 1";
		}
		
		ztreeService =  (ZtreeService<ZtreeEntity>) SpringUtil.getBean(
				ResourceManager.getInstance().getServletContext(),
				"ztreeService");
		ZtreeEntity zbean = new ZtreeEntity();
		zbean.setTablename(table);
		zbean.setZtreeid(ztreevalue);
		zbean.setZtreetext(ztreetext);
		zbean.setParameter(parameter);
		zbean.setParentid(parentid);
		
		List<Map> ztreelist = (List<Map>) ztreeService.selectztreehead(zbean);
		String jsonData = createJsonData(ztreelist);

		StringBuffer results = new StringBuffer("");
		results.append("<ul id=\""+name+"\" class=\"ztree\" table=\""+table+"\" ztreevalue=\""+ztreevalue+"\" parentid=\""+parentid+"\" ztreetext=\""+ztreetext+"\"></ul>");
		results.append("<script>");
		results.append("$(function($){loadTree("+jsonData+",\""+name+"\")});");
		results.append("</script>");
		try {
			JspWriter out = pageContext.getOut();
			out.print(results.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.doStartTag();
		
	}
	
	public String createJsonData(List<Map> list){
		StringBuffer jsonData = new StringBuffer();
		int length = list.size();
		if(length == 0)
			return "";
		
		for(int i=0;i<length;i++){
			Map unit = list.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(unit.get(""+ztreevalue+"").toString());
			jsonData.append("\", \"pid\":\"");
			if (unit.get(""+parentid+"") == null || unit.get(""+parentid+"") == "")
				jsonData.append("");
			else
				jsonData.append(unit.get(""+parentid+"").toString());
			jsonData.append("\", \"name\":\"");
			jsonData.append(unit.get(""+ztreetext+"").toString());
			if (Integer.valueOf(unit.get("childCount").toString()).intValue() >= 1) {
				jsonData.append("\", \"isParent\":\"");
				jsonData.append("true");
				jsonData.append("\", \"open\":\"");
				jsonData.append("true");
				jsonData.append("\"},");
			} else {
				jsonData.append("\", \"isChild\":\"");
				jsonData.append("true");
				jsonData.append("\", \"open\":\"");
				jsonData.append("false");
				jsonData.append("\"},");
			}
		}

		String _jsonData = jsonData.toString();
		length = _jsonData.length();
		return _jsonData.substring(0, length - 1);

	}
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getZtreevalue() {
		return ztreevalue;
	}

	public void setZtreevalue(String ztreevalue) {
		this.ztreevalue = ztreevalue;
	}

	public String getZtreetext() {
		return ztreetext;
	}

	public void setZtreetext(String ztreetext) {
		this.ztreetext = ztreetext;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getCssWidth() {
		return cssWidth;
	}

	public void setCssWidth(String cssWidth) {
		this.cssWidth = cssWidth;
	}

	public ZtreeEntity getBean() {
		return bean;
	}

	public void setBean(ZtreeEntity bean) {
		this.bean = bean;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ZtreeService<ZtreeEntity> getZtreeService() {
		return ztreeService;
	}

	public void setZtreeService(ZtreeService<ZtreeEntity> ztreeService) {
		this.ztreeService = ztreeService;
	}
}
