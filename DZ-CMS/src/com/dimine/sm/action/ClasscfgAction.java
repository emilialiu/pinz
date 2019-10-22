package com.dimine.sm.action;

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
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.ClasscfgEntity;
import com.dimine.sm.service.ClasscfgService;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.service.DeptService;
import com.dimine.sys.service.DictService;

/**
 * 用于对班次配置表进行系列的操作的action
 * 
 * @author dimine 2015-07-08 16:55:30
 * 
 */
@Namespace("/biz/sm/classcfg")
@Scope("request")
@Controller("classcfgAction")
public class ClasscfgAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(ClasscfgAction.class);
	// 业务处理
	@Resource
	private ClasscfgService<ClasscfgEntity> classcfgService;
	@Resource
	private DictService<DictEntity> dictservice;
	@Resource
	private DeptService<DeptEntity> deptservice;
	// 参数实体
	private ClasscfgEntity bean = new ClasscfgEntity();
	private DictEntity dictbean = new DictEntity();
	private DeptEntity dept=new DeptEntity();	
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;
	/**
	 * 根据项目部查询该部门的班次
	 * 
	 * @return
	 */
	@Action(value = "selectBc", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String selectBc() {
		try{
			List<ClasscfgEntity> datalist = classcfgService.selectBcinfo(getLoginUser().getOrgid());
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (ClasscfgEntity data : datalist) {
					sbf.append("'").append(data.getValue()).append("':'")
							.append(data.getText()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));
				
			} else {
				this.setJsonStr("");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);
	
		}
		return SUCCESS;
	}
	/**
	 * 对班次配置表进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			if (bean.getDeptid() == null || "".equals(bean.getDeptid())) {
				bean.setDeptid(getLoginUser().getOrgid());
			}			
			List<ClasscfgEntity> datalist = classcfgService.selectByList(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (ClasscfgEntity data : datalist) {
					sbf.append("'").append(data.getClasscfgid()).append("':'")
							.append(data.getClasstype()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.getRequest().setAttribute("jsonStr",
						_jsonData.substring(0, length - 1));
			} else {
				this.getRequest().setAttribute("jsonStr", "");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);
			logger.error(ex);
		}
		return SUCCESS;
	}
	@Action(value = "listdata", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String listdata() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {

							@Override
							public String executeField(String f) {

								if (f.equals("classtype"))
									return "d.PARAMNAME";								
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
			List<ClasscfgEntity> dataList = classcfgService.selectListData(bean);
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
	 * 筛选班次列表<功能详细描述>
	 * 
	 * @return
	 * 
	 */
	//获取班次的值
	@Action(value = "bclist", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getBcCombobox() {
		List datalist = classcfgService.selectBcByDeptid(bean);
		String var = JSONUtil.toJSONString(datalist);
		this.getRequest().setAttribute("jsonStr", var);
		return SUCCESS;
	}
	/**
	 * 通过id查询班次<功能详细描述>
	 * 
	 * @return
	 * 
	 */
	@Action(value = "selectbcbyid", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String selectbcbyid() {
		String info="";
		try {
				ClasscfgEntity data=classcfgService.selectById(bean.getClasscfgid());
				if(data!=null){
					info=data.getClasstype();
				}else{
					info="no";
				}
		
		} catch (Exception ex) {
			info="no";
			ex.printStackTrace();		
			logger.error(ex);
		} 
		this.setJsonStr(info);
		return SUCCESS;
	}	
	/**
	 * 时间是否重叠<功能详细描述>
	 * 
	 * @return
	 * 
	 */
	
	@Action(value = "timelist", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String timelist() {
		List<ClasscfgEntity> dataList=classcfgService.selectalltime(bean);
		String var = JSONUtil.toJSONString(dataList);
		this.getRequest().setAttribute("jsonStr", var);
		return SUCCESS;
	}

	/**
	 * 删除班次配置表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			classcfgService.delete(bean);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对班次配置表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String doModify() {
		try {
			bean = classcfgService.selectById(bean);
			this.setJsonStr("[" + JSONUtil.toJSONString(bean) + "]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception ex) {
			this.setErrorMessage(this.getText("modifyfailure"));
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 对班次配置表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
				if (StringUtils.isBlank(bean.getClasscfgid())) {
					int num=classcfgService.selectByCount(bean);
					if(num<4){
						bean.setCreateid(getLoginUser().getUserid());
						classcfgService.insert(bean);
					}else{
						bean.setErrorMessage("该项目部班次已添加！");
					}
				
				} else {
					bean.setModifyid(getLoginUser().getUserid());
					classcfgService.update(bean, getActiontype());
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
	 * 查询所有班次
	 * @return
	 */
	@Action(value = "getCombobox", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getCombobox() {
		//bean.setDeptid(getLoginUser().getOrgid());
		//查出是登陆人是项目部还是分公司
	   try {
		//	dept=deptservice.selectById(getLoginUser().getOrgid());		
	   // if(dept.getOrgtype().equalsIgnoreCase("ZZJGLX001") || dept.getOrgtype().equalsIgnoreCase("ZZJGLX002")){
	    	//bean.setDeptid(null);
	    //}
	   // bean.setDeptid(null);
		List<ClasscfgEntity> datalist = classcfgService.selectList(bean);
		if (datalist != null && datalist.size() > 0) {
			StringBuffer sbf = new StringBuffer();
			for (ClasscfgEntity data : datalist) {
				sbf.append("'").append(data.getClasscfgid()).append("':'")
						.append(data.getClasstype()).append("',");
			}
			String _jsonData = sbf.toString();
			int length = _jsonData.length();
			this.getRequest().setAttribute("jsonStr",
					_jsonData.substring(0, length - 1));
		} else {
			this.getRequest().setAttribute("jsonStr", "");
		}
	    } catch (Exception e) {
				e.printStackTrace();
			}
		return SUCCESS;
	}

	/***
	 * 查询考勤类型
	 * 
	 * @return
	 */

	@Action(value = "getClockingIn", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getClockingIn() {
		try {
			// bean.setDeptid(getLoginUser().getOrgid());
			List<DictEntity> dataList = dictservice.dictBydicttypid("KQLX");
			if (dataList != null && dataList.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (DictEntity data : dataList) {
					sbf.append("'").append(data.getCode()).append("':'")
							.append(data.getParamname()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.getRequest().setAttribute("jsonStr",
						_jsonData.substring(0, length - 1));
			} else {
				this.getRequest().setAttribute("jsonStr", "");
			}
		} catch (Exception e) {
			e.getStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "班次配置表信息管理";
	}

	public ClasscfgEntity getBean() {
		return bean;
	}

	public void setBean(ClasscfgEntity bean) {
		this.bean = bean;
	}

	public ClasscfgService<ClasscfgEntity> getClasscfgService() {
		return classcfgService;
	}

	public void setClasscfgService(
			ClasscfgService<ClasscfgEntity> classcfgService) {
		this.classcfgService = classcfgService;
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

	public DictService<DictEntity> getDictservice() {
		return dictservice;
	}

	public void setDictservice(DictService<DictEntity> dictservice) {
		this.dictservice = dictservice;
	}

	public DictEntity getDictbean() {
		return dictbean;
	}

	public void setDictbean(DictEntity dictbean) {
		this.dictbean = dictbean;
	}

	public DeptService<DeptEntity> getDeptservice() {
		return deptservice;
	}

	public void setDeptservice(DeptService<DeptEntity> deptservice) {
		this.deptservice = deptservice;
	}

	public DeptEntity getDept() {
		return dept;
	}

	public void setDept(DeptEntity dept) {
		this.dept = dept;
	}

}
