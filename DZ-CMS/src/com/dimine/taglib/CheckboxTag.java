package com.dimine.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.dimine.sys.data.ResourceManager;
import com.dimine.base.taglib.AbstractTag;
import com.dimine.sys.util.SpringUtil;
import com.dimine.taglib.entity.CheckboxEntity;
import com.dimine.taglib.service.CheckboxService;

public class CheckboxTag  extends AbstractTag{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8015338026423925793L;
	/***
	 * 复选框
	 */
	private String table;//查询的表名
	private String checkboxvalue;//所有复选框的值
	private String checkboxtext;//复选框的名字
	private String isnull;//是否显示空值
	private String fixValue;//默认的被选中
	private String style;
	private String sclass;
	private String parameter;
	private CheckboxService<CheckboxEntity> checkboxService;
	
	public CheckboxTag(){
		table = "";
		checkboxvalue = "";
		checkboxtext = "";
		isnull = "";
		fixValue = "";
		style = "";
		parameter = "";
		sclass = "";
	}
	
	public int doStartTag() throws JspException{
		Object value = getCurrentValue();
		if (fixValue != null && !fixValue.equals(""))
			value = fixValue;
		if(parameter==null||parameter.equals("")){
			parameter = "1 = 1";
		}
		checkboxService =  (CheckboxService<CheckboxEntity>) SpringUtil.getBean(
				ResourceManager.getInstance().getServletContext(),
				"checkboxService");
		CheckboxEntity checkbox = new CheckboxEntity();
		checkbox.setTablename(table);
		checkbox.setCheckboxid(checkboxvalue);
		checkbox.setCheckboxtext(checkboxtext);
		checkbox.setParameter(parameter);
		List<Map> checklist = (List<Map>) checkboxService.checkboxselectlist(checkbox);
		StringBuffer results = new StringBuffer("");
		if (checklist != null){
			int length = checklist.size();
			for (int i = 0; i < length; i++){
				results.append("<input type=\"checkbox\" name=\""+getName()+"\" id=\""+getName()+i+"\" style=\""+style+"\" class=\""+sclass+"\" value=\""+checklist.get(i).get(checkboxvalue)+"\"/> ").append(checklist.get(i).get(checkboxtext));
			}
		}
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

	public String getCheckboxvalue() {
		return checkboxvalue;
	}

	public void setCheckboxvalue(String checkboxvalue) {
		this.checkboxvalue = checkboxvalue;
	}

	public String getCheckboxtext() {
		return checkboxtext;
	}

	public void setCheckboxtext(String checkboxtext) {
		this.checkboxtext = checkboxtext;
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public CheckboxService<CheckboxEntity> getCheckboxService() {
		return checkboxService;
	}

	public void setCheckboxService(CheckboxService<CheckboxEntity> checkboxService) {
		this.checkboxService = checkboxService;
	}
	
}
