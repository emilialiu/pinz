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
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sc.entity.T_sc_processEntity;
import com.dimine.sc.service.T_sc_processService;

/**
 * 用于对工序信息进行系列的操作的action
 * 
 * @author dimine 2016-07-04 11:14:52
 * 
 */
@Namespace("/webpage/biz/sc/process")
@Scope("request")
@Controller("t_sc_processAction")
public class T_sc_processAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_sc_processAction.class);
	// 业务处理
	@Resource
	private T_sc_processService<T_sc_processEntity> t_sc_processService;

	// 参数实体
	private T_sc_processEntity bean = new T_sc_processEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	
	@Action(value = "doEnter", results = { @Result(name = "success", location = "/webpage/biz/sc/process/main.jsp") })
	public String doEnter(){
		return SUCCESS;
	}
	/**
	 * 加载工序单元树
	 * @return
	 */
	@Action(value = "tree", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String tree() {
		try {
			if (StringUtils.isNotBlank(bean.getProcid())) {				
				bean.setTdeptid(this.getLoginUser().getOrgid());
			}
			List<T_sc_processEntity> processList = t_sc_processService
					.getGxList(bean);
			String jsonData = createJsonData(processList);
			System.out.println(jsonData);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}
	/**
	 * 拼接json字符串
	 * @param projectList
	 * @return
	 */
	private String createJsonData(List<T_sc_processEntity> projectList) {
		StringBuffer jsonData = new StringBuffer();
		int length = projectList.size();
		if (length <= 0)
			return "";
		for (int i = 0; i < length; i++) {
			T_sc_processEntity process = projectList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(process.getProcid());
			jsonData.append("\", \"pid\":\"");
			if (process.getParentid() == null
					|| process.getParentid() == "")
				jsonData.append("");
			else
				jsonData.append(process.getParentid());
			jsonData.append("\", \"name\":\"");
			jsonData.append(process.getProctname());
			jsonData.append("\", \"deptid\":\"");
			if (process.getTdeptid() == null
					|| process.getTdeptid() == "")
				jsonData.append("");
			else
				jsonData.append(process.getTdeptid());
			if (Integer.valueOf(process.getChildCount()).intValue() >= 1) {
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
	 * 对工序信息进行列表查询操作
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
								if (f.equals("parentname"))
									return "(select ProctName from T_SC_Process where ProcID=t.parentid)";
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
			bean.setPager(this.getPager());
			if (StringUtils.isNotBlank(bean.getTechid())) {				
				bean.setTdeptid(this.getLoginUser().getOrgid());
			}
			if(StringUtils.isEmpty(bean.getPager().getOrderField())){
				bean.getPager().setOrderField("SerialNo");
			}
			List<T_sc_processEntity> dataList = t_sc_processService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
			System.out.println(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 新增工序信息信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sc/process/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增工序信息信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_sc_processService.insert(bean);
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
	 * 删除工序信息信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			bean.setModifyid(this.getLoginUser().getUserid());
			//逻辑删除自己和本身下面所有的下级节点
			t_sc_processService.delete(bean);
			//查询该节点的父节点是否有孩子节点，如果没有孩子节点，则更新它为叶子节点
			if(t_sc_processService.selectChildCount(bean)==0){
				t_sc_processService.updateParentLeafInfo(bean);
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对工序信息进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sc/process/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_sc_processService.selectById(bean.getProcid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对工序信息进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
   				if(StringUtils.isBlank(bean.getProcid())){
   						bean.setTdeptid(this.getLoginUser().getOrgid());
   						bean.setIsdel("0");
   						bean.setIsleaf("1");
   						bean.setProcid(KeyUtils.createKeyID());
   	   					bean.setCreateid(getLoginUser().getUserid());
   	   					bean.setParentstr(t_sc_processService.getParentStr(bean));
   	   					t_sc_processService.insert(bean);
   				}else{    
   						bean.setModifyid(getLoginUser().getUserid());
   						t_sc_processService.update(bean, getActiontype());
   				}
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
	 * 首页查询工艺
	 * 
	 * @return
	 */
	@Action(value = "selectlistForSY", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String selectlistForSY() {
		try {
			List<T_sc_processEntity> datalist = t_sc_processService.selectGongXuByGongYi(bean);
			// 设置页面数据
			this.setJsonStr(JSONUtil.toJSONString(datalist));
		} catch (Exception ex) {
			this.setErrorMessage(ex);

		}
		
		return SUCCESS;
	}
	

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "工序信息信息管理";
	}

	public T_sc_processEntity getBean() {
		return bean;
	}

	public void setBean(T_sc_processEntity bean) {
		this.bean = bean;
	}

	public T_sc_processService<T_sc_processEntity> getT_sc_processService() {
		return t_sc_processService;
	}

	public void setT_sc_processService(T_sc_processService<T_sc_processEntity> t_sc_processService) {
		this.t_sc_processService = t_sc_processService;
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
