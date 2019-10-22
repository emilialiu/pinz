<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String json = (String) request.getAttribute("jsonStr");
%>
<%=json.toString()%>
<% //System.out.println("json.toString()="+json.toString()); %>