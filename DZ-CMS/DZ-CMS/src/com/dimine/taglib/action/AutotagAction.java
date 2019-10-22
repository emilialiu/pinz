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
import com.dimine.taglib.entity.AutocompleteEntity;
import com.dimine.taglib.service.AutotagService;

/**
 * 自动完成组件
 * 
 * @author 张杰
 * 
 */
@Namespace("/dm/autocomplete")
@Scope("prototype")
@Controller("autotagAction")
public class AutotagAction extends BaseAction {

	private static final long serialVersionUID = 3452299971550218651L;
	
	@Resource
	private AutotagService<AutocompleteEntity> autotagService;

	private AutocompleteEntity auto = new AutocompleteEntity();

	@Action(value = "auto", results = { @Result(name = "success", location = "/pub/jsonpager.jsp") })
	public String list() {
		
		List<Map> autolist = (List<Map>) autotagService.selectautolist(auto);
		StringBuffer str = new StringBuffer(); 
		str.append("[");
		for(int i=0;i<autolist.size();i++){			
			str.append("{\'label\':\'"+autolist.get(i).get(""+auto.getAutotext()+"").toString()+"\',");
			str.append("\'value\':\'"+autolist.get(i).get(""+auto.getAutotext()+"").toString()+"\',");
			str.append("\'idvalue\':\'"+autolist.get(i).get(""+auto.getAutotext()+"").toString()+"\'}");
			if(i+1 < autolist.size()){
				str.append(",");
			}
			
		}
		str.append("]");
		ServletActionContext.getRequest().setAttribute("jsonString", str.toString());
		
		return SUCCESS;
	}

	
	
	public AutotagService<AutocompleteEntity> getAutotagService() {
		return autotagService;
	}

	public void setAutotagService(AutotagService<AutocompleteEntity> autotagService) {
		this.autotagService = autotagService;
	}

	public AutocompleteEntity getAuto() {
		return auto;
	}

	public void setAuto(AutocompleteEntity auto) {
		this.auto = auto;
	}

}
