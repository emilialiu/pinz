$(function() {
	// grid参数
	$("#team_grid").data("gridOptions", {
		height : 280,
		url :  rootpath + '/biz/sm/team/list.action',
		colNames : [ 
					'操作',
					'班组ID',
					'所属项目部ID',
					'组织机构ID',
					'组织机构',
					'班组编号',
					'班组类型',
					'班组名称',
					'人数',
					'班组责任人',
					'联系电话',
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
					{name : 'teamid',index : 'teamid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'tdeptid',index : 'tdeptid',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,hidden : true},
					{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,hidden : true},
					{name : 'deptname',index : 'deptname',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'teamcode',index : 'teamcode',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'teamtypename',index : 'teamtypename',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'teamname',index : 'teamname',fixed : true,width : 140,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'peoplenum',index : 'peoplenum',fixed : true,width : 80,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'dutymanname',index : 'dutymanname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'phone',index : 'phone',fixed : true,width : 100,sorttype : 'varchar(32)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
				],
		onSelectRow: function(rowid, status){
				 	var rowData = $("#team_grid").jqGrid('getRowData',rowid);
					reloadByTeamid(rowData.teamid);
				},
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		infowidth:500,
		viewurl : "/biz/sm/team/doModify.action",
		addurl : "/biz/sm/team/doAdd.action",
		modifyurl : "/biz/sm/team/doModifySave.action",
		deleteurl : "/biz/sm/team/delete.action"
	});
	
	initgrid("team");
	
	$("#teamstaff_grid").data("gridOptions", {
		height : 150,
		url :  rootpath + '/biz/sm/teamstaff/list.action?bean.teamid=--',
		colNames : [ 
					'操作',
					'班组ID',
					'编码',
					'员工'
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter,hidden : true},
					{name : 'teamid',index : 'teamid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'staffid',index : 'staffid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : false},
					{name : 'staffname',index : 'staffname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
				],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		//viewurl : rootpath+"/biz/sm/teamstaff/doModify.action",
		addurl : "/biz/sm/teamstaff/doAdd.action"//,
		//modifyurl : rootpath+"/biz/sm/teamstaff/doModifySave.action?actiontype=modifysave",
		//deleteurl : rootpath+"/biz/sm/teamstaff/delete.action?actiontype=delete"
	});
	
	initgrid("teamstaff");

	function reloadByTeamid(teamid){
		jQuery("#teamstaff_grid").setGridParam({url:rootpath + '/biz/sm/teamstaff/list.action?bean.teamid='+teamid}).trigger("reloadGrid");
	}

	/**调整左右div宽度*/
	var bizid = new Array();
	bizid[0] = "team";
	bizid[1] = "teamstaff";
	doresizable(bizid);
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	if(_bizname == "team"){
		return {"bean.teamid" : str.teamid};
	}else{
		return {"bean.staffid" : str.staffid};	
	}
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    return "?bean.teamid="+str.teamid;  
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {
		"bean.teamid" : datalist.teamid,
		"bean.deptid" : datalist.deptid,
		"bean.teamcode" : datalist.teamcode,
		"bean.teamtype" : datalist.teamtype,
		"bean.teamname" : datalist.teamname,
		"bean.dutyman" : datalist.dutyman,
		"bean.peoplenum" : datalist.peoplenum,
		"bean.phone" : datalist.phone,
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
		teamid : dataJson.teamid,
		deptid : datalist.deptid,
		teamcode : datalist.teamcode,
		teamtype : datalist.teamtype,
		teamname : datalist.teamname,
		dutyman : datalist.dutyman,
		peoplenum : datalist.peoplenum,
		phone : datalist.phone,
		createid : datalist.createid,
		createdate : datalist.createdate,
		modifyid : datalist.modifyid,
		modifydate : datalist.modifydate,
		memo : datalist.memo
    };
}

//重写新增方法
function _openDialog4Adding(_bizname) {
	if(_bizname == 'team'){
		var srcNode = BOTree1.getSelectedNodes();
		/*if(srcNode[0].orgtype != 'ZZJGLX003'){
			alertMsg("请先选择工区!");
	        return;
		}*/
		var srcNode = BOTree1.getSelectedNodes();
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl+"?bean.deptid="+srcNode[0].id+"&bean.deptname="+srcNode[0].name;
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	} else if(_bizname == 'teamstaff'){
		var selectedRowId = $("#team_grid").jqGrid("getGridParam", "selrow");
	    if (!selectedRowId) {
	    	alertMsg("请先选择一条班组信息!");
	        return;
	    }
	    var datalist = $("#team_grid").jqGrid('getRowData', selectedRowId);
		parent.openwin_nobutton("persona","/webpage/biz/sm/team/persona.jsp?teamid="+datalist.teamid+"&deptid="+datalist.deptid,"添加班组人员",850,600);
	}
}

//查询
function dosearchbydept(deptID){
	//设置查询参数
	$("#team_grid").setGridParam({postData:{"bean.tdeptid": deptID}}).trigger("reloadGrid");
}
function teamstaffReloadGrid(){
	$("#teamstaff_grid").trigger("reloadGrid");
}