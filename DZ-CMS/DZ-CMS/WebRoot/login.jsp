<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", -10);
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>定制平台</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/login/login.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/font-awesome/css/font-awesome.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-skins.min.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-fonts.min.css" />
	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-ie.min.css" />
	<![endif]-->
	<!--[if lt IE 10]>
		<script src="<%=request.getContextPath()%>/plugins/jquery/css3-mediaqueries.js"></script>
	<![endif]-->
<style>
	body{background-color:#fff;}
	input::-ms-clear{
		display: none;
	}
	.spinner-preview {
		width:100px;
		height:100px;
		text-align:center;
		margin-top:60px;
	}

	.dropdown-preview {
		margin:0 5px;
		display:inline-block;
	}
	.dropdown-preview  > .dropdown-menu {
		display: block;
		position: static;
		margin-bottom: 5px;
	}
	#box{
		background-image:url('<%=request.getContextPath()%>/style/login/images/dlbj2.png');
		width:290px; 
		height:308px; 
		overflow:hidden; 
		position:relative;
	}
	
	#box1{
		background-image:url('<%=request.getContextPath()%>/style/login/images/dlbj3.png');
		width:290px; 
		height:308px; 
		overflow:hidden; 
		position:relative;
	}

	@media screen and (max-width:2400px) {
		.logo{margin-top:3%;}
		#box{
		position: fixed;
		top:150px;
		right:220px
		}
		#box1{
		position: fixed;
		top:150px;
		right:220px
		}
	}
	@media screen and (min-width:1601px) and (max-width:1920px) {
		.logo{margin-top:3%;}
		#box{
		position: fixed;
		top:150px;
		right:800px
		}
		#box1{
		position: fixed;
		top:150px;
		right:100px
		}
	}
	@media screen and (max-width:1600px) {
		#box{
		position: fixed;
		top:150px;
		right:100px
		}
		#box1{
		position: fixed;
		top:150px;
		right:100px
		}
	}
	@media screen and (max-width:1366px) {
		#box{  
		position: fixed;
		top:150px;
		right:100px
		}
		#box1{  
		position: fixed;
		top:150px;
		right:100px
		}
	}
	@media screen and (max-width:767px) {
		#box{  
		position: fixed;
		top:150px;
		right:80px
		}
		#box1{  
		position: fixed;
		top:150px;
		right:80px
		}
	}
	@media screen and (max-width:480px) {
		#box{
		position: fixed;
		right:30px
		}
		#box1{
		position: fixed;
		right:30px
		}
	}
	@media screen and (max-width:320px) {
		#box{
		position: fixed;
		left:10px;
		right:1px
		}
		#box1{
		position: fixed;
		left:10px;
		right:1px
		}
	}
