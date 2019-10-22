<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String json = (String) request.getAttribute("jsonString");
%>
<%="["+json.toString()+"]" %>