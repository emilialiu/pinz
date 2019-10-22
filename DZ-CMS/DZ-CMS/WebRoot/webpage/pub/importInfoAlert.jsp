<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
   Object obj = session.getAttribute("SIQunUser");
   String path = request.getContextPath();
   if(obj!=null){
       out.print("[{'success':true,'url':'"+path+"/web/bs/downloadLog.action?downloadLogType=2'}]");
   }else{
       out.print("[{'success':false}]");
   }
   //session.removeAttribute("SIQunUser");
%>