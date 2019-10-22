function addChildNode() {
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].name==undefined){
		alertMsg('请选择上级机构');
		return ;
	}
	clearDeptInfo(srcNode[0].id, srcNode[0].name, srcNode[0].orglevel);
}
function getNodeInfo(deptID) {
	if(deptID == ''){
		return ;
	}
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/manager/sys/dept/modify.action", 
		cache: false,
		data:{"bean.deptid":deptID}, 
		type: 'post',
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				setDeptInfo(dataJson.cells[0]);
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertMsg("操作失败");
		}
	});
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
function checkSave(){
	var flag = true;
	var deptname = $("#deptname").val();
	var deptcode = $("#deptcode").val();
	if(($.trim(deptname)=='')&&($.trim(deptcode)=='')){
		//console.info(deptname.trim()+"-11111111-"+deptcode.trim());
		document.getElementById("f_deptname").style.visibility="visible";
		document.getElementById("f_deptcode").style.visibility="visible";
		flag = false;
	}else{
		if($.trim(deptname)==''&&$.trim(deptcode)!=''){
			document.getElementById("f_deptname").style.visibility="visible";
			document.getElementById("f_deptcode").style.visibility="hidden";
			flag = false;
		}if($.trim(deptname)!=''&&$.trim(deptcode)==''){
			document.getElementById("f_deptcode").style.visibility="visible";
			document.getElementById("f_deptname").style.visibility="hidden";
			flag = false;
		}
	}
	return flag;
}
function submit_add() {
	var parentdeptid = document.getElementById('parentdeptid').value;
	if(parentdeptid == ''){
		alertMsg('请选择上级机构');
		return;
	}
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].name==undefined){
		alertMsg('请选择您要修改的机构');
		return ;
	}
	if(!checkSave()){
		return
	}
	document.getElementById("f_deptname").style.visibility="hidden";
	document.getElementById("f_deptcode").style.visibility="hidden";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/manager/sys/dept/add.action", 
		type:"post", 
		cache: false,
		data: eval('('+getDeptInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);//baocuo
			if (dataJson.success) {
				alertMsg('信息添加成功');
				var deptID = dataJson.cells[0].deptID;
				document.getElementById("deptid").value = deptID;
				var deptName = document.getElementById("deptname").value ;
				var newNode = [{id:deptID,pid:BOTree1.getSelectedNodes()[0].id,name:deptName}];
				BOTree1.addNodes(BOTree1.getSelectedNodes()[0], newNode);
			} else {
				//console.info(dataJson.errormessage);
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
	var parentdeptid = document.getElementById('parentdeptid').value;
	if(parentdeptid == ''){
		alertMsg('请选择上级机构');
		return;
	}
	if(!checkSave()){
		return
	}
	document.getElementById("f_deptname").style.visibility="hidden";
	document.getElementById("f_deptcode").style.visibility="hidden";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/manager/sys/dept/modify.action", 
		type:"post", 
		cache: false,
		data: eval('('+getDeptInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg('信息修改成功');
				var deptname = document.getElementById("deptname") ;
				renameTreeNode(deptname);
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
	var deptid = document.getElementById('deptid').value;
	if(deptid == ''){
		alertMsg('请选择您要删除的机构');
		return ;
	}
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].name==undefined){
		alertMsg('请选择您要删除的机构');
		return ;
	}
	if(srcNode[0].isParent){
		alertMsg('该机构还含有子机构，不能够删除');
		return ;
	}
	parent.bootbox.confirm("确定要删除选中的机构信息",function(type){
			if(!type)
				return;
			$.ajax({
				url:rootpath + "/manager/sys/dept/delete.action", 
				type:"post", 
				cache: false,
				data: {'bean.deptid': deptid}, 
				success:function (data) {
					var dataJson = eval(data);
					if (dataJson.success) {
						alertMsg('信息删除成功');
						removeTreeNode();
						clearDeptInfo('', '');
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
function setDeptInfo(deptObj) {
	document.getElementById("parentdeptid").value = deptObj.parentdeptid;
	document.getElementById("parentName").value = deptObj.parentName;
	document.getElementById("deptid").value = deptObj.deptid;
	document.getElementById("actiontype").value = deptObj.actiontype;
	document.getElementById("deptname").value = deptObj.deptname;
	document.getElementById("tel").value = deptObj.tel;
	document.getElementById("fax").value = deptObj.fax;
	document.getElementById("deptcode").value = deptObj.deptcode;
	document.getElementById("orderno").value = deptObj.orderno;
	document.getElementById("memo").value = deptObj.memo;
	document.getElementById("orglevel").value = deptObj.orglevel;
	document.getElementById("orgtype").value = deptObj.orgtype;
}

//获得表单信息,以json格式返回
function getDeptInfo() {
	var parentid = document.getElementById("parentdeptid").value;
	var deptid = document.getElementById("deptid").value;
	var actiontype = document.getElementById("actiontype").value;
	var deptname = document.getElementById("deptname").value;
	var deptcode = document.getElementById("deptcode").value;
	var tel = document.getElementById("tel").value;
	var fax = document.getElementById("fax").value;
	var orderno = document.getElementById("orderno").value;
	var memo = document.getElementById("memo").value;
	var orglevel = document.getElementById("orglevel").value;
	var orgtype = document.getElementById("orgtype").value;
	var dept = "{'bean.parentdeptid':'";
	dept += parentid;
	dept += "', 'bean.deptid':'";
	dept += deptid;
	dept += "', 'bean.deptname':'";
	dept += deptname;
	dept += "', 'bean.deptCode':'";
	dept += deptcode;
	dept += "', 'bean.tel':'";
	dept += tel;
	dept += "', 'bean.fax':'";
	dept += fax;
	dept += "', 'bean.memo':'";
	dept += memo;
	dept += "', 'bean.orderNo':'";
	dept += orderno;
	dept += "', 'bean.orglevel':'";
	dept += orglevel;
	dept += "', 'bean.orgtype':'";
	dept += orgtype;
	dept += "', 'actiontype':'";
	dept += actiontype;
	dept += "'}";
	return dept;
}
function clearMsg(){
	document.getElementById("f_deptname").style.visibility="hidden";
	document.getElementById("f_deptcode").style.visibility="hidden";
}
//清除机构信息
function clearDeptInfo(parentID, parentName, orglevel) {
	document.getElementById("f_deptname").style.visibility="hidden";
	document.getElementById("f_deptcode").style.visibility="hidden";
	document.getElementById("parentdeptid").value = parentID;
	document.getElementById("parentName").value = parentName;
	document.getElementById("deptid").value = '';
	document.getElementById("actiontype").value = 'addsave';
	document.getElementById("deptname").value = '';
	document.getElementById("deptcode").value = '';
	document.getElementById("tel").value = '';
	document.getElementById("fax").value = '';
	document.getElementById("orderno").value = '0';
	document.getElementById("orglevel").value = parseInt(orglevel,10)+1;
	document.getElementById("memo").value = '';
}
