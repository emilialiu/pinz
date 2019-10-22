<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headinfo.jsp" %>
<div id="act_feeborrow_info" >
	<form id="act_feeborrow_form" method="post" class="form-horizontal">
		<input type="hidden" id="borrowid" name="borrowid"/> 
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="borrowamount">
				借支金额:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<input type="text" name="borrowamount" datatype="*" nullmsg="借支金额必填！" id="borrowamount" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="cause">
				借支原因:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="cause" datatype="*" nullmsg="借支原因必填！" id="cause" class="col-xs-12" /> </textarea>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		
	</form>
</div>
<%@include file="/pub/biz/footerinfo.jsp" %>