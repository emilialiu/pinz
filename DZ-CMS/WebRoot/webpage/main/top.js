//加载左边的菜单   2015
//function doSelectMemu(){
//	$.ajax({
//		url: rootpath+'/main/home/childfuncs.action',
//		cache: false,
//		data: {"bean.rootpath":rootpath},
//		success: function(data){
//			var dataJson = eval(data);
//			if(dataJson.cells[0] != null){
//				$("#nav-nav-list-func").html(dataJson.cells[0].funcinfo);
//			}
//		},
//		error: function(data) {
//			//$("#alarmID").html(dataJson.cells[0].text);
//		}
//	});
//}
function doSelectMemu(){
	doSelectTopfuncs();
}

//获取一级栏目
function doSelectTopfuncs(){
	$.ajax({
		url: rootpath+'/main/home/doSelectTopfuncs.action',
		cache: false,
		data: {"bean.rootpath":rootpath},
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.cells[0] != null){
				$("#doSelectTopfuncsinfo").html(dataJson.cells[0].funcinfo);
				//doselectfirsthome();
				var id = $("#doSelectTopfuncsinfo div:eq(0)").find("ul").attr("id");
				openinfo(id.replace("div",""))
			}
		},
		error: function(data) {
			//$("#alarmID").html(dataJson.cells[0].text);
		}
	});	
}
var preselmenucode="";
var pagecontentiw =800;
var fisrthomecode="";//"20140718000000000711";
function doselectfirsthome(){
	pagecontentiw = $(".page-content").width()+18;
	updatebackimg(fisrthomecode);
	
	//$("#nav-nav-list-func").html("");
	$("#nav-nav-list-func").css('display','none');		
	$("#sidebar-collapse").css('display','none');
	var sidebar = document.getElementById('sidebar');
	ace.toggleClass(sidebar , 'h-sidebar');
	$(".page-content").css('width',pagecontentiw+190);
	$(".page-content").trigger('main_container_fixed');
}
//修改备选的背景
function updatebackimg(funccode){
	$(".funactive").removeClass("funactive");
	$("#div"+funccode+" li").addClass("funactive");
	preselmenucode = funccode;
}

function openinfo(funccode){
	//if(funccode == preselmenucode){
		if(funccode=='20151218000000000888'){
			window.open(rootpath+"/webpage/biz/dimine/index.jsp");
			return;
		}
	//}
	updatebackimg(funccode);
	$.ajax({
		url: rootpath+'/main/home/childfuncs.action',
		cache: false,
		data: {"bean.rootpath":rootpath,"bean.parentfunccode":funccode},
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.cells[0] != null){
				$("#nav-nav-list-func").html(dataJson.cells[0].funcinfo);
				onLoadFristUrl(funccode);
			}
		},
		error: function(data) {
			//$("#alarmID").html(dataJson.cells[0].text);
		}
	});
}

//获取改栏目下第一个连接
function onLoadFristUrl(funccode){
	$.ajax({
		url: rootpath+'/main/home/onLoadFristUrl.action',
		cache: false,
		data: {"bean.rootpath":rootpath,"bean.parentfunccode":funccode},
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.cells[0] != null){
				var arr = new Array();
				arr = dataJson.cells[0].funcinfo.split(",");
				if(arr[0]=='true'){
					
				} else{
					location.href = rootpath+"/webpage/main/adminindex.jsp#dimine"+arr[1];
				}
			}
		},
		error: function(data) {
			//$("#alarmID").html(dataJson.cells[0].text);
		}
	});
}
/**
 * 加载顶部待办信息
 */
$(function(){
	var html = "";
	$.ajax({
		url: rootpath+'/webpage/biz/pub/tomatter/doviewdealt.action',
		cache: false,
		data: {},
		success: function(data){
			var dataJson = eval("("+data+")");
			$(".badge-grey").html(dataJson.total);
		    $(".tomatter_count").html(dataJson.total);
		},
		error: function(data) {

		}
	});
});

function viewtomatter(matterid){
	location.href = "#dimine/manager/sys/tomatter/main.jsp";
}

/**
 * 加载顶部告警信息
 */
