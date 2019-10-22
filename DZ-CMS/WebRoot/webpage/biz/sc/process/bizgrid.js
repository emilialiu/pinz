var dw={};
var sjlx={};
var hqsjlx={};
//定义一个变量用来存储工序id
var procids="";
//记录点击
$(function() {
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'dw'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			dw = eval('({'+data+'})');	
		}  
	 });
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'SJLX'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			sjlx = eval('({'+data+'})');	
		}  
	 });
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'HQSJLX'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			hqsjlx = eval('({'+data+'})');	
		}  
	 });
	// grid参数
	$("#t_sc_process_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/process/list.action',
		colNames : [ 
					pub_operation,
					sc_process_procid,
					sc_process_tdeptid,
					sc_process_proctname,
					sc_process_proctnameen,
					sc_process_proccode,
					sc_process_shortname,
					sc_process_parentid,
					sc_process_parentname,
					sc_process_isleaf,
					sc_process_parentstr,
					sc_process_serialno,
					sc_process_memo
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'procid',index : 'procid',width : 60,sorttype : "varchar(40)",editable : true,hidden : true},
						{name : 'tdeptid',index : 'tdeptid',width : 60,sorttype : "varchar(40)",editable : true,hidden : true},
						{name : 'proctname',index : 'proctname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : true,editrules:{ required:true},editoptions:{maxlength: 50},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'proctnameen',index : 'proctnameen',fixed : true,width : 100,sorttype : 'varchar(100)',editable : true,editrules:{ required:true},editoptions:{maxlength: 50},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'proccode',index : 'proccode',fixed : true,width : 100,sorttype : 'varchar(40)',editable : true,editoptions:{maxlength: 20},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'shortname',index : 'shortname',width : 60,sorttype : "varchar(20)",editable : true,hidden : true},
						{name : 'parentid',index : 'parentid',fixed : true,width : 100,sorttype : 'varchar(40)',hidden:true,editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'parentname',index : 'parentname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isleaf',index : 'isleaf',width : 60,sorttype : "char(1)",editable : true,hidden : true},
						{name : 'parentstr',index : 'parentstr',width : 60,sorttype : "varchar(500)",editable : true,hidden : true},
						{name : 'serialno',index : 'serialno',fixed : true,width : 100,sorttype : 'int(11)',editable : true,editrules:{ required:true,number:true},editoptions:{maxlength:9},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',width : 60,sorttype : "varchar(1000)",editable : true,hidden : true}
					],
		//选择某一行的点击事件
		onSelectRow: function(rowid){
		 	var rowData = $("#t_sc_process_grid").jqGrid('getRowData',rowid);
		 	//alert(rowData.procid);
		 	 if(rowData.procid.indexOf("<")>-1){
		 		reloadByType('-1');
			 }else{ 
				reloadByType(rowData.procid);
			 }
		},
		processbtncol:2,//操作按钮放在第几个列
		isedit:true,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/sc/process/doModify.action",
		addurl : "/webpage/biz/sc/process/doAdd.action",
		modifyurl : "/webpage/biz/sc/process/doModifySave.action?actiontype=modifysave",
		deleteurl : "/webpage/biz/sc/process/delete.action"
		
	});
	
	initgrid("t_sc_process");
	$("#t_sc_process_grid_pager_left td").slice(0, 4).hide();
	
	// grid参数
	$("#t_bi_target_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/target/selectTargetByGx.action',
		colNames : [ 
					pub_operation,
					bi_target_targetid,
					bi_target_procid,
					bi_target_targetname,
					bi_target_targetnameen,
					bi_target_shortname,
					bi_target_targetunit,
					bi_target_serialno,
					bi_target_hqdatatype,
					bi_target_talgorithm,
					bi_target_tal_desc,
					bi_target_isevent,
					bi_target_isedit,
					bi_target_ismust,
					bi_target_issum,
					bi_target_isdisplay,
					bi_target_ismaintarget,
					bi_target_datatype,
					bi_target_isyplan,
					bi_target_ismplan,
					bi_target_isproduce,
					bi_target_isaccept,
					bi_target_isused,
					bi_target_memo
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;TAlgorithm
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'targetid',index : 'targetid',width : 60,sorttype : "varchar(40)",editable : true,hidden : true},
						{name : 'procid',index : 'procid',width : 60,sorttype : "varchar(40)",editable : true,hidden : true},
						{name : 'targetname',index : 'targetname',fixed : true,width : 80,sorttype : 'varchar(100)',editable : true,editrules:{ required:true},editoptions:{dataEvents:[{type:'focus',fn:initTalgorithm}],maxlength: 50},searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
						{name : 'targetnameen',index : 'targetnameen',fixed : true,width : 100,sorttype : 'varchar(100)',editable : true,editrules:{ required:true},editoptions:{maxlength: 50},searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
						{name : 'shortname',index : 'shortname',width : 60,sorttype : "varchar(20)",editable : false,hidden : true},
						{name : 'targetunit',index : 'targetunit',fixed : true,width : 80,sorttype : 'varchar(10)',editable : true,edittype:'select',editoptions:{value:dw},formatter:function(v){return dw[v];}, unformat:function(v){for(k in dw){if(v == dw[k])return k;}},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'serialno',index : 'serialno',fixed : true,width : 80,sorttype : 'varchar(200)',editable : true,search:false,editoptions:{dataEvents:[{type:'focus',fn:initTalgorithm}],maxlength:9},editrules:{ required:true,number:true},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'hqdatatype',index : 'hqdatatype',fixed : true,width : 100,sorttype : 'varchar(200)',editable : true,edittype:'select',editoptions:{value:hqsjlx},formatter:function(v){return hqsjlx[v];}, unformat:function(v){for(k in hqsjlx){if(v == hqsjlx[k])return k;}},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'talgorithm',index : 'talgorithm',fixed : true,width : 180,sorttype : 'varchar(200)',editable : true,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'tal_desc',index : 'tal_desc',fixed : true,width : 180,sorttype : 'varchar(200)',edittype:'text',editable : true,search:false,editoptions:{maxlength: 100},editoptions:{readonly:true,dataEvents:[{type:'click',fn:setTalgorithm}]},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isevent',index : 'isevent',fixed : true,width : 100,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isedit',index : 'isedit',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ismust',index : 'ismust',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'issum',index : 'issum',fixed : true,width : 100,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isdisplay',index : 'isdisplay',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ismaintarget',index : 'ismaintarget',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'datatype',index : 'datatype',fixed : true,width : 80,sorttype : 'varchar(10)',editable : true,edittype:'select',search:false,editoptions:{value:sjlx},formatter:function(v){return sjlx[v];}, unformat:function(v){for(k in sjlx){if(v == sjlx[k])return k;}},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isyplan',index : 'isyplan',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ismplan',index : 'ismplan',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isproduce',index : 'isproduce',fixed : true,width : 100,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isaccept',index : 'isaccept',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isused',index : 'isused',fixed : true,width : 80,sorttype : 'char(1)',editable : false,formatter: "checkbox",search:false,formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',width : 80,sorttype : "varchar(200)",editable : true}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:true,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/bi/target/doModify.action",
		addurl : "/webpage/biz/bi/target/doAdd.action",
		modifyurl : "/webpage/biz/bi/target/doModifySave.action",
		deleteurl : "/webpage/biz/bi/target/delete.action"
		
	});
	
	initgrid("t_bi_target");
	$("#t_bi_target_grid_pager_left td").slice(0, 4).hide();
	function reloadByType(procid){
		$("#t_bi_target_grid").jqGrid('setGridParam', {
			url : rootpath + '/webpage/biz/bi/target/selectTargetByGx.action?bean.procid='+procid,
			page : 1
		}).trigger("reloadGrid"); 
	}
});

