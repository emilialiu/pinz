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
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sm.entity.StaffEntity;
import com.dimine.sm.service.StaffService;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.entity.UserEntity;
import com.dimine.sys.service.DeptService;
import com.dimine.sys.service.UserService;

/**
 * 用于对员工信息进行系列的操作的action
 * 
 * @author dimine 2015-06-16 17:51:24
 * 
 */
@Namespace("/biz/sm/staff")
@Scope("request")
@Controller("staffAction")
public class StaffAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(StaffAction.class);
	// 业务处理
	@Resource
	private StaffService<StaffEntity> staffService;
	@Resource
	private UserService<UserEntity> userService;
	@Resource
	private DeptService<DeptEntity> deptService;

	// 参数实体
	private StaffEntity bean = new StaffEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对员工信息进行列表查询操作
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
								if (f.equals("deptname"))
									return "dept.deptname";
								if (f.equals("sexname"))
									return "d2.PARAMNAME";
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
			if (bean.getDeptid() == null || "".equals(bean.getDeptid())) {
				bean.setDeptid(getLoginUser().getOrgid());
			}
			List<StaffEntity> dataList = staffService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception ex) {
			this.setErrorMessage(ex);
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 对员工信息进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list1", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list1() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("deptname"))
									return "dept.deptname";
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
			if (bean.getDeptid() == null || "".equals(bean.getDeptid())) {
				bean.setMemo(getLoginUser().getOrgid());
			} else if (getLoginUser().getOrgid().equals(bean.getDeptid())) {
				bean.setMemo(getLoginUser().getOrgid());
				bean.setDeptid("");
			}
			List<StaffEntity> dataList = staffService.selectByList1(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception ex) {
			this.setErrorMessage(ex);
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 新增信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sm/staff/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增员工信息信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			bean.setCreateid(this.getLoginUser().getUserid());
			String keyID = KeyUtils.createKeyID();
			// 生成编号
			bean.setStaffid(keyID);
			staffService.insert(bean);
			if ("1".equals(bean.getIsmanager())) {// 如果是部门主管，则修改机构表中机构主管id
				DeptEntity dept = new DeptEntity();
				dept.setDeptid(bean.getDeptid());
				dept.setManagerid(bean.getStaffid());
				deptService.updateManager(dept);
			}

			bean.setDeptname(deptService.selectById(bean.getDeptid())
					.getDeptname());
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			logger.error(ee);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除员工信息信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			staffService.deleteStaff(bean.getStaffid());
			userService.delete(bean.getStaffid());
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对员工信息进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sm/staff/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = staffService.selectById(bean);
			UserEntity user = userService.selectById(bean.getStaffid());
			DeptEntity dept = deptService.selectById(bean.getDeptid());
			if (bean.getStaffid().equals(dept.getManagerid())) {
				bean.setIsmanager("1");
			} else {
				bean.setIsmanager("0");
			}
			if (user != null) {
				bean.setLoginname(user.getLoginname());
				bean.setLoginpwd(user.getLoginpwd());
			}
		} catch (Exception ex) {
			this.setErrorMessage(this.getText("modifyfailure"));
			logger.error(ex);
		}
		return SUCCESS;
	}

	/**
	 * 对员工信息进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setModifyid(this.getLoginUser().getUserid());
			staffService.update(bean, getActiontype());

			if ("1".equals(bean.getIsmanager())) {// 如果是部门主管，则修改机构表中机构主管id
				DeptEntity dept = new DeptEntity();
				dept.setDeptid(bean.getDeptid());
				dept.setManagerid(bean.getStaffid());
				deptService.updateManager(dept);
			}

			bean.setDeptname(deptService.selectById(bean.getDeptid())
					.getDeptname());
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			logger.error(ee);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	/**
	 *根据员工ID查询员工所属部门 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "selectbystaffid", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String selectbystaffid() {
		try {
			bean=staffService.selectById(bean);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "员工信息信息管理";
	}

	public StaffEntity getBean() {
		return bean;
	}

	public void setBean(StaffEntity bean) {
		this.bean = bean;
	}

	public StaffService<StaffEntity> getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService<StaffEntity> staffService) {
		this.staffService = staffService;
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

	public UserService<UserEntity> getUserService() {
		return userService;
	}

	public void setUserService(UserService<UserEntity> userService) {
		this.userService = userService;
	}

	public DeptService<DeptEntity> getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService<DeptEntity> deptService) {
		this.deptService = deptService;
	}

}
