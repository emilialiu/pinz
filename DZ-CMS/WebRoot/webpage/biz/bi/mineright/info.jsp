<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="t_bi_mineright_info" >
	<form id="t_bi_mineright_form" action="<%=request.getContextPath()%>/webpage/biz/bi/mineright/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="rightid" name="rightid" value="${bean.rightid}"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="cooperateent">合作单位</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="cooperateent" id="cooperateent" value="${bean.cooperateent}" datatype="*6-16" nullmsg="合作单位6到16位任意字符!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="planscale">设计规模</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="planscale" id="planscale" value="${bean.planscale}" datatype="*6-16" nullmsg="设计规模6到16位任意字符!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="involume">投资额</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="involume" id="involume" value="${bean.involume}" datatype="n" nullmsg="投资额必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="arearange">矿区范围</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="arearange" id="arearange" value="${bean.arearange}" datatype="*6-16" nullmsg="矿区范围6到16位任意字符!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mrarea">矿权面积(km2)</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="mrarea" id="mrarea" value="${bean.mrarea}" datatype="n" nullmsg="矿权面积(km2)必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mineraltype">矿种</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="mineraltype" id="mineraltype" class="form-control" ><option value ="">矿种</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="catype">共伴生类型</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="catype" id="catype" class="form-control" ><option value ="">共伴生类型</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="verifyreserve">探明的地质储量</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="verifyreserve" id="verifyreserve" value="${bean.verifyreserve}" datatype="n" nullmsg="探明的地质储量必须是数字!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mineway">开采方式</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="mineway" id="mineway" class="form-control" ><option value ="">开采方式</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="miningmethod">采矿方法</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="miningmethod" id="miningmethod" class="form-control" ><option value ="">采矿方法</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="benemethod">选矿方法</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="benemethod" id="benemethod" class="form-control" ><option value ="">选矿方法</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="depthupper">采深上限</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="depthupper" id="depthupper" value="${bean.depthupper}" datatype="*6-16" nullmsg="采深上限6到16位任意字符!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="depthlower">采深下限</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><input type="text" name="depthlower" id="depthlower" value="${bean.depthlower}" datatype="*6-16" nullmsg="采深下限6到16位任意字符!" class="col-xs-12" /></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="minegetway">采矿权获取方式</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><select name="minegetway" id="minegetway" class="form-control" ><option value ="">采矿权获取方式</option></select></div><div class="col-sm-3"></div></div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">备注</label><div class="col-xs-12 col-sm-9"><div class="col-sm-6"><!-- class="clearfix" --><textarea name="remark" id="remark" datatype="*6-16" nullmsg="备注6到16位任意字符!" class="col-xs-12" ></textarea></div><div class="col-sm-3"><div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div></div></div>
		</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/bi/mineright/info.js"></script>
<script type="text/javascript">
		var rightid = '<%=request.getAttribute("bean.rightid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_bi_mineright");
		else if(actiontype=='modifysave')
			modifySave("t_bi_mineright");
		else if(actiontype=='deletesave')
			deleteSave("t_bi_mineright");
	}
	initvalid("t_bi_mineright");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_bi_mineright",{"取消": function() {parent.closewin("t_bi_mineright");}});
		else 
			parent.setDialogButtons("t_bi_mineright",{"取消": function() {parent.closewin("t_bi_mineright");},"确定": function() {parent.window.frames["t_bi_mineright_frame"].onSave();}});
	});
</script>
</html>