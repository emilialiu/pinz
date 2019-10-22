//新增、修改、删除、查看弹出窗口
function createwindow(wid,url,title,width,height){
	if($("#"+wid).length > 0){
		$("#"+wid+"_frame").attr("src",rootpath+url);
	}else{
		var windiv = document.createElement('div');
		windiv.id = wid;
		windiv.style.overflow = "hidden";
		document.body.appendChild(windiv);
		windiv.innerHTML = '<iframe src="'+rootpath+url+'" frameborder="0" style="overflow: hidden; padding: 0px; margin: 0px; width:100%;height:100%" id="'+wid+'_frame" name="'+wid+'_frame" scolling="no" noresize="noresize"></iframe>';
	}
	//弹出表单
	$("#"+wid).dialog({
		autoOpen:false,
		modal:true,
		resizable:true,
		width:width,
		height:height/*,
		buttons: {// 为对话框添加按钮 
			"取消": function() {closewin(wid);},
			"保存": function() {window.frames[wid+"_frame"].onSave();}
		}*/,
		close: function() {
			$('#'+wid+'_frame').src="";	
			$("#"+wid).innerHTML = "";
			/*if (navigator.userAgent.indexOf("Firefox")>0 || navigator.userAgent.indexOf("Chrome")>0) {
				windiv.parentNode.removeChild(windiv);
			} else {
				windiv.removeNode(true);
			}*/
			$(this).dialog("destroy").remove();
		}
	});
	//$("#"+wid).parent().find("div.ui-dialog-buttonset button").addClass('btn btn-xs btn-primary');
	$("#"+wid).dialog("option", "title", title).dialog("open");
	PageObject.set($('#'+wid),wid);
}

//设置dialog按钮
function setDialogButtons(wid, buttons){
	$("#"+wid).dialog("option", "buttons", buttons);
	$("#"+wid).parent().find("div.ui-dialog-buttonset button").addClass('btn btn-xs btn-primary');
}

//选择框弹出窗口
function openwin(wid,url,title,width,height){
	if($("#"+wid).length > 0){
		$("#"+wid+"_frame").attr("src",rootpath+url);
	}else{
		var windiv = document.createElement('div');
		windiv.id = wid;
		windiv.style.overflow = "hidden";
		document.body.appendChild(windiv);
		windiv.innerHTML = '<iframe src="'+rootpath+url+'" frameborder="0" style="overflow: hidden; padding: 0px; margin: 0px; width:100%;height:100%" id="'+wid+'_frame" name="'+wid+'_frame" scolling="no" noresize="noresize"></iframe>';
	}
	//弹出表单
	$("#"+wid).dialog({
		autoOpen:false,
		modal:true,
		resizable:true,
		width:width,
		height:height,
		buttons: {// 为对话框添加按钮 
			"取消": function() {closewin(wid);},
			"清空选择": function() {window.frames[wid+"_frame"].uncheck();},
			"确定": function() {window.frames[wid+"_frame"].onSave();}
		},
		close: function() {
			$('#'+wid+'_frame').src="";	
			$("#"+wid).innerHTML = "";
			/*if (navigator.userAgent.indexOf("Firefox")>0 || navigator.userAgent.indexOf("Chrome")>0) {
				windiv.parentNode.removeChild(windiv);
			} else {
				windiv.removeNode(true);
			}*/
			$(this).dialog("destroy").remove();
		}
	});
	$("#"+wid).parent().find("div.ui-dialog-buttonset button").addClass('btn btn-xs btn-primary');
	$("#"+wid).dialog("option", "title", title).dialog("open");
	PageObject.set($('#'+wid),wid);
}
//弹出窗口
function openwin_nobutton(wid,url,title,width,height){
	if($("#"+wid).length > 0){
		$("#"+wid+"_frame").attr("src",rootpath+url);
	}else{
		var windiv = document.createElement('div');
		windiv.id = wid;
		windiv.style.overflow = "hidden";
		document.body.appendChild(windiv);
		windiv.innerHTML = '<iframe src="'+rootpath+url+'" frameborder="0" style="overflow: hidden; padding: 0px; margin: 0px; width:100%;height:100%" id="'+wid+'_frame" name="'+wid+'_frame" scolling="no" noresize="noresize"></iframe>';
	}
	//弹出表单
	$("#"+wid).dialog({
		autoOpen:false,
		modal:true,
		resizable:true,
		width:width,
		height:height,
		close: function() {
			$('#'+wid+'_frame').src="";	
			$("#"+wid).innerHTML = "";
			/*if (navigator.userAgent.indexOf("Firefox")>0 || navigator.userAgent.indexOf("Chrome")>0) {
				windiv.parentNode.removeChild(windiv);
			} else {
				windiv.removeNode(true);
			}*/
			$(this).dialog("destroy").remove();
		}
	});
	$("#"+wid).dialog("option", "title", title).dialog("open");
	PageObject.set($('#'+wid),wid);
}

//关闭窗口
function closewin(wid,childfunc) {
	$("#"+wid).dialog("close");
	if (childfunc != "" && childfunc != undefined) {
		var currid = $("#tabs").find("li[class='active']").children("a").attr("aria-controls");
		/*if(params != undefined && null!=params && params!=""){
			var fun = eval('content_' + currid + "." + childfunc.replace("()",""));
			fun(params);
		}else{*/
			eval('content_' + currid + "." + childfunc);
		/*}*/
	}
}

var PageObject = {
	id : "", //页面的唯一ID，如果弹出窗口，则是窗口ID，这个唯一性是相对
	self : this,
	parent : {},
	preid : "", //前一个id
	preOb : {}, //前一个对象
	title : "", //页面的名称
	url : "", //页面路径
	remark : "", //有关备注
	items : [], //对象子集
	itemids : [], //ID集合
	itemMap : [], //窗口对象集
	objMap : [] //当前对象集
};

PageObject.set = function(obj, id, parent, title, url) {
	PageObject.preid = PageObject.id;
	PageObject.preob = PageObject.self;
	PageObject.id = id;
	PageObject.self = obj;
	PageObject.parent = parent;
	PageObject.title = title;
	PageObject.url = url;
	var arr = PageObject.items;
	arr[arr.length] = obj;
	var arr = PageObject.itemids;
	arr[arr.length] = id;
	PageObject.itemMap[id] = obj;
	PageObject.objMap[id] = this;
};

// 取得当前iframe的id
function getCurIframeID(){
	return "content_"+$("#tabs").find("li[class='active']").children("a").attr("aria-controls");
}