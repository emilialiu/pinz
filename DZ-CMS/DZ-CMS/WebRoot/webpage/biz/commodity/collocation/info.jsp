<%@ page language="java" pageEncoding="utf-8"%>
<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_commodity_collocation_info" >
	<form id="dz_commodity_collocation_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/collocation/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		<input type="hidden" id="banner_url" name="banner_url" value="${bean.banner_url}"/>
		<input type="hidden" id="designer_head_url" name="designer_head_url" value="${bean.designer_head_url}"/> 
		<input type="hidden" id="single_ids" name ="single_ids" value="${bean.single_ids}"/>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="banner_url">banner图</label>
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
					for="designer_head_url">设计师头像</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<table>
						<tr id="alfxbgsctr">
							<td><input type="file" name="uploadify2" id="uploadify2" /></td>
							<td>
								<div class="uploadify" style="height: 30px; width: 120px;cursor:pointer;">
									<div class="uploadify-button" style="height: 30px; line-height: 30px; width: 120px;">
										<span class="uploadify-button-text" onclick="javascript:jQuery('#uploadify2').uploadify('upload','*');">开始上传</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span id="ckyydeclarename2"></span>
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
					for="designer_name">设计师名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="designer_name" id="designer_name"
							value="${bean.designer_name}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="designer_said">设计师说明内容</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<textarea name="designer_said" id="designer_said"
							class="col-xs-12"></textarea>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="single_ids">单品组成</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="names" id="names"
							value="${bean.names}" readonly="readonly" onclick="selectsp()" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="remarks">备注</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<textarea name="remarks" id="remarks" class="col-xs-12"></textarea>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			
			

		</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/collocation/info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
		var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_commodity_collocation");
		else if(actiontype=='modifysave')
			modifySave("dz_commodity_collocation");
		else if(actiontype=='deletesave')
			deleteSave("dz_commodity_collocation");
	}
	initvalid("dz_commodity_collocation");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("dz_commodity_collocation",{"取消": function() {parent.closewin("dz_commodity_collocation");}});
		else 
			parent.setDialogButtons("dz_commodity_collocation",{"取消": function() {parent.closewin("dz_commodity_collocation");},"确定": function() {parent.window.frames["dz_commodity_collocation_frame"].onSave();}});
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
		$("#banner_url").val(filepath);
	}
		initUploadify2();
	function initUploadify2(){
		$("#uploadify2").uploadify({
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
				savefile2(file.name, data);
	        },  
	        'onUploadComplete':function(){
	        }
		});
	}
	function savefile2(filename, filepath){
		$("#designer_head_url").val(filepath);
	}
	
</script>
</html>