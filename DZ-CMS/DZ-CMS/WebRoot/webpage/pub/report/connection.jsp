<%@ page import="java.sql.*"%>
<%
javax.servlet.ServletContext servletContext=request.getSession().getServletContext(); 
  org.springframework.web.context.WebApplicationContext webApplicationContext = org.springframework.web.context.support.WebApplicationContextUtils 
.getRequiredWebApplicationContext(servletContext); 
org.apache.commons.dbcp.BasicDataSource dataSource = (org.apache.commons.dbcp.BasicDataSource)webApplicationContext.getBean("dataSource");
 
Connection conn = dataSource.getConnection(); 
%>