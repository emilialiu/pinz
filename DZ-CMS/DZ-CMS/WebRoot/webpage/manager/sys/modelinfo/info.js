$("#sys_modelinfo_info").on('domi.add.after' , function() {//新增按钮之后事件
	//先生成ID，上传文件再保存信息
	$.ajax({
		url: rootpath+"/manager/sys/modelinfo/doAdd.action",
		cache: false,
		async: false,
		type : "POST",
		success: function(data){
			var dataJson = eval(data);
			$("#modelid").val(dataJson.id);
		},
		error: function(data) {
			alert("新增失败，请与管理员联系！");
		}
	});
});
$("#sys_modelinfo_info").on('domi.addsave.before' , function() {//新增保存之前事件
	//开始上传文件
	var modelid = $("#modelid").val(); //ID
	var fileName = $("#modelfielname").val(); //获取文件名称
    var suffix = fileName.split('.')[1];//文件后缀
    //设置本地文件名称到页面
    var filenameT = fileName.substring(fileName.lastIndexOf('\\')+1);
    $("#exceltb").val(filenameT);
    //设置服务器保存的文件名称到页面
    $("#targetname").val(modelid+'.'+suffix);
    
    var fileObj = document.getElementById("modelfielname").files[0]; //获取文件对象
    var FileController = rootpath+"/manager/sys/modelinfo/doUpFile.action";//接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("modelid", modelid);//ID
    form.append("file", fileObj);//文件对象
    form.append("suffix", suffix);//文件后缀
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.send(form);
});

$("#sys_modelmapp_info").on('domi.add.after' , function() {//新增按钮之后事件
	//先生成ID，上传文件再保存信息
	var selectedRowId = $("#sys_modelinfo_grid").jqGrid("getGridParam", "selrow");
	var rowData = $("#sys_modelinfo_grid").jqGrid('getRowData',selectedRowId);
	var modelid = rowData.modelid;
	$.ajax({
		url: rootpath+"/manager/sys/modelmapp/doAdd.action",
		cache: false,
		async: false,
		type : "POST",
		data: { 'modelid' : modelid},
		success: function(data){
			var dataJson = eval(data);
			$("#ecolnameT")[0].innerHTML = dataJson.excelcolname;
			$("#mfieldT")[0].innerHTML = dataJson.fieldsList;
			$("#dicttypeidT")[0].innerHTML = dataJson.dicttypeList;
			$("#dtypeT")[0].innerHTML = dataJson.dtypeList;
		},
		error: function(data) {
			alert("新增失败，请与管理员联系！");
		}
	});
});

