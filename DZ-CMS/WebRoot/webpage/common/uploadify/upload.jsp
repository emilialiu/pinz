<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript">
		var rootpath = "<%=request.getContextPath()%>";
		$(document).ready(function() {
			$("#uploadify").uploadify({
				'swf' : rootpath+'/webpage/common/uploadify/uploadify.swf',
				'uploader' : rootpath+'/uploadServlet',
				'cancelImg' : rootpath+'/webpage/common/uploadify/uploadify-cancel.png',
				'folder' : 'uploads',
				'queueID' : 'fileQueue',
				'auto' : false,
				'multi' : true,
				'uploadLimit' : 1,
				'simUploadLimit' : 1,
				'buttonText' : '添加附件',
				'onUploadStart' : function(file) {
		        },
		        'onUploadSuccess':function(file, data, response){  
					//alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
		           	//alert("上传成功！");
		        },  
		        'onUploadComplete':function(){  
		           	//alert(0);
		        }
			});
		});
	</script>
		<div id="fileQueue"></div>
		<input type="file" name="uploadify" id="uploadify" />
		<p>
			<a href="javascript:jQuery('#uploadify').uploadify('upload','*');">开始上传</a>&nbsp;
			<a href="javascript:jQuery('#uploadify').uploadify('cancel','*')">取消所有上传</a>
		</p>
