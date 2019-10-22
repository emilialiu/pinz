package com.dimine.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.dimine.base.taglib.AbstractTag;

public class AutocompleteTag extends AbstractTag {

	/**
	 * 自动完成
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String table;
	private String autotext;
	private String fixValue;
	private String cssStyle;
	private String cssClass;
	private String parameter;
	private String cssWidth;
	private String maxsize;
	
	public AutocompleteTag(){
		table = "";
		autotext = "";
		fixValue = "";
		cssStyle = "";
		cssClass = "";
		parameter = "";
		cssWidth = "";
		maxsize = "";
	}
	
	public int doStartTag() throws JspException{
		
		Object value = getCurrentValue();
		if (fixValue != null && !fixValue.equals(""))
			value = fixValue;
		if(parameter==null||parameter.equals("")){
			parameter = "1 = 1";
		}
		if(maxsize==null||maxsize.equals("")){
			maxsize = "10";
		}
		
		StringBuffer results = new StringBuffer("");
		results.append("<input name=\"");
		results.append(getName());
		results.append("\" id=\""+getName()+"\" type=\""+cssClass+"\" class=\"ui-autocomplete-input\" style=\""+cssStyle+"\"");
		results.append("/>");
		
		results.append("<script>");
		results.append("$(function($){");
		results.append("if(navigator.userAgent.indexOf(\"MSIE\")>0){");
		results.append("document.getElementById('"+getName()+"').attachEvent(\"onpropertychange\",function(){if(arguments[0].propertyName!=\'value\'){ return false;}else{Change(\""+getName()+"\",\""+table+"\",\""+autotext+"\",\""+parameter+"\",\""+cssWidth+"\",\""+maxsize+"\");}},false); ");
		results.append("}else{ ");
		results.append("document.getElementById('"+getName()+"').addEventListener(\"input\",function(){Change(\""+getName()+"\",\""+table+"\",\""+autotext+"\",\""+parameter+"\",\""+cssWidth+"\",\""+maxsize+"\");},false);");
		results.append("}});");
		
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
	
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getAutotext() {
		return autotext;
	}
	public void setAutotext(String autotext) {
		this.autotext = autotext;
	}
	public String getFixValue() {
		return fixValue;
	}
	public void setFixValue(String fixValue) {
		this.fixValue = fixValue;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCssWidth() {
		return cssWidth;
	}

	public void setCssWidth(String cssWidth) {
		this.cssWidth = cssWidth;
	}

	public String getMaxsize() {
		return maxsize;
	}

	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}
	
}
