<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="team_info">
	<form id="team_form" method="post" class="form-horizontal">
		<input type="hidden" id="teamid" name="teamid" value="${bean.teamid}"/> 
		<input type="hidden" id="deptid" name="deptid" value="${bean.deptid}"/>
		<input type="hidden" id="tdeptid" name="tdeptid" value="${bean.tdeptid}"/>
		<input type="hidden" id="deptname" name="deptname" value="${bean.deptname}"/>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="teamcode">班组编号</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="teamcode" id="teamcode" value="${bean.teamcode}" datatype="byterange"
						min="1" max="40" nullmsg="班组编号信息必填!" class="col-xs-12" />
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
				for="teamtype">班组类型</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<dmtag:sourcemanager sourcename="BZFL" name="teamtype"
						cssClass="form-control" isnull="false" fixValue="${bean.teamtype}"></dmtag:sourcemanager>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="teamname">班组名称</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="teamname" id="teamname" value="${bean.teamname}" datatype="byterange"
						min="1" max="100" nullmsg="班组名称信息必填!" class="col-xs-12" />
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
				for="peoplenum">人数</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="peoplenum" id="peoplenum" value="${bean.peoplenum}" datatype="n,numrange"
						min="1" max="9000" nullmsg="人数必须是正整数!" class="col-xs-12" />
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
				for="dutyman">班组责任人</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="dutymanname" id="dutymanname" value="${bean.dutymanname}" readonly="true" onclick="selectPerson()" class="col-xs-12" />
					<input type="hidden" name="dutyman" id="dutyman" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="phone">联系电话</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="phone" id="phone" value="${bean.phone}" datatype="byterange"
						min="0" max="14" class="col-xs-12" />
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
				for="memo">备注</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<textarea name="memo" id="memo" class="col-xs-12" datatype="byterange"
						min="0" max="1000">${bean.memo}</textarea>
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

	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/team/info.js"></script>
<script type="text/javascript">
	var teamid = '<%=request.getAttribute("bean.teamid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("team");
		else if(actiontype=='modifysave')
			modifySave("team");
		else if(actiontype=='deletesave')
			deleteSave("team");
	}
	initvalid("team");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("team",{"取消": function() {parent.closewin("team");}});
		else 
			parent.setDialogButtons("team",{"取消": function() {parent.closewin("team");},"确定": function() {parent.window.frames["team_frame"].onSave();}});
	});
</script>
</html>