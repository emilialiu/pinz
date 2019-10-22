package com.dimine.sc.action;

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
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.common.ValidException;
import com.dimine.sc.entity.T_sc_weightparameterEntity;
import com.dimine.sc.service.T_sc_weightparameterService;

/**
 * 用于对业务参数配置进行系列的操作的action
 * 
 * @author dimine 2016-07-04 09:45:39
 * 
 */
@Namespace("/webpage/biz/sc/weightparameter")
@Scope("request")
@Controller("t_sc_weightparameterAction")
public class T_sc_weightparameterAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_sc_weightparameterAction.class);
	// 业务处理
	@Resource
	private T_sc_weightparameterService<T_sc_weightparameterEntity> t_sc_weightparameterService;

	// 参数实体
	private T_sc_weightparameterEntity bean = new T_sc_weightparameterEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对业务参数配置进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("paraname"))
									return "d1.paramname";
								if (f.equals("unit"))
									return "d2.paramname";
								return f;
							}

							@Override
							public String executeData(String f, String o,
									String d) {
								if (o.equals("bw") || o.equals("en"))
									return (new StringBuilder("'")).append(d)
											.append("%'").toString();
								if (o.equals("ew") || o.equals("en"))
									return (new StringBuilder("'%")).append(d)
											.append("'").toString();
								if(o.equals("cn")||o.equals("nc")){
										if(d.contains("%")){
										String y="";
										char[] b=d.toCharArray();
										for (char c : b) {
											if(c=='%'){
												String e="\\"+c;
												y+=e;
											}else{
											y+=c;
											}
										}
										d=y;
										}
										return(new StringBuilder("'%")).append(d).append("%'").toString();
								}else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<T_sc_weightparameterEntity> dataList = t_sc_weightparameterService.selectByList(bean);
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
	 * 新增业务参数配置信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sc/weightparameter/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增业务参数配置信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_sc_weightparameterService.insert(bean);
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
	 * 删除业务参数配置信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_sc_weightparameterService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对业务参数配置进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sc/weightparameter/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_sc_weightparameterService.selectById(bean.getParaid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对业务参数配置进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
				if(StringUtils.isBlank(bean.getParaid())){
						bean.setCreateid(this.getLoginUser().getUserid());
						bean.setTdeptid(getLoginUser().getOrgid());
						String id=KeyUtils.createKeyID();
						bean.setParaid(id);
						t_sc_weightparameterService.insert(bean);
						bean=t_sc_weightparameterService.selectById(bean);
				}else{    
					bean.setModifyid(this.getLoginUser().getUserid());
					t_sc_weightparameterService.update(bean, getActiontype());	
					bean=t_sc_weightparameterService.selectById(bean);
   			}			
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
	 * 对字典值列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "selectList", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String selectList() {
		try {
			List<T_sc_weightparameterEntity> datalist=t_sc_weightparameterService.selectList(bean);
			this.setJsonStr(JSONUtil.toJSONString(datalist));
		} catch (Exception ex) {
			this.setErrorMessage(ex);
		}
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "业务参数配置信息管理";
	}

	public T_sc_weightparameterEntity getBean() {
		return bean;
	}

	public void setBean(T_sc_weightparameterEntity bean) {
		this.bean = bean;
	}

	public T_sc_weightparameterService<T_sc_weightparameterEntity> getT_sc_weightparameterService() {
		return t_sc_weightparameterService;
	}

	public void setT_sc_weightparameterService(T_sc_weightparameterService<T_sc_weightparameterEntity> t_sc_weightparameterService) {
		this.t_sc_weightparameterService = t_sc_weightparameterService;
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
