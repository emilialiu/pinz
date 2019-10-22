<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<%@page import="java.util.Date"%>
<%@page import="com.dimine.sys.util.DateUtil"%>

<head></head>
<style>
 a{
 color: #FFFFFF;
 }
 a:HOVER {
  	color: #FFFFFF;
	cursor: pointer;
	text-decoration: underline;
 }
  li{
 	display: inline;
 	float:left;
 	margin-right:50px;
 	height: 28px;
 	line-height: 28px;
 } 
 li a{
 	color: #307ECC;
 	font-size: medium;
 }
 li a:HOVER{
 	color: #000;
 	cursor: pointer;
	text-decoration: underline;
 }
 .dbinfo{
    color: #337ab7;
    font-size: 15px;
 }
 .dbinfo:hover{
    color: #337ab7;
 }
</style>
<script type="text/javascript">
var realtechid='';
var realtechname='';
var langcode='<s:text name="dimine.lang" />';
var home_productdayrpt='<s:text name="dmmes.home.productdayrpt" />';
var home_productweekrpt='<s:text name="dmmes.home.productweekrpt" />';
var home_alarmDetail='<s:text name="dmmes.home.alarmDetail" />';
var sys_task_claim = '<s:text name="dmmes.sys.task.claim"/>';
var sys_task_handle = '<s:text name="dmmes.sys.task.handle"/>';
</script>
<body>
<div class="row">
	<div class="col-xs-6">
		<div id="shssc_grid_box"
			class="widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><s:text name="dmmes.home.productdayrpt" /></h5> 
				<input id="pdate" type="text" style="height: 38px;width: 100px;margin-left:20px;margin-top:0px; padding-top:0px; padding-bottom: 0px;" readonly="readonly"  value="<%=DateUtil.addDay(new Date(), -1, "yyyy-MM-dd") %>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,onpicked:selectInfoByDate()})" />		
				<a id="shssc_grid_btn_more" class="widget-toolbar no-border"
					href="#" funcname="生产日报" url="/webpage/report/pdrpt/kssczhrb.jsp?height=1100" 
					funccode="d8b63c6f23e446d799ea376864f1a9c1" onclick="selectmore('shssc_grid')">
						<s:text name="dmmes.home.more" /> &gt;&gt;
				</a>
			</div>
			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="shssc_grid"></table>
			
					<div id="shssc_grid_pager"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-6">				
		<div id="tomatter_grid_box" class="widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title" ><s:text name="dmmes.home.tomatter" /></h5> 
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<div id="tomatter" style="height:272px;"></div>
				</div>
			</div>
		</div>
	</div><!-- /.col -->
	<div class="col-xs-6">				
		<div id="message_grid_box" class="widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><s:text name="dmmes.home.message" /></h5>
				<a id="message_grid_btn_more" class="widget-toolbar no-border"
				href="#" funcname="<s:text name="dmmes.home.message" />" url="/webpage/manager/sys/message/main.jsp" 
				funccode="e1f308a9c9524cf9b96c0c8279b8d118" onclick="selectmore('message_grid')">
						<s:text name="dmmes.home.more" /> &gt;&gt;
				</a>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="message_grid"></table>
					<div id="message_grid_pager"></div>
				</div>
			</div>
		</div>
	</div><!-- /.col -->
	
	<div class="col-xs-6">
		<div id="alarm_grid_box"
			class="widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title">
					<s:text name="dmmes.home.alarm" />
				</h5>
				<a id="alarm_grid_btn_more" class="widget-toolbar no-border"
				href="#" funcname="<s:text name="dmmes.home.alarm" />" url="/webpage/manager/sys/alarm/main.jsp" 
				funccode="626029727f494ea684a273c00f6a3b83" onclick="selectmore('alarm_grid')">
							<s:text name="dmmes.home.more" /> &gt;&gt;
				</a>
			</div>
			<div class="widget-body">
				<div class="widget-main no-padding">
					<table id="alarm_grid"></table>
					<div id="alarm_grid_pager"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/main/bizgrid.js"></script>
</html>
