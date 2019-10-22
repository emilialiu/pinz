package com.dimine.home.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.WebUtil;
import com.dimine.home.service.HomeService;
import com.dimine.sys.entity.FuncEntity;
import com.dimine.sys.entity.SystemParamEntity;

/**
 * 首页功能类
 * 
 * @author ssm
 */
@Namespace("/main/home")
@Scope("request")
@Controller("homeAction")
public class HomeAction extends BaseAction {
	FuncEntity bean = new FuncEntity();
	// 系统参数
	SystemParamEntity param = new SystemParamEntity();
	// 业务逻辑处理
	@Resource
	private HomeService<FuncEntity> homeservice = null;
	// 导航栏搜索传进来的参数值
	private String query;
	//国际化
	@Action(value = "doEnter", results = { @Result(name = "success", location = "/webpage/main/main.jsp") })
	public String doEnter(){
		return SUCCESS;
	}
	
	/**
	 * 加载首页一级菜单
	 * @return
	 */
	
	@Action(value = "doSelectTopfuncs", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doSelectTopfuncs(){
		try{
			StringBuffer htmlstr = new StringBuffer("");
			bean.setUserid(getLoginUser().getUserid());
			htmlstr.append("{funcinfo:\'");
			List childFuncParent = homeservice.listSelectParentTop(bean);
			int length = childFuncParent.size();
			for (int i = 0; i < length; i++) {
				FuncEntity tmp = (FuncEntity) childFuncParent.get(i);
/*				if ("0".equals(tmp.getChildCount())) {
					htmlstr.append("<a id=\"div"+tmp.getFunccode()+"\" href=\"/sdhj/main/adminindex.jsp#dimine/main/main.jsp\" class=\"navbar-brand\"> ");
					htmlstr.append("<img src=\"../images/sy.jpg\" height=\"35\" /> </a>");
				}else{*/
				htmlstr.append("<div class=\"navbar-header pull-left\">");
					htmlstr.append("<ul id=\"div"+tmp.getFunccode()+"\" class=\"menu"+tmp.getFunccode()+"\"> <li><span onclick=\"javascript:openinfo(\\'"+tmp.getFunccode()+"\\')\">"+getFuncname(tmp)+"</span></li> </ul> ");
					htmlstr.append(" </div>");
//				}
			}
			htmlstr.append("\'}");
			this.getRequest().setAttribute("jsonString", htmlstr.toString());
		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Action(value = "onLoadFristUrl", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String onLoadFristUrl(){
		try{
			StringBuffer htmlstr = new StringBuffer("");
			bean.setUserid(getLoginUser().getUserid());
			List<FuncEntity> childFuncParent = homeservice.listByParentTop(bean);
			if(childFuncParent.isEmpty()){
				htmlstr.append("{funcinfo:\'ture,\'}");
			}else{
				FuncEntity tmp = (FuncEntity) childFuncParent.get(0);
				if ("0".equals(tmp.getChildCount())) {
					htmlstr.append("{funcinfo:\'ture,"+tmp.getUrl()+"\'}");
				}else{
					htmlstr.append("{funcinfo:\'false,"+tmp.getUrl()+"\'}"); 
				}
			}
			this.getRequest().setAttribute("jsonString", htmlstr.toString());
		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 获得目标父节点下的所有的功能 这个是4.0的版本
	 * 
	 * @return
	 */
	@Action(value = "childfuncs", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doChildFuncs() {
		try {
			// StringBuffer sb = new StringBuffer();
			StringBuffer htmlstr = new StringBuffer("");
			bean.setUserid(getLoginUser().getUserid());
			htmlstr.append("{funcinfo:\'");
			List childFuncParent = homeservice.listByParentTop(bean);
			int length = childFuncParent.size();
			for (int i = 0; i < length; i++) {
				FuncEntity tmp = (FuncEntity) childFuncParent.get(i);
				//获取第一级
				if ("0".equals(tmp.getChildCount())) {
					if(i==0){
						htmlstr.append("<li class=\"active\">");
						htmlstr.append(" <a data-url=\"dimine" + tmp.getUrl()+ "\" href=\"#\" funcname=\"" + getFuncname(tmp) + "\" url=\"" + tmp.getUrl() + "\" funccode=\"" + tmp.getFunccode() + "\" onclick=\"collectmenu(this)\"> ");
						htmlstr.append(tmp.getFuncicon());
						htmlstr.append("		" + getFuncname(tmp) + "");
						htmlstr.append("	</a>");
						htmlstr.append("<b class=\"arrow\"></b>");
						htmlstr.append("</li>");
					}else{
						htmlstr.append("<li class=\"\">");
						htmlstr.append(" <a data-url=\"dimine" + tmp.getUrl()+ "\" href=\"#\" funcname=\"" + getFuncname(tmp) + "\" url=\"" + tmp.getUrl() + "\" funccode=\"" + tmp.getFunccode() + "\" onclick=\"collectmenu(this)\"> ");
						htmlstr.append(tmp.getFuncicon());
						htmlstr.append("		" + getFuncname(tmp) + "");
						htmlstr.append("	</a>");
						htmlstr.append("<b class=\"arrow\"></b>");
						htmlstr.append("</li>");
					}
					
				} else {
					htmlstr.append("<li class=\"\">");
					htmlstr.append(" <a href=\"#\" class=\"dropdown-toggle\">");
					htmlstr.append(tmp.getFuncicon());
					htmlstr.append("	   	<span class=\"menu-text\"> "
							+ getFuncname(tmp) + " </span>");
					htmlstr.append("		<b class=\"arrow fa fa-angle-down\"></b>");
					htmlstr.append("	</a>");
					htmlstr.append("<b class=\"arrow\"></b>");
					// ---------
					bean.setFunccode(tmp.getFunccode());
					List childFuncParentSec = homeservice.listByParentSec(bean);
					htmlstr.append("<ul class=\"submenu\">");
					int lengthsec = childFuncParentSec.size();
					for (int j = 0; j < lengthsec; j++) {
						FuncEntity tmpsec = (FuncEntity) childFuncParentSec
								.get(j);
						if ("0".equals(tmpsec.getChildCount())) {
							htmlstr.append("<li class=\"\">");
							htmlstr.append(" <a data-url=\"dimine"
									+ tmpsec.getUrl()
									+ "\" href=\"#\" funcname=\""
									+ getFuncname(tmpsec) + "\" url=\""
									+ tmpsec.getUrl() + "\" funccode=\""
									+ tmpsec.getFunccode()
									+ "\" onclick=\"collectmenu(this)\"> ");
							htmlstr.append(tmpsec.getFuncicon());
							htmlstr.append("		" + getFuncname(tmpsec) + "");
							htmlstr.append("	</a>");
							htmlstr.append("<b class=\"arrow\"></b>");
							htmlstr.append("</li>");
						} else {
							htmlstr.append("<li class=\"\">");
							htmlstr.append("		<a href=\"#\" class=\"dropdown-toggle\">");
							htmlstr.append(tmpsec.getFuncicon());
							htmlstr.append("			" + getFuncname(tmpsec) + "");
							htmlstr.append(" <b class=\"arrow fa fa-angle-down\"></b>");
							htmlstr.append("	</a>");
							htmlstr.append("<b class=\"arrow\"></b>");
							bean.setFunccode(tmpsec.getFunccode());
							List childFuncParentthr = homeservice
									.listByParentSec(bean);
							htmlstr.append("<ul class=\"submenu\">");
							int lengththr = childFuncParentthr.size();
							for (int k = 0; k < lengththr; k++) {
								FuncEntity tmpthr = (FuncEntity) childFuncParentthr.get(k);
								if ("0".equals(tmpthr.getChildCount())) {
									htmlstr.append("<li class=\"\">");
									htmlstr.append(" <a data-url=\"dimine"
											+ tmpthr.getUrl()
											+ "\" href=\"#\" funcname=\""
											+ getFuncname(tmpthr)
											+ "\" url=\""
											+ tmpthr.getUrl()
											+ "\" funccode=\""
											+ tmpthr.getFunccode()
											+ "\" onclick=\"collectmenu(this)\"> ");
									htmlstr.append(tmpthr.getFuncicon());
									htmlstr.append("		" + getFuncname(tmpthr)
											+ "");
									htmlstr.append("	</a>");
									htmlstr.append("<b class=\"arrow\"></b>");
									htmlstr.append("</li>");
								} else {
									htmlstr.append("<li class=\"\">");
									htmlstr.append("	<a href=\"#\" class=\"dropdown-toggle\">");
									htmlstr.append(tmpthr.getFuncicon());
									htmlstr.append("			" + getFuncname(tmpthr)
											+ "");
									htmlstr.append("		 <b class=\"arrow fa fa-angle-down\"></b>");
									htmlstr.append("	</a>");
									htmlstr.append("<b class=\"arrow\"></b>");
									bean.setFunccode(tmpthr.getFunccode());
									List childFuncParentfro = homeservice
											.listByParentSec(bean);
									htmlstr.append("<ul class=\"submenu\">");
									int lengthfro = childFuncParentfro.size();
									for (int s = 0; s < lengthfro; s++) {
										FuncEntity tmpfro = (FuncEntity) childFuncParentfro
												.get(s);
										htmlstr.append("<li class=\"\">");
										htmlstr.append(" <a data-url=\"dimine"
												+ tmpfro.getUrl()
												+ "\" href=\"#\" funcname=\""
												+ getFuncname(tmpfro)
												+ "\" url=\""
												+ tmpfro.getUrl()
												+ "\" funccode=\""
												+ tmpfro.getFunccode()
												+ "\" onclick=\"collectmenu(this)\"> ");
										htmlstr.append(tmpfro.getFuncicon());
										htmlstr.append("		"
												+ getFuncname(tmpfro) + "");
										htmlstr.append("	</a>");
										htmlstr.append("<b class=\"arrow\"></b>");
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
					// ---------------------
				}

			}
			htmlstr.append("\'}");
			this.getRequest().setAttribute("jsonString", htmlstr.toString());
		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获取参数值
	 * 
	 * @return
	 */
	@Action(value = "doparam", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doParam() {
		String value = homeservice.getValueByCode(param.getParamcode());
		StringBuffer htmlstr = new StringBuffer("");
		htmlstr.append("{parainfo:\'");
		htmlstr.append(value);
		htmlstr.append("\'}");
		this.getRequest().setAttribute("jsonString", htmlstr.toString());
		return "success";

	}
	
	/**
	 * 导航栏的搜索
	 */
	@Action(value = "donavigation", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String donavigation() {
		bean.setFuncname(query);
		bean.setMemo(query);
		bean.setUserid(getLoginUser().getUserid());
		List<FuncEntity> list = new ArrayList<FuncEntity>();
		list = homeservice.searchBynav(bean);
		StringBuffer sb = new StringBuffer();
		sb.append("{navigatinfo:");
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			FuncEntity entity = list.get(i);
			sb.append("{");
			sb.append("name:");
			sb.append("\"" + entity.getFuncname()+"("+entity.getFuncnamepyshort() + ")\"").append(",");
			sb.append("url:");
			sb.append("\"" + entity.getUrl() + "\"").append(",");
			sb.append("funccode:");
			sb.append("\"" + entity.getFunccode() + "\"").append("},");
		}
		String str = sb.toString();
		if (list.size() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		str = str + "]" + "}";
		
		this.getRequest().setAttribute("jsonString", str);
		return SUCCESS;
	}

	/**
	 * 首页菜单的搜索
	 */
	@Action(value = "doFuncSearch", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doFuncSearch() {
		bean.setFuncname(query);
		bean.setUserid(getLoginUser().getUserid());
		List<FuncEntity> list = new ArrayList<FuncEntity>();
		list = homeservice.doFuncSearch(bean);
		StringBuffer sb = new StringBuffer();
		sb.append("{navigatinfo:");
		sb.append("[");
		for (int i = 0; i < list.size(); i++) {
			FuncEntity entity = list.get(i);
			sb.append("{");
			sb.append("name:");
			sb.append("\"" + entity.getFuncname() + "\"").append(",");
			sb.append("url:");
			sb.append("\"" + entity.getUrl() + "\"").append(",");
			sb.append("memo:");
			sb.append("\"" + entity.getMemo() + "\"").append(",");
			sb.append("funcicon:");
			sb.append("\'" + entity.getFuncicon() + "\'").append(",");
			sb.append("funccode:");
			sb.append("\"" + entity.getFunccode() + "\"").append("},");
		}
		String str = sb.toString();
		if (list.size() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		str = str + "]" + "}";

		this.getRequest().setAttribute("jsonString", str);
		return SUCCESS;
	}
	
	public String getFuncname(FuncEntity bean){
		String funcname = "";
		if(WebUtil.getCurrentLanguage(getRequest()).equals("zh")){
			funcname = bean.getFuncname();
		}else{
			funcname = bean.getFuncnameen();
		}
		return funcname;
	}

	public FuncEntity getBean() {
		return bean;
	}

	public void setBean(FuncEntity bean) {
		this.bean = bean;
	}

	public HomeService<FuncEntity> getHomeservice() {
		return homeservice;
	}

	public void setHomeservice(HomeService<FuncEntity> homeservice) {
		this.homeservice = homeservice;
	}

	public void setParam(SystemParamEntity param) {
		this.param = param;
	}

	public SystemParamEntity getParam() {
		return param;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
