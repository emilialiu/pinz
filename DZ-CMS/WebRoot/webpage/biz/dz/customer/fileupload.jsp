<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<body>
	<div id="dz_customer_info">
		<form id="dz_customer_form"
			action="<%=request.getContextPath()%>/webpage/biz/bm/geologicalmonthpractice/saveFile.action"
			method="post" class="form-horizontal">
			<div class="form-group">
				<div class="col-xs-12 col-sm-12">
					<div class="col-sm-9">
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

		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/biz/dz/customer/info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	var uid = '<%=request.getAttribute("uid")%>';
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
		$.ajax({
			url: "<%=request.getContextPath()%>/webpage/biz/bm/geologicalmonthpractice/saveFile.action",
			cache:false,
			type:"POST",
			data: { "bean.uid":uid,"bean.head_url":filepath },
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					alertMsg("文件保存成功!");
				}else{
					alertErrorMsg("文件保存失败！");
				}
				parent.window.frames[parent.getCurIframeID()].reloadByGzzdid(uid);
			},
			error: function(data) {
				alertErrorMsg("文件保存失败！");
				parent.window.frames[parent.getCurIframeID()].reloadByGzzdid(uid);
			}
		});
	}
</script>
</html>