//重写新增方法
function _openDialog4Adding(_bizname){
	if(_bizname=="t_bi_target"){
		var selectedRowId = $("#t_sc_process_grid").jqGrid("getGridParam", "selrow");
		var rowData = $("#t_sc_process_grid").jqGrid('getRowData',selectedRowId);
	    if(selectedRowId==''||selectedRowId==null){
			alertMsg("请先选择工序!");
			return;
		}
	    if(rowData.procid.indexOf("<")>-1||rowData.procid==''){
	    	alertMsg("请先保存工序信息!");
			return;
	    }
	}
	addrow(_bizname);
}
//清空算法
function initTalgorithm(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target; 
	var $obj = $(obj);
	var rowid = ($(obj).attr("id")).split("_")[0];
	$("#"+rowid+"_talgorithm").val("");
	$("#"+rowid+"_tal_desc").val("");
}
//维护算法
function setTalgorithm(event){
	//获取事件源
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target; 
	var $obj = $(obj);
	var rowid = ($(obj).attr("id")).split("_")[0];
	//维护算法前必须填写好序号和指标名称
	if($("#"+rowid+"_serialno").val().replace(/\s+/g,"")==''||$("#"+rowid+"_targetname").val().replace(/\s+/g,"")==''){
		alertMsg("请先填写好指标名称和序号!");
		return;
	}
//	var selectedRowId = $("#t_sc_process_grid").jqGrid("getGridParam", "selrow");
//	var rowData = $("#t_sc_process_grid").jqGrid('getRowData',selectedRowId);
	if(procids==undefined){
		alertMsg("请先选择工序!");
		return;
	}
	parent.openwin("talgorithm","/webpage/pub/select/talgorithm/main.jsp?procid="+procids,"指标算法",805,615);
	parent.PageObject.itemMap['talgorithm'].callback = function(no1,no2){
		getTalgorithmCallback(no1,no2,rowid);
	};
}	
function getTalgorithmCallback(no1,no2,rowid){
	if(no1.replace(/\s+/g,"")==''){
		$("#"+rowid+"_talgorithm").val('');
	}else{
		$("#"+rowid+"_talgorithm").val('column_'+$("#"+rowid+"_serialno").val().replace(/\s+/g,"")+'='+no1.replace(/\s+/g,""));
	}
	
	if(no2.replace(/\s+/g,"")==''){
		$("#"+rowid+"_tal_desc").val('');
	}else{
		$("#"+rowid+"_tal_desc").val($("#"+rowid+"_targetname").val().replace(/\s+/g,"")+'='+no2.replace(/\s+/g,""));
	}
}
/**
 * 新增行
 * @param _bizname
 */
