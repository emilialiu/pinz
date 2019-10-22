<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	//jqgrid 页面主键
	String jsonStr = (String)request.getAttribute("jsonStr");
	
	StringBuffer data = new StringBuffer("("); 
	data.append(jsonStr);
	data.append(")");
%>

<%=data.toString() %>