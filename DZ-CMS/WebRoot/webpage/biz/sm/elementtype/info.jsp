<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body>
	<div id="elementtype_info">
		<form id="elementtype_form" method="post" class="form-horizontal">
			<input type="hidden" id="eletypeid" name="eletypeid" value="${bean.eletypeid}"/>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="eletypekind">单位</label>
				<div class="col-xs-12 col-sm-9" style="width:50%">
					<div class="col-sm-9">
						<dmtag:sourcemanager sourcename="YSLXFL" name="eletypekind"
							cssClass="form-control" isnull="false" fixValue="${bean.eletypekind}"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label
					class="control-label col-xs-12 col-sm-3 no-paddishiding-right"
					for="memo">备注</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="memo" id="memo" maxlength="40"
							value="${bean.memo}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
		</form>
	</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/elementtype/info.js"></script>
<script type="text/javascript">
	var eletypeid = '<%=request.getAttribute("bean.eletypeid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("elementtype");
		else if(actiontype=='modifysave')
			modifySave("elementtype");
		else if(actiontype=='deletesave')
			deleteSave("elementtype");
	}
	initvalid("elementtype");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("elementtype",{"取消": function() {parent.closewin("elementtype");}});
		else 
			parent.setDialogButtons("elementtype",{"取消": function() {parent.closewin("elementtype");},"确定": function() {parent.window.frames["elementtype_frame"].onSave();}});
	});
</script>
</body>
</html>