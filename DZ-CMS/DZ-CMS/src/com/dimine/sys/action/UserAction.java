package com.dimine.sys.action;

import java.io.PrintWriter;
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
import com.dimine.base.util.Md5Encrypt;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.entity.DeptEntity;
import com.dimine.sys.entity.UserEntity;
import com.dimine.sys.service.DeptService;
import com.dimine.sys.service.UserService;

/**
 * 对用户进行操作的action
 * 
 * @author Administrator
 * 
 */
@Namespace("/manager/sys/user")
@Scope("request")
@Controller("sysUserAction")
public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(UserAction.class);
	private static final long serialVersionUID = 1L;
	private UserEntity bean = new UserEntity();
	@Resource
	private UserService<UserEntity> userService;
	@Resource
	private DeptService<DeptEntity> deptService;
	private String addtype;
	private String filters;
	private String oldpasswd;// 旧密码
	private String newpasswd;// 新密码
	private String confirmpasswd;// 确认密码

	/**
	 * 对角色进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							public String executeField(String f) {
								if (f.equals("loginname"))
									return "a.loginname";
								if (f.equals("username"))
									return "a.username";
								if (f.equals("tel"))
									return "a.tel";
								if (f.equals("deptname"))
									return "d.deptname";
								if (f.equals("deptCode"))
									return "d.deptCode";
								return f;
							}

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
				System.out.println(query);
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<UserEntity> dataList = userService.selectByList(bean);
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

	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/manager/sys/user/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增用户信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			userService.insert(bean, getLoginUser().getUserid());
			DeptEntity dept = deptService.selectById(bean.getDeptid());
			bean.setDeptname(dept.getDeptname());
			bean.setDeptCode(dept.getDeptCode());
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

	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/manager/sys/user/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = userService.selectById(bean.getUserid());
		} catch (Exception ee) {
			setErrorFlag("true");
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setModifyman(this.getLoginUser().getUserid());
			userService.update(bean);
			DeptEntity dept = deptService.selectById(bean.getDeptid());
			bean.setDeptname(dept.getDeptname());
			bean.setDeptCode(dept.getDeptCode());
		} catch (Exception ee) {
			setErrorFlag("true");
			bean.setErrorMessage(ee);
			ee.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			userService.delete(bean.getUserid());
		} catch (Exception ee) {
			setErrorFlag("true");
			bean.setErrorMessage(ee);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Action(value = "modifypwd", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doModifyPwd() {
		try {
			userService.updatePassword(bean);
			this.setJsonStr(bean.getUserid());
			setErrorFlag("password");
		} catch (Exception ee) {
			setErrorFlag("true");
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Action(value = "updatepwd", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doUpdatePasswd() {
		try {
			if ((oldpasswd == null) || (oldpasswd.equals(""))) {
				throw new Exception("原始密码没有输入!");
			}
			if ((newpasswd == null) || (newpasswd.equals(""))) {
				throw new Exception("新密码没有输入!");
			}
			if ((confirmpasswd == null) || (confirmpasswd.equals(""))) {
				throw new Exception("确认密码没有输入!");
			}
			if (!confirmpasswd.equals(newpasswd)) {
				throw new Exception("两次输入的密码不一致，请确认后重新输入。");
			}
			String oldEnCoder = Md5Encrypt.md5(oldpasswd);
			if (!oldEnCoder.equals(this.getLoginUser().getLoginpwd())) {
				throw new Exception("原始密码错误!");
			}
			bean.setUserid(this.getLoginUser().getUserid());
			bean.setLoginpwd(newpasswd);
			userService.updatePassword(bean);
		} catch (Exception ee) {
			setErrorFlag("true");
			setErrorMessage(ee);
		}
		return SUCCESS;
	}

	/**
	 * 验证验证码是否正确
	 * 
	 * @return
	 */
	@Action(value = "authentication")
	public String authentication() {
		try {
			String codeOk = "true";
			String code = this.getRequest().getParameter("code");
			String validatecode = (String) this.getSession().getAttribute(
					"validatecode");
			if (!code.equals(validatecode)) {
				codeOk = "false";
			}
			PrintWriter out = this.getResponse().getWriter();
			getResponse().setCharacterEncoding("utf-8");
			out.print(codeOk.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public UserEntity getBean() {
		return bean;
	}

	public void setBean(UserEntity bean) {
		this.bean = bean;
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

	public String getAddtype() {
		return addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	public String getConfirmpasswd() {
		return confirmpasswd;
	}

	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}

}
