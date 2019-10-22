<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>告警管理</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="sys_alarm_textsearch" class="form-control search-query" placeholder="<s:text name="dmmes.sys.alarm.name" />" />
					<span class="input-group-btn">
						<button id="sys_alarm_btnsearch" type="button" class="btn btn-primary btn-sm" onclick="dosimplesearch('sys_alarm')">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="sys_alarm_btn_advsearch" style="margin-left:10px;" class="btn btn-primary btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="sys_alarm_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.home.alarm" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="sys_alarm_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="sys_alarm_grid"></table>
					
							<div id="sys_alarm_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
				
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
	var alarmid = "<%=request.getParameter("alarmid")%>";
	var sys_alarm_name='<s:text name="dmmes.sys.alarm.name" />';
	var sys_alarm_type='<s:text name="dmmes.sys.alarm.type" />';
	var sys_alarm_level='<s:text name="dmmes.sys.alarm.level" />';
	var sys_alarm_publish='<s:text name="dmmes.sys.alarm.publish" />';
	var sys_alarm_handlestatus='<s:text name="dmmes.sys.alarm.handlestatus" />';
	var sys_alarm_handletime='<s:text name="dmmes.sys.alarm.handletime" />';
	var sys_alarm_handlename='<s:text name="dmmes.sys.alarm.handlename" />';
	var sys_alarm_handlecontent='<s:text name="dmmes.sys.alarm.handlecontent" />';
	var qt_assaysample_memo='<s:text name="dmmes.qt.assaysample.memo" />';
	var home_alarmDetail='<s:text name="dmmes.home.alarmDetail" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/manager/sys/alarm/bizgrid.js"></script>