$(function(){
	var html = "";
	var color = "";
	$.ajax({
		url: rootpath+'/manager/sys/alarm/getAlarmListByUserid.action',
		cache: false,
		data: {},
		success: function(data){
			var dataJson = eval(data);
		  $(".badge-important").html(dataJson.count);
			$(".alarm_count").html(dataJson.count);
			html = "";
			$.each(dataJson.list, function(i, str) {
				if(str.alarmlevel == 'GJDJ001'){
					color = "btn-info";
				}else if(str.alarmlevel == 'GJDJ002'){
					color = "btn-yellow";
				}else if(str.alarmlevel == 'GJDJ003'){
					color = "btn-warning";
				}else{
					color = "btn-danger";
				}
				html += "<li><a href='javascript:viewalarm(\""+str.id+"\")'>"+"<i class='btn btn-xs "+color+" fa fa-user'></i> "+str.title+"</a></li>";
				//$(".alarm_list").html(html);
			});
		},
		error: function(data) {
			
		}
	});
});

function viewalarm(alarmid){
	location.href = "#dimine/manager/sys/alarm/main.jsp";
}

/**
 * 加载顶部公告信息
 */
$(function(){
	var html = "";
	$.ajax({
		url: rootpath+'/manager/sys/message/getMessageListByUserid.action',
		cache: false,
		data: {},
		success: function(data){
			var dataJson = eval(data);
			$(".badge-success").html(dataJson.count);
			$(".message_count").html(dataJson.count);
			html = "";
			$.each(dataJson.list, function(i, str) {
				html += "<li><a href='javascript:viewmessage(\""+str.id+"\")'>"+"<div class='clearfix'><span class='pull-left'> "+str.title+"</span></div></a></li>"; 
			//$(".message_list").html(html);
			});
		},
		error: function(data) {
			
		}
	});
});

function viewmessage(messageid){
	location.href = "#dimine/manager/sys/message/main.jsp";
}

//弹出修改密码表单
$("#update_pwd").dialog({
	autoOpen:false,
	modal:true,
	resizable:true,
	width:400,
	buttons: {  // 为对话框添加按钮  
         "取消": function() {$("#update_pwd").dialog("close");},  
         "保存": function() {updatePwd();}
     }  
});
function updatePwd(){
	//检查不成功，跳出
	if(!$("#update_pwd_form").Validform().checkForm()) return;
	
    var consoleDlg = $("#update_pwd"); 
    var oldpasswd = $.trim(consoleDlg.find("#oldpasswd").val());
    var newpasswd = $.trim(consoleDlg.find("#newpasswd").val());
    var confirmpasswd = $.trim(consoleDlg.find("#confirmpasswd").val());
    
    $.ajax( {  
        url : rootpath+"/manager/sys/user/updatepwd.action",  
        data : {'oldpasswd':oldpasswd,'newpasswd':newpasswd,'confirmpasswd':confirmpasswd},  
        cache : false,  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {  
                consoleDlg.dialog("close");  
                alertMsg("密码修改成功!");  
            } else {  
            	alertMsg("密码修改失败"+dataJson.errormessage);  
                consoleDlg.trigger('domi.addsave.failue');
            }  
        },
        error : function(data) {  
        	alertMsg("系统ajax交互错误" );  
        } 
    });  
}
initvalid("update_pwd");

//修改密码
function updatePassword(){
	var consoleDlg = $("#update_pwd");  
	var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
	dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
	consoleDlg.find("input").removeAttr("disabled").val("");  
	
	$("#update_pwd_form").Validform().resetForm();
	consoleDlg.find(".info").css('display','none');
	
	consoleDlg.dialog("option", "title", "修改密码").dialog("open");    	
}
/**
 * 查看所有待办信息
 */
function view_db(){
	addTabs({id:'dbxx',title:'待办消息',close:true,url:'/webpage/biz/pub/tomatter/main.jsp'});
}
/**
 * 跳转快捷键
 */
function openShortcut(){
	addTabs({id:'b5666498c6d14096b7c83b9bd9b05f87',title:'快捷功能管理',close:true,url:'/webpage/biz/shortcut/func/main.jsp'});
}
/**
 * 告警预警
 */
function openwaring(){
	addTabs({id:'626029727f494ea684a273c00f6a3b83',title:'告警信息',close:true,url:'/webpage/manager/sys/alarm/main.jsp'});
}
/**
 * 公告
 */
function openmessage(){
	addTabs({id:'e1f308a9c9524cf9b96c0c8279b8d118',title:'通知公告',close:true,url:'/webpage/manager/sys/message/main.jsp'});
}