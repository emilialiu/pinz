package com.dimine.sys.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.ShortcutEntity;
import com.dimine.sys.service.FuncService;

/**
 * 权限功能类
 * 
 * @author LCF
 */
@Namespace("/manager/sys/func")
@Scope("request")
@Controller("funcAction")
public class FuncAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 业务逻辑处理
	@Resource
	private FuncService<FuncEntity> funcService = null;

	// 实例bean
	private FuncEntity bean = new FuncEntity();

	// 功能编号
	private String funccode = "";

	// 功能编号数组
	private String[] funcInfo;

	// 需要写到页面的字符串
	private String htmlstr = "";

	private String currentName;

	//
	private String date = "";
	
	//jb图标级别
	private String tbjb;

	public String getTbjb() {
		return tbjb;
	}

	public void setTbjb(String tbjb) {
		this.tbjb = tbjb;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 菜单树型显示
	 * 
	 * @return
	 */
	@Action(value = "tree", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doTree() {
		try {
			List<FuncEntity> treeData = funcService.tree(bean.getFunccode());
			String jsonData = createJsonData(treeData);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 创建JSON格式的对象 <功能详细描述>
	 * 
	 * @param funcList
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String createJsonData(List<FuncEntity> funcList) {
		StringBuffer jsonData = new StringBuffer();
		int length = funcList.size();
		if (length <= 0) {
			return "";
		}
		FuncEntity func;
		/*
		 * FuncEntity func = funcList.get(0); jsonData.append("{\"id\":\"");
		 * jsonData.append(func.getFunccode());
		 * jsonData.append("\", \"pid\":\"");
		 * jsonData.append(func.getParentfunccode());
		 * jsonData.append("\", \"isParent\":\""); jsonData.append("true");
		 * jsonData.append("\", \"name\":\"");
		 * jsonData.append(func.getFuncname());
		 * jsonData.append("\", \"open\":true},");
		 */
		for (int i = 0; i < length; i++) {
			func = funcList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(func.getFunccode());
			jsonData.append("\", \"pid\":\"");
			jsonData.append(func.getParentfunccode());
			jsonData.append("\", \"name\":\"");
			jsonData.append(func.getFuncname());
			if (Integer.valueOf(func.getChildCount()) >= 1) {
				jsonData.append("\", \"isParent\":\"");
				jsonData.append("true");
				jsonData.append("\", \"close\":\"");
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

	/**
	 * 新增功能 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "add", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doAdd() {
		try {
			String path = "<ul class=\\\"breadcrumb\\\">";
			FuncEntity f = new FuncEntity();
			f = funcService.selectById(bean.getParentfunccode());
			//获取第一级菜单
			path+="<li class='active'>"+f.getParentfuncName()+"</li>";
			//获取第二级菜单
			path+="<li class='active'>"+f.getFuncname()+"</li>";
			//获取本级菜单
			path+="<li class='active'>"+bean.getFuncname()+"</li>";
			path+="</ul>";
			bean.setClasspath(path);
			bean.getFuncnamepy();
			bean.getFuncnamepyshort();
			String funccode = funcService.insert(bean);
			StringBuffer result_string = new StringBuffer("{\"funccode\":\"");
			result_string.append(funccode);
			result_string.append("\"}");
			getRequest().setAttribute("jsonString", result_string.toString());
			this.setErrorFlag("addtrue");
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 修改功能信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "modify", results = {
			@Result(name = "modify", location = "/webpage/manager/sys/func/jsonpager.jsp"),
			@Result(name = "modify_success", location = "/webpage/pub/result.jsp") })
	public String doModify() {
		String result = "modify";
		try {
			if ("modify".equals(getActiontype())) {// 存储

				bean = funcService.selectById(bean.getFunccode());
				List dictlist = funcService.getSelectTblx(null);
				int length = dictlist.size();
				StringBuffer buff = new StringBuffer("");
				for (int i = 0; i < length; i++) {
					DictEntity dict = (DictEntity)dictlist.get(i);
					buff.append("<li>").append("<a onclick=\"onchangeTblx(this)\" id=\""+dict.getCode()+"\">").append(dict.getParamname()).append("</a></li>");
				}
				String str = buff.toString();
				this.setActiontype("modifysave");
				getRequest().setAttribute("jsonString", createJsonData(bean));
				getRequest().setAttribute("getSelectTblx", str);
			} else if ("modifysave".equals(getActiontype())) {// 修改保存
				result = "modify_success";
				String path = "<ul class='breadcrumb'>";
				FuncEntity f = new FuncEntity();
				f = funcService.selectById(bean.getParentfunccode());
				//获取第一级菜单
				path+="<li class='active'>"+f.getParentfuncName()+"</li>";
				//获取第二级菜单
				path+="<li class='active'>"+f.getFuncname()+"</li>";
				//获取本级菜单
				path+="<li class='active'>"+bean.getFuncname()+"</li>";
				path+="</ul>";
				bean.setClasspath(path);
				bean.getFuncnamepy();
				bean.getFuncnamepyshort();
				funcService.update(bean);
				this.setErrorFlag("modifytrue");
			}
		} catch (Exception ee) {
			ee.printStackTrace();
			this.setErrorMessage(ee);
		}
		return result;
	}
	/**
	 * 通过级别改变图标类型
	 * @return
	 */
	@Action(value = "changetbjb", results = {@Result(name = "success", location = "/webpage/manager/sys/shortcut/rightdata.jsp")})
	public String getSelectTblx(){
		try{
		List dictlist = funcService.getSelectTblx(tbjb);
		int length = dictlist.size();
		StringBuffer buff = new StringBuffer("");
		for (int i = 0; i < length; i++) {
			DictEntity dict = (DictEntity)dictlist.get(i);
			buff.append("<li>").append("<a onclick=\"onchangeTblx(this)\" id=\""+dict.getCode()+"\">").append(dict.getParamname()).append("</a></li>");
		}
		this.getRequest().setAttribute("jsonStr",buff.toString());
		}catch (Exception ee) {
				ee.printStackTrace();
				this.setErrorMessage(ee);
			}
		return SUCCESS;
	}
	/**
	 * 组件json对象 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String createJsonData(FuncEntity func) {
		StringBuffer funcString = new StringBuffer("{");
		funcString.append("\"parentfunccode\":\"");
		funcString.append(func.getParentfunccode() == null ? "" : func
				.getParentfunccode());
		funcString.append("\",\"funccode\":\"");
		funcString.append(func.getFunccode() == null ? "" : func.getFunccode());
		funcString.append("\",\"parentname\":\"");
		funcString.append(func.getParentfuncName() == null ? "" : func
				.getParentfuncName());
		funcString.append("\",\"funcname\":\"");
		funcString.append(func.getFuncname() == null ? "" : func.getFuncname());
		funcString.append("\",\"funcnameen\":\"");
		funcString.append(func.getFuncnameen() == null ? "" : func.getFuncnameen());
		funcString.append("\",\"url\":\"");
		funcString.append(func.getUrl() == null ? "" : func.getUrl());
		funcString.append("\",\"actiontype\":\"");
		funcString.append(getActiontype() == null ? "" : getActiontype());
		funcString.append("\",\"functype\":\"");
		funcString.append(func.getFunctype() == null ? "" : func.getFunctype());
		funcString.append("\",\"orderno\":\"");
		funcString.append(func.getOrderno() == null ? "" : func.getOrderno());
		funcString.append("\",\"memo\":\"");
		funcString.append(func.getMemo() == null ? "" : func.getMemo());
		
		funcString.append("\",\"funcnamepy\":\"");
		funcString.append(func.getFuncnamepy() == null ? "" : func.getFuncnamepy());
		funcString.append("\",\"funcnamepyshort\":\"");
		funcString.append(func.getFuncnamepyshort() == null ? "" : func.getFuncnamepyshort());
		funcString.append("\",\"memopy\":\"");
		funcString.append(func.getMemopy() == null ? "" : func.getMemopy());
		funcString.append("\",\"memopyshort\":\"");
		funcString.append(func.getMemopyshort() == null ? "" : func.getMemopyshort());
		
//		funcString.append("\",\"getselecttblx\":\'");
//		funcString.append(str == null ? "" : str);
		
		funcString.append("\",\"funcicon\":'");
		funcString.append(func.getFuncicon() == null ? "" : func.getFuncicon());
		funcString.append("'}");
		//System.out.println(funcString.toString());
		return funcString.toString();
	}

	/**
	 * 删除功能信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDelete() {
		try {
			funcService.delete(bean.getFunccode());
		} catch (Exception ee) {
			this.setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 获得目标父节点下的所有的功能 这个是4.0的版本
	 * 
	 * @return
	 */
	@Action(value = "childfunc", results = { @Result(name = "success", location = "/webpage/main/mainleft.jsp") })
	public String doChildFunc() {
		try {
			StringBuffer htmlstr = new StringBuffer("");
			bean.setUserid(getLoginUser().getUserid());

			htmlstr.append("{\"menus\":[");

			List childFuncData = funcService.listByParent(bean);
			int length = childFuncData.size();
			for (int i = 0; i < length; i++) {
				FuncEntity tmp = (FuncEntity) childFuncData.get(i);
				tmp.setUserid(getLoginUser().getUserid());

				if (i > 0) {
					htmlstr.append(",");
				}
				htmlstr.append("{\"menuid\":\"" + tmp.getFunccode()
						+ "\",\"icon\":\"" + tmp.getFuncicon()
						+ "\",\"menuname\":\"" + tmp.getFuncname() + "\",\n");
				htmlstr.append("	\"menus\":[\n");

				// 查找该节点下的子节点
				try {
					List childFuncList = funcService.searchByRoleid(tmp);
					int length_ = childFuncList.size();
					for (int j = 0; j < length_; j++) {
						FuncEntity tmp_ = (FuncEntity) childFuncList.get(j);
						if (j > 0) {
							htmlstr.append(",");
						}
						htmlstr.append("		{\"menuid\":\"" + tmp_.getFunccode()
								+ "\",\"menuname\":\"" + tmp_.getFuncname()
								+ "\",\"icon\":\"" + tmp_.getFuncicon()
								+ "\",\"url\":\"" + tmp_.getUrl() + "\"}\n");
					}
				} catch (Exception e) {
				}
				htmlstr.append("	]\n}");
			}
			htmlstr.append("]}");
			this.htmlstr = htmlstr.toString();
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 插入获得快捷键
	 * 
	 * @return
	 */
	@Action(value = "insertshortcut", results = { @Result(name = "success", location = "/webpage/manager/sys/shortcut/rightdata.jsp") })
	public String doShortcutInsert() {
		try {
			String array = funccode;
			String []str = array.split(",");
			if(str.length>0){
				funcService.deleteShortcut(this.getLoginUser().getUserid());
			if(str.length>4){
				for(int i=0;i<4;i++){
				funcService.insertShortcut(str[i],i,this.getLoginUser().getUserid());
				}
			}else{
				for(int i=0;i<str.length;i++){
					funcService.insertShortcut(str[i],i,this.getLoginUser().getUserid());
					}
			}
			}
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	/**
	 * 展示快捷键
	 * 
	 * @return
	 */
	@Action(value = "toshortcut", results = { @Result(name = "success", location = "/webpage/manager/sys/shortcut/rightdata.jsp") })
	public String toShortcut() {
		try{
			StringBuffer htmlstr = new StringBuffer("");
			List shortcutlist = funcService.listShortcutByFunc(this.getLoginUser().getUserid());
			int length = shortcutlist.size();
			for (int i = 0; i < length; i++) {
				FuncEntity tmp = (FuncEntity) shortcutlist.get(i);
				if(i==length-1){
					htmlstr.append(tmp.getUrl());
				}else{
				htmlstr.append(tmp.getUrl()+",");
				}
			}
			this.getRequest().setAttribute("jsonStr",htmlstr.toString());
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 获得快捷键右
	 * 
	 * @return
	 */
	@Action(value = "shortcut", results = { @Result(name = "success", location = "/webpage/manager/sys/shortcut/rightdata.jsp") })
	public String doShortcutRightTree() {
		try{
			StringBuffer htmlstr = new StringBuffer("");
			List shortcuttree = funcService.listByShortCut(this.getLoginUser().getUserid());//-----
			int length = shortcuttree.size();
			//htmlstr.append("<div class='dd' id='nestable2'>");
			htmlstr.append("<div class='dd dd-draghandle' id='nestable2'>");
			htmlstr.append("<ol class='dd-list'>\n");
			for (int i = 0; i < length; i++) {
				ShortcutEntity tmp = (ShortcutEntity) shortcuttree.get(i);
				htmlstr.append("<li class=\"dd-item\" data-id=\""+tmp.getFunccode()+"\">\n");
				htmlstr.append("<div class='dd-handle'>\n");
				htmlstr.append(tmp.getFuncname());
				htmlstr.append("<span class='sticker'>\n");
				htmlstr.append("<span class='label label-success arrowed-in'>\n");
				htmlstr.append("<i class='ace-icon fa fa-check bigger-110'></i>\n");
				htmlstr.append("</span>\n");
				htmlstr.append("</span>\n");
				htmlstr.append("</div>\n");
				htmlstr.append("</li>\n");
			}
			if(length<1){
			  htmlstr.append("<div class='dd dd-draghandle'><div class='dd-empty'></div></div>");
			}
			htmlstr.append("</ol>\n");
			htmlstr.append("</div>\n");
			//System.out.println("htmlstrRight="+htmlstr+"----------------------------------------------------------------------");
			this.getRequest().setAttribute("jsonStr",htmlstr.toString());
		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 获得快捷键左树
	 * 
	 * @return
	 */
	@Action(value = "shortcutlefttree", results = { @Result(name = "success", location = "/webpage/manager/sys/shortcut/jsondata.jsp") })
	public String doShortcutLeftTree() {
		try {
			StringBuffer htmlstr = new StringBuffer("");
			bean.setUserid(getLoginUser().getUserid());
			List childFuncParent = funcService.listByParentTopShortCut(bean);//-----
			int length = childFuncParent.size();
			htmlstr.append("<div class='dd' id='nestable'>");
			htmlstr.append("<ol class='dd-list'>\n");
			for (int i = 0; i < length; i++) {//length=2
				FuncEntity tmp = (FuncEntity) childFuncParent.get(i);
				if("0".equals(tmp.getChildCount())){
					htmlstr.append("<li class=\"dd-item\" data-id=\""+tmp.getFunccode()+"\">\n");
					htmlstr.append("<div class='dd-handle'>\n");
					htmlstr.append(tmp.getFuncname());
					
					htmlstr.append("<span class='sticker'>\n");
					htmlstr.append("<span class='label label-success arrowed-in'>\n");
					htmlstr.append("<i class='ace-icon fa fa-check bigger-110'></i>\n");
					htmlstr.append("</span>\n");
					htmlstr.append("</span>\n");
					
					htmlstr.append("</div>\n");
					htmlstr.append("</li>\n");
				}else{
					htmlstr.append("<li class=\"dd-item\" data-id=\""+tmp.getFunccode()+"\">\n");
					htmlstr.append("<div class='dd-nodrag'>\n");
					htmlstr.append(tmp.getFuncname());
					htmlstr.append("</div>\n");
					htmlstr.append("<ol class='dd-list'>\n");
					//---------
					bean.setFunccode(tmp.getFunccode());
					List childFuncParentSec = funcService.listByParentSecShortCut(bean);//-----
					int lengthsec = childFuncParentSec.size();//length=5
					for (int j = 0; j < lengthsec; j++){
						FuncEntity tmpsec = (FuncEntity) childFuncParentSec.get(j);	
						if("0".equals(tmpsec.getChildCount())){
							htmlstr.append("<li class=\"dd-item\" data-id=\""+tmpsec.getFunccode()+"\">\n");
							htmlstr.append("<div class='dd-handle'>\n");
							htmlstr.append(tmpsec.getFuncname());
							htmlstr.append("<span class='sticker'>\n");
							htmlstr.append("<span class='label label-success arrowed-in'>\n");
							htmlstr.append("<i class='ace-icon fa fa-check bigger-110'></i>\n");
							htmlstr.append("</span>\n");
							htmlstr.append("</span>\n");
							htmlstr.append("</div>\n");
							htmlstr.append("</li>\n");
						}else{
							htmlstr.append("<li class=\"dd-item\" data-id=\""+tmpsec.getFunccode()+"\">\n");
							htmlstr.append("<div class='dd-nodrag'>\n");
							htmlstr.append(tmpsec.getFuncname());
							htmlstr.append("</div>\n");
							htmlstr.append("<ol class='dd-list'>\n");
							bean.setFunccode(tmpsec.getFunccode());
							List childFuncParentthr = funcService.listByParentSecShortCut(bean);//-----
							int lengththr = childFuncParentthr.size();//length=4
							for (int k = 0; k < lengththr; k++){
								FuncEntity tmpthr = (FuncEntity) childFuncParentthr.get(k);	
								if("0".equals(tmpthr.getChildCount())){
									htmlstr.append("<li class=\"dd-item\" data-id=\""+tmpthr.getFunccode()+"\">\n");
									htmlstr.append("<div class='dd-handle'>\n");
									htmlstr.append(tmpthr.getFuncname());
									htmlstr.append("<span class='sticker'>\n");
									htmlstr.append("<span class='label label-success arrowed-in'>\n");
									htmlstr.append("<i class='ace-icon fa fa-check bigger-110'></i>\n");
									htmlstr.append("</span>\n");
									htmlstr.append("</span>\n");
									htmlstr.append("</div>\n");
									htmlstr.append("</li>\n");
								}else{
									htmlstr.append("<li class=\"dd-item\" data-id=\""+tmpthr.getFunccode()+"\">\n");
									htmlstr.append("<div class='dd-nodrag'>\n");
									htmlstr.append(tmpthr.getFuncname());
									htmlstr.append("</div>\n");
									htmlstr.append("<ol class='dd-list'>\n");
									bean.setFunccode(tmpthr.getFunccode());
									List childFuncParentfro = funcService.listByParentSecShortCut(bean);//-----
									int lengthfro = childFuncParentfro.size();
									for (int s = 0; s < lengthfro; s++){
										FuncEntity tmpfro = (FuncEntity) childFuncParentfro.get(s);	
										htmlstr.append("<li class=\"dd-item\" data-id=\""+tmpfro.getFunccode()+"\">\n");
										htmlstr.append("<div class='dd-handle'>\n");
										htmlstr.append(tmpfro.getFuncname());
										htmlstr.append("<span class='sticker'>\n");
										htmlstr.append("<span class='label label-success arrowed-in'>\n");
										htmlstr.append("<i class='ace-icon fa fa-check bigger-110'></i>\n");
										htmlstr.append("</span>\n");
										htmlstr.append("</span>\n");
										htmlstr.append("</div>\n");
										htmlstr.append("</li>\n");
									}
									htmlstr.append("</ol>\n");
									htmlstr.append("</li>\n");
								}
							}
							htmlstr.append("</ol>\n");
							htmlstr.append("</li>\n");
						}
					
					}
					htmlstr.append("</ol>\n");
					htmlstr.append("</li>\n");
					//---------------------
				}
				
			}
			htmlstr.append("</ol>\n");
			htmlstr.append("</div>\n");
//			System.out.println("htmlstr="+htmlstr);
			this.getRequest().setAttribute("jsonStr",htmlstr.toString());
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	/**
	 * 获得目标父节点下的所有的功能 这个是4.0的版本
	 * 
	 * @return
	 */
	@Action(value = "childfuncs", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doChildFuncs2() {
		try {
			//StringBuffer sb = new StringBuffer();
			FuncEntity entity = funcService.selectById(bean.getFunccode());
			//本级的功能id
			String funcode = entity.getFunccode();
			//上级的功能id
			String parrentfuncod = entity.getParentfunccode();
			//最顶级的功能id
			entity = funcService.selectById(parrentfuncod);
			String parrentfuncod1 = entity.getParentfunccode();
			StringBuffer htmlstr = new StringBuffer("");
			bean.setUserid(getLoginUser().getUserid());
			htmlstr.append("{funcinfo:\'");
			List childFuncParent = funcService.listByParentTop(bean);
			int length = childFuncParent.size();
			for (int i = 0; i < length; i++) {
				FuncEntity tmp = (FuncEntity) childFuncParent.get(i);
				if("0".equals(tmp.getChildCount())){
					htmlstr.append("<li>");
					htmlstr.append("	<a href=\""+bean.getRootpath()+tmp.getUrl()+"\">");
					htmlstr.append(tmp.getFuncicon());
					htmlstr.append("		<span class=menu-text> "+tmp.getFuncname()+" </span>");
					htmlstr.append("	</a>");
					htmlstr.append("<li>");
				}else{
					if(tmp.getFunccode().equals(parrentfuncod1)){
						htmlstr.append("<li class=\"open\">");
					}else{
						htmlstr.append("<li >");
					}
					htmlstr.append("	<a href=\"#\" class=\"dropdown-toggle\">");
					htmlstr.append(tmp.getFuncicon());
					htmlstr.append("		<span class=menu-text> "+tmp.getFuncname()+" </span>");
					htmlstr.append("		<b class=\"arrow icon-angle-down\"></b>");
					htmlstr.append("	</a>");
					//---------
					bean.setFunccode(tmp.getFunccode());
					List childFuncParentSec = funcService.listByParentSec(bean);
					if(tmp.getFunccode().equals(parrentfuncod1)){
						htmlstr.append("<ul class=\"submenu\" style=\"display: block;\">");
					}else{
						htmlstr.append("<ul class=\"submenu\">");
					}
					
					int lengthsec = childFuncParentSec.size();
					for (int j = 0; j < lengthsec; j++){
						FuncEntity tmpsec = (FuncEntity) childFuncParentSec.get(j);	
						if("0".equals(tmpsec.getChildCount())){
							htmlstr.append("<li>");
							htmlstr.append("	<a href="+bean.getRootpath()+tmpsec.getUrl()+">");
							htmlstr.append("		<i class=\"icon-double-angle-right\"></i>");
							htmlstr.append("			"+tmpsec.getFuncname()+"");
							htmlstr.append("	</a>");
							htmlstr.append("</li>");
						}else{
							if(tmpsec.getFunccode().equals(parrentfuncod)){
								htmlstr.append("<li class=\"open\"> ");
							}else{
								htmlstr.append("<li> ");
							}
							
							htmlstr.append("	<a href=\"#\" class=\"dropdown-toggle\">");
							htmlstr.append("		<i class=\"icon-double-angle-right\"></i>");
							htmlstr.append("			"+tmpsec.getFuncname()+"");
							htmlstr.append("		<b class=\"arrow icon-angle-down\"></b>");
							htmlstr.append("	</a>");
							
							bean.setFunccode(tmpsec.getFunccode());
							List childFuncParentthr = funcService.listByParentSec(bean);
							if(tmpsec.getFunccode().equals(parrentfuncod)){
								htmlstr.append("<ul class=\"submenu\" style=\"display: block;\">");
							}else{
								htmlstr.append("<ul class=\"submenu\">");
							}
						
							int lengththr = childFuncParentthr.size();
							for (int k = 0; k < lengththr; k++){
								FuncEntity tmpthr = (FuncEntity) childFuncParentthr.get(k);	
								if("0".equals(tmpthr.getChildCount())){
									htmlstr.append("<li>");
									htmlstr.append("	<a href="+bean.getRootpath()+tmpthr.getUrl()+"?functioncode="+tmpthr.getFunccode()+">");
									htmlstr.append(tmpthr.getFuncicon());
									htmlstr.append("			"+tmpthr.getFuncname()+"");
									htmlstr.append("	</a>");
									htmlstr.append("</li>");
								}else{
									htmlstr.append("<li>");
									htmlstr.append("	<a href=\"#\" class=\"dropdown-toggle\">");
									htmlstr.append("		<i class=\"icon-pencil\"></i>");
									htmlstr.append("			"+tmpthr.getFuncname()+"");
									htmlstr.append("		<b class=\"arrow icon-angle-down\"></b>");
									htmlstr.append("	</a>");
									
									bean.setFunccode(tmpthr.getFunccode());
									List childFuncParentfro = funcService.listByParentSec(bean);
									htmlstr.append("<ul class=\"submenu\">");
									int lengthfro = childFuncParentfro.size();
									for (int s = 0; s < lengthfro; s++){
										FuncEntity tmpfro = (FuncEntity) childFuncParentfro.get(s);	
											htmlstr.append("<li>");
											htmlstr.append("	<a href="+bean.getRootpath()+tmpfro.getUrl()+">");
											htmlstr.append("		<i class=\"icon-plus\"></i>");
											htmlstr.append("			"+tmpfro.getFuncname()+"");
											htmlstr.append("	</a>");
											htmlstr.append("</li>");
									}
									htmlstr.append("</ul>");
									htmlstr.append("</li>");
								}
							}
							htmlstr.append("</ul>");
							htmlstr.append("</li>");
						}
					
					}
					htmlstr.append("</ul>");
					htmlstr.append("</li>");
					//---------------------
				}
				
			}
			htmlstr.append("\'}");
			this.getRequest().setAttribute("jsonString",htmlstr.toString());
 		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 获取导航栏目
	 * @return
	 */
	@Action(value = "getNavigation", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getNavigation() {
		try {
			bean = funcService.selectById(bean.getFunccode());
			this.getRequest().setAttribute("jsonStr",bean.getClasspath());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.setErrorMessage(e);
		}
		
		return SUCCESS;
	}
	
	@Override
	public String getBiztypename() {
		return "系统功能信息管理";
	}

	public String[] getFuncInfo() {
		return funcInfo;
	}

	public void setFuncInfo(String[] funcInfo) {
		this.funcInfo = funcInfo;
	}

	public String getHtmlstr() {
		return htmlstr;
	}

	public void setHtmlstr(String htmlstr) {
		this.htmlstr = htmlstr;
	}

	public String getFunccode() {
		return funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public FuncEntity getBean() {
		return bean;
	}

	public void setNode(String id) {
		if (id == null || id.trim().equals("") || id.trim().equals("0")) {
			bean.setFunccode(null);
		} else {
			bean.setFunccode(id);
		}
	}

	public void setBean(FuncEntity bean) {
		this.bean = bean;
	}

	public String getCurrentName() {
		return currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}

	public FuncService getFuncService() {
		return funcService;
	}

	public void setFuncService(FuncService funcService) {
		this.funcService = funcService;
	}

}
