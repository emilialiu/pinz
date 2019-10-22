function onSave(){
	submit();
}
$(function (){
	$("#techname").on("keyup keydown change blur",function (e){
		var funcnamePystr = $(this).toPinyin();
		if($.trim(funcnamePystr)!=''){
			var array = funcnamePystr.split(' ');
			var temp='';
			if(array.length>0){
				for(var i=0;i<array.length;i++){
					temp = temp + array[i].substring(0,1);
				}
			}
			$("#shortname").val(temp);
		}
	});
});
function clearMsg(){
	//document.getElementById("f_funcnameMsg").style.display="none";
	document.getElementById("s_tbjb").value="0";
}
function addChildNode() {
	clearFuncInfo($("#techid").val(),$("#techname").val(),$("#level").val());
}
function deleteTreeNode() {
	var srcNode = BOTree1.getSelectedNodes();
	removeTreeNode();
}
function getNodeInfo(rowData) {
	if(rowData.techid == ''){
		return ;
	}
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/webpage/biz/sc/technology/modify.action", 
		cache: false,
		data:{"bean.techid":rowData.techid}, 
		type: 'post',
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				setGyInfo(dataJson);	
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertErrorMsg("操作失败");
		}
	});
			
}
function checkSave(){
	var flag = true;
	var funcname = $("#techname").val();
	if($.trim(funcname)==''){
		document.getElementById("f_funcnameMsg").style.display="";
		flag = false;
	}
	return flag;
}
//判断对提交数据的处理方式
function submit() {
	var actiontype = document.getElementById('actiontype').value;
	//添加新节点
	if(actiontype == 'addsave'){
		submit_add();
	}else{//修改节点信息
		submit_modify();
	}
}
function submit_add() {
	var parentfunccode = document.getElementById('parentid').value;
	if(parentfunccode == ''){
		alertMsg('请选择上级工艺');
		return;
	}
	if(!checkSave()){
		return
	}
	
	document.getElementById("f_funcnameMsg").style.display="none";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/webpage/biz/sc/technology/doAddSave.action", 
		type:"post", 
		cache: false,
		data: eval('('+getTechInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg('信息添加成功');
				$("#t_sc_technology_grid").trigger("reloadGrid");
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertMsg("操作失败");
		}
	});
	
}
//修改节点
function submit_modify() {
	var parentid = document.getElementById('parentid').value;
	if(parentid == ''){
		alertMsg('最顶级生产工艺不能修改');
		return;
	}
	if(!checkSave()){
		return
	}
	document.getElementById("f_funcnameMsg").style.display="none";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/webpage/biz/sc/technology/doModifySave.action", 
		type:"post", 
		cache: false,
		data: eval('('+getTechInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg('信息修改成功');
				$("#t_sc_technology_grid").trigger("reloadGrid");
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertMsg("操作失败");
		}
	});
}
//删除节点
function submit_delete() {
	var techid = document.getElementById('techid').value;
	var parentid=document.getElementById('parentid').value;
	if(techid == ''){
		alertMsg('请选择您要删除的工艺');
		return;
	}
	bootbox.confirm("确定要删除选中的工艺",function(type){
		if(!type)
			return;
		//这里主要负责和服务器的交互
		$.ajax({
			url:rootpath + "/webpage/biz/sc/technology/delete.action", 
			type:"post", 
			cache: false,
			data: {'bean.techid': techid,'bean.parentid': parentid}, 
			success:function (data) {
				var dataJson = eval(data);
				if (dataJson.success) {
					alertMsg('信息删除成功');
					$("#t_sc_technology_grid").trigger("reloadGrid");
					clearFuncInfo('', '','');
				} else {
					alertMsg(dataJson.errormessage);
				}
			}, 
			error:function (data) {
				alertMsg("操作失败");
			}
		});
	});
}

//设置详细信息
function setGyInfo(rowData) {
	document.getElementById("parentname").value = rowData.parentname;
	document.getElementById("parentid").value = rowData.parentid;
	document.getElementById("level").value = rowData.level;
	document.getElementById("techname").value = rowData.techname;
	document.getElementById("technameen").value = rowData.technameen;
	document.getElementById("techcode").value = rowData.techcode;
	document.getElementById("serialno").value = rowData.serialno;
	document.getElementById("memo").value = rowData.memo;
	document.getElementById("techid").value = rowData.techid;
	document.getElementById("actiontype").value = 'modifysave';
}

//获得表单信息,以json格式返回
function getTechInfo() {
	var parentid = $("#parentid").val();
	var level=$("#level").val();
	var techid = $("#techid").val();
	var techname = $("#techname").val().replace(/\'/g,"’").replace(/\"/g,"”");
	var technameen = $("#technameen").val().replace(/\'/g,"’").replace(/\"/g,"”");
	var shortname=$("#shortname").val();
	var techcode=$("#techcode").val().replace(/\'/g,"’").replace(/\"/g,"”");
	var actiontype = $("#actiontype").val();
	var serialno = $("#serialno").val();
	var memo = $("#memo").val().replace(/\'/g,"’").replace(/\"/g,"”");
	var tech = "{'bean.parentid':'";
	tech += parentid;
	tech += "', 'bean.techid':'";
	tech += techid;
	tech += "', 'bean.techname':'";
	tech += techname;
	tech += "', 'bean.technameen':'";
	tech += technameen;
	tech += "', 'bean.shortname':'";
	tech += shortname;
	tech += "', 'bean.level':'";
	tech += level;
	tech += "', 'bean.techcode':'";
	tech += techcode;
	tech += "', 'bean.serialno':'";
	tech += serialno;
	tech += "', 'bean.memo':'";
	tech += memo;
	tech += "', 'actiontype':'";
	tech += actiontype;
	tech += "'}";
	return tech;
}

//清除工艺信息
function clearFuncInfo(parentID, parentName,level) {
	//document.getElementById("f_funcnameMsg").style.display="none";
	document.getElementById("parentid").value = parentID;
	document.getElementById("parentname").value = parentName;
	document.getElementById("level").value = level;
	document.getElementById("techcode").value = '';
	document.getElementById("actiontype").value = 'addsave';
	document.getElementById("techname").value = '';
	document.getElementById("technameen").value = '';
	document.getElementById("serialno").value = '0';
	document.getElementById("memo").value = '';
}
