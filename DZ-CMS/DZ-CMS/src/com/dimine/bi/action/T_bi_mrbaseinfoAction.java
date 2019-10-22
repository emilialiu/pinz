package com.dimine.bi.action;

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
import com.dimine.bi.entity.T_bi_exprightEntity;
import com.dimine.bi.entity.T_bi_minerightEntity;
import com.dimine.bi.entity.T_bi_mrbaseinfoEntity;
import com.dimine.bi.entity.T_bi_mrbaseinfofeeEntity;
import com.dimine.bi.service.T_bi_exprightService;
import com.dimine.bi.service.T_bi_minerightService;
import com.dimine.bi.service.T_bi_mrbaseinfoService;
import com.dimine.bi.service.T_bi_mrbaseinfofeeService;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.service.DeptService;
import com.dimine.util.StringUtils;

/**
 * 用于对矿业权基本信息表进行系列的操作的action
 * 
 * @author dimine 2017-08-15 10:34:46
 * 
 */
@Namespace("/webpage/biz/bi/mrbaseinfo")
@Scope("request")
@Controller("t_bi_mrbaseinfoAction")
public class T_bi_mrbaseinfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(T_bi_mrbaseinfoAction.class);
	// 业务处理
	@Resource
	private T_bi_mrbaseinfoService<T_bi_mrbaseinfoEntity> t_bi_mrbaseinfoService;
	@Resource
	private T_bi_exprightService<T_bi_exprightEntity> t_bi_exprightService;// 探矿权service
	@Resource
	private T_bi_minerightService<T_bi_minerightEntity> t_bi_minerightService;// 采矿权service
	@Resource
	private T_bi_mrbaseinfofeeService<T_bi_mrbaseinfofeeEntity> t_bi_mrbaseinfofeeService;//矿权费用service
	@Resource
	private DeptService<DeptEntity> deptService;

	// 参数实体
	private T_bi_exprightEntity tkqbean = new T_bi_exprightEntity();
	// 参数实体
	private T_bi_minerightEntity ckqbean = new T_bi_minerightEntity();
	// 参数实体
	private T_bi_mrbaseinfoEntity bean = new T_bi_mrbaseinfoEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对矿业权基本信息表进行列表查询操作
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
								if (f.equals("mrcategoryname"))
									return "d1.PARAMNAME";
								if (f.equals("projectqualityname"))
									return "d2.PARAMNAME";
								if (f.equals("mrstatename"))
									return "d3.PARAMNAME";
								if (f.equals("ownwayname"))
									return "d4.PARAMNAME";
								if (f.equals("economictypename"))
									return "d5.PARAMNAME";
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
			List<T_bi_mrbaseinfoEntity> dataList = t_bi_mrbaseinfoService
					.selectByList(bean);
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
	 * 新增矿业权基本信息表信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = {
			@Result(name = "KQLB002", location = "/webpage/biz/bi/mrbaseinfo/ckqinfo.jsp"),
			@Result(name = "KQLB001", location = "/webpage/biz/bi/mrbaseinfo/info.jsp") })
	public String doAdd() {
		String returnflag = "KQLB001";
		// TODO 其他业务处理
		if (StringUtils.isEmpty(bean.getMrcategory())) {
			bean.setMrcategory("KQLB002");// 采矿权
			returnflag = "KQLB002";
		} else {
			bean.setMrcategory("KQLB001");// 探矿权
		}
		DeptEntity dept = deptService.getDept(getLoginUser().getOrgid());
		bean.setDeptname(dept.getDeptname());
		bean.setMineid(getLoginUser().getOrgid());
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return returnflag;
	}

	/**
	 * 新增矿业权基本信息表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_bi_mrbaseinfoService.insert(bean);
			if ("KQLB001".equals(bean.getMrcategory())) {// 探矿权
				tkqbean.setRightid(bean.getRightid());
				t_bi_exprightService.insert(tkqbean);
			} else {// 采矿权
				ckqbean.setRightid(bean.getRightid());
				t_bi_minerightService.insert(ckqbean);
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
	 * 删除矿业权基本信息表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			if ("KQLB001".equals(bean.getMrcategory())) {// 探矿权
				tkqbean.setRightid(bean.getRightid());
				t_bi_exprightService.delete(tkqbean);
			} else {// 采矿权
				ckqbean.setRightid(bean.getRightid());
				t_bi_minerightService.delete(ckqbean);
			}
			t_bi_mrbaseinfoService.delete(bean);
			t_bi_mrbaseinfofeeService.deleteAllByRightId(bean.getRightid());
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对矿业权基本信息表进行修改查询操作
	 */
	@Action(value = "doModify", results = {
			@Result(name = "KQLB002", location = "/webpage/biz/bi/mrbaseinfo/ckqinfo.jsp"),
			@Result(name = "KQLB001", location = "/webpage/biz/bi/mrbaseinfo/info.jsp") })
	public String doModify() {
		String returnflag = "KQLB001";
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = t_bi_mrbaseinfoService.selectById(bean.getRightid());
			DeptEntity dept = deptService.getDept(bean.getMineid());
			bean.setDeptname(dept.getDeptname());
			if ("KQLB001".equals(bean.getMrcategory())) {// 探矿权
				tkqbean = t_bi_exprightService.selectById(bean.getRightid());
			} else {// 采矿权
				ckqbean = t_bi_minerightService.selectById(bean.getRightid());
				returnflag = "KQLB002";
			}
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return returnflag;
	}

	/**
	 * 对矿业权基本信息表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			t_bi_mrbaseinfoService.update(bean, getActiontype());
			if ("KQLB001".equals(bean.getMrcategory())) {// 探矿权
				tkqbean.setRightid(bean.getRightid());
				t_bi_exprightService.update(tkqbean);
			} else {// 采矿权
				ckqbean.setRightid(bean.getRightid());
				t_bi_minerightService.update(ckqbean);
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "矿业权基本信息表信息管理";
	}

	public T_bi_mrbaseinfoEntity getBean() {
		return bean;
	}

	public void setBean(T_bi_mrbaseinfoEntity bean) {
		this.bean = bean;
	}

	public T_bi_mrbaseinfoService<T_bi_mrbaseinfoEntity> getT_bi_mrbaseinfoService() {
		return t_bi_mrbaseinfoService;
	}

	public void setT_bi_mrbaseinfoService(
			T_bi_mrbaseinfoService<T_bi_mrbaseinfoEntity> t_bi_mrbaseinfoService) {
		this.t_bi_mrbaseinfoService = t_bi_mrbaseinfoService;
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

	public T_bi_exprightService<T_bi_exprightEntity> getT_bi_exprightService() {
		return t_bi_exprightService;
	}

	public void setT_bi_exprightService(
			T_bi_exprightService<T_bi_exprightEntity> t_bi_exprightService) {
		this.t_bi_exprightService = t_bi_exprightService;
	}

	public T_bi_minerightService<T_bi_minerightEntity> getT_bi_minerightService() {
		return t_bi_minerightService;
	}

	public void setT_bi_minerightService(
			T_bi_minerightService<T_bi_minerightEntity> t_bi_minerightService) {
		this.t_bi_minerightService = t_bi_minerightService;
	}

	public T_bi_minerightEntity getCkqbean() {
		return ckqbean;
	}

	public void setCkqbean(T_bi_minerightEntity ckqbean) {
		this.ckqbean = ckqbean;
	}

	public T_bi_exprightEntity getTkqbean() {
		return tkqbean;
	}

	public void setTkqbean(T_bi_exprightEntity tkqbean) {
		this.tkqbean = tkqbean;
	}

	public DeptService<DeptEntity> getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService<DeptEntity> deptService) {
		this.deptService = deptService;
	}

}
