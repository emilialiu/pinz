<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<body>
<div id="t_pub_attachment_info" >
	<form id="t_pub_attachment_form" action="<%=request.getContextPath()%>/webpage/biz/pub/attachment/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="attachmentid" name="attachmentid" value="${bean.attachmentid}"/>
		<input type="hidden" name="attachmentname" id="attachmentname" value="${attachmentname}"/>
		<input type="hidden" name="attachmenturl" id="attachmenturl" value="${attachmenturl}"/>
		<input type="hidden" name="businessid" id="businessid" value="${bean.businessid}"/>
		<input type="hidden" name="detailid" id="detailid" value="${bean.detailid}"/>
		<input type="hidden" name="filename" id="filename" value=""/>
		<input type="hidden" name="filepath" id="filepath" value=""/>
		<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="uploadify">文件上传</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-12">
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
					<div id="uploadify-queue">
						
					</div>
					</div>
				</div>
			</div>
		
	</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/pub/attachment/info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
		var attachmentid = '<%=request.getAttribute("bean.attachmentid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_pub_attachment");
		else if(actiontype=='modifysave')
			modifySave("t_pub_attachment");
		else if(actiontype=='deletesave')
			deleteSave("t_pub_attachment");
	}
	initvalid("t_pub_attachment");
	setTimeout("initUploadify()",0);
	
	function initUploadify(){
		$("#uploadify").uploadify({
			'swf' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify.swf',
			'uploader' : '<%=request.getContextPath()%>/uploadServlet?path=infor',
			'cancelImg' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify-cancel.png',
			'folder' : 'uploads',
			'queueID' : 'uploadify-queue',
			'auto' : false,
			'multi' : true,
			'uploadLimit' : 1,
			'simUploadLimit' : 1,
			'buttonText' : '上传附件',
			'onUploadStart' : function(file) {
	        },
	        'onUploadSuccess':function(file, data, response){  
	       
	        	$("#filename").val(file.name);
	        	$("#filepath").val(data);
				//$("#t_sf_information_info").find("#attachmentname").val(file.name);
        		//$("#t_sf_information_info").find("#attachmenturl").val(data);
	        },  
	        'onUploadComplete':function(){
	        }
		});
	}

	
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("t_pub_attachment",{"取消": function() {parent.closewin("t_pub_attachment");}});
		else 
			parent.setDialogButtons("t_pub_attachment",{"取消": function() {parent.closewin("t_pub_attachment");},"确定": function() {parent.window.frames["t_pub_attachment_frame"].onSave();}});
	});
</script>
</html>