<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String title = (String) request.getAttribute("title");
	String content = (String) request.getAttribute("content");
	String total = (String) request.getAttribute("total");
	String errorMessage = (String) request.getAttribute("errorMessage");
	StringBuffer jsondata = new StringBuffer("{");
	if ("".equals(errorMessage)) {//如果为空
		jsondata.append("\"success\":true,\"errormessage\":\"\"");
	} else {
		jsondata.append("\"success\":false");
		jsondata.append(", \"errormessage\":\"");
		jsondata.append(errorMessage);
		jsondata.append("\"");
	}
	jsondata.append(",\"title\":[");
	jsondata.append(title);
	jsondata.append("]");
	
	jsondata.append(",\"total\":\"");
	jsondata.append(total);
	jsondata.append("\"");
	
	jsondata.append(",\"content\":[");
	jsondata.append(content);
	jsondata.append("]}");
%>
<%=jsondata.toString() %>