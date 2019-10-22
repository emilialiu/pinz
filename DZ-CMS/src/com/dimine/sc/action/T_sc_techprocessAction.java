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
import com.dimine.base.common.ValidException;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sc.entity.T_sc_techprocessEntity;
import com.dimine.sc.service.T_sc_techprocessService;
import com.dimine.util.StringUtils;

/**
 * 用于对工艺对应工序进行系列的操作的action
 * 
 * @author dimine 2016-07-13 16:04:47
 * 
 */
@Namespace("/webpage/biz/sc/techprocess")
@Scope("request")
@Controller("t_sc_techprocessAction")
public class T_sc_techprocessAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_sc_techprocessAction.class);
	// 业务处理
	@Resource
	private T_sc_techprocessService<T_sc_techprocessEntity> t_sc_techprocessService;

	// 参数实体
	private T_sc_techprocessEntity bean = new T_sc_techprocessEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对工艺对应工序进行列表查询操作
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
								if (f.equals("processname"))
									return "t3.ProctName";
								if (f.equals("parentprocess"))
									return "(select ProctName from T_SC_Process where ProcID=(select parentid from T_SC_Process where ProcID=t1.ProcID))";
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
								if (o.equals("cn") || o.equals("nc"))
									return (new StringBuilder("'%")).append(d)
											.append("%'").toString();
								else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			if(StringUtils.isNotEmpty(bean.getParam())){
 	 			String[] param=bean.getParam().split(",");
 	 			bean.setParam(param[0]);
 			}
			bean.setPager(this.getPager());
			if(StringUtils.isEmpty(bean.getPager().getOrderField())){
				bean.getPager().setOrderField("t1.serialno");
			}
			List<T_sc_techprocessEntity> dataList = t_sc_techprocessService.selectByList(bean);
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
	 * 新增工艺对应工序信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sc/techprocess/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增工艺对应工序信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_sc_techprocessService.insert(bean);
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
	 * 批量更新工序信息是否计划和验收
	 * @return
	 */
	@Action(value = "updateTechProc", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String updateTechProc() {
		try {
			String[] procid=bean.getProcid().split(",");
			String[] techid=bean.getTechid().split(",");
			String[] isplan=bean.getIsplan().split(",");
			String[] isaccept=bean.getIsaccept().split(",");
			String[] serialno=bean.getSerialno().split(",");
			T_sc_techprocessEntity entity=new T_sc_techprocessEntity();
			for(int i=0;i<procid.length;i++){
				entity.setProcid(procid[i]);
				entity.setTechid(techid[i]);
				entity.setIsplan(isplan[i]);
				entity.setIsaccept(isaccept[i]);
				entity.setSerialno(serialno[i]);
				entity.setCreateid(this.getLoginUser().getUserid());
				t_sc_techprocessService.update(entity, getActiontype());
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr("y");
		return SUCCESS;
	}
	
	/**
	 * 批量维护工艺和工序之间的关系
	 * @return
	 */
	@Action(value = "addTechProc", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String addTechProc() {
		try {
			//接受前台同步的数据
			if("".equals(bean.getProcid())){
				this.setJsonStr("n");
				return SUCCESS;
			}
			String[] procid=bean.getProcid().split(",");
			T_sc_techprocessEntity entity=new T_sc_techprocessEntity();
			entity.setTechid(bean.getTechid());
			for(int i=0;i<procid.length;i++){
				entity.setProcid(procid[i]);
				entity.setTdeptid(this.getLoginUser().getOrgid());
				entity.setCreateid(this.getLoginUser().getUserid());
				t_sc_techprocessService.insert(entity);
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr("y");
		return SUCCESS;
	}
	

	/**
	 * 删除工艺对应工序信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_sc_techprocessService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对工艺对应工序进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sc/techprocess/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_sc_techprocessService.selectById(bean.getTechid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对工艺对应工序进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			t_sc_techprocessService.update(bean, getActiontype());				
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "工艺对应工序信息管理";
	}

	public T_sc_techprocessEntity getBean() {
		return bean;
	}

	public void setBean(T_sc_techprocessEntity bean) {
		this.bean = bean;
	}

	public T_sc_techprocessService<T_sc_techprocessEntity> getT_sc_techprocessService() {
		return t_sc_techprocessService;
	}

	public void setT_sc_techprocessService(T_sc_techprocessService<T_sc_techprocessEntity> t_sc_techprocessService) {
		this.t_sc_techprocessService = t_sc_techprocessService;
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
