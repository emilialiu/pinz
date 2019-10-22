<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_bi_threeore_info" >
	<form id="t_bi_threeore_form" action="<%=request.getContextPath()%>/webpage/biz/bi/threeore/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="threeoreid" name="threeoreid" value="${bean.threeoreid}"/>

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="yearvalue">年份</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="yearvalue" id="yearvalue"
								value="${bean.yearvalue}" datatype="*" nullmsg="年份信息必填!"
								class="form-control"
								onfocus="WdatePicker({dateFmt:'yyyy-MM'})" /><span
								class="input-group-addon"><i
								class="ace-icon fa fa-calendar"></i> </span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="element">金属元素</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<input type="text" name="element" id="element"
							value="${bean.element}" datatype="*" nullmsg="金属元素信息必填!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="reclamount">开拓量（t）</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="reclamount" id="reclamount"
							value="${bean.reclamount}" datatype="num" nullmsg="保有量（t）必须是数字!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="expamount">采准量（t）</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="expamount" id="expamount"
							value="${bean.expamount}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="extamount">可采量（t）</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="extamount" id="extamount"
							value="${bean.extamount}" datatype="num" nullmsg="可采量（t）必须是数字!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="reclamount">保有期限（月）</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="byqx" id="byqx"
							value="${bean.byqx}" datatype="num" nullmsg="保有期限必须是数字!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div> --%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/threeore/info.js"></script>
<script type="text/javascript">
		var threeoreid = '<%=request.getAttribute("bean.threeoreid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_bi_threeore");
		else if(actiontype=='modifysave')
			modifySave("t_bi_threeore");
		else if(actiontype=='deletesave')
			deleteSave("t_bi_threeore");
	}
	initvalid("t_bi_threeore");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_bi_threeore",{"取消": function() {parent.closewin("t_bi_threeore");}});
		else 
			parent.setDialogButtons("t_bi_threeore",{"取消": function() {parent.closewin("t_bi_threeore");},"确定": function() {parent.window.frames["t_bi_threeore_frame"].onSave();}});
	});
</script>
</html>