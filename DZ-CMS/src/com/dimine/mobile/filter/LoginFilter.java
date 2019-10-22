package com.dimine.mobile.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		System.out.println("开始验证手机端用户是否登录...");
		/*if (request.getRequestURI().indexOf("login.action") == -1) {
			if (session.getAttribute("access_token") == null
					|| request.getParameter("access_token") == null) {
				StringBuffer sbf = new StringBuffer();
				sbf.append("{\"success\":false,\"errormessage\":\"您还未登录，请先登录！\",\"access_token\":\"\"}");
				request.setAttribute("jsonStr", sbf.toString());
//				response.sendRedirect("../webpage/pub/jsondata.jsp");
				System.out.println("手机端用户未登录...");
				request.getRequestDispatcher("../webpage/pub/jsondata.jsp").forward(request, response);
				return;
			} else {
				if (!request.getParameter("access_token").equals(
						session.getAttribute("access_token"))) {
					StringBuffer sbf = new StringBuffer();
					sbf.append("{\"success\":false,\"errormessage\":\"登录超时，请重新登录！\",\"access_token\":\"\"}");
					request.setAttribute("jsonStr", sbf.toString());
//					response.sendRedirect("../webpage/pub/jsondata.jsp");
					System.out.println("手机端用户未登录...");
					request.getRequestDispatcher("../webpage/pub/jsondata.jsp").forward(request, response);
					return;
				}
			}
		}*/
		System.out.println("手机端用户已登录...");
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
