package com.dimine.security.action;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.Md5Encrypt;
import com.dimine.base.util.WebUtil;
import com.dimine.security.entity.LoginUserEntity;
import com.dimine.security.service.SecurityService;
import com.dimine.sys.entity.LogLoginEntity;
import com.dimine.sys.service.LogLoginService;
import com.opensymphony.xwork2.ActionContext;

@Namespace("/main")
@Scope("request")
@Controller("loginAction")
public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username = "";
	private String password = "";
	private String langcode = "";

	@Resource
	private SecurityService securityService;
	@Resource
	private LogLoginService logLoginService;

	@Action(value = "login", results = {
			@org.apache.struts2.convention.annotation.Result(name = "success", location = "/login.jsp"),
			@org.apache.struts2.convention.annotation.Result(name = "main", location = "/webpage/main/search.jsp") })
	public String login() {
		LogLoginEntity logloginEntity = new LogLoginEntity();			
		logloginEntity.setLoginname(username);
		logloginEntity.setLogintype("1");	
		try {
			Subject currentUser = SecurityUtils.getSubject();

			String minwen = Md5Encrypt.md5(this.password);
			UsernamePasswordToken token = new UsernamePasswordToken(
					this.username, minwen);
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				setErrorMessage("用户名或密码错误！");
				logloginEntity.setLoginflag("0");
				logloginEntity.setLoginmessage(this.getErrorMessage());	
				insertLogLogin(logloginEntity);	
				return "success";
			} catch (IncorrectCredentialsException ice) {
				setErrorMessage("密码错误！");
				logloginEntity.setLoginflag("0");
				logloginEntity.setLoginmessage(this.getErrorMessage());	
				insertLogLogin(logloginEntity);
				return "success";
			} catch (LockedAccountException lae) {
				setErrorMessage("用户已经被锁定不能登录，请与管理员联系！");
				logloginEntity.setLoginflag("0");
				logloginEntity.setLoginmessage(this.getErrorMessage());	
				insertLogLogin(logloginEntity);
				return "success";
			} catch (ExcessiveAttemptsException eae) {
				setErrorMessage("错误次数过多！");
				logloginEntity.setLoginflag("0");
				logloginEntity.setLoginmessage(this.getErrorMessage());	
				insertLogLogin(logloginEntity);
				return "success";
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				setErrorMessage("其他的登录错误！");
				logloginEntity.setLoginflag("0");
				logloginEntity.setLoginmessage(this.getErrorMessage());	
				insertLogLogin(logloginEntity);
				return "success";
			}
			if (currentUser.isAuthenticated()) {
				Locale locale = Locale.SIMPLIFIED_CHINESE;
//				if (langcode.equals("zh_CN")) {
					locale = Locale.SIMPLIFIED_CHINESE;
					WebUtil.languageChanged(getRequest(), "zh");
//				} else {
//					locale = Locale.US;
//					WebUtil.languageChanged(getRequest(), "en");
//				}
				ActionContext.getContext().setLocale(locale);
				getSession().setAttribute("WW_TRANS_I18N_LOCALE", locale);
				// 新增登陆日志
				LoginUserEntity userbean = getLoginUser();
				logloginEntity.setDeptid(userbean.getDeptid());
				logloginEntity.setUserid(userbean.getUserid());
				logloginEntity.setLoginflag("1");
				logloginEntity.setLoginmessage("登录成功");
				insertLogLogin(logloginEntity);
				return "main";
			}
			token.clear();
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage(e);
			logloginEntity.setLoginflag("0");
			logloginEntity.setLoginmessage("登录失败");	
			insertLogLogin(logloginEntity);
		}
		return "success";
	}
	
	/**
	 * 新增登陆日志
	 */
	public void insertLogLogin(LogLoginEntity logloginEntity){
		logloginEntity.setLogid(KeyUtils.createKeyID());
		logloginEntity.setLoginip(getIpAddr(this.getRequest()));	
		try {
			logLoginService.insert(logloginEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Action(value = "logout", results = { @org.apache.struts2.convention.annotation.Result(name = "success", location = "/login.jsp") })
	public String logout() {
		try {
			HttpSession session = getSession();
			session.removeAttribute("LOGIN_USER");
			Subject subject = SecurityUtils.getSubject();

			if (subject.isAuthenticated()) {
				subject.logout();
			}
			if (session != null)
				session.invalidate();
		} catch (Exception localException) {
		}
		return "success";
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public LogLoginService getLogLoginService() {
		return logLoginService;
	}

	public void setLogLoginService(LogLoginService logLoginService) {
		this.logLoginService = logLoginService;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}
}