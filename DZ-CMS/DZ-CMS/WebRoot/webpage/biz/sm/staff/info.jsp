<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="staff_info">
	<form id="staff_form" method="post" class="form-horizontal">
		<input type="hidden" id="staffid" name="staffid" value="${bean.staffid}" /> 
		<input type="hidden" name="deptid" id="deptid" value="${bean.deptid}" />
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="staffcode">所属部门</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="deptname" id="deptname" value="${bean.deptname}" datatype="byterange"
						onclick="selectdept()" readonly="readonly" class="col-xs-12" />
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
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="staffcode">员工编码</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="staffcode" id="staffcode" value="${bean.staffcode}" datatype="byterange"
						min="0" max="40" nullmsg="员工编码必填!" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>

			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="staffname">姓名</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="staffname" id="staffname" value="${bean.staffname}" datatype="byterange"
						min="1" max="50" nullmsg="姓名信息必填!" class="col-xs-12" />
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
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="sex">性别</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<dmtag:sourcemanager sourcename="XB" name="sex"
						cssClass="form-control" isnull="false" fixValue="${bean.sex}"></dmtag:sourcemanager>
				</div>
				<div class="col-sm-3"></div>
			</div>

			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="nation">民族</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<dmtag:sourcemanager sourcename="MZ" name="nation"
						cssClass="form-control" isnull="false" fixValue="${bean.nation}"></dmtag:sourcemanager>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="age">年龄</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="age" id="age" value="${bean.age}" datatype="n"
						nullmsg="年龄必须是数字!" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>

			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="idcard">身份证号码</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="idcard" id="idcard" value="${bean.idcard}" datatype="byterange"
						min="0" max="18" class="col-xs-12" />
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
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="jobtitle">职务</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="jobtitle" id="jobtitle" value="${bean.jobtitle}" datatype="byterange"
						min="0" max="40" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>

			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="tptitle">职称</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="tptitle" id="tptitle" value="${bean.tptitle}" datatype="byterange"
						min="0" max="50" class="col-xs-12" />
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
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="entrydate">入职时间</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9" style="display: table">
					<!-- class="clearfix" -->
					<input type="text" name="entrydate" id="entrydate" value="${bean.entrydate}" 
						class="form-control date-picker"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					<span class="input-group-addon">
						<i class="ace-icon fa fa-calendar"></i>
					</span>
				</div>
				<div class="col-sm-3">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>

			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="station">岗位</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="station" id="station" value="${bean.station}" datatype="byterange"
						min="0" max="50" class="col-xs-12" />
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
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="education">学历</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<dmtag:sourcemanager sourcename="XL" name="education"
						cssClass="form-control" isnull="false" fixValue="${bean.education}"></dmtag:sourcemanager>
				</div>
				<div class="col-sm-3"></div>
			</div>
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="operatetype">操作类别</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<dmtag:sourcemanager sourcename="CZLB" name="operatetype"
						cssClass="form-control" isnull="false" fixValue="${bean.operatetype}"></dmtag:sourcemanager>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="address">家庭住址</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="address" id="address" value="${bean.address}" datatype="byterange"
						min="0" max="200" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>

			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="relaphone">联系电话</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9">
					<!-- class="clearfix" -->
					<input type="text" name="relaphone" id="relaphone"
						value="${bean.relaphone}" datatype="byterange"
						min="0" max="20" class="col-xs-12" />
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
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="isleaveoffice">是否离职</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9" style="margin-top: 8px;">
					<!-- class="clearfix" -->
					<input type="radio" class="radioItem" name="isleaveoffice" value="0" checked />否
					<input type="radio" class="radioItem" name="isleaveoffice" value="1"/>是
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div id="lodate_div" style="display: none;">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="lodate">离职时间</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9" style="display: table">
						<!-- class="clearfix" -->
						<input type="text" name="lodate" id="lodate" value="${bean.lodate}" class="form-control date-picker"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						<span class="input-group-addon">
							<i class="ace-icon fa fa-calendar"></i>
						</span>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
		</div>
		<div class="form-group" id="loreason_div" style="display: none;">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="loreason">离职原因</label>
			<div class="col-xs-12 col-sm-10">
				<div class="col-sm-11">
					<!-- class="clearfix" -->
					<textarea name="loreason" id="loreason" datatype="byterange"
						min="0" max="200" class="col-xs-12">${bean.loreason}</textarea>
				</div>
				<div class="col-sm-1">
					<div class="info">
						<span class="Validform_checktip"></span><span class="dec"><s
							class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="ismanager">是否部门主管</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9" style="margin-top: 8px;">
					<!-- class="clearfix" -->
					<input type="radio" class="mngItem" name="ismanager" value="0" checked />否
					<input type="radio" class="mngItem" name="ismanager" value="1"/>是
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="rolename">
				登录用户名
			</label>
			<div class="col-xs-12 col-sm-4">
				<div class="col-sm-9"><!-- class="clearfix" -->
					<input type="text" name="loginname" datatype="byterange"
						min="1" max="100" nullmsg="登录用户名必填！" id="loginname" value="${bean.loginname}" class="col-xs-12" />
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
			<div id="password">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right" for="loginpwd">
					登录密码
				</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9"><!-- class="clearfix" -->
						<input type="password" name="loginpwd" datatype="byterange"
						min="1" max="100" nullmsg="登录密码必填！" id="loginpwd" value="${bean.loginpwd}" class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-2 no-padding-right"
				for="memo">备注</label>
			<div class="col-xs-12 col-sm-10">
				<div class="col-sm-11">
					<!-- class="clearfix" -->
					<textarea name="memo" id="memo" datatype="byterange"
						min="0" max="1000" class="col-xs-12">${bean.memo}</textarea>
				</div>
				<div class="col-sm-1">
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
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/staff/info.js"></script>
<script type="text/javascript">
	var staffid = '<%=request.getAttribute("bean.staffid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	var isleaveoffice = '<%=request.getAttribute("bean.isleaveoffice")==null?"":request.getAttribute("bean.isleaveoffice")%>';
	var ismanager = '<%=request.getAttribute("bean.ismanager")==null?"":request.getAttribute("bean.ismanager")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("staff");
		else if(actiontype=='modifysave')
			modifySave("staff");
		else if(actiontype=='deletesave')
			deleteSave("staff");
	}
	initvalid("staff");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("staff",{"取消": function() {parent.closewin("staff");}});
		else 
			parent.setDialogButtons("staff",{"取消": function() {parent.closewin("staff");},"确定": function() {parent.window.frames["staff_frame"].onSave();}});

		if(actiontype=='addsave'){
			$("#password").show();
		}else{
			if($("#loginpwd").val()!="")
				$("#password").hide();
		}
		leavChange(isleaveoffice);
		$("input:radio[name='isleaveoffice']").click(function(){
			leavChange($(this).val());
		});
		
	    if(ismanager == '1'){
	    	$("input:radio[name='ismanager']").eq(0).attr("checked",false);
	    	$("input:radio[name='ismanager']").eq(1).attr("checked",true);
	    	$("input:radio[name='ismanager']").eq(1).click();
	    }else{
	    	$("input:radio[name='ismanager']").eq(0).attr("checked",true);
	    	$("input:radio[name='ismanager']").eq(1).attr("checked",false);
	    	$("input:radio[name='ismanager']").eq(0).click();
	    }
	});
	
	function leavChange(isleaveoffice){
		if(isleaveoffice == '1'){
	    	$("input:radio[name='isleaveoffice']").eq(0).attr("checked",false);
	    	$("input:radio[name='isleaveoffice']").eq(1).attr("checked",true);
	    	$("input:radio[name='isleaveoffice']").eq(1).click();
	    	$("#lodate_div").css('display','block');
	    	$("#loreason_div").css('display','block');
	    }else{
	    	$("input:radio[name='isleaveoffice']").eq(0).attr("checked",true);
	    	$("input:radio[name='isleaveoffice']").eq(1).attr("checked",false);
	    	$("input:radio[name='isleaveoffice']").eq(0).click();
	    	$("#lodate_div").css('display','none');
	    	$("#loreason_div").css('display','none');
	    }
	}
	
	//选择部门
	function selectdept(){
		parent.openwin("selectdept","/pub/select/dept/selectDept.action","组织机构选择",325,465);
		parent.PageObject.itemMap['selectdept'].callback = function(no){
			selectDeptCallBack(no);
		};
	}
	function selectDeptCallBack(arr){
		$("#deptid").val(arr[0].id);
		$("#deptname").val(arr[0].name);
	}
</script>
</body>
</html>