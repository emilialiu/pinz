<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<body>
	<div id="dz_home_page_manager_info">
		<form id="dz_home_page_manager_form"
			action="<%=request.getContextPath()%>/webpage/biz/home/page/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="id" name="id" value="${bean.id}" />
			<input type="hidden" id="img_url" name="img_url" value="${bean.img_url}" />
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="img_url">banner图</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<table>
							<tr id="alfxbgsctr">
								<td><input type="file" name="uploadify" id="uploadify" />
								</td>
								<td>
									<div class="uploadify"
										style="height: 30px; width: 120px;cursor:pointer;">
										<div class="uploadify-button"
											style="height: 30px; line-height: 30px; width: 120px;">
											<span class="uploadify-button-text"
												onclick="javascript:jQuery('#uploadify').uploadify('upload','*');">开始上传</span>
										</div>
									</div></td>
							</tr>
							<tr>
								<td colspan="2"><span id="ckyydeclarename"></span></td>
							</tr>
						</table>
						<div id="uploadify-queue"></div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="is_start">是否启用</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="is_start"  class="col-xs-12" >
							<option  value="0">启用</option>
							<option value="1">停用</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sort">排序</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<input type="text" name="sort" id="sort"
							value="${bean.sort}" class="col-xs-12" />
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
						<textarea name="remarks" id="remarks" class="col-xs-12">${bean.remarks}</textarea>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/home/page/info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	var is_start = '<%=request.getAttribute("bean.is_start")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_home_page_manager");
		else if(actiontype=='modifysave')
			modifySave("dz_home_page_manager");
		else if(actiontype=='deletesave')
			deleteSave("dz_home_page_manager");
	}
	initvalid("dz_home_page_manager");
	$(function(){
	$("#is_start  option[value='"+is_start+"']").attr("selected","selected");
		if(actiontype=='view')
			parent.setDialogButtons("dz_home_page_manager",{"取消": function() {parent.closewin("dz_home_page_manager");}});
		else 
			parent.setDialogButtons("dz_home_page_manager",{"取消": function() {parent.closewin("dz_home_page_manager");},"确定": function() {parent.window.frames["dz_home_page_manager_frame"].onSave();}});
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
		$("#img_url").val(filepath);
	}
</script>
</html>