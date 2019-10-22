function onSave(){
	submit();
}
$(function (){
	$("#funcname").on("keyup keydown change blur",function (e){
		$("#funcnamepy").val($(this).toPinyin());
		var funcnamePystr = $("#funcnamepy").val();
		if($.trim(funcnamePystr)!=''){
			var array = funcnamePystr.split(' ');
			var temp='';
			if(array.length>0){
				for(var i=0;i<array.length;i++){
					temp = temp + array[i].substring(0,1);
				}
			}
			$("#funcnamepyshort").val(temp);
		}
	});
	$("#memo").on("keyup keydown change blur",function (e){
		$("#memopy").val($(this).toPinyin());
		if($("#memopy").val()!=''){
			$("#memopyshort").val($("#memopy").val());
			var memoPystr = $("#memopy").val();
			if($.trim(memoPystr)!=''){
				var array = memoPystr.split(' ');
				var temp='';
				if(array.length>0){
					for(var i=0;i<array.length;i++){
						temp = temp + array[i].substring(0,1);
					}
				}
				$("#memopyshort").val(temp);
			}
		}
	});

});
function clearMsg(){
	document.getElementById("f_funcnameMsg").style.display="none";
	document.getElementById("s_tbjb").value="0";
}
function addChildNode() {
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''){
		alertMsg('请选择上级功能');
		return ;
	}
	clearFuncInfo(srcNode[0].id, srcNode[0].name);
}
function deleteTreeNode() {
	var srcNode = BOTree1.getSelectedNodes();
	removeTreeNode();
}
function getNodeInfo(funccode) {
	if(funccode == ''){
		return ;
	}
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/manager/sys/func/modify.action", 
		cache: false,
		data:{"bean.funccode": funccode, 'actiontype':'modify'}, 
		type: 'post',
		success:function (data) {
			var array = data.split("!");
			var temp = "";
			var tempB = "";
			if(array.length>0){
			for(var i=0;i<array.length;i++){
				if(i!=1){
				temp = temp + array[i];
				}else{
				tempB = array[i];
				}
			}
			}
			var dataJson = eval(temp);
			if (dataJson.success) {
				setFuncInfo(dataJson.cells[0],tempB);
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertMsg("操作失败");
		}
	});
}
function checkSave(){
	var flag = true;
	var funcname = $("#funcname").val();
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
	var parentfunccode = document.getElementById('parentfunccode').value;
	if(parentfunccode == ''){
		alertMsg('请选择上级功能');
		return;
	}
	if(!checkSave()){
		return
	}
	
	document.getElementById("f_funcnameMsg").style.display="none";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/manager/sys/func/add.action", 
		type:"post", 
		cache: false,
		data: eval('('+getFuncInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg('信息添加成功');
				var funccode = dataJson.cells[0].funccode;
				document.getElementById("funccode").value = funccode;
				var funcname = document.getElementById("funcname").value ;
				var newNode = [{id:funccode,pid:BOTree1.getSelectedNodes()[0].id,name:funcname}];
				BOTree1.addNodes(BOTree1.getSelectedNodes()[0], newNode);
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
	var parentfunccode = document.getElementById('parentfunccode').value;
	if(parentfunccode == ''){
		alertMsg('请选择上级功能');
		return;
	}
	if(!checkSave()){
		return
	}
	document.getElementById("f_funcnameMsg").style.display="none";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/manager/sys/func/modify.action", 
		type:"post", 
		cache: false,
		data: eval('('+getFuncInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg('信息修改成功');
				var funcname = document.getElementById("funcname") ;
				renameTreeNode(funcname);
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
	var funccode = document.getElementById('funccode').value;
	if(funccode == ''){
		alertMsg('请选择您要删除的功能');
		return;
	}
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode[0].isParent){
		alertMsg('该功能项还含有子功能，不能够删除');
		return ;
	}
	parent.bootbox.confirm("确定要删除选中的功能",function(type){
		if(!type)
			return;
		//这里主要负责和服务器的交互
		$.ajax({
			url:rootpath + "/manager/sys/func/delete.action", 
			type:"post", 
			cache: false,
			data: {'bean.funccode': funccode}, 
			success:function (data) {
				var dataJson = eval(data);
				if (dataJson.success) {
					alertMsg('信息删除成功');
					deleteTreeNode();
					var srcNode = BOTree1.getSelectedNodes();
					clearFuncInfo('', '');
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
function setFuncInfo(funcObj,temp) {
	document.getElementById("parentfunccode").value = funcObj.parentfunccode;
	document.getElementById("funccode").value = funcObj.funccode;
	document.getElementById("parentfuncname").value = funcObj.parentname;
	document.getElementById("funcname").value = funcObj.funcname;
	document.getElementById("funcnameen").value = funcObj.funcnameen;
	document.getElementById("url").value = funcObj.url;
	document.getElementById("actiontype").value = funcObj.actiontype;
	document.getElementById("functype").value = funcObj.functype;
	document.getElementById("orderno").value = funcObj.orderno;
	document.getElementById("memo").value = funcObj.memo;
	//document.getElementById("funcicon").value = funcObj.funcicon;
	document.getElementById("funcnamepy").value = funcObj.funcnamepy;
	document.getElementById("funcnamepyshort").value = funcObj.funcnamepyshort;
	document.getElementById("memopy").value = funcObj.memopy;
	document.getElementById("memopyshort").value = funcObj.memopyshort;
	$("#funcicon").html(funcObj.funcicon);
	$("#u_tblx").html(temp);
}

//获得表单信息,以json格式返回
function getFuncInfo() {
	var parentfunccode = document.getElementById("parentfunccode").value;
	var funccode = document.getElementById("funccode").value;
	var funcname = document.getElementById("funcname").value;
	var funcnameen = document.getElementById("funcnameen").value;
	var actiontype = document.getElementById("actiontype").value;
	var url = document.getElementById("url").value;
	var functype = document.getElementById("functype").value;
	var orderno = document.getElementById("orderno").value;
	var memo = document.getElementById("memo").value;
	var funcicon = $("#funcicon").html();
	var funcnamepy = document.getElementById("funcnamepy").value;
	var funcnamepyshort = document.getElementById("funcnamepyshort").value;
	var memopy = document.getElementById("memopy").value;
	var memopyshort = document.getElementById("memopyshort").value;
	var func = "{'bean.funccode':'";
	func += funccode;
	func += "', 'bean.parentfunccode':'";
	func += parentfunccode;
	func += "', 'bean.funcname':'";
	func += funcname;
	func += "', 'bean.funcnameen':'";
	func += funcnameen;
	func += "', 'bean.url':'";
	func += url;
	func += "', 'bean.functype':'";
	func += functype;
	func += "', 'bean.orderno':'";
	func += orderno;
	func += "', 'bean.memo':'";
	func += memo;
	func += "', 'bean.funcicon':'";
	func += funcicon;
	func += "', 'actiontype':'";
	func += actiontype;
	func += "', 'bean.funcnamepy':'";
	func += funcnamepy;
	func += "', 'bean.funcnamepyshort':'";
	func += funcnamepyshort;
	func += "', 'bean.memopy':'";
	func += memopy;
	func += "', 'bean.memopyshort':'";
	func += memopyshort;
	func += "'}";
	return func;
}

//清除机构信息
function clearFuncInfo(parentID, parentName) {
	document.getElementById("s_tbjb").value="0";
	document.getElementById("f_funcnameMsg").style.display="none";
	document.getElementById("parentfunccode").value = parentID;
	document.getElementById("parentfuncname").value = parentName;
	document.getElementById("funccode").value = '';
	document.getElementById("actiontype").value = 'addsave';
	document.getElementById("funcname").value = '';
	document.getElementById("funcnameen").value = '';
	document.getElementById("url").value = '';
	document.getElementById("functype").value = '0';
	document.getElementById("orderno").value = '0';
	document.getElementById("memo").value = '';
	document.getElementById("funcicon").value = '';
	document.getElementById("funcnamepy").value = '';
	document.getElementById("funcnamepyshort").value = '';
	document.getElementById("memopy").value = '';
	document.getElementById("memopyshort").value = '';
}
