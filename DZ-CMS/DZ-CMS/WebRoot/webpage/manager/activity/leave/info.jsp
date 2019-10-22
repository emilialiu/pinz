<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headinfo.jsp" %>
<div id="act_leave_info" >
	<form id="act_leave_form" method="post" class="form-horizontal" >
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="name">
				请假类型:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<select id="leaveType" name="leaveType">
						<option>公休</option>
						<option>病假</option>
						<option>调休</option>
						<option>事假</option>
						<option>婚假</option>
					</select>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="startTime">
				开始时间:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<!-- #section:plugins/date-time.datepicker -->
										<div class="input-group">
											<input class="form-control date-picker" id="startTime" name="startTime"datatype="*"  type="text"  nullmsg="开始时间必填!" data-date-format="yyyy-mm-dd" />
											<span class="input-group-addon">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
										</div>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="endTime">
				结束时间:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<!-- #section:plugins/date-time.datepicker -->
										<div class="input-group">
											<input class="form-control date-picker" id="endTime" name="endTime" type="text" datatype="*"  nullmsg="结束时间必填!" data-date-format="yyyy-mm-dd" />
											<span class="input-group-addon">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
										</div>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			
			</div>
		</div>
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="reason">
				请假原因:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="reason" id="reason" class="col-sm-12"></textarea>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="/pub/biz/footerinfo.jsp" %>