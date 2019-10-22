$(function() {
	// grid参数
	$("#sys_user_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/manager/sys/user/list.action',
		colNames : [ '操作', '用户ID', '登录名称', '用户姓名', '组织机构', '机构代码', '机构ID' ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'processbtn',index:'', width:130, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
					 {name : 'userid',index : 'userid',width : 60,sorttype : "string",editable : true,hidden : true}, 
					 {name : 'loginname',index : 'loginname',width : 100, fixed:true, sorttype : "string",editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
					 {name : 'username',index : 'username',fixed : true,width : 140,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
					 {name : 'deptname',index : 'deptname',fixed : true,width : 150,sortable : false,editable : true,searchoptions : {sopt : [ 'cn' ]},edittype : "textarea",editoptions : {rows : "2",cols : "10"}}, 
					 {name : 'deptCode',index : 'deptCode',width : 100,fixed:true,search : false, editable : true} ,
					 {name : 'deptid',index : 'deptid',width : 60,editable : true,hidden : true} 
					],
		processbtncol:2,//操作按钮放在第几个列
		multiselect:true,
		isedit:true,//是否可编辑列表
		advsearch:true,
		viewurl : "/manager/sys/user/doModify.action",
		addurl : "/manager/sys/user/doAdd.action",
		modifyurl : "/manager/sys/user/doModifySave.action",
		deleteurl : "/manager/sys/user/delete.action"
	});
	
	initgrid("sys_user");
	
	function btnformat(cellvalue,options,rowobj){
		update = "<div class='ui-pg-div ui-inline-edit' style='float: left; margin-left: 8px;' title='修改' onclick=_doopenmodify('sys_user','"+ options.rowId
			+ "')><span class='ui-icon ui-icon-pencil'/></div>";
		del = "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('sys_user','"+ options.rowId
			+ "')><span class='ui-icon ui-icon-trash'/></div>";
		edit = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='角色' onclick=Editpersona('"+options.rowId+"')><span class='ui-icon fa-edit'/></div>";
		cog = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='重置密码' onclick=resetPassword('"+options.rowId+"')><span class='ui-icon fa-cog'/></div>";
		return update + del + edit + cog;
	}
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return {"bean.userid" : str.userid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.userid="+str.userid;  
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {
        "bean.userid" : datalist.userid,
        "bean.loginname" : datalist.loginname,
        "bean.username" : datalist.username, 
        "bean.tel" : datalist.tel,
        "bean.deptname" : datalist.deptname,
        "bean.deptCode" : datalist.deptCode,
        "bean.deptid" : datalist.deptid
    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {
		userid : dataJson.userid,
		loginname : datalist.loginname,
		username : datalist.username,
		tel : datalist.tel,
		deptname : datalist.deptname,
		deptCode : datalist.deptCode,
		deptid : datalist.deptid
    };
}

//查询
function dosearchbydept(deptID){
	//设置查询参数
	$("#sys_user_grid").setGridParam({postData:{"bean.deptid": deptID}}).trigger("reloadGrid");
}
//重写新增方法
function _openDialog4Adding(){
	var srcNode = BOTree1.getSelectedNodes();
	var url = $("#sys_user_grid").data("gridOptions").addurl+"?bean.deptid="+srcNode[0].id;
	parent.createwindow("sys_user",url,"新增",800,320,true);
}

//重置密码
function resetPassword(rowid){
	var datalist = $("#sys_user_grid").jqGrid('getRowData', rowid);
	var url = "/webpage/manager/sys/user/resetpwd.jsp?userid="+datalist.userid;
	parent.createwindow("sys_user_reset",url,"重置密码",800,300,true);
}

//角色
function Editpersona(rowid){
	var datalist = $("#sys_user_grid").jqGrid('getRowData', rowid);
	var url = "/webpage/manager/sys/user/persona.jsp?userid="+datalist.userid;
	parent.createwindow("sys_user_persona",url,"角色",1000,540,true);
}
