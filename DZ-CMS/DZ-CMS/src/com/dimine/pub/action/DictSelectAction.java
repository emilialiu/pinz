package com.dimine.pub.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.data.ResourceManager;
import com.dimine.taglib.entity.ResourceBean;
import com.dimine.util.StringUtils;

@Namespace("/pub/select/dict")
@Scope("request")
@Controller("dictSelectAction")
public class DictSelectAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourcename;
	// 接受阶段的值
	private String jdname;
	// 接受业务的值
	private String ywname;
	private String deptid;//矿山id
	// 获取一级下拉列表中的值
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getCombobox() {
		List datalist = null;
		//判断是否是获取元素表信息
		if(!StringUtils.isEmpty(deptid)){
			datalist= ResourceManager.getInstance().getData(sourcename,
					new String[] { "" },deptid);
		}else{
			datalist=ResourceManager.getInstance().getData(sourcename,
					new String[] { "" },deptid);
		}
		String var = JSONUtil.toJSONString(datalist);
		this.getRequest().setAttribute("jsonStr", var);
		return SUCCESS;
	}
	// 获取二级下拉列表支护子类型中的值
	@Action(value = "childlist", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getchildCombobox() {
		List datalist = ResourceManager.getInstance().getChildData(sourcename,
				new String[] { "" });
		String var = JSONUtil.toJSONString(datalist);
		this.getRequest().setAttribute("jsonStr", var);
		return SUCCESS;
	}

	@Action(value = "comboboxList", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getComboboxValue() {
		try {
			@SuppressWarnings("unchecked")
			List<ResourceBean> datalist = ResourceManager.getInstance()
					.getData(sourcename, new String[] { "" },deptid);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (ResourceBean data : datalist) {
					sbf.append("'").append(data.getCode()).append("':'")
							.append(data.getName()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.getRequest().setAttribute("jsonStr",
						_jsonData.substring(0, length - 1));
			} else {
				this.getRequest().setAttribute("jsonStr", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public String getJdname() {
		return jdname;
	}

	public void setJdname(String jdname) {
		this.jdname = jdname;
	}

	public String getYwname() {
		return ywname;
	}

	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public void setYwname(String ywname) {
		this.ywname = ywname;
	}

}
