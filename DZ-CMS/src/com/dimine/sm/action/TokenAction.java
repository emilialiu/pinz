package com.dimine.sm.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.sm.entity.TokenEntity;
import com.dimine.sm.service.TokenService;

@Namespace("/biz/sm/token")
@Scope("request")
@Controller("tokenAction")
public class TokenAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(TokenAction.class);
	// 业务处理
	@Resource
	private TokenService<TokenEntity> tokenService;

	// 参数实体
	private TokenEntity bean = new TokenEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	public TokenService<TokenEntity> getTokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService<TokenEntity> tokenService) {
		this.tokenService = tokenService;
	}

	public TokenEntity getBean() {
		return bean;
	}

	public void setBean(TokenEntity bean) {
		this.bean = bean;
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
