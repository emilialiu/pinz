var csmc={};
var dw={};
$(function() {
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'csmc'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			csmc = eval('({'+data+'})');	
		}  
	 });
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
	// grid参数
	$("#t_sc_weightparameter_grid").data("gridOptions", {
		height :529, //parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/weightparameter/list.action',
		colNames : [ 
					pub_operation,
					'参数ID',
					'所属矿山',
					'参数编码',
					sc_weightparameter_parametersName,
					qt_assaysample_unit,
					sc_weightparameter_parametersValues,
					'是否删除，0,未删除;1表示删除',
					'创建人',
					'创建日期',
					'修改人',
					qt_assaysample_modifydate,
					qt_assaysample_memo
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'paraid',index : 'paraid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'tdeptid',index : 'tdeptid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'paracode',index : 'paracode',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'paraname',index : 'paraname',fixed : true,width : 200,sorttype : 'varchar(50)',editable : true,edittype:'select',editrules:{custom:true,custom_func:mycheck}, editable : true,editoptions:{value:csmc},formatter:function(v){return csmc[v];}, unformat:function(v){for(k in csmc){if(v == csmc[k])return k;}},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'unit',index : 'unit',fixed : true,width : 200,sorttype : 'varchar(10)',edittype:'select',editable : true,editoptions:{value:dw},formatter:function(v){return dw[v];}, unformat:function(v){for(k in dw){if(v == dw[k])return k;}},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'paravalue',index : 'paravalue',fixed : true,width : 200,sorttype : 'decimal(16,4)',editable : true,editrules:{custom:true, custom_func:checkvalue,required: true},editoptions:{ maxlength: 17},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isdel',index : 'isdel',fixed : true,width : 200,sorttype : 'char(1)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createid',index : 'createid',fixed : true,width : 200,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createdate',index : 'createdate',fixed : true,width : 200,sorttype : 'datetime',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifyid',index : 'modifyid',fixed : true,width : 200,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifydate',index : 'modifydate',fixed : true,width : 200,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'demo',index : 'demo',fixed : false,width : 100,sorttype : 'varchar(1000)',editable : true,editoptions:{ maxlength: 250},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:true,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/sc/weightparameter/doModify.action",
		addurl : "/webpage/biz/sc/weightparameter/doAdd.action",
		modifyurl : "/webpage/biz/sc/weightparameter/doModifySave.action",
		deleteurl : "/webpage/biz/sc/weightparameter/delete.action"
		
	});
	
	initgrid("t_sc_weightparameter");
	$("#t_sc_weightparameter_grid_pager_left td").slice(0, 5).hide();
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.paraid" : str.paraid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.paraid="+str.paraid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
		paraname:$('#'+selectedRowId+"_paraname").val(),
		unit:$('#'+selectedRowId+"_unit").val()
		
	});
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.paraid" : datalist.paraid,
			"bean.tdeptid" : datalist.tdeptid,
			"bean.paracode" : datalist.paracode,
			"bean.paraname" : datalist.paraname,
			"bean.unit" : datalist.unit,
			"bean.paravalue" : datalist.paravalue,
			"bean.isdel" : datalist.isdel,
			"bean.createid" : datalist.createid,
			"bean.createdate" : datalist.createdate,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate,
			"bean.demo" : datalist.demo
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				paraid : dataJson.paraid,
				tdeptid : datalist.tdeptid,
				paracode : datalist.paracode,
				paraname : datalist.paraname,
				unit : datalist.unit,
				paravalue : datalist.paravalue,
				isdel : datalist.isdel,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : dataJson.modifydate,
				demo : datalist.demo
	}; 
}
var _openDialog4Adding = function(_bizname) {  
	if(_bizname=='t_sc_weightparameter'){
		addnullrow(_bizname);
	}
}; 
//增加一行
function addnullrow(_bizname){
	//获取表格的初始化model  
	var colModel = $("#"+_bizname+"_grid").jqGrid().getGridParam("colModel") ;  
	var cellLenth = colModel.length;
	//设置所有列可编辑（如果特定的列不可编辑，则需判断）  
	var newRow = JSON.stringify(colModel);
		//获得所有行的ID数组
	var ids = $("#"+_bizname+"_grid").jqGrid("getDataIDs");
	//如果jqgrid中没有数据 定义行号为1 ，否则取当前最大行号+1
	//alert(Math.max.apply(Math,ids));
	var newrowid = (ids.length ==0 ? 1: Math.max.apply(Math,ids)+1);
	//设置grid单元格不可编辑 （防止在添加时，用户修改其他非添加行的数据）  
	$("#"+_bizname+"_grid").setGridParam({cellEdit:false});
	//将新行追加到表格头部  
	$("#"+_bizname+"_grid").jqGrid("addRowData", newrowid, newRow, "first");
	//设置grid单元格可编辑（防止追加行后，可编辑列无法编辑）  
	$("#"+_bizname+"_grid").jqGrid("editRow", newrowid, false);
	updateEditIcons(_bizname);
	_doeditmodify(_bizname,newrowid);
	
	if(_bizname=='t_sc_weightparameter'){
		$.ajax( {  
			url : rootpath + '/webpage/biz/sc/weightparameter/selectList.action', 
			type:'POST',
			cache : false,
			async : false,
			error : function(data) {  
				alertErrorMsg("系统ajax交互错误");   
			},  
			success : function(data) {
				var csmc1 = eval(data);
				for(var i=0;i<csmc1.length;i++){
					$("#"+newrowid+"_paraname option[value='"+csmc1[i].paraname+"']").remove(); 
				}
			}  
		});
	}
	$("#"+_bizname+"_grid").setSelection(newrowid,true);
}

function _doeditmodify(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").editRow(selectedRowId);
   	$("#"+_bizname+"_grid").trigger('domi.modify.before');

   	var paraname=$("#"+selectedRowId+"_paraname").val();
   	var paraid1=$("#"+selectedRowId+"_paraid").val();
   	if(paraid1!=""){
	//初始化
		$.ajax( {  
			url : rootpath + '/webpage/biz/sc/weightparameter/selectList.action?bean.paraname='+paraname, 
			type:'POST',
			cache : false,
			async : false,
			error : function(data) {  
				alertErrorMsg("系统ajax交互错误");   
			},  
			success : function(data) {
				var paraname1 = eval(data);
				for(var i=0;i<paraname1.length;i++){
					$("#"+selectedRowId+"_paraname option[value='"+paraname1[i].paraname+"']").remove(); 
				}
			}  
		});
   	}
	col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").hide();
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").show();
	$("#"+_bizname+"_grid").trigger('domi.modify.after');
}

function checkvalue(value,colname){
	if(value.indexOf(".")==-1){  //不含小数点
		return pnumcheck(value,colname);
	}else{   //含小数点
		if(value.length<=17){
			if(value.indexOf(".")==0 || value.indexOf(".")==(value.length-1)){
				return [false,sc_weightparameter_parametersValid+" !"];
			}else{
				return numcheck(value,colname);
			}
		}else{
			return [false,sc_weightparameter_parametersValid+" !"];
		}
	}
}

function pnumcheck(value,colname){
	var maxValue = 999999999999;
	if(/^(-)?[1-9][0-9]*$/.test(value)){
		if (value != null && value <=maxValue){ 
			return [true,""];
		} else { 
			return [false,sc_weightparameter_parametersValid+" !"];
		} 
	}else{
		if(value == 0){
			return [true,"OK"];
		}else{
			return [false,sc_weightparameter_parametersValid+" !"];
		}
	}
}
function numcheck(value,colname){
	var maxValue = 999999999999;
	if(/^(\+|\-)?((([1-9]([0-9]*))|0)(\.\d{1,4})?)?$/.test(value)){
		if (value != null && value <=maxValue){ 
			return [true,"OK"];
		} else {
			if(value == 0){
				return [true,"OK"];
			}else{
				return [false,sc_weightparameter_parametersValid+" !"];
			}
		} 
	}else{
			return [false,sc_weightparameter_parametersValid+" !"];
	}
}

function _doeditcancel(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").restoreRow("" + selectedRowId + "");
	col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
	$("#"+_bizname+"_grid").trigger("reloadGrid");
}

//字段规则验证
function mycheck(value, colname) {
	if((value+"")=='undefined'||value==null||value==""){
		return [false,sc_weightparameter_parametersMust+" !"];
	}else{
		return [true,""];
	}		
}

function _doeditcancel(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").restoreRow("" + selectedRowId + "");
	col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
	$("#"+_bizname+"_grid").trigger("reloadGrid");
}