</style>
<script type="text/javascript">
	function doSubmit(){
		var a = VerifyEmpty();
		var b = VerifyEmpty2();
		if(!a||!b){
			return ;
		}
		//if (validator(document.forms[0])){
		document.getElementById('form').submit();
		//}
	}
	//让用户按下enter键之后响应的事件
	document.onkeydown=function(el){
		try{
			if(!el){
				el = window.event || el;
			}
		}catch(e){}
		if(el.keyCode==13){
			doSubmit();
		}
	}
					
	//初始化
	window.onload=function(){
		var errorFlag = '<%=request.getAttribute("errorFlag") %>';
		if (errorFlag == 'true') {
			//alert('登录失败，失败原因：<%=request.getAttribute("errorMessage") %>');
			document.getElementById("i_yhm_true").style.display="none";
			document.getElementById("i_yhm_false").style.display="";
			document.getElementById("d_yhm").setAttribute("class", "form-group has-error");
			document.getElementById("d_mm").setAttribute("class", "form-group has-error");
			document.getElementById("d_loginmessage").setAttribute("class", "form-group has-error");
			$("#login-error").html('登录失败，失败原因：<%=request.getAttribute("errorMessage")%>');
			document.getElementById("username-error").style.visibility="hidden";
			document.getElementById("password-error").style.visibility="hidden";
		}else{
			document.getElementById("i_yhm_true").style.display="none";
			document.getElementById("i_yhm_false").style.display="none";
			document.getElementById("d_yhm").setAttribute("class", "");
			document.getElementById("d_mm").setAttribute("class", "");
			document.getElementById("username-error").style.visibility="hidden";
			document.getElementById("password-error").style.visibility="hidden";
			document.getElementById("login-error").style.visibility="hidden";
		}
	}
	//重置
	function resetValue() {
		document.getElementById('username').value = '';
		document.getElementById('password').value = '';
	}
	function VerifyEmpty() {
		//javascript:VerifyEmpty(this);
		var loginname = document.getElementById('username').value;
		if(loginname==''){
			document.getElementById("i_yhm_true").style.display="none";
			document.getElementById("i_yhm_false").style.display="";
			document.getElementById("d_yhm").setAttribute("class", "form-group has-error");
			document.getElementById("username-error").style.visibility="visible";
			return false;
		}else{
			document.getElementById("i_yhm_true").style.display="none";
			document.getElementById("i_yhm_false").style.display="none";
			document.getElementById("d_yhm").setAttribute("class", "");
			document.getElementById("username-error").style.visibility="hidden";
			return true;
		}
	}
	function VerifyEmpty2() {
		var loginpwd = document.getElementById('password').value;
		if(loginpwd==''){
			document.getElementById("i_mm_true").style.display="none";
			document.getElementById("i_mm_false").style.display="";
			document.getElementById("d_mm").setAttribute("class", "form-group has-error");
			document.getElementById("password-error").style.visibility="visible";
			return false;	
		}else{
			document.getElementById("i_mm_true").style.display="none";
			document.getElementById("i_mm_false").style.display="none";
			document.getElementById("d_mm").setAttribute("class", "");
			document.getElementById("password-error").style.visibility="hidden";
			return true;
		}
	}
	function replacecode(){
		var random = Math.random();
		document.getElementById("code").src=rootpath+"/AuthImg?param="+random;
	}
	function checkcode() {
		var value = $("#codetext").val();
		var flag = false;
		$.ajax({
			url : '<%=request.getContextPath()%>/manager/sys/user/authentication.action',
			async : false,
			cache : false,
			data : {
				'code' : value
			},//键值对.传递的参数,如果是多个参数，写法就是这样[{ name : 'bean.id', value : ll[0].id }]
			success : function(data) {
				if (data == 'false') {
					document.getElementById("i_yzm_false").style.display="";
					document.getElementById("i_yzm_true").style.display="none";
					document.getElementById("d_yzm").setAttribute("class", "form-group has-error");
					return false;
				}else{
					document.getElementById("i_yzm_true").style.display="";
					document.getElementById("i_yzm_false").style.display="none";
					document.getElementById("d_yzm").setAttribute("class", "form-group has-success"); 
				}
			}
		});
		return flag;
	}
