<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_bi_expright_info" >
	<form id="t_bi_expright_form" action="<%=request.getContextPath()%>/webpage/biz/bi/expright/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="rightid" name="rightid" value="${bean.rightid}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="cooperateent">合作单位</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="cooperateent" id="cooperateent" value="${bean.cooperateent}" datatype="*6-16" nullmsg="合作单位6到16位任意字符!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mineralstage">勘查阶段</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="mineralstage" id="mineralstage" class="form-control" ><option value ="">勘查阶段</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mineraltype">勘查矿种</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="mineraltype" id="mineraltype" class="form-control" ><option value ="">勘查矿种</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="rightgetway">探矿权获取方式</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="rightgetway" id="rightgetway" class="form-control" ><option value ="">探矿权获取方式</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="totalamount">获取投资总金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="totalamount" id="totalamount" value="${bean.totalamount}" datatype="n" nullmsg="获取投资总金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="year1amount">第一勘查年投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="year1amount" id="year1amount" value="${bean.year1amount}" datatype="n" nullmsg="第一勘查年投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="year2amount">第二勘查年投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="year2amount" id="year2amount" value="${bean.year2amount}" datatype="n" nullmsg="第二勘查年投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="year3amount">第三勘查年投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="year3amount" id="year3amount" value="${bean.year3amount}" datatype="n" nullmsg="第三勘查年投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="stateamount">国家投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="stateamount" id="stateamount" value="${bean.stateamount}" datatype="n" nullmsg="国家投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="localamount">地方投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="localamount" id="localamount" value="${bean.localamount}" datatype="n" nullmsg="地方投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="enterpriseamount">企业投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="enterpriseamount" id="enterpriseamount" value="${bean.enterpriseamount}" datatype="n" nullmsg="企业投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="foreignamount">外商投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="foreignamount" id="foreignamount" value="${bean.foreignamount}" datatype="n" nullmsg="外商投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="personalamount">个人投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="personalamount" id="personalamount" value="${bean.personalamount}" datatype="n" nullmsg="个人投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="otheramount">其他投资金额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="otheramount" id="otheramount" value="${bean.otheramount}" datatype="n" nullmsg="其他投资金额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="result">勘查工作程度或成果</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="result" id="result" datatype="*6-16" nullmsg="勘查工作程度或成果6到16位任意字符!" class="col-xs-12" ></textarea></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remark" id="remark" datatype="*6-16" nullmsg="备注6到16位任意字符!" class="col-xs-12" ></textarea></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/expright/info.js"></script>
<script type="text/javascript">
		var rightid = '<%=request.getAttribute("bean.rightid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_bi_expright");
		else if(actiontype=='modifysave')
			modifySave("t_bi_expright");
		else if(actiontype=='deletesave')
			deleteSave("t_bi_expright");
	}
	initvalid("t_bi_expright");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_bi_expright",{"取消": function() {parent.closewin("t_bi_expright");}});
		else 
			parent.setDialogButtons("t_bi_expright",{"取消": function() {parent.closewin("t_bi_expright");},"确定": function() {parent.window.frames["t_bi_expright_frame"].onSave();}});
	});
</script>
</html>