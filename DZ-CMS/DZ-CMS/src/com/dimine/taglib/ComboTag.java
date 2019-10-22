package com.dimine.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;


import com.dimine.base.taglib.AbstractTag;
import com.dimine.sys.util.SpringUtil;
import com.dimine.taglib.entity.ComboEntity;
import com.dimine.taglib.service.ComboService;
import com.dimine.sys.data.ResourceManager;


public class ComboTag extends AbstractTag {

	/**
	 * 组合框
	 */
	private static final long serialVersionUID = 7592706830950069150L;
	private ComboService<ComboEntity> comboService;
	
	private String table;//查询的表名
	private String combovalue;//
	private String combotext;//
	private String isnull;//是否显示空值
	private String fixValue;
	private String cssStyle;
	private String cssClass;
	private String parameter;
	private String comcode;
	
	public ComboTag(){
		table = "";
		combovalue = "";
		combotext = "";
		isnull = "";
		fixValue = "";
		cssStyle = "";
		parameter = "";
		cssClass = "";
		comcode = "";
	}
	
	public int doStartTag() throws JspException{
		 
		Object value = getCurrentValue();
		if (fixValue != null && !fixValue.equals(""))
			value = fixValue;
		if(parameter==null||parameter.equals("")){
			parameter = "1 = 1";
		}
		
		ComboEntity combo = new ComboEntity();
		combo.setTablename(table);
		combo.setComboid(combovalue);
		combo.setCombotext(combotext);
		combo.setParameter(parameter);
		combo.setComcode(comcode);
		comboService = (ComboService<ComboEntity>) SpringUtil.getBean(ResourceManager.getInstance().getServletContext(), "comboService");
		List<Map> combolist = (List<Map>) comboService.selectcombolist(combo);
		
		StringBuffer results = new StringBuffer("");
		results.append("<select name=\"");
		results.append(getName());
		results.append("\" id=\""+getName()+"\" class=\""+cssClass+"\" style=\""+cssStyle+"\" onchange=\""+onchange+"\"");
		results.append(">");
		
		results.append("<option value=\"\"></option>");
		if (combolist != null)
		{
			int length = combolist.size();
			for (int i = 0; i < length; i++)
			{
				String selected = "";
				String strTemp = (String)value;
				if (strTemp.equalsIgnoreCase(combolist.get(i).get(combovalue).toString()))
					selected = "selected";
				results.append("<option value=\"");
				results.append(combolist.get(i).get(combovalue).toString());
				results.append("\"");
				results.append(selected);
				results.append("\"");
				results.append(">");
				results.append(combolist.get(i).get(combotext).toString());
				results.append("</option>");
				results.append("");
			}

		}
		results.append("</select>");
		
		try {
			JspWriter out = pageContext.getOut();
			System.out.println(results.toString());
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
	public String getCombovalue() {
		return combovalue;
	}
	public void setCombovalue(String combovalue) {
		this.combovalue = combovalue;
	}
	public String getCombotext() {
		return combotext;
	}
	public void setCombotext(String combotext) {
		this.combotext = combotext;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
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

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public void setComboService(ComboService<ComboEntity> comboService) {
		this.comboService = comboService;
	}

	public ComboService<ComboEntity> getComboService() {
		return comboService;
	}

	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}
	
}
