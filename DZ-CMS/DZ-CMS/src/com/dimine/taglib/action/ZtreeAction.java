package com.dimine.taglib.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.KeyUtils;
import com.dimine.taglib.entity.ZtreeEntity;
import com.dimine.taglib.service.ZtreeService;


@Namespace("/dm/ztree")
@Scope("request")
@Controller("ztreeAction")
public class ZtreeAction extends BaseAction {
	@Resource
	private ZtreeService<ZtreeEntity> ztreeService;
	private ZtreeEntity bean = new ZtreeEntity();
	/**
	 * 
	 */
	private static final long serialVersionUID = -4229493064924078163L;
	@Action(value = "list", results = { @Result(name = "success", location = "/pub/jsonpager.jsp") })
	public String getList(){
		try {
			List<Map> ztreelist = (List<Map>) ztreeService.selectztreelist(bean);
			String jsonData = createData(ztreelist);
			
			this.setErrorMessage("");
			
			ServletActionContext.getRequest().setAttribute("jsonString", jsonData);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
		
	}
	
	public String createData(List<Map> list){
		StringBuffer jsonData = new StringBuffer();
		int length = list.size();
		if(length == 0)
			return "";
		
		for(int i=0;i<length;i++){
			Map unit = list.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(unit.get(""+bean.getZtreeid()+"").toString());
			jsonData.append("\", \"pid\":\"");
			if (unit.get(""+bean.getParentid()+"") == null || unit.get(""+bean.getParentid()+"") == "")
				jsonData.append("");
			else
				jsonData.append(unit.get(""+bean.getParentid()+"").toString());
			jsonData.append("\", \"name\":\"");
			jsonData.append(unit.get(""+bean.getZtreetext()+"").toString());
			if (Integer.valueOf(unit.get("childCount").toString()).intValue() >= 1) {
				jsonData.append("\", \"isParent\":\"");
				jsonData.append("true");
				jsonData.append("\", \"open\":\"");
				jsonData.append("false");
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
	@Action(value = "modify", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doModify(){
		
		try {
			ztreeService.update(bean);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	@Action(value = "drop", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doDrop(){
		
		try {
			ztreeService.updatedrop(bean);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	@Action(value = "remove", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doRemove(){
		
		try {
			ztreeService.delete(bean);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	@Action(value = "getId", results = { @Result(name = "success", location = "/pub/jsonpager.jsp") })
	public String getId(){
		
		try {
			bean.setId(KeyUtils.createKeyID());
			ztreeService.insert(bean);
			
			this.setErrorMessage("");
			ServletActionContext.getRequest().setAttribute("jsonString", "\""+bean.getId()+"\"");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}

	public ZtreeEntity getBean() {
		return bean;
	}

	public void setBean(ZtreeEntity bean) {
		this.bean = bean;
	}

	public ZtreeService<ZtreeEntity> getZtreeService() {
		return ztreeService;
	}

	public void setZtreeService(ZtreeService<ZtreeEntity> ztreeService) {
		this.ztreeService = ztreeService;
	}
}
