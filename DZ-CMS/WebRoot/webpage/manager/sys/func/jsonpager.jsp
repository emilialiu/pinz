<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String json = (String) request.getAttribute("jsonString");
	String errorMessage = (String) request.getAttribute("errorMessage");
	String tblx = (String) request.getAttribute("getSelectTblx");
	StringBuffer jsondata = new StringBuffer("({");
	if ("".equals(errorMessage)) {//如果为空
		jsondata.append("success:true,errormessage:\"\"").append("!"+tblx+"!");
	} else {
		jsondata.append("success:false");
		jsondata.append(", errormessage:\"");
		jsondata.append(errorMessage);
		jsondata.append("\"");
	}
	jsondata.append(",cells:[");
	jsondata.append(json);
	jsondata.append("]})");
%>
<%=jsondata.toString() %>