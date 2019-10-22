$(function() {
	// grid参数
	$("#staff_grid").data("gridOptions", {
		height : 483,//getClientHeights()-205,
		url :  rootpath + '/biz/sm/staff/list.action',
		colNames : [ 
					'操作',
					'部门名称',
					'员工编码',
					'姓名',
					'岗位',
					'性别',
					'民族',
					'年龄',
					'身份证号码',
					'职务',
					'职称',
					'入职时间',
					'家庭住址',
					'员工ID',
					'角色名称'
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:120, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
					//{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'deptname',index : 'deptname',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'staffcode',index : 'staffcode',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'staffname',index : 'staffname',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'station',index : 'station',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'sexname',index : 'sexname',fixed : true,width : 50,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'nation',index : 'nation',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,hidden : true},
					{name : 'age',index : 'age',fixed : true,width : 100,sorttype : 'int(11)',editable : false,hidden : true},
					{name : 'idcard',index : 'idcard',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'jobtitle',index : 'jobtitle',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'tptitle',index : 'tptitle',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'entrydate',index : 'entrydate',fixed : true,width : 80,sorttype : 'date',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'address',index : 'address',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'staffid',index : 'staffid',fixed : true,width : 245,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'rolename',index : 'rolename',fixed : true,width : 200,sorttype : 'varchar(40)',editable : false,hidden : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		viewurl : "/biz/sm/staff/doModify.action",
		addurl : "/biz/sm/staff/doAdd.action",
		modifyurl : "/biz/sm/staff/doModifySave.action",
		deleteurl : "/biz/sm/staff/delete.action"
	});
	
	initgrid("staff");
	
	function btnformat(cellvalue,options,rowobj){
		update = "<div class='ui-pg-div ui-inline-edit' style='float: left; margin-left: 8px;' title='修改' onclick=_doopenmodify('staff','"+ options.rowId
		+ "')><span class='ui-icon ui-icon-pencil'/></div>";
		del = "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('staff','"+ options.rowId
			+ "')><span class='ui-icon ui-icon-trash'/></div>";
		edit = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='角色' onclick=Editpersona('"+options.rowId+"')><span class='ui-icon fa-edit'/></div>";
		cog = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='重置密码' onclick=resetPassword('"+options.rowId+"')><span class='ui-icon fa-cog'/></div>";
		
		return update + del + edit + cog;
	}

	/**调整左右div宽度*/
	var bizid = new Array();
	bizid[0] = "staff";
	doresizable(bizid);
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {"bean.staffid" : str.staffid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return "?bean.staffid="+str.staffid;  
}

//查询
function dosearchbydept(deptID){
	//设置查询参数
	$("#staff_grid").setGridParam({page:1,postData:{"bean.deptid": deptID}}).trigger("reloadGrid");
}

//重写新增方法
function _openDialog4Adding(_bizname){
	var srcNode = BOTree1.getSelectedNodes();
	var url = $("#"+_bizname+"_grid").data("gridOptions").addurl+"?bean.deptid="+srcNode[0].id;
	parent.createwindow(_bizname,url,"新增",_formw,600,true);
}

//重置密码
function resetPassword(rowid){
	var datalist = $("#staff_grid").jqGrid('getRowData', rowid);
	var url = "/webpage/manager/sys/user/resetpwd.jsp?userid="+datalist.staffid;
	parent.createwindow("sys_user_reset",url,"重置密码",800,300,true);
}

//角色
function Editpersona(rowid){
	var datalist = $("#staff_grid").jqGrid('getRowData', rowid);
	var url = "/webpage/manager/sys/user/persona.jsp?userid="+datalist.staffid;
	parent.createwindow("sys_user_persona",url,"角色",1000,540,true);
}