function addrow(_bizname){
	//获取表格的初始化model  
	var colModel = $("#"+_bizname+"_grid").jqGrid().getGridParam("colModel") ;  
	var cellLenth = colModel.length;
	//设置所有列可编辑（如果特定的列不可编辑，则需判断）  
	/*for (var i = 0; i < cellLenth; i++) {
		if(i==1 || i==14)
			colModel[i].editable = false;
		else
			colModel[i].editable = true;
	}*/
    var newRow = JSON.stringify(colModel);
	//获得所有行的ID数组
    var ids = $("#"+_bizname+"_grid").jqGrid("getDataIDs");
    //如果jqgrid中没有数据 定义行号为1 ，否则取当前最大行号+1
    var newrowid = (ids.length ==0 ? 1: Math.max.apply(Math,ids)+1);
    //设置grid单元格不可编辑 （防止在添加时，用户修改其他非添加行的数据）  
    $("#"+_bizname+"_grid").setGridParam({cellEdit:false});
    //将新行追加到表格头部  
    $("#"+_bizname+"_grid").jqGrid("addRowData", newrowid, newRow, "first");
    //设置单元格的值
    if(_bizname=="t_sc_process"){
    	var srcNode = BOTree1.getSelectedNodes();
	    $("#"+_bizname+"_grid").setCell(newrowid,8,srcNode[0].id);
	    $("#"+_bizname+"_grid").setCell(newrowid,9,srcNode[0].name);
    }else if(_bizname=="t_bi_target"){
    	var selectedRowId = $("#t_sc_process_grid").jqGrid("getGridParam", "selrow");
    	var rowData = $("#t_sc_process_grid").jqGrid('getRowData',selectedRowId);
    	$("#"+_bizname+"_grid").setCell(newrowid,3,rowData.procid);
    }
    //设置grid单元格可编辑（防止追加行后，可编辑列无法编辑）  
    $("#"+_bizname+"_grid").jqGrid("editRow", newrowid, false);
    updateEditIcons(_bizname);
    _doeditmodify(_bizname,newrowid);
    $("#"+_bizname+"_grid").setSelection(newrowid,true);
}
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	if(_bizname=='t_sc_process'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
			return {"bean.procid" : str.procid,
					"bean.parentid" : str.parentid
					};
	}else if(_bizname=='t_bi_target'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
			return {"bean.targetid" : str.targetid};
	}
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.procid="+str.procid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	if(_bizname=='t_sc_process'){
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		var shortname=toShortName(datalist.proctname);
		return {  
				"bean.procid" : datalist.procid,
				"bean.tdeptid" : datalist.tdeptid,
				"bean.proctname" : datalist.proctname,
				"bean.proctnameen" : datalist.proctnameen,
				"bean.proccode" : datalist.proccode,
				"bean.shortname" : shortname,
				"bean.parentid" : datalist.parentid,
				"bean.isdel" : datalist.isdel,
				"bean.isleaf" : datalist.isleaf,
				"bean.parentstr" : datalist.parentstr,
				"bean.serialno" : datalist.serialno,
				"bean.createid" : datalist.createid,
				"bean.createdate" : datalist.createdate,
				"bean.modifyid" : datalist.modifyid,
				"bean.modifydate" : datalist.modifydate,
				"bean.memo" : datalist.memo
		}; 
	}else if(_bizname=='t_bi_target'){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			targetunit:$('#'+selectedRowId+"_targetunit").val(),
			datatype:$('#'+selectedRowId+"_datatype").val(),
			hqdatatype:$('#'+selectedRowId+"_hqdatatype").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		//去掉空格
		datalist.targetname=datalist.targetname.replace(/\s+/g,"");
		var shortname=toShortName(datalist.targetname);
		if(datalist.isevent=='Yes'){
			datalist.isevent='1';
		}else{
			datalist.isevent='0';
		}
		if(datalist.isedit=='Yes'){
			datalist.isedit='1';
		}else{
			datalist.isedit='0';
		}
		if(datalist.ismust=='Yes'){
			datalist.ismust='1';
		}else{
			datalist.ismust='0';
		}
		if(datalist.isyplan=='Yes'){
			datalist.isyplan='1';
		}else{
			datalist.isyplan='0';
		}
		if(datalist.ismplan=='Yes'){
			datalist.ismplan='1';
		}else{
			datalist.ismplan='0';
		}
		if(datalist.isproduce=='Yes'){
			datalist.isproduce='1';
		}else{
			datalist.isproduce='0';
		}
		if(datalist.isaccept=='Yes'){
			datalist.isaccept='1';
		}else{
			datalist.isaccept='0';
		}
		if(datalist.isused=='Yes'){
			datalist.isused='1';
		}else{
			datalist.isused='0';
		}
		
		if(datalist.issum=='Yes'){
			datalist.issum='1';
		}else{
			datalist.issum='0';
		}
		if(datalist.isdisplay=='Yes'){
			datalist.isdisplay='1';
		}else{
			datalist.isdisplay='0';
		}
		if(datalist.ismaintarget=='Yes'){
			datalist.ismaintarget='1';
		}else{
			datalist.ismaintarget='0';
		}
		
		return {  
				"bean.targetid" : datalist.targetid,
				"bean.procid" : datalist.procid,
				"bean.targetname" : datalist.targetname,
				"bean.targetnameen" : datalist.targetnameen,
				"bean.shortname" : shortname,
				"bean.targetunit" : datalist.targetunit,
				"bean.isused" : datalist.isused,
				"bean.isdel" : datalist.isdel,
				"bean.isedit" : datalist.isedit,
				"bean.issum" : datalist.issum,
				"bean.ismust" : datalist.ismust,
				"bean.datatype" : datalist.datatype,
				"bean.isyplan" : datalist.isyplan,
				"bean.ismplan" : datalist.ismplan,
				"bean.isproduce" : datalist.isproduce,
				"bean.isaccept" : datalist.isaccept,
				"bean.memo" : datalist.memo,
				"bean.createid" : datalist.createid,
				"bean.createdate" : datalist.createdate,
				"bean.modifyid" : datalist.modifyid,
				"bean.modifydate" : datalist.modifydate,
				"bean.serialno" : datalist.serialno,
				"bean.isdisplay" : datalist.isdisplay,
				"bean.ismaintarget" : datalist.ismaintarget,
				"bean.hqdatatype" : datalist.hqdatatype,
				"bean.talgorithm" : datalist.talgorithm,
				"bean.tal_desc" : datalist.tal_desc,
				"bean.isevent" : datalist.isevent
		}; 
	}
	
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	if(_bizname=='t_sc_process'){
		return dataRow = {  
					procid : dataJson.procid,
					tdeptid : datalist.tdeptid,
					proctname : datalist.proctname,
					proctnameen : datalist.proctnameen,
					proccode : datalist.proccode,
					shortname : datalist.shortname,
					parentid : datalist.parentid,
					isdel : datalist.isdel,
					isleaf : datalist.isleaf,
					parentstr : datalist.parentstr,
					serialno : datalist.serialno,
					createid : datalist.createid,
					createdate : datalist.createdate,
					modifyid : datalist.modifyid,
					modifydate : datalist.modifydate,
					memo : datalist.memo
		}; 
	}else if(_bizname=='t_bi_target'){
		return dataRow = {  
					targetid : dataJson.targetid,
					procid : datalist.procid,
					targetname : datalist.targetname,
					targetnameen : datalist.targetnameen,
					shortname : datalist.shortname,
					targetunit : datalist.targetunit,
					isused : datalist.isused,
					isdel : datalist.isdel,
					isedit : datalist.isedit,
					issum : datalist.issum,
					ismust : datalist.ismust,
					datatype : datalist.datatype,
					isyplan : datalist.isyplan,
					ismplan : datalist.ismplan,
					isproduce : datalist.isproduce,
					isaccept : datalist.isaccept,
					memo : datalist.memo,
					createid : datalist.createid,
					createdate : datalist.createdate,
					modifyid : datalist.modifyid,
					modifydate : datalist.modifydate,
					serialno : datalist.serialno,
					isdisplay : datalist.isdisplay,
					ismaintarget : datalist.ismaintarget,
					hqdatatype : datalist.hqdatatype,
					talgorithm : datalist.talgorithm,
					tal_desc : datalist.tal_desc,
					isevent : datalist.isevent
		}; 
	}
	
}
//提交信息方法
function _doeditsubmit(_bizname,selectedRowId) {
	if(_bizname=='t_sc_process'){
		//修改保存开始
		$("#"+_bizname+"_grid").trigger('domi.modifysave.before');
		var validflag = $("#"+_bizname+"_grid").saveRow(selectedRowId);
		if(!validflag){
			return;
		}else{
			//去编辑列表行数据
			var params = getEditGridParams(_bizname,selectedRowId);
			$.ajax({
				url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").modifyurl,
				cache : false,
				data : params,
				type : 'post',
				success : function(data) {
					var dataJson = eval(data);
					//alert(data);
					if(dataJson.success) {
						gridrowdata = initEditGridRowData(_bizname,data,selectedRowId);
						$("#"+_bizname+"_grid").jqGrid("setRowData", selectedRowId, gridrowdata, {color:"#FF0000"});
						//判断是增加还是修改
						if(dataJson.modifyid==''){
							//在工序单元树上面个添加节点
							var newNode = [{id:dataJson.procid,pid:BOTree1.getSelectedNodes()[0].id,name:dataJson.proctname}];
							BOTree1.addNodes(BOTree1.getSelectedNodes()[0], newNode);
						}else if(dataJson.createid==''){		
							//通过id获取该节点的信息，并且进行修改
							var srcNode=BOTree1.getNodeByParam("id",dataJson.procid,null);
							srcNode.name = dataJson.proctname;
							BOTree1.updateNode(srcNode);
						}
						parent.alertMsg("信息更新成功!");
						//修改保存完成
						$("#"+_bizname+"_grid").trigger('domi.modifysave.after');
					} else {
						parent.alertErrorMsg("操作失败:"+dataJson.errorMessage);
						$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
					}
				},
				error : function(data) {
					parent.alertErrorMsg("系统ajax交互错误 ");
					$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
				}
			});
			col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
			$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
			$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
		}
	}else{
		//修改保存开始
		$("#"+_bizname+"_grid").trigger('domi.modifysave.before');
		var validflag = $("#"+_bizname+"_grid").saveRow(selectedRowId);
		if(!validflag){
			return;
		}else{
			//去编辑列表行数据
			var params = getEditGridParams(_bizname,selectedRowId);
			$.ajax({
				url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").modifyurl,
				cache : false,
				data : params,
				type : 'post',
				success : function(data) {
					var dataJson = eval(data);
					if(dataJson.success) {
						gridrowdata = initEditGridRowData(_bizname,data,selectedRowId);
						$("#"+_bizname+"_grid").jqGrid("setRowData", selectedRowId, gridrowdata, {color:"#FF0000"});
						parent.alertMsg("信息更新成功!");
						//修改保存完成
						$("#"+_bizname+"_grid").trigger('domi.modifysave.after');
					} else {
						parent.alertErrorMsg("操作失败:"+dataJson.errorMessage);
						$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
					}
				},
				error : function(data) {
					parent.alertErrorMsg("系统ajax交互错误 ");
					$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
				}
			});
			col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
			$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
			$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
		}
	}
}
//删除保存的方法
//删除信息
function _doeditdelete(_bizname,selectedRowId) {
	if(_bizname=='t_sc_process'){
		setTimeout(function(){
			bootbox.confirm("确定删除吗?", function(result) {
				if(result) {
					var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
					//删除保存开始
				    $("#"+_bizname+"_grid").trigger('domi.deletesave.before'); 
				    //提取表单参数
				    var params = getGridParams(_bizname);
				    
				    $.ajax({  
				        url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").deleteurl,
				        data : params,  
				        cache : false,  
				        error : function(data) {  
				        	parent.alertErrorMsg("系统ajax交互错误");  
				        },  
				        success : function(data) {  
				        	var dataJson = eval(data);
				            if(dataJson.success) { 
				                $("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId); 
				                //通过id获取该节点的信息，并且进行删除
								var srcNode=BOTree1.getNodeByParam("id",dataJson.procid,null);
								BOTree1.removeNode(srcNode);
								//逻辑删除之后，刷新grid
								$("#"+_bizname+"_grid").trigger("reloadGrid");
				                parent.alertMsg("信息删除成功!");  
				            	//删除保存完成
				                $("#"+_bizname+"_grid").trigger('domi.deletesave.after');
				            } else {  
				            	parent.alertErrorMsg("删除失败:"+dataJson.errorMessage);  
				            	$("#"+_bizname+"_grid").trigger('domi.deletesave.failue');
				            }  
				        }  
				    });  
				}
			});
		},0);
	}else{
		setTimeout(function(){
			bootbox.confirm("确定删除吗?", function(result) {
				if(result) {
					var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
					//删除保存开始
				    $("#"+_bizname+"_grid").trigger('domi.deletesave.before'); 
				    //提取表单参数
				    var params = getGridParams(_bizname);
				    
				    $.ajax({  
				        url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").deleteurl,
				        data : params,  
				        cache : false,  
				        error : function(data) {  
				        	parent.alertErrorMsg("系统ajax交互错误");  
				        },  
				        success : function(data) {  
				        	var dataJson = eval(data);
				            if(dataJson.success) { 
				                $("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId);  
				                  
				                parent.alertMsg("信息删除成功!");  
				            	//删除保存完成
				                $("#"+_bizname+"_grid").trigger('domi.deletesave.after');
				            } else {  
				            	parent.alertErrorMsg("删除失败:"+dataJson.errorMessage);  
				            	$("#"+_bizname+"_grid").trigger('domi.deletesave.failue');
				            }  
				        }  
				    });  
				}
			});
		},0);
	}
}

//修改信息（重写）
function _doeditmodify(_bizname,selectedRowId) {
	if(_bizname=='t_bi_target'){
		var process_selectedRowId = $("#t_sc_process_grid").jqGrid("getGridParam", "selrow");
		var rowData = $("#t_sc_process_grid").jqGrid('getRowData',process_selectedRowId);
		//记录所选中的工序id
		procids=rowData.procid;
		$("#"+_bizname+"_grid").editRow(selectedRowId);
	   	$("#"+_bizname+"_grid").trigger('domi.modify.before');

		col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").hide();
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").show();
		$("#"+_bizname+"_grid").trigger('domi.modify.after');
	}else{
		$("#"+_bizname+"_grid").editRow(selectedRowId);
	   	$("#"+_bizname+"_grid").trigger('domi.modify.before');

		col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").hide();
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").show();
		$("#"+_bizname+"_grid").trigger('domi.modify.after');
	}
}
