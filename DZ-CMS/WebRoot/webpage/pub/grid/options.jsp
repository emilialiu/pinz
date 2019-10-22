<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dimine.framework.select.SelectEntity" %>
<%
	List<SelectEntity> listData = (List<SelectEntity>)request.getAttribute("listData");
	String errorMessage = (String)request.getAttribute("errorMessage");
	StringBuffer result = new StringBuffer("{");
	//如果没有错误
	if("".equals(errorMessage)){
		result.append("success:true, errorMessage:'', ");
	}else{
		result.append("success:false, errorMessage:'");
		result.append(errorMessage);
		result.append("', ");
	}
	if(listData == null){
		result.append("cells:[]");
	}else{
		result.append("cells:[");
		int length = listData.size();
		for(int i = 0; i < length; i++){
	SelectEntity option = listData.get(i);
	result.append("{'key':'");
	result.append(option.getSelectValue());
	result.append("', 'value':'");
	result.append(option.getSelectName());
	result.append("'}");
	if(i + 1 < length){
		result.append(", ");
	}
		}
		result.append("]");
	}
	result.append("}");
%>
<%=result.toString() %>