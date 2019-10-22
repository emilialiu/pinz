$(function() {
	// grid参数
	$("#orgcfg_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/biz/sm/orgcfg/list.action',
		colNames : [ 
					'操作',
					'组织机构配置ID',
					'组织机构类型',
					'组织机构类型',
					'是否组织机构',
					'是否组织机构',
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
					{name : 'orgcfgid',index : 'orgcfgid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'orgtype',index : 'orgtype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,hidden : true},
					{name : 'orgtypename',index : 'orgtypename',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'isorg',index : 'isorg',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,hidden : true},
					{name : 'isorgname',index : 'isorgname',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
				],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		viewurl : "/biz/sm/orgcfg/doModify.action",
		addurl : "/biz/sm/orgcfg/doAdd.action",
		modifyurl : "/biz/sm/orgcfg/doModifySave.action",
		deleteurl : "/biz/sm/orgcfg/delete.action"
	});
	
	initgrid("orgcfg");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
  var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
  return {"bean.orgcfgid" : str.orgcfgid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.orgcfgid="+str.orgcfgid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
		"bean.orgcfgid" : datalist.orgcfgid,
		"bean.orgtype" : datalist.orgtype,
		"bean.isorg" : datalist.isorg,
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
		orgcfgid : dataJson.orgcfgid,
		orgtype : datalist.orgtype,
		isorg : datalist.isorg,
		createid : datalist.createid,
		createdate : datalist.createdate,
		modifyid : datalist.modifyid,
		modifydate : datalist.modifydate,
		demo : datalist.demo
    }; 
}
