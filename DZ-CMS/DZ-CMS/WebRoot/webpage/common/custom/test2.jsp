<%@ page language="java" pageEncoding="utf-8"%>

	<title>上传文件到服务器</title>

	<div class="main-container">
	   	<div class="page-content">
		   	<div class="row">
			
				<div class="center" style="width:400px; margin:12px;">
					<h3 class="header blue smaller lighter">
						上传文件到服务器
					</h3>
					
					<!-- our form -->
					<form id="myform" method="post" action="<%=request.getContextPath() %>/biz/file/uploadinfo/doImport.action">
						<input  type="file" id="ufile" name="avatar"  />
						
						<div class="hr hr-12 dotted"></div>
						
						<button type="submit" class="btn btn-sm btn-primary">上传</button>
						<button type="reset" class="btn btn-sm">取消</button>
					</form>
				</div>
	
		  	</div>
	  	</div>
	</div>

				
	<script type="text/javascript">
		var scripts = [null, null];

		$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
			$(function() {
				var $form = $('#myform');
				//you can have multiple files, or a file input with "multiple" attribute
				var file_input = $form.find('input[type=file]');
				var upload_in_progress = false;

				file_input.ace_file_input({
					style : 'well',
					btn_choose : 'Select or drop files here',
					btn_change: null,
					droppable: true,
					thumbnail: 'large',
					
					maxSize: 12000000,//bytes
					allowExt: ["xls","xlsx"],
					//allowMime: ["image/jpg", "image/jpeg", "image/png", "image/gif","excel/xls","excel/xlsx"],

					before_remove: function() {
						if(upload_in_progress)
							return false;//if we are in the middle of uploading a file, don't allow resetting file input
						return true;
					},

					preview_error: function(filename , code) {
						//code = 1 means file load error
						//code = 2 image load error (possibly file is not an image)
						//code = 3 preview failed
					}
				})
				file_input.on('file.error.ace', function(ev, info) {
					if(info.error_count['ext'] ) alert('请选择excel文件上传!');
				    //if(info.error_count['size']) alert('文件超出 10M');
					
					//you can reset previous selection on error
					//ev.preventDefault();
					//file_input.ace_file_input('reset_input');
				});
				
				var ie_timeout = null;//a time for old browsers uploading via iframe
				
				$form.on('submit', function(e) {
					e.preventDefault();
				
					var files = file_input.data('ace_input_files');
					if( !files || files.length == 0 ) return false;//no files selected
										
					var deferred ;
					if( "FormData" in window ) {
						//for modern browsers that support FormData and uploading files via ajax
						//we can do >>> var formData_object = new FormData($form[0]);
						//but IE10 has a problem with that and throws an exception
						//and also browser adds and uploads all selected files, not the filtered ones.
						//and drag&dropped files won't be uploaded as well
						
						//so we change it to the following to upload only our filtered files
						//and to bypass IE10's error
						//and to include drag&dropped files as well
						formData_object = new FormData();//create empty FormData object
						
						//serialize our form (which excludes file inputs)
						$.each($form.serializeArray(), function(i, item) {
							//add them one by one to our FormData 
							formData_object.append(item.name, item.value);							
						});
						//and then add files
						$form.find('input[type=file]').each(function(){
							var field_name = $(this).attr('name');
							//for fields with "multiple" file support, field name should be something like `myfile[]`

							var files = $(this).data('ace_input_files');
							if(files && files.length > 0) {
								for(var f = 0; f < files.length; f++) {
									formData_object.append(field_name, files[f]);
								}
							}
						});

						upload_in_progress = true;
						file_input.ace_file_input('loading', true);
						
					    deferred = $.ajax({
					        url: $form.attr('action'),
					        type: $form.attr('method'),
							processData: false,//important
							contentType: false,//important
					 	    dataType: 'json',
							data: formData_object
						})

					}else {
						//for older browsers that don't support FormData and uploading files via ajax
						//we use an iframe to upload the form(file) without leaving the page

						deferred = new $.Deferred //create a custom deferred object
						
						var temporary_iframe_id = 'temporary-iframe-'+(new Date()).getTime()+'-'+(parseInt(Math.random()*1000));
						var temp_iframe = 
								$('<iframe id="'+temporary_iframe_id+'" name="'+temporary_iframe_id+'" \
								frameborder="0" width="0" height="0" src="about:blank"\
								style="position:absolute; z-index:-1; visibility: hidden;"></iframe>')
								.insertAfter($form)

						$form.append('<input type="hidden" name="temporary-iframe-id" value="'+temporary_iframe_id+'" />');
						
						temp_iframe.data('deferrer' , deferred);
						//we save the deferred object to the iframe and in our server side response
						//we use "temporary-iframe-id" to access iframe and its deferred object
						
						$form.attr({
									  method: 'POST',
									 enctype: 'multipart/form-data',
									  target: temporary_iframe_id //important
									});

						upload_in_progress = true;
						file_input.ace_file_input('loading', true);//display an overlay with loading icon
						$form.get(0).submit();
						
						//if we don't receive a response after 10 seconds, let's declare it as failed!
						ie_timeout = setTimeout(function(){
							ie_timeout = null;
							temp_iframe.attr('src', 'about:blank').remove();
							deferred.reject({'status':'OK', 'message':'Timeout!'});
						} , 10000);
					}

					deferred
					.done(function(result) {//success	
			            if(result.status == "OK") {  
			                alertMsg("文件上传成功!"); 
			            } else {  
			            	alertMsg("文件上传失败"+result.message);  
			            }
					})
					.fail(function(result) {//failure	
			            if(result.status == "OK") {  
			                alertMsg("文件上传成功!"); 
			            } else {  
			            	alertMsg("文件上传失败"+result.message);  
			            }
					})
					.always(function() {//called on both success and failure
						if(ie_timeout) clearTimeout(ie_timeout)
						ie_timeout = null;
						upload_in_progress = false;
						file_input.ace_file_input('loading', false);
						
						file_input.ace_file_input('reset_input_ui');
					});

					deferred.promise();
				});

				//when "reset" button of form is hit, file field will be reset, but the custom UI won't
				//so you should reset the ui on your own
				$form.on('reset', function() {
					$(this).find('input[type=file]').ace_file_input('reset_input_ui');
				});

			});
		});
	</script>
