var kzys={};
var dw={};
$(function() {
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'KZ'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			kzys = eval('({'+data+'})');	
		}  
	});
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'DW'},  
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
	$("#t_sc_oretype_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/oretype/list.action',
		colNames : [ 
					'操作',
					'矿种id',
					'矿种',
					'部门(矿山)',
					'单位',
					'序号',
					'备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'oretypeid',index : 'oretypeid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]},hidden:true},
						{name : 'oretype',index : 'oretype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : true,edittype:'select',editoptions:{value:kzys},formatter:function(v){if(v==null||v==''){return '';}else{return kzys[v];}}, unformat:function(v){for(k in kzys){if(v ==kzys[k])return k;}},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true},
						{name : 'unit',index : 'unit',fixed : true,width : 100,sorttype : 'varchar(10)',edittype:'select',editoptions:{value:dw},formatter:function(v){if(v==null||v==''){return '';}else{return dw[v];}}, unformat:function(v){for(k in dw){if(v ==dw[k])return k;}},editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'indexno',index : 'indexno',fixed : true,width : 100,sorttype : 'int(11)',editable : true,editrules:{required:true,number:true,minValue:0},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'demo',index : 'demo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable :true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:true,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/sc/oretype/doModify.action",
		addurl : "/webpage/biz/sc/oretype/doAdd.action",
		modifyurl : "/webpage/biz/sc/oretype/doModifySave.action",
		deleteurl : "/webpage/biz/sc/oretype/delete.action"
		
	});
	initgrid("t_sc_oretype");
	$("#t_sc_oretype_grid_pager_left td").slice(0, 5).hide();
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.oretypeid" : str.oretypeid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.oretypeid="+str.oretypeid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
		unit:$('#'+selectedRowId+"_unit").val(),
		oretype:$('#'+selectedRowId+"_oretype").val()
	});
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.oretypeid" : datalist.oretypeid,
			"bean.oretype" : datalist.oretype,
			"bean.deptid" : datalist.deptid,
			"bean.unit" : datalist.unit,
			"bean.indexno" : datalist.indexno,
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
				oretypeid : dataJson.id,
				oretype : datalist.oretype,
				deptid : datalist.deptid,
				unit : datalist.unit,
				indexno : datalist.indexno,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate,
				demo : datalist.demo
	}; 
}

var _openDialog4Adding = function(_bizname) { 
	addnullrow(_bizname);
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
	$("#"+_bizname+"_grid").setSelection(newrowid,true);
}
