$(function() {
	// grid参数
	$("#t_pub_attachment_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/pub/attachment/list.action',
		colNames : [ 
					'操作',
					'附件ID',
					'矿山ID',
					'业务类型',
					'业务ID',
					'附件类型',
					'附件名称',
					'附件所在路径',
					'附件所属年份',
					'创建人',
					'创建日期',
					'修改人',
					'修改日期',
					'备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'attachmentid',index : 'attachmentid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'bzstype',index : 'bzstype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'businessid',index : 'businessid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'attachmenttype',index : 'attachmenttype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'attachmentname',index : 'attachmentname',fixed : true,width : 100,sorttype : 'varchar(128)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'attachmenturl',index : 'attachmenturl',fixed : true,width : 100,sorttype : 'varchar(300)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'yearvalue',index : 'yearvalue',fixed : true,width : 100,sorttype : 'varchar(4)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createid',index : 'createid',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createdate',index : 'createdate',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifyid',index : 'modifyid',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifydate',index : 'modifydate',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/pub/attachment/doModify.action",
		addurl : "/webpage/biz/pub/attachment/doAdd.action",
		modifyurl : "/webpage/biz/pub/attachment/doModifySave.action",
		deleteurl : "/webpage/biz/pub/attachment/delete.action"
		
	});
	
	initgrid("t_pub_attachment");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.attachmentid" : str.attachmentid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.attachmentid="+str.attachmentid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.attachmentid" : datalist.attachmentid,
			"bean.deptid" : datalist.deptid,
			"bean.bzstype" : datalist.bzstype,
			"bean.businessid" : datalist.businessid,
			"bean.attachmenttype" : datalist.attachmenttype,
			"bean.attachmentname" : datalist.attachmentname,
			"bean.attachmenturl" : datalist.attachmenturl,
			"bean.yearvalue" : datalist.yearvalue,
			"bean.createid" : datalist.createid,
			"bean.createdate" : datalist.createdate,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate,
			"bean.memo" : datalist.memo
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				attachmentid : dataJson.id,
				deptid : datalist.deptid,
				bzstype : datalist.bzstype,
				businessid : datalist.businessid,
				attachmenttype : datalist.attachmenttype,
				attachmentname : datalist.attachmentname,
				attachmenturl : datalist.attachmenturl,
				yearvalue : datalist.yearvalue,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate,
				memo : datalist.memo
	}; 
}
