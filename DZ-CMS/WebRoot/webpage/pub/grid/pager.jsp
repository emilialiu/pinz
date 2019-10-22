<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String errorMessage = (String)request.getAttribute("errorMessage");
	//json数据
	String jsondata = (String)request.getAttribute("jsonStr");
	StringBuffer buf = new StringBuffer("{");
	if("".equals(errorMessage)|| errorMessage == null){//如果errorMessage为空
		buf.append("\"success\":true, ");
	}else{
		buf.append("\"success\":false, ");
	}
	
	buf.append("\"errormessage\":\"");
	buf.append(errorMessage==null?"":errorMessage);
	buf.append("\", ");

//	jsondata = jsondata.substring(1);
	buf.append(jsondata);
	buf.append("}");
	//jsondata = buf.toString();
%>
<%=jsondata %>
