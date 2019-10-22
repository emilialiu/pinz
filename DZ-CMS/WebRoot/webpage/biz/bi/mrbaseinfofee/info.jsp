<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_bi_mrbaseinfofee_info" >
		<form id="t_bi_mrbaseinfofee_form"
			action="<%=request.getContextPath()%>/webpage/biz/bi/mrbaseinfofee/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="feeid" name="feeid" value="${bean.feeid}" />
			<input type="hidden" id="rightid" name="rightid" value="${bean.rightid}" />
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="feename">费用名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="feename" id="feename"
							value="${bean.feename}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="feetype">费用类型</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<!-- <select name="feetype" id="feetype" class="form-control"><option
								value="">费用类型</option>
						</select> -->
						<dmtag:sourcemanager sourcename="FYLX" name="feetype"
								cssClass="form-control" fixValue="${bean.feetype}"
								isnull="false"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="feestage">费用发生阶段</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<!-- <select name="feestage" id="feestage" class="form-control"><option
								value="">费用发生阶段</option>
						</select> -->
						<dmtag:sourcemanager sourcename="FYFSJD" name="feestage"
								cssClass="form-control" fixValue="${bean.feestage}"
								isnull="false"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="feeamount">费用（￥）</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="feeamount" id="feeamount"
							value="${bean.feeamount}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="feedate">费用产生日期</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="feedate" id="feedate"
								value="${bean.feedate}" class="form-control"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><span
								class="input-group-addon"><i
								class="ace-icon fa fa-calendar"></i> </span>
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="memo">备注</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<textarea name="memo" id="memo" class="col-xs-12">${bean.memo}</textarea>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/mrbaseinfofee/info.js"></script>
<script type="text/javascript">
		var feeid = '<%=request.getAttribute("bean.feeid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_bi_mrbaseinfofee");
		else if(actiontype=='modifysave')
			modifySave("t_bi_mrbaseinfofee");
		else if(actiontype=='deletesave')
			deleteSave("t_bi_mrbaseinfofee");
	}
	initvalid("t_bi_mrbaseinfofee");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_bi_mrbaseinfofee",{"取消": function() {parent.closewin("t_bi_mrbaseinfofee");}});
		else 
			parent.setDialogButtons("t_bi_mrbaseinfofee",{"取消": function() {parent.closewin("t_bi_mrbaseinfofee");},"确定": function() {parent.window.frames["t_bi_mrbaseinfofee_frame"].onSave();}});
	});
</script>
</html>