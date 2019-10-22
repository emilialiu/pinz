<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<script type="text/javascript">
	var bussinesskey='<%=request.getParameter("bussinesskey")%>';
	var prokey='<%=request.getParameter("prokey")%>';
</script>
<body>
	<div class="row" style="margin:0px;">
		<div class="col-xs-12">	
			<div id="processhis_grid_box" class="widget-box widget-color-blue">
				<div class="widget-body">
					<div class="widget-main no-padding">	
					 	<table id="processhis_grid"></table>
				     	<div id="processhis_grid_pager"></div>
					</div>
				</div>
			</div>
		</div> 
	</div>
</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/pub/select/processhis/bizgrid.js"></script>

