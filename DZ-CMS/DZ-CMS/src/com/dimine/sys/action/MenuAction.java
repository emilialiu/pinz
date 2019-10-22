package com.dimine.sys.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.service.FuncService;

/**
 * 
 * <p>
 * 首页菜单动态菜单
 * </p>
 * 
 * @author dimine
 */
@Namespace("/manager/main")
@Scope("request")
@Controller("menuAction")
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 业务逻辑处理
	@Resource
	private FuncService<FuncEntity> funcService = null;

	/** 功能实体 */
	private FuncEntity bean = new FuncEntity();

	/** Ajax返回的字符串 */
	private String jsonString = "";

	//
	private String date = "";

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Action(value = "menuinfo", results = { @Result(name = "success", location = "/main/menuinfo.jsp") })
	public String doList() {
		StringBuffer menuStr = new StringBuffer();
		String errorMessage = "";
		try {
			bean.setUserid(getLoginUser().getUserid());// 把当前登录用户的ID加入到查询条件,根据权限查询对应的功能
			List menuList = funcService.listByParent(bean);
			int size = menuList.size();
			menuStr.append("menuStr:\"");
			for (int i = 0; i < size; i++) {
				FuncEntity funcEntity = (FuncEntity) menuList.get(i);
				menuStr.append("<div><a id=&quot" + funcEntity.getFunccode()
						+ "&quot href=&quotjavascript:_selmenu('");
				menuStr.append(funcEntity.getFunccode() + "','news");
				menuStr.append(funcEntity.getFunccode() + "');&quot><span>");
				menuStr.append(funcEntity.getFuncname());
				menuStr.append("</span></a></div>");
			}
			menuStr.append("\"");
		} catch (Exception e) {
			this.setErrorMessage(e.getMessage());
		}
		// menuStr.append("</ul>\"");
		this.setJsonString(menuStr.toString());

		return SUCCESS;
	}

	@Action(value = "topmenu", results = { @Result(name = "success", location = "/main/topmenu.jsp") })
	public String doTopMenu() {
		StringBuffer menuStr = new StringBuffer();
		String errorMessage = "";
		try {
			bean.setUserid(getLoginUser().getUserid());
			List menuList = funcService.listByParent(bean);
			int size = menuList.size();
			for (int i = 0; i < size; i++) {
				FuncEntity funcEntity = (FuncEntity) menuList.get(i);
				if (i > 0) {
					menuStr.append(",");
				}
				menuStr.append("{&quotmenuid&quot:&quot"
						+ funcEntity.getFunccode()
						+ "&quot,&quotmenuname&quot:&quot"
						+ funcEntity.getFuncname()
						+ "&quot,&quoticon&quot:&quot"
						+ funcEntity.getFuncicon() + "&quot}");
			}
		} catch (Exception e) {
			this.setErrorMessage(e.getMessage());
		}
		this.setJsonString(menuStr.toString());

		return SUCCESS;
	}

	public String getBiztypename() {
		return "menu";
	}

	public FuncService getFuncService() {
		return funcService;
	}

	public void setFuncService(FuncService funcService) {
		this.funcService = funcService;
	}

	public FuncEntity getBean() {
		return bean;
	}

	public void setBean(FuncEntity bean) {
		this.bean = bean;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}
