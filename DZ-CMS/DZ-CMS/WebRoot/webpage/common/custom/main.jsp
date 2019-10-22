<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>
	<script type="text/javascript">
		var scripts = [null, null]
		$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
		
		$('#id-input-file-2').ace_file_input({
			no_file:'No File ...',
			btn_choose:'Choose',
			btn_change:'Change',
			droppable:false,
			onchange:null,
			thumbnail:true //| true | large
			//whitelist:'gif|png|jpg|jpeg'
			//blacklist:'exe|php'
			//onchange:''
			//
		});
		
		function doImport(){
			var fileObj = document.getElementById("file").files[0]; // 获取文件对象
            var FileController = rootpath+"/pub/upfile/doUpFile.action";                    // 接收上传文件的后台地址 
            // FormData 对象

            var form = new FormData();
//            form.append("author", "hooyes");                        // 可以增加表单数据
            form.append("file", fileObj);                           // 文件对象
            // XMLHttpRequest 对象
            var xhr = new XMLHttpRequest();
            xhr.open("post", FileController, true);
            xhr.onload = function () {
                alert("上传完成!");

            };
            xhr.send(form);
		}
	</script>
<form>
	<div class="row">
		<div class="col-xs-12">
			<label class="col-sm-1">
				上传文件:
			</label>
			<div class="col-sm-4">
				<input multiple="" type="file" id="file" />
			</div>
			<div class="col-sm-1">
				<button class="btn btn-xs btn-success bigger no-border" onclick="javascript:doImport()">
					<i class="ace-icon fa fa-save"></i>
					提交
				</button>
			</div>
		</div>
	</div>

</form>
<%@include file="/pub/biz/footerlist.jsp" %>