</script>

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
	window.jQuery || document.write("<script src='<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<%=request.getContextPath()%>/plugins/components/jquery.gritter/js/jquery.gritter.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-extra.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/jqueryui-touch-punch/jquery.ui.touch-punch.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/chosen/chosen.jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/bootbox.js/bootbox.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/components/jquery-validation/dist/jquery.validate.min.js"></script>
<!-- script src="<%=request.getContextPath()%>/plugins/assets/js/bootstrap-tag.min.js"></script -->
<script type="text/javascript">
$(function(){
	$('.mainbanner').each(function(){
		var $_root = $(this);
		var $window_b = $_root.find('.mainbanner_window');
		var $list = $_root.find('.mainbanner_list');
		var $items = $list.children();
		var $window_ul = $window_b.find('#slideContainer');
		var count = $items.length;
		var item_size = 2000;
		var dur_ms = 1000;
		var autoplay_interval = 4000;		
		var cur_idx = 0;
		var fix_idx = function(_idx){
			if( _idx < 0 )
			return
			(count - 1);
			if( _idx >= count )
			return 0;
			return _idx;
		}	

		var goto = function(_idx){
			var idx = fix_idx( _idx );
			$items.eq(idx).addClass('active').siblings().removeClass('active');
			if( cur_idx != idx ){
				var offset_x = - idx * item_size;
				$window_ul.stop().animate({'left':offset_x},dur_ms);
				cur_idx = idx;
			}
		}

		$items.each(function(index, element){
			var $cur_item = $(this);
			var $cur_a = $cur_item.find('a');
			$cur_a.data('index',index);
			$cur_a.click(function(){
				var index = $(this).data('index');
				goto(index);
				return false;
			});
		});
		
		var autoplay_flag = true;

		window.setInterval(function(){
			if(autoplay_flag){
				goto( cur_idx + 1 );
			}
		},autoplay_interval);

		$_root.hover(function(){
			autoplay_flag = false;
		},function(){
			autoplay_flag = true;
		});
		
		goto(0);
	});
	
});

</script>		
</head>
<body>
<%-- 	<div class="logo"> <img src="<%=request.getContextPath()%>/style/login/images/logo.png" style="height:48px;"/></div> --%>

	<div class="mainbanner">
		<div class="mainbanner_window">
			<ul id="slideContainer">
				<li><img src="<%=request.getContextPath()%>/style/login/images/login_main.jpg" alt="" height="50%" width="100%"/></li>
			</ul>
	  	</div>
		<div class="tab-content" style="border:0;">
			<div class="tab-pane active" id="logintab" role="tabpanel">	
		    	<div id="box">
					<form name="form" id="form" method="post"
						action="<%=request.getContextPath()%>/main/login.action"
						target="_top" class="form-horizontal">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td id="createbtn">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><div id="d_loginmessage" class="form-group"><div style="width:250px;margin:0 auto;" class="help-block" id="login-error"></div></div></td>
							</tr>
							<tr>
								<td>
									<div style=" width:200px;margin:0 auto;" id="d_yhm" class="form-group">
										<span class="block input-icon input-icon-left"> 
											<i class="ace-icon fa fa-user"></i>
											<input type="text" id="username" name="username" placeholder="用户名" class="width-100" value="admin"/> 
											<i class="icon-ok-sign" id="i_yhm_true" style="display:none"></i> 
											<i class="icon-remove-sign" id="i_yhm_false" style="display:none"></i> 
											</span>
											<div id="username-error" class="help-block"  >用户名信息必填</div>
									</div></td>
							</tr>				
							<tr>
								<td>
									<div style=" width:200px;margin:0 auto;" id="d_mm" class="form-group">
										<span class="block input-icon input-icon-left">
											<!-- block input-icon input-icon-right --> <i
											class="ace-icon fa fa-lock"></i> <input type="password"
											id="password" name="password" placeholder="密码"
											class="width-100" value="123"/> <i class="icon-ok-sign" id="i_mm_true"
											style="display:none"></i> <i class="icon-remove-sign"
											id="i_mm_false" style="display:none"></i> </span>
											<div id="password-error" class="help-block" >密码信息必填</div>
									</div></td>
							</tr>
							<tr>
								<td>
									<div style=" width:150px;margin:0 auto;">
										<button class="btn btn-sm btn-info" type="button"
											onclick="doSubmit();">
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <i
												class="ace-icon fa fa-external-link bigger-110"></i> <span
												class="bigger-110 no-text-shadow">登陆</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</button>
									</div></td>
							</tr>
						</table>
					</form>
					<!-- <div style="text-align: center;margin-top: 20px;">
						<a href="#apptab" data-toggle="tab">APP下载</a>
					</div> -->
				</div>
			</div>
			<div class="tab-pane" id="apptab" role="tabpanel">
				<div id="box1">
					<div style="text-align: center;margin-top: 65px;">
						<img src="<%=request.getContextPath()%>/style/login/images/ewm.png" height="200px"/>
					</div>
					<div style="text-align: center;margin-top: 5px;">
						<a href="#logintab" data-toggle="tab">账号登录</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <div style="margin-left:20%; margin-top:1%;">
		<p>Changsha Digital Mine Co.,Ltd. All Right Reserved©2016</p>
	</div> -->
</body>
</html>
