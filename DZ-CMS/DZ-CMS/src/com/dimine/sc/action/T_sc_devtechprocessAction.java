package com.dimine.sc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import jodd.util.StringUtil;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.common.ValidException;
import com.dimine.sc.entity.T_sc_devtechprocessEntity;
import com.dimine.sc.service.T_sc_devtechprocessService;

/**
 * 用于对工序对应设备进行系列的操作的action
 * 
 * @author dimine 2016-08-17 11:06:21
 * 
 */
@Namespace("/webpage/biz/sc/devtechprocess")
@Scope("request")
@Controller("t_sc_devtechprocessAction")
public class T_sc_devtechprocessAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_sc_devtechprocessAction.class);
	// 业务处理
	@Resource
	private T_sc_devtechprocessService<T_sc_devtechprocessEntity> t_sc_devtechprocessService;

	// 参数实体
	private T_sc_devtechprocessEntity bean = new T_sc_devtechprocessEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对工序对应设备进行列表查询操作
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
								if (f.equals("memo"))
									return "d.memo";
								if (f.equals("belongname"))
									return "t.teamname";
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
									}
								else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<T_sc_devtechprocessEntity> dataList = t_sc_devtechprocessService.selectByList(bean);
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
	 * 新增工序对应设备信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sc/devtechprocess/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增工序对应设备信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_sc_devtechprocessService.insert(bean);
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
	 * 删除工序对应设备信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_sc_devtechprocessService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对工序对应设备进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sc/devtechprocess/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_sc_devtechprocessService.selectById(bean.getProcid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对工序对应设备进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			t_sc_devtechprocessService.update(bean, getActiontype());				
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
	 * 对设备信息进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "devicelist", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String devicelist() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {

							@Override
							public String executeField(String f) {
								if (f.equals("memo"))
									return "d.memo";
								if (f.equals("belongname"))
									return "t.teamname";
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
			if(StringUtil.isEmpty(bean.getTdeptid())){
				bean.setTdeptid(this.getLoginUser().getOrgid());				
			}
			int rowCount = t_sc_devtechprocessService.selectDeviceByCount(bean);
			bean.getPager().setRowCount(rowCount);
			List<T_sc_devtechprocessEntity> dataList = t_sc_devtechprocessService.selectDeviceByList(bean);
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
	 * 批量维护工序和设备之间的关系
	 * @return
	 */
	@Action(value = "addprocessDevice", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String addprocessDevice() {
		try {
			//接受前台的数据
			if("".equals(bean.getDevid())){
				this.setJsonStr("n");
				return SUCCESS;
			}
			String[] devid=bean.getDevid().split(",");
			T_sc_devtechprocessEntity entity=new T_sc_devtechprocessEntity();
			entity.setProcid(bean.getProcid());
			entity.setTdeptid(bean.getTdeptid());
			for(int i=0;i<devid.length;i++){
				entity.setDevid(devid[i]);
				t_sc_devtechprocessService.insert(entity);
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr("y");
		return SUCCESS;
	}
	
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "工序对应设备信息管理";
	}

	public T_sc_devtechprocessEntity getBean() {
		return bean;
	}

	public void setBean(T_sc_devtechprocessEntity bean) {
		this.bean = bean;
	}

	public T_sc_devtechprocessService<T_sc_devtechprocessEntity> getT_sc_devtechprocessService() {
		return t_sc_devtechprocessService;
	}

	public void setT_sc_devtechprocessService(T_sc_devtechprocessService<T_sc_devtechprocessEntity> t_sc_devtechprocessService) {
		this.t_sc_devtechprocessService = t_sc_devtechprocessService;
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