$("#sys_modelmapp_info").on('domi.modify.after' , function() {//修改按钮之后事件
	//先生成ID，上传文件再保存信息
	var selectedRowId = $("#sys_modelinfo_grid").jqGrid("getGridParam", "selrow");
	var rowData = $("#sys_modelinfo_grid").jqGrid('getRowData',selectedRowId);
	var modelid = rowData.modelid;
	$.ajax({
		url: rootpath+"/manager/sys/modelmapp/doAdd.action",
		cache: false,
		async: false,
		type : "POST",
		data: { 'modelid' : modelid},
		success: function(data){
			var dataJson = eval(data);
			$("#ecolnameT")[0].innerHTML = dataJson.excelcolname;
			$("#mfieldT")[0].innerHTML = dataJson.fieldsList;
			$("#dicttypeidT")[0].innerHTML = dataJson.dicttypeList;
			$("#dtypeT")[0].innerHTML = dataJson.dtypeList;
		},
		error: function(data) {
			alert("修改失败，请与管理员联系！");
		}
	});
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    if(_bizname=='sys_modelinfo'){
    	return {
            "bean.modelid" : str.modelid
        };
    }else{
    	return {
            "bean.mid" : str.mid
        };
    }
	 
}
//给表单赋值
function setInfoData(_bizname,data){
	var dataJson = eval(data);
	var rowData = dataJson[0];
	var consoleDlg = $("#"+_bizname+"_info");
	if(_bizname=='sys_modelinfo'){
		consoleDlg.find("#modelid").val(rowData.modelid);
		consoleDlg.find("#modelname").val(rowData.modelname);
		consoleDlg.find("#mtable").val(rowData.mtable);
		consoleDlg.find("#exceltb").val(rowData.exceltb);
		consoleDlg.find("#targetname").val(rowData.targetname);
	}else{
		consoleDlg.find("#mid").val(rowData.mid);
		consoleDlg.find("#modelid").val(rowData.modelid);
		consoleDlg.find("#ecolname").val(rowData.ecolname);
		consoleDlg.find("#ecolindex").val(rowData.ecolindex);
		consoleDlg.find("#mfield").val(rowData.mfield);
		consoleDlg.find("#isnull").val(rowData.isnull);
		consoleDlg.find("#isscode").val(rowData.isscode);
		consoleDlg.find("#dicttypeid").val(rowData.dicttypeid);
		consoleDlg.find("#dtype").val(rowData.dtype);
	}
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");
    if(_bizname=='sys_modelinfo'){
		var modelid = $.trim(consoleDlg.find("#modelid").val());
		var modelname = $.trim(consoleDlg.find("#modelname").val());
		var mtable = $.trim(consoleDlg.find("#mtable").val());
		var exceltb = $.trim(consoleDlg.find("#exceltb").val());
		var targetname = $.trim(consoleDlg.find("#targetname").val());
		
	    return {
	        "bean.modelid" : modelid,
	        "bean.modelname" : modelname,
	        "bean.mtable" : mtable,
	        "bean.exceltb" : exceltb,
	        "bean.targetname" : targetname
	    };
    }else{
    	var mid = $.trim(consoleDlg.find("#mid").val());
    	var modelid = $.trim(consoleDlg.find("#modelid").val());
    	var ecolname = $.trim(consoleDlg.find("#ecolname").find("option:selected").text());
    	var ecolindex = $.trim(consoleDlg.find("#ecolname").val());
    	var mfield = $.trim(consoleDlg.find("#mfield").val());
    	var isnull = '1';
    	var isscode = '1';
    	var dicttypeid = $.trim(consoleDlg.find("#dicttypeid").val());
    	var dtype = $.trim(consoleDlg.find("#dtype").val());
    	
    	if(consoleDlg.find("#isnull").is(':checked')){
    		isnull='1';
	    }else{
	    	isnull='0';
	    }
    	if(consoleDlg.find("#isscode").is(':checked')){
    		isscode='1';
	    }else{
	    	isscode='0';
	    }

    	return {   
            "bean.mid" : mid,
            "bean.modelid" : modelid,
            "bean.ecolname" : ecolname,
            "bean.ecolindex" : ecolindex,
            "bean.mfield" : mfield,
            "bean.isnull" : isnull,
            "bean.isscode" : isscode,
            "bean.dicttypeid" : dicttypeid,
            "bean.dtype" : dtype
        };
    }
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    if(_bizname=='sys_modelinfo'){
		var modelname = $.trim(consoleDlg.find("#modelname").val());	
		var mtable = $.trim(consoleDlg.find("#mtable").val());	
		var exceltb = $.trim(consoleDlg.find("#exceltb").val());	
		var targetname = $.trim(consoleDlg.find("#targetname").val());	
		var dataRow = { 			
			modelid : dataJson.id,// 从Server端得到系统分配的id
			modelname : modelname,
			mtable : mtable,
			exceltb : exceltb,
			targetname : targetname
		};
    }else{
    	var modelid = $.trim(consoleDlg.find("#modelid").val());	
    	var ecolname = $.trim(consoleDlg.find("#ecolname").find("option:selected").text());	
    	var ecolindex = $.trim(consoleDlg.find("#ecolname").val());	
    	var mfield = $.trim(consoleDlg.find("#mfield").val());		
    	var dicttypeid = $.trim(consoleDlg.find("#dicttypeid").val());	
    	var dtype = $.trim(consoleDlg.find("#dtype").val());    	
    	var isnull = '1';
    	var isscode = '1';
    	
    	if(consoleDlg.find("#isnull").is(':checked')){
    		isnull='1';
	    }else{
	    	isnull='0';
	    }
    	if(consoleDlg.find("#isscode").is(':checked')){
    		isscode='1';
	    }else{
	    	isscode='0';
	    }

    	var dataRow = { 			
    		mid : dataJson.id,// 从Server端得到系统分配的id
    		modelid : modelid,
    		ecolname : ecolname,
    		ecolindex : ecolindex,
    		mfield : mfield,
    		isnull : isnull,
    		isscode : isscode,
    		dicttypeid : dicttypeid,
    		dtype : dtype
    	};
    }
	return dataRow;
}

//是否转码控制
function hiddencodetext(){
	if($("#sys_modelmapp_info").find("#isscode").is(':checked')){
		$("#sys_modelmapp_info").find("#isdicttypeid").show();
    }else{
    	$("#sys_modelmapp_info").find("#isdicttypeid").hide();
    }
}
