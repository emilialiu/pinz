//连接数据库获取表名
function connectDB(){
	var dbtype = document.getElementById("DBType").value;
	var sid = document.getElementById("sid").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	url = rootpath + '/webpage/manager/bogen/connectDB.action?dbtype='+dbtype+'&&sid='+sid+'&&username='+username+'&&password='+password;
	$.ajax({
		url: url,
		cache: false,
		async: false,
		type : "get", 
		success: function(data){
			var dataJson = eval(data);
			var items = eval(dataJson.cells[0].tablesinfo);
			createComboBox("cb1", { itemSource: items, style: "dropdown", autoComplete: "local" });
		},
		error: function(data) {
			alert("连接数据库服务器查询表信息失败，请检查网络或重新输入连接信息！");
		}
	});
}

//创建ComboBox控件
function createComboBox(eltid, settings) {
    var cb = new ComboBox(document.getElementById(eltid), settings);
    cb.onValueChanged = cb_onValueChanged;
    cb_onValueChanged(cb, { value: cb.get_value()});
}
//ComboBox下拉改变值事件
function cb_onValueChanged(sender, e) {
	if(e.value != null){
 	 	document.getElementById("tablenameHidden").value = e.value;
 	 	var myArray = e.value.split(' ');
 	 	if(myArray.length > 1){
 	 		document.getElementById("className").value = myArray[0];
 	 		document.getElementById("titleName").value = myArray[1];
 	 		var myArrayT = myArray[0].split('_');
 	 		if(myArrayT.length > 1){
 	 			if(myArrayT.length > 2){//表示表名格式为：T_XX_XXX
 	 				document.getElementById("srcRoot").value = myArrayT[1];
		  	 		document.getElementById("webRoot").value = 'WEBPAGE/BIZ/' + myArrayT[1] + '/' + myArrayT[2];
 	 			}else{//表示表名格式为：XX_XXX
 	 				document.getElementById("srcRoot").value = myArrayT[0];
		  	 		document.getElementById("webRoot").value = 'WEBPAGE/BIZ/' + myArrayT[0] + '/' + myArrayT[1];
 	 			}
 	 		}
 	 	}else{
 	 		document.getElementById("className").value = myArray[0];
 	 		document.getElementById("titleName").value = '';
 	 		var myArrayT = myArray[0].split('_');
 	 		if(myArrayT.length > 1){
 	 			if(myArrayT.length > 2){//表示表名格式为：T_XX_XXX
 	 				document.getElementById("srcRoot").value = myArrayT[1];
		  	 		document.getElementById("webRoot").value = 'WEBPAGE/BIZ/' + myArrayT[1] + '/' + myArrayT[2];
 	 			}else{//表示表名格式为：XX_XXX
 	 				document.getElementById("srcRoot").value = myArrayT[0];
		  	 		document.getElementById("webRoot").value = 'WEBPAGE/BIZ/' + myArrayT[0] + '/' + myArrayT[1];
 	 			}
 	 		}
 	 	}		
		//获取数据库类型
 	 	var dbtype = $("#DBType").val();
 	 	var sid = $("#sid").val();
		url = rootpath + "/webpage/manager/bogen/doInfo.action?tablename="+e.value+"&dbtype="+dbtype+"&sid="+sid;	
		document.getElementById('listIframe').src = url;
	}
}

//生成源代码
function getSelectValue(obj){
	var isShowPK = document.getElementById("isShowPK").checked;
	var className_temp = document.getElementById("className").value;
	var titleName_temp = document.getElementById("titleName").value;
	var srcRoot_temp = document.getElementById("srcRoot").value;
	var webRoot_temp = document.getElementById("webRoot").value;
	if(className_temp==null || className_temp==""){
		alert("请连接数据库并选择表名");
		return;
	}else{
		var className = className_temp;
	}			
	if(titleName_temp==null || titleName_temp==""){
		alert("请输入title名称");
		return;
	}else{
		var titleName = titleName_temp;
	}
	if(srcRoot_temp==null || srcRoot_temp==""){
		alert("请输入源码配置文件模块名");
		return;
	}else{
		var srcRoot = srcRoot_temp;
	}
	if(webRoot_temp==null || webRoot_temp==""){
		alert("请输入功能页面路径");
		return;
	}else{
		var webRoot = webRoot_temp;
	}
	listIframe.getSelectValue(obj,isShowPK,className,titleName,srcRoot,webRoot)
}

//生成该表到WORD文档
function createWord(obj){
	var tablenameObj = document.getElementById("tablenameHidden").value;
	var myArray = tablenameObj.split(' ');
	if(myArray.length <= 0){
		alert("请连接数据库并选择表名");
	}
	var tablenameE;
	var tablenameC;
	if(myArray.length > 1){
		tablenameE = myArray[0];
		tablenameC = myArray[1];
	}else{
		tablenameE = myArray[0];
		tablenameC = '';
	}
	listIframe.createWord(obj,tablenameE,tablenameC);
}

//生成所有表到WORD文档
function createWordAll(){
	listIframe.createWordAll();
}

//下载源代码
function downFile(){
		var previewPath = document.getElementById("previewPath").value;
		if(previewPath == ''||previewPath == null){
			alert('请先生成源代码！');
			return;
		}
		zipFile();
}
//压缩源码包
function zipFile(){
		var url = rootpath + "/webpage/manager/bogen/freemarker/doFileZip.action";
		$.ajax({
			url: url,
			cache: false,
			async: false,
			type : "get", 
			success: function(data){
				window.document.location = rootpath + "/tmp/" +sessionID+ ".zip";
			},
			error: function(data) {
				alert("源代码下载失败,请与管理员联系！");
			}
		});
}

//预览页面
function showPage(){
	var previewPath = document.getElementById("previewPath").value;
	if(previewPath == ''||previewPath == null){
		alert('请先生成源代码！');
		return;
	}
	var url = rootpath + "/webpage/main/adminindex.jsp#dimine/" +previewPath+ "/main.jsp";
	window.open(url);
}

//操作说明
function showHelp(){
	var url = rootpath + "/webpage/manager/bogen/help/help.htm";
	window.open(url);
}

