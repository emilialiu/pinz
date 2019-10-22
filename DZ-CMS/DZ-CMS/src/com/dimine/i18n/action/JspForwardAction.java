package com.dimine.i18n.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class JspForwardAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String result = (String) request.getAttribute("jsp");
		url = result;
		if (request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}
		return SUCCESS;
	}
}
