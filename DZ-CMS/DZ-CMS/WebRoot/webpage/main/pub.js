var rootpath = "/DZ-CMS";

//var clientHeights = document.documentElement.clientHeight;
function getClientHeights(){
	return document.documentElement.clientHeight-269;
}

//初始化验证
var initvalid = function(_bizname) {
	$("#"+_bizname+"_form").Validform({
		showAllError:true,
		ajaxPost:true,
		tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=o.obj.parent("div").next().find(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
				
				var infoObj=o.obj.parent("div").next().find(".info");
				if(o.type==2){
					infoObj.fadeOut(200);
				}else{
					if(infoObj.is(":visible")){return;}
					infoObj.css({
						left:-80
					}).show().animate({
						top:0
					},200);
				}
				
			}	
		}
	});
};

/**
*
*	弹出提示
*
*/
function alertMsg(msg){
	$.gritter.add({
		title : '<strong>'+msg+'</strong>',
		class_name : 'gritter-info gritter-center',
		time : '500'
	});
}
/**
*
*	弹出错误消息
*
*/
function alertErrorMsg(msg){
	$.gritter.add({
		title : '<strong>'+msg+'</strong>',
		class_name : 'gritter-info gritter-center',
		time : '500'
	});
}


/**
 * 加载 国家-省份-城市
 * @param objvalue 作为参数的上级控件值
 * @param objSelect 当前控件
 * @param selectValue 当前控件需要选中的值
 */
function getChildId(objvalue,objSelect,selectValue){
	$.ajax({
		url : rootpath + "/pub/select/dict/list.action", 
		data : {"sourcename" : objvalue},
		async : false,  
		cache : false,  
		success : function(data) { 
			var dataJson = eval(data);
			if(dataJson.length>0){
				objSelect.options.length = 0;
				for(var i = 0;i<dataJson.length;i++){
					var objOption = new Option(dataJson[i].name,dataJson[i].code);
					objSelect.add(objOption);
				}
			}else{
				var objOption = new Option("","");
				objSelect.add(objOption);
			}
			if(selectValue != null && selectValue != ''){
				objSelect.value = selectValue;
			}
		},
		error : function(data){
			alert("ajax交互错误");
		}
	});
}

/**
 * 删除文件
 * @param attachmenturl 文件路径
 * @param element 元素 
 * @param param 是否删除文件记录 1删除，0不删除
 * @param uploadobj 对应的上传的upload对象，用来做可以上传控件进行是否可点击操作
 */
function delFile(attachmenturl,element,param){
	$.ajax({
		url : rootpath + "/biz/pub/attachment/delFile.action", 
		data : {"bean.attachmenturl" : attachmenturl, "param":param},
		async : false,  
		cache : false,  
		success : function(data) {
			$(element).parent("div").remove();
			uploadifyDestroy();
			init_Uploadify();   
			
		},
		error : function(data){
			alert("ajax交互错误");
		}
	});
}
/**
 * 全屏遮盖
 */
function fullHide() {
	CommonPerson.Base.LoadingPic.FullScreenHide();
}
/**
 * 部分遮盖
 * @param div
 */
function partHide(div) {
	CommonPerson.Base.LoadingPic.PartHide(div);
}

/**
 * 全屏显示,msg是要展示的消息
 */
function fullShow(msg) {
	CommonPerson.Base.LoadingPic.FullScreenShow(msg);
}
/**
 * 
 * @param div	消息展示的div
 * @param url   图片
 * @param msg	消息
 */
function partShow(div,url,msg) {
	CommonPerson.Base.LoadingPic.PartShow(div,url,msg);
}

/**
 * 下载只有url的附件
 * @param obj
 */
function downurlfile(obj){
	var attachmentname = $(obj).attr("attachmentname");
	var attachmenturl = $(obj).attr("attachmenturl");
	location.href=rootpath+"/biz/pub/attachment/downLoadurlFile.action?bean.attachmentname="+attachmentname+"&bean.attachmenturl="+attachmenturl;
	
}

/**
 * 创建有下载和预览的a标签
 * @param attachmentname			附件名称
 * @param attachmentid				附件id
 */
function _createAstrDowndiv(attachmentname,attachmentid){
	return "<a href=\"javascript:;\" onclick=\"_onlinedownFile(this,'"+attachmentid+"')\" >"+attachmentname+"</a>&nbsp;&nbsp;";
}

/**
 * 在线下载
 */
function _onlinedownFile(obj,attachmentid){
	location.href=rootpath+"/biz/pub/attachment/toDownload.action?bean.attachmentid="+attachmentid;
}
/**
 * 在线预览,doc文件、xls文件、pdf文件、图片、dimine模型文件、cad文件
 * @param attachmentid
 */
function _onlineview(attachmentid){
	parent.openwin_nobutton('fjyl','/biz/pub/attachment/attpreview.action?bean.attachmentid='+attachmentid,'在线附件预览',window.screen.availWidth*0.9,600);
	$("#fjyl").css("overflow-y","hidden");
}

//根据当前用户id查询该用户所在班组信息（teamid teamname）
function getTeamInfoByUserid(){
	var jsonData;
	$.ajax({
		url : rootpath + "/biz/sm/teamstaff/getTeamInfoByUserid.action", 
		data : {},
		async : false,  
		cache : false,  
		success : function(data) {
			jsonData = eval(data);
		},
		error : function(data){
			alert("ajax交互错误");
		}
	});
	return jsonData;
}