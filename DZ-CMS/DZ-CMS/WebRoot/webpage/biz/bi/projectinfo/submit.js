function addChildNode() {
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].name==undefined){
		alertMsg('请选择上级工程名称');
		return ;
	}
	clearProjectInfo(srcNode[0].id, srcNode[0].name, srcNode[0].projectlevel);
}
function getNodeInfo(projectid) {
	if(projectid == ''){
		return ;
	}
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/biz/bi/projectinfo/modify.action", 
		cache: false,
		data:{"bean.projectid":projectid}, 
		type: 'post',
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				setProjectInfo(dataJson.cells[0]);
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertErrorMsg("操作失败");
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
	var projectname = $("#projectname").val();
	if($.trim(projectname)==''){
		document.getElementById("f_projectname").style.visibility="visible";
		flag = false;
	}
	return flag;
}
function submit_add() {
	var parentitemid = document.getElementById('parentitemid').value;
	if(parentitemid == ''){
		alertMsg('请选择上级工程名称');
		return;
	}
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].name==undefined){
		alertMsg('请选择您要修改的工程');
		return ;
	}
	if(!checkSave()){
		return;
	}
	document.getElementById("f_projectname").style.visibility="hidden";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/biz/bi/projectinfo/add.action", 
		type:"post", 
		cache: false,
		data: eval('('+getProjectInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);//baocuo
			if (dataJson.success) {
				alertMsg('信息添加成功');
				var projectid = dataJson.cells[0].projectid;
				document.getElementById("projectid").value = projectid;
				var projectname = document.getElementById("projectname").value;
				var projectlevel = document.getElementById("projectlevel").value;
				var newNode = [{id:projectid,pid:BOTree1.getSelectedNodes()[0].id,name:projectname,projectlevel:projectlevel}];
				BOTree1.addNodes(BOTree1.getSelectedNodes()[0], newNode);
			} else {
				alertErrorMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertErrorMsg("操作失败");
		}
	});
}
//修改节点
function submit_modify() {
	var projectid = $('#projectid').val();
	if(projectid == '0000'){
		alertMsg('最顶级工程信息不能修改');
		return;
	}
	var parentitemid = $('#parentitemid').val();
	if(parentitemid == ''){
		alertMsg('请选择上级工程名称');
		return;
	}
	if(!checkSave()){
		return;
	}
	document.getElementById("f_projectname").style.visibility="hidden";
	//这里主要负责和服务器的交互
	$.ajax({
		url:rootpath + "/biz/bi/projectinfo/modify.action", 
		type:"post", 
		cache: false,
		data: eval('('+getProjectInfo()+')'), 
		success:function (data) {
			var dataJson = eval(data);
			if (dataJson.success) {
				alertMsg('信息修改成功');
				var projectname = document.getElementById("projectname") ;
				renameTreeNode(projectname);
			} else {
				alertMsg(dataJson.errormessage);
			}
		}, 
		error:function (data) {
			alertErrorMsg("操作失败");
		}
	});
}
//删除节点
function submit_delete() {
	var projectid = $('#projectid').val();
	if(projectid == ''){
		alertMsg('请选择您要删除的工程');
		return ;
	}
	var srcNode = BOTree1.getSelectedNodes();
	if(srcNode == null||srcNode==''||srcNode[0].name==undefined){
		alertMsg('请选择您要删除的工程');
		return ;
	}
	if(srcNode[0].isParent){
		alertMsg('该工程还含有子工程，不能够删除');
		return ;
	}
	parent.bootbox.confirm("确定要删除选中的工程信息",function(type){
		if(!type)
			return;
		$.ajax({
			url:rootpath + "/biz/bi/projectinfo/delete.action", 
			type:"post", 
			cache: false,
			data: {'bean.projectid': projectid}, 
			success:function (data) {
				var dataJson = eval(data);
				if (dataJson.success) {
					alertMsg('信息删除成功');
					removeTreeNode();
					clearProjectInfo('', '');
				} else {
					alertMsg(dataJson.errormessage);
				}
			}, 
			error:function (data) {
				alertErrorMsg("操作失败");
			}
		});
	});
}

//设置详细信息
function setProjectInfo(projectObj) {
	$("#parentitemid").val(projectObj.parentitemid);
	$("#parentname").val(projectObj.parentname);
	$("#projectid").val(projectObj.projectid);
	$("#actiontype").val(projectObj.actiontype);
	$("#projectname").val(projectObj.projectname);
	$("#projectcode").val(projectObj.projectcode);
	$("#projectlevel").val(projectObj.projectlevel);
	$("#indexno").val(projectObj.indexno);
	$("#memo").val(projectObj.memo);
	if(projectObj.projectstatus=='0'){
		$("#projectstatus").prop("checked", true);
	}else{
		$("#projectstatus").prop("checked", false);
	}
	if(projectObj.isleaf=='0'){
		$("input[name='isleaf']:eq(0)").attr("checked",true);
		$("input[name='isleaf']:eq(1)").attr("checked",false);
		$("input[name='isleaf']:eq(0)").prop("checked",true);
    }else{
    	$("input[name='isleaf']:eq(1)").attr("checked",true);
    	$("input[name='isleaf']:eq(0)").attr("checked",false);
		$("input[name='isleaf']:eq(1)").prop("checked",true);
    }
}

//获得表单信息,以json格式返回
function getProjectInfo() {
	var parentitemid = $("#parentitemid").val();
	var projectid = $("#projectid").val();
	var projectname = $("#projectname").val().replace(/\'/g,"’").replace(/\"/g,"”");
	var projectcode = $("#projectname").val();
	var projectstatus='1';
	var isleaf = $("input[name='isleaf']:checked").val();
	if($("#projectstatus").is(':checked')){
		projectstatus='0';
	}else{
		projectstatus='1';
	}
	var projectlevel = $("#projectlevel").val();
	var actiontype = $("#actiontype").val();
	var indexno = $("#indexno").val();
	var memo = $("#memo").val().replace(/\'/g,"’").replace(/\"/g,"”");
	var project = "{'bean.parentitemid':'";
	project += parentitemid;
	project += "', 'bean.projectid':'";
	project += projectid;
	project += "', 'bean.projectname':'";
	project += projectname;
	project += "', 'bean.projectcode':'";
	project += projectcode;
	project += "', 'bean.isleaf':'";
	project += isleaf;
	project += "', 'bean.projectstatus':'";
	project += projectstatus;
	project += "', 'bean.projectlevel':'";
	project += projectlevel;
	project += "', 'bean.indexno':'";
	project += indexno;
	project += "', 'bean.memo':'";
	project += memo;
	project += "', 'actiontype':'";
	project += actiontype;
	project += "'}";
	return project;
}
function clearMsg(){
	document.getElementById("f_projectname").style.visibility="hidden";
}
//清除工程信息
function clearProjectInfo(parentitemid, parentname, projectlevel) {
	document.getElementById("f_projectname").style.visibility="hidden";
	$("#parentitemid").val(parentitemid);
	$("#parentname").val(parentname);
	$("#projectid").val('');
	$("#projectname").val('');
	$("#projectcode").val('');
	$("input[name='isleaf']:eq(1)").prop("checked",true);
	$("input[name='isleaf']:eq(1)").attr("checked",true);
	$("#projectstatus").prop("checked", true);
	$("#actiontype").val('addsave');
	$("#indexno").val('0');
	$("#memo").val('');
	if(projectlevel==null||projectlevel=='null'||projectlevel==''){
		$("#projectlevel").val('GCJB001');
	}else{
		var level = parseInt(projectlevel.substring(4,7),10)+1;
		$("#projectlevel").val('GCJB'+padLeft(level+'',3));
	}
}
function padLeft(str,lenght){ 
	if(str.length >= lenght) 
		return str; 
	else 
		return padLeft("0" +str,lenght); 
}

