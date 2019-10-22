package com.dimine.shortcut.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.common.ValidException;
import com.dimine.shortcut.entity.Sys_shortcut_funcEntity;
import com.dimine.shortcut.service.Sys_shortcut_funcService;

/**
 * 用于对人员快捷功能进行系列的操作的action
 * 
 * @author dimine 2016-09-09 17:39:35
 * 
 */
@Namespace("/webpage/biz/shortcut/func")
@Scope("request")
@Controller("sys_shortcut_funcAction")
public class Sys_shortcut_funcAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Sys_shortcut_funcAction.class);
	// 业务处理
	@Resource
	private Sys_shortcut_funcService<Sys_shortcut_funcEntity> sys_shortcut_funcService;

	// 参数实体
	private Sys_shortcut_funcEntity bean = new Sys_shortcut_funcEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对人员快捷功能进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setUserid(this.getLoginUser().getUserid());
			List<Sys_shortcut_funcEntity> dataList = sys_shortcut_funcService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 对人员快捷功能进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "selectlist", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String selectlist() {
		try {
			bean.setUserid(this.getLoginUser().getUserid());
			List<Sys_shortcut_funcEntity> datalist = sys_shortcut_funcService.selectList(bean);
			// 设置页面数据
			this.setJsonStr(JSONUtil.toJSONString(datalist));
		} catch (Exception ex) {
			this.setErrorMessage(ex);

		}
		
		return SUCCESS;
	}
	
	/**
	 * 新增人员快捷功能信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/shortcut/func/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增人员快捷功能信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			sys_shortcut_funcService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除人员快捷功能信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			sys_shortcut_funcService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对人员快捷功能进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/shortcut/func/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = sys_shortcut_funcService.selectById(bean.getUserid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对人员快捷功能进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			sys_shortcut_funcService.update(bean, getActiontype());				
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 批量维护常用功能
	 * @return
	 */
	@Action(value = "addFunc", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String addFunc() {
		try {
			//接受前台同步的数据
			if("".equals(bean.getFunccode())){
				this.setJsonStr("n");
				return SUCCESS;
			}
			String[] funccode=bean.getFunccode().split(",");
			Sys_shortcut_funcEntity entity=new Sys_shortcut_funcEntity();
			entity.setUserid(this.getLoginUser().getUserid());
			for(int i=0;i<funccode.length;i++){
				entity.setFunccode(funccode[i]);
				//Sys_shortcut_funcEntity entity1=sys_shortcut_funcService.selectById(entity);
				int count=sys_shortcut_funcService.selectMaxOrderno(entity);
				entity.setOrderno(count+1+"");
				//if(entity1==null){
					sys_shortcut_funcService.insert(entity);
				//}
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr("y");
		return SUCCESS;
	}
	/**
	 * 修改排序
	 * @return
	 */
	@Action(value = "changOrderno", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String changOrderno() {
		try {
			//接受前台同步的数据
			String[] funccode=bean.getFunccode().split(",");
			String[] orderno=bean.getOrderno().split(",");
			Sys_shortcut_funcEntity entity=new Sys_shortcut_funcEntity();
			entity.setUserid(this.getLoginUser().getUserid());
			for(int i=0;i<funccode.length;i++){
				entity.setFunccode(funccode[i]);
				entity.setOrderno(orderno[i]);
				sys_shortcut_funcService.update(entity);
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "人员快捷功能信息管理";
	}

	public Sys_shortcut_funcEntity getBean() {
		return bean;
	}

	public void setBean(Sys_shortcut_funcEntity bean) {
		this.bean = bean;
	}

	public Sys_shortcut_funcService<Sys_shortcut_funcEntity> getSys_shortcut_funcService() {
		return sys_shortcut_funcService;
	}

	public void setSys_shortcut_funcService(Sys_shortcut_funcService<Sys_shortcut_funcEntity> sys_shortcut_funcService) {
		this.sys_shortcut_funcService = sys_shortcut_funcService;
	}

	public String getAddtype() {
		return addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}

	public String getSomename() {
		return somename;
	}

	public void setSomename(String somename) {
		this.somename = somename;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}
	
}
