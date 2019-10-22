package com.dimine.bi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.common.ValidException;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.bi.entity.T_bi_threeoreEntity;
import com.dimine.bi.service.T_bi_threeoreService;

/**
 * 用于对三级矿量信息表进行系列的操作的action
 * 
 * @author dimine 2017-08-15 13:43:48
 * 
 */
@Namespace("/webpage/biz/bi/threeore")
@Scope("request")
@Controller("t_bi_threeoreAction")
@ParentPackage("json-default")
public class T_bi_threeoreAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_bi_threeoreAction.class);
	// 业务处理
	@Resource
	private T_bi_threeoreService<T_bi_threeoreEntity> t_bi_threeoreService;

	// 参数实体
	private T_bi_threeoreEntity bean = new T_bi_threeoreEntity();
	
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	

	/**
	 * 对三级矿量信息表进行列表查询操作
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
			List<T_bi_threeoreEntity> dataList = t_bi_threeoreService.selectByList(bean);
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
	 * 新增三级矿量信息表信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/bi/threeore/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增三级矿量信息表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_bi_threeoreService.insert(bean);
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
	 * 删除三级矿量信息表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_bi_threeoreService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对三级矿量信息表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/bi/threeore/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_bi_threeoreService.selectById(bean.getThreeoreid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对三级矿量信息表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			t_bi_threeoreService.update(bean, getActiontype());				
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

	
	@Action(value = "threeoreInter", results = { @Result(name = "success", type = "json" , params={"root","jsonMap"})})
	public String threeoreInter() {
		
		try{
			List<T_bi_threeoreEntity> dataList =t_bi_threeoreService.selectByYearValue(bean);
			if(dataList==null || dataList.size()==0){
				t_bi_threeoreService.insert(bean);
			}else{
				t_bi_threeoreService.update(bean, getActiontype());
			}
			jsonMap.put("success", true);
			jsonMap.put("errormessage","");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("success", false);
			jsonMap.put("errormessage","调用失败:"+e);
		}
		this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		return SUCCESS;
	}
	
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "三级矿量信息表信息管理";
	}

	public T_bi_threeoreEntity getBean() {
		return bean;
	}

	public void setBean(T_bi_threeoreEntity bean) {
		this.bean = bean;
	}

	public T_bi_threeoreService<T_bi_threeoreEntity> getT_bi_threeoreService() {
		return t_bi_threeoreService;
	}

	public void setT_bi_threeoreService(T_bi_threeoreService<T_bi_threeoreEntity> t_bi_threeoreService) {
		this.t_bi_threeoreService = t_bi_threeoreService;
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

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
}
