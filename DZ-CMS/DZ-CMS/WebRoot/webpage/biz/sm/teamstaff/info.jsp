<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headinfo.jsp" %>
<div id="teamstaff_info" style="display:none">
	<form id="teamstaff_form" method="post" class="form-horizontal">
		<input type="hidden" id="teamid" name="teamid"/>
		<input type="hidden" id="staffid" name="staffid"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teamid">班组ID</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="teamid" id="teamid" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="staffid">员工ID</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="staffid" id="staffid" class="col-xs-12" /></div><div class="col-sm-3"></div></div>
		</div>
		
	</form>
</div>
<%@include file="/pub/biz/footerinfo.jsp" %>