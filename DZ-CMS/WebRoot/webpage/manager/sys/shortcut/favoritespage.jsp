<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
		<title>快捷键管理</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/select2.min.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-skins.min.css" />
		<style>
.dd-nodrag,.dd2-content {
	display: block;
	min-height: 38px;
	margin: 5px 0;
	padding: 8px 12px;
	background: #f8faff;
	border: 1px solid #dae2ea;
	color: #7c9eb2;
	text-decoration: none;
	font-weight: bold;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box
}
		</style>

<script type="text/javascript">
	var scripts = [null,null];
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	//页面脚本
	});
</script>
		
		
		<div class="row">
			<div class="col-xs-12">
									<div class="col-sm-6">
										<div class="widget-box widget-color-blue2">
											<div class="widget-header">
												<h4 class="widget-title lighter smaller">功能信息树</h4>
											</div>
											<div class="widget-body">
												<div class="widget-main padding-8" id="leftid">
													
												</div>
											</div>
										</div>
									</div>

									<div class="col-sm-6">
										<div class="widget-box widget-color-green2">
											<div class="widget-header">
												<h4 class="widget-title lighter smaller">功能信息</h4>
											</div>
											<div class="widget-body">
											 <form id="Form1" name="Form1"  method="post">
												<div class="widget-main padding-8" id="rightid">
													
												</div>
											</form>	
											</div>
										</div>
									</div>
</div></div>
		<script src="<%=request.getContextPath()%>/assets/js/jquery.nestable.min.js"></script>
		<!-- ace scripts -->

		<!-- inline scripts related to this page -->
		
		<script type="text/javascript">
jQuery(function($){
		$.ajax({
			url: rootpath+'/manager/sys/func/shortcutlefttree.action',
			cache: false,
			type:'post',
			async:false,
			data:{},
			success: function(data){
				$("#leftid").html(data);
				},
			   error: function(data) {
				}
			});
		$.ajax({
			url: rootpath+'/manager/sys/func/shortcut.action',
			cache: false,
			type:'post',
			async:false,
			data:{},
			success: function(data){
				$("#rightid").html(data);
				},
			   error: function(data) {
				}
			});
});

		$('.dd').nestable();
		$('.dd').nestable('collapseAll');
		$('.dd-handle a').on('mousedown', function(e){
			e.stopPropagation();
		});
		$('[data-rel="tooltip"]').tooltip();
		$('#nestable2').nestable().on('change', function(){
	           var r = $('.dd').nestable('serialize');
	         var temp =  backcall(r);
	           $.ajax({
	   			url: rootpath+'/manager/sys/func/insertshortcut.action',
	   			cache: false,
	   			type:'post',
	   			async:false,
	   			data:{'funccode':temp},
	   			success: function(data){
					//window.Form1.submit();
	   			},
	   			   error: function(data) {
	   				   alert('错误');
	   				}
	   			});
	      //  console.log(temp);
		});    
	        // $('#nestable').nestable().on('change', updateOutput);//改变后执行修改方法
		function backcall(r){
			var temp='';
	          for(var i=0; i<r.length; i++)
	          {
	        	  temp=temp+r[i].id+",";
	        	  if(r[i].children!=undefined){
	        		  for(var j=0;j<r[i].children.length;j++){
	        			  temp=temp+r[i].children[j].id+",";
	        			  if(r[i].children[j].children!=undefined){
	    	        		  for(var k=0;k<r[i].children[j].children.length;k++){
	    	        			  temp=temp+r[i].children[j].children[k].id+",";
	    	        			  if(r[i].children[j].children[k].children!=undefined){
	    	    	        		  for(var l=0;l<r[i].children[j].children[k].children.length;l++){
	    	    	        			  temp=temp+r[i].children[j].children[k].children[l].id+",";
	    	    	        			  if(r[i].children[j].children[k].children[l].children!=undefined){
	    	    	    	        		  for(var m=0;m<r[i].children[j].children[k].children[l].children.length;m++){
	    	    	    	        			  temp=temp+r[i].children[j].children[k].children[l].children[m].id+",";
	    	    	    	        		  }
	    	    	        			  }
	    	    	        		  }
	    	        			  }
	    	        		  }
	        			  } 
	        		  }
	        	  }
	        	  
	          }
	        var reg=/,$/gi;
	        temp=temp.replace(reg,"");
	        return temp
	    }
		</script>