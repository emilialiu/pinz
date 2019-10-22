<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	//这个页面的主要作用就是当列表执行ajax操作的时候，对操作的结果进行返回
	//错误信息
	String errorMessage = (String)request.getAttribute("errorMessage");
	//jqgrid 页面主键
	String id = (String)request.getAttribute("jsonStr");
	String mestime = (String)request.getAttribute("mestime");
	
	StringBuffer data = new StringBuffer("({"); 
	//错误信息为空,则操作成功
	if(errorMessage==null || "".equals(errorMessage)){
		data.append("success:true,errormessage:\"\",id:\"");
		data.append(id);
		data.append("\",\"mestime\":\"");
		data.append(mestime);
		data.append("\"");
	} else {
		data.append("success:false");
		data.append(", errormessage:\"");
		if(!("failed").equals(errorMessage)||!("").equals(errorMessage)){
			data.append("："+errorMessage);
		}
		data.append("\",id:\"\",\"mestime\":\"\"");
	}
	data.append("})");
%>

<%=data.toString() %>