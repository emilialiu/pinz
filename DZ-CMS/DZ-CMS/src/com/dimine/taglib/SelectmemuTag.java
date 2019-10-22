package com.dimine.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.dimine.base.taglib.AbstractTag;
import com.dimine.sys.data.ResourceManager;
import com.dimine.sys.util.SpringUtil;
import com.dimine.taglib.entity.SelectmemuEntity;
import com.dimine.taglib.service.SelectmemuService;

public class SelectmemuTag extends AbstractTag {

	/**
	 * 下拉列表
	 */
	private static final long serialVersionUID = 6309233591264114889L;
	private SelectmemuService<SelectmemuEntity> selectmemuService;

	private String table;
	private String parentid;
	private String checkboxvalue;
	private String checkboxtext;
	private String fixValue;
	private String cssStyle;
	private String cssClass;
	private String parameter;
	private String cssWidth;
	private String onclick;
	private String checkdiv;
	private String checktree;
	private String treewidth;
	private String treeheight;
	
	public SelectmemuTag(){
		table = "";
		checkboxvalue = "";
		checkboxtext = "";
		fixValue = "";
		cssStyle = "";
		cssClass = "";
		parameter = "";
		cssWidth = "";
		parentid = "";
		onclick = "";
		checkdiv = "";
		checktree = "";
		treewidth = "";
		treeheight = "";
	}
	
	public int doStartTag() throws JspException{
		
		if(parameter==null||parameter.equals("")){
			parameter = "1 = 1";
		}
		if(treewidth==null||treewidth.equals("")){
			treewidth = "180px";
		}
		if(treeheight==null||treeheight.equals("")){
			treeheight = "300px";
		}
		selectmemuService = (SelectmemuService<SelectmemuEntity>) SpringUtil
				.getBean(ResourceManager.getInstance().getServletContext(),
						"selectmemuService");

		SelectmemuEntity bean = new SelectmemuEntity();
		bean.setTablename(table);
		bean.setCheckboxid(checkboxvalue);
		bean.setCheckboxtext(checkboxtext);
		bean.setParentid(parentid);
		
		List<Map> checkboxlist = (List<Map>) selectmemuService.selectmemulist(bean);
		
		StringBuffer results = new StringBuffer("");
		results.append("<input id=\""+name+"\" type=\"text\" readonly style=\""+cssStyle+"\" onclick=\""+onclick+"\"/>");
		results.append("<input id=\""+name+"_id\" type=\"hidden\" style=\""+cssStyle+"\" />");
		results.append("<div id=\""+checkdiv+"\" class=\"menuContent\" style=\"display:none; position: absolute;\">");
		results.append("<ul id=\""+checktree+"\" class=\"ztree\" style=\"margin-top:0; width:"+treewidth+"; height: "+treeheight+";\"></ul>");
		results.append("</div>");
		
		StringBuffer strsb = new StringBuffer("");
		strsb.append("<script type=\"text/javascript\">");
		strsb.append("var zNodes =[");
		String nodes = "";
		if(checkboxlist.size()>0){			
			for(int i=0;i<checkboxlist.size();i++){
				Map str = checkboxlist.get(i);
				if(checkboxlist.get(i).get("childcount").toString().equals("0")){					
					strsb.append("{id:\""+str.get(""+checkboxvalue+"").toString()+"\", pId:\""+str.get(""+parentid+"").toString()+"\", name:\""+str.get(""+checkboxtext+"").toString()+"\"},");
				}else{
					strsb.append("{id:\""+str.get(""+checkboxvalue+"").toString()+"\", pId:\""+(str.get(""+parentid+"")==null?"":str.get(""+parentid+"").toString())+"\", name:\""+str.get(""+checkboxtext+"").toString()+"\", open:true, nocheck:true},");
				}
			}
			nodes = strsb.substring(0, strsb.length()-1);
		}
		results.append(nodes);
		results.append("];");
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

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getCheckdiv() {
		return checkdiv;
	}

	public void setCheckdiv(String checkdiv) {
		this.checkdiv = checkdiv;
	}

	public String getChecktree() {
		return checktree;
	}

	public void setChecktree(String checktree) {
		this.checktree = checktree;
	}

	public String getTreewidth() {
		return treewidth;
	}

	public void setTreewidth(String treewidth) {
		this.treewidth = treewidth;
	}

	public String getTreeheight() {
		return treeheight;
	}

	public void setTreeheight(String treeheight) {
		this.treeheight = treeheight;
	}

	public SelectmemuService<SelectmemuEntity> getSelectmemuService() {
		return selectmemuService;
	}

	public void setSelectmemuService(
			SelectmemuService<SelectmemuEntity> selectmemuService) {
		this.selectmemuService = selectmemuService;
	}
	
	
}
