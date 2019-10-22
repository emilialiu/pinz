$(function() {
	// grid参数
	$("#dz_customer_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/dz/customer/list.action',
		colNames : [ 
					'操作',
					'id',
					'微信open_id',
					'会员等级',
					'姓名',
					'昵称',
					'头像路径',
					'积分数',
					'性别',
					'微信标识',
					'所在市',
					'所在省',
					'所在国家',
					'创建时间',
					'最后登录时间',
					'所属代理商id'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:130, fixed:true, sortable:false, resize:false,search : false,formatter:practice_btnformatter},
						{name : 'uid',index : 'uid',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'openid',hidden:true,index : 'openid',width : 60,sorttype : "varchar(200)",editable : false,hidden : true},
						{name : 'viplevelname',index : 'viplevelname',fixed : true,width : 100,sorttype : 'varchar(64)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'name',index : 'name',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'nick_name',index : 'nick_name',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'head_url',index : 'head_url',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'integral_num',index : 'integral_num',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'sexname',index : 'sexname',fixed : true,width : 100,sorttype : 'int(11)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'union_id',hidden:true,index : 'union_id',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'city',index : 'city',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'province',index : 'province',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'country',index : 'country',fixed : true,width : 100,sorttype : 'varchar(20)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'create_time',index : 'create_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'last_logon_time',index : 'last_logon_time',fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'belong_agent',index : 'belong_agent',width : 60,sorttype : "varchar(64)",editable : false,hidden : true}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		formw:800,
		formh:600,
		viewurl : "/webpage/biz/dz/customer/doModify.action",
		addurl : "/webpage/biz/dz/customer/doAdd.action",
		modifyurl : "/webpage/biz/dz/customer/doModifySave.action",
		deleteurl : "/webpage/biz/dz/customer/delete.action"
		
	});
	
	initgrid("dz_customer");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.uid" : str.uid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.uid="+str.uid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.uid" : datalist.uid,
			"bean.openid" : datalist.openid,
			"bean.vip_level" : datalist.vip_level,
			"bean.name" : datalist.name,
			"bean.nick_name" : datalist.nick_name,
			"bean.head_url" : datalist.head_url,
			"bean.integral_num" : datalist.integral_num,
			"bean.sex" : datalist.sex,
			"bean.union_id" : datalist.union_id,
			"bean.city" : datalist.city,
			"bean.province" : datalist.province,
			"bean.country" : datalist.country,
			"bean.create_time" : datalist.create_time,
			"bean.last_logon_time" : datalist.last_logon_time,
			"bean.belong_agent" : datalist.belong_agent
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				uid : dataJson.id,
				openid : datalist.openid,
				vip_level : datalist.vip_level,
				name : datalist.name,
				nick_name : datalist.nick_name,
				head_url : datalist.head_url,
				integral_num : datalist.integral_num,
				sex : datalist.sex,
				union_id : datalist.union_id,
				city : datalist.city,
				province : datalist.province,
				country : datalist.country,
				create_time : datalist.create_time,
				last_logon_time : datalist.last_logon_time,
				belong_agent : datalist.belong_agent
	}; 
}
function practice_btnformatter(cellvalue,options,rowobj){
	update = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='修改' onclick=_doopenmodify('dz_customer','"+options.rowId+"')><span class='ui-icon ui-icon-pencil'/></div>";
	delet = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('dz_customer','"+options.rowId+"')><span class='ui-icon ui-icon-trash'/></div>";
	detail = "<div class='ui-pg-div' style='float: left; margin-left: 5px;' title='查看头像' onclick=openfile('dz_customer','"+options.rowId+"')><span class='ui-icon fa-eye'/></div>";
	return update+delet+detail;
}
function openfile(_bizname,rowid){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
	var rowData =$("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	window.open(rootpath + "/webpage/biz/showimg.jsp?path="+rowData.head_url);
  }

