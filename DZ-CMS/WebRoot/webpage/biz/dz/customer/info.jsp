<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<body>
	<div id="dz_customer_info">
		<form id="dz_customer_form"
			action="<%=request.getContextPath()%>/webpage/biz/dz/customer/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="uid" name="uid" value="${bean.uid}" />
			<input type="hidden" id="head_url" name="head_url" value="${bean.head_url}">
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="vip_level">会员等级： </label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="vip_level"  class="col-xs-12" >
							<option  value="0">普通</option>
							<option value="1">会员</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="name">姓名</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="name" id="name" value="${bean.name}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="nick_name">昵称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="nick_name" id="nick_name"
							value="${bean.nick_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="head_url">头像</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<table>
						<tr id="alfxbgsctr">
							<td><input type="file" name="uploadify" id="uploadify" /></td>
							<td>
								<div class="uploadify" style="height: 30px; width: 120px;cursor:pointer;">
									<div class="uploadify-button" style="height: 30px; line-height: 30px; width: 120px;">
										<span class="uploadify-button-text" onclick="javascript:jQuery('#uploadify').uploadify('upload','*');">开始上传</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span id="ckyydeclarename"></span>
							</td>
						</tr>
					</table>
					<div id="uploadify-queue"></div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="integral_num">积分数</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="integral_num" id="integral_num"
							value="${bean.integral_num}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sex">性别</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="sex" class="col-xs-12" >
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
				<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="country">所在国家</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="country" id="country"
							value="${bean.country}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
		
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="province">所在省</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="province" id="province"
							value="${bean.province}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="city">所在市</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="city" id="city" value="${bean.city}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
	<%-- 		<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="create_time">创建时间</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="create_time" id="create_time"
							value="${bean.create_time}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="last_logon_time">最后登录时间</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="last_logon_time" id="last_logon_time"
							value="${bean.last_logon_time}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div> --%>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/biz/dz/customer/info.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">

		var uid = '<%=request.getAttribute("bean.uid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_customer");
		else if(actiontype=='modifysave')
			modifySave("dz_customer");
		else if(actiontype=='deletesave')
			deleteSave("dz_customer");
	}
	initvalid("dz_customer");
	var vip_level = '<%=request.getAttribute("bean.vip_level")%>';
	var sex = '<%=request.getAttribute("bean.sex")%>';
	$(function(){
	    $("#sex  option[value='"+sex+"']").attr("selected","selected");
	       $("#vip_level  option[value='"+vip_level+"']").attr("selected","selected");
		if(actiontype=='view')
			parent.setDialogButtons("dz_customer",{"取消": function() {parent.closewin("dz_customer");}});
		else 
			parent.setDialogButtons("dz_customer",{"取消": function() {parent.closewin("dz_customer");},"确定": function() {parent.window.frames["dz_customer_frame"].onSave();}});
	});
	initUploadify();
	function initUploadify(){
		$("#uploadify").uploadify({
			'swf' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify.swf',
			'uploader' : '<%=request.getContextPath()%>/uploadServlet?path=drillrecord',
			'cancelImg' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify-cancel.png',
			'folder' : 'uploads',
			'queueID' : 'uploadify-queue',
			'auto' : false,
			'multi' : true,
			'uploadLimit' : 10,
			'simUploadLimit' : 1,
			'buttonText' : '添加附件',
			'queueSizeLimit': 1,
			'fileTypeExts':"*.jpg;*.png;",
			'onUploadStart' : function(file) {
	        },
	        'onUploadSuccess':function(file, data, response){  
				savefile(file.name, data);
	        },  
	        'onUploadComplete':function(){
	        }
		});
	}
	function savefile(filename, filepath){
		$("#head_url").val(filepath);
	}
</script>
</html>