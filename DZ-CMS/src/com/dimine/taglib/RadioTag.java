package com.dimine.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.dimine.sys.data.ResourceManager;
import com.dimine.base.taglib.AbstractTag;
import com.dimine.sys.util.SpringUtil;
import com.dimine.taglib.entity.RadioEntity;
import com.dimine.taglib.service.RadioService;

public class RadioTag extends AbstractTag {
	/***
	 * 单选框
	 */
	private String table;//查询的表名
	private String radiovalue;//所有但选框的值
	private String radiotext;//单选框的名字
	private String isnull;//是否显示空值
	private String fixValue;//默认的被选中
	private String style;
	private String sclass;
	private String parameter;
	private RadioService<RadioEntity> radioService;
	
	public RadioTag(){
		table = "";
		radiovalue = "";
		radiotext = "";
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
		radioService = (RadioService<RadioEntity>) SpringUtil.getBean(
				ResourceManager.getInstance().getServletContext(),
				"radioService");
		RadioEntity radio = new RadioEntity();
		radio.setTablename(table);
		radio.setRadioid(radiovalue);
		radio.setRadiotext(radiotext);
		radio.setParameter(parameter);
		List<Map> radiolist = (List<Map>) radioService.selectradiolist(radio);
		StringBuffer results = new StringBuffer("");
		if (radiolist != null){
			int length = radiolist.size();
			for (int i = 0; i < length; i++){
				results.append("<input type=\"radio\" name=\""+getName()+"\" id=\""+getName()+i+"\" style=\""+style+"\" class=\""+sclass+"\" value=\""+radiolist.get(i).get(radiovalue)+"\"/> ").append(radiolist.get(i).get(radiotext));
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

	public String getRadiovalue() {
		return radiovalue;
	}

	public void setRadiovalue(String radiovalue) {
		this.radiovalue = radiovalue;
	}

	public String getRadiotext() {
		return radiotext;
	}

	public void setRadiotext(String radiotext) {
		this.radiotext = radiotext;
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
	public RadioService<RadioEntity> getRadioService() {
		return radioService;
	}
	public void setRadioService(RadioService<RadioEntity> radioService) {
		this.radioService = radioService;
	}

}
