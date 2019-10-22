<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>sessionʧЧ</title>
	</head>

	<body>
		<script type="text/javascript">
		<!--
			var href = top.location.href;
			var start_idx = href.indexOf("?");
			var end_idx = href.indexOf("=");
			var param = href.substring(start_idx+1, end_idx);
			if(param != ''){
				top.location.href='<%=request.getContextPath() %>/'+param+'/login.jsp';
			}else{
				top.location.href='<%=request.getContextPath() %>/login.jsp';
			}
		//-->
		</script>
	</body>
</html>
