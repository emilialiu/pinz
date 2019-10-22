<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	//这个页面的主要作用就是当列表执行ajax操作的时候，对操作的结果进行返回
	//错误信息
	String errorMessage = (String)request.getAttribute("errorMessage");
	StringBuffer data = new StringBuffer("[{"); 
	//错误信息为空,则操作成功
	data.append("success:");
	if("".equals(errorMessage)){
		data.append("true");
	}else{
		data.append("false");
	}
	data.append(", errormessage:");
	data.append("\"");
	data.append(errorMessage);
	data.append("\"");
	data.append("}]");
%>

<%=data.toString() %>