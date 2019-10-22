<%@ page language="java" pageEncoding="utf-8"%>
	<!-- basic scripts -->
	<!--[if !IE]> -->
	<script src="<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.js"></script>
	<!-- <![endif]-->

	<!--[if IE]>
	<script src="<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.js"></script>
	<![endif]-->

	<script type="text/javascript">
		if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
	</script>

	<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui.custom/jquery-ui.custom.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jqueryui-touch-punch/jquery.ui.touch-punch.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/select2/dist/js/select2.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/typeahead.js/dist/typeahead.jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jquery.gritter/js/jquery.gritter.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/bootbox.js/bootbox.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/spin.js/spin.min.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/components/ladda/ladda.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jquery-ui/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jqgrid/js/jquery.jqgrid.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/components/jqgrid/js/i18n/grid.locale-<s:text name="grid.lang" />.js"></script>
	
   	<script src="<%=request.getContextPath()%>/plugins/validform/Validform_v5.3.2.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/validform/Validform_Datatype.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.contextmenu.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/jquery/CommonPerson.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery/owin_zh.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jquery/jquery-json.js"></script>
	<script src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/pub/biz/grid_<s:text name="dimine.lang" />.js"></script>
	<!-- ace scripts -->
	<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>