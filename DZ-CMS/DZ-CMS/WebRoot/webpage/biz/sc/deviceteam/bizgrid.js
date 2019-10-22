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
					dm_deviceteam_deptname,
					dm_deviceteam_teamcode,
					dm_deviceteam_teamtypename,
					dm_deviceteam_teamname,
					dm_deviceteam_peoplenum,
					dm_deviceteam_dutymanname,
					dm_deviceteam_phone
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,hidden : true,search : false,formatter:btnformatter},
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
	$("#team_grid_pager_left td").slice(0, 4).hide();
	
	$("#deviceteam_grid").data("gridOptions", {
		height : 150,
		url :  rootpath + '/webpage/biz/dm/deviceteam/list.action?bean.teamid=--',
		colNames : [ 
					pub_operation,
					'设备ID',
					'班组id',
					'所属矿山',
					'设备编号',
					'设备名称',
					
					'规格型号',
					'投用时间',
					'创建人',
					'创建日期',
					'修改人',
					
					'修改日期',
					'备注',
					'所属工段',
					'发动机型号',
					'车架号',
					
					'发动机号',
					'产量',
					'运行小时数',
					'柴油消耗',
					'备件消耗',
					
					'故障小时数',
					'设备来源'
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		            {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
					{name : 'devid',index : 'devid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'teamid',index : 'teamid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'tdeptid',index : 'tdeptid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
					{name : 'devcode',index : 'devcode',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'devname',index : 'devname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					
					{name : 'devmodel',index : 'devmodel',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'userdate',index : 'userdate',fixed : true,width : 100,sorttype : 'date',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'createid',index : 'createid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
					{name : 'createdate',index : 'createdate',width : 60,sorttype : "datetime",editable : false,hidden : true},
					{name : 'modifyid',index : 'modifyid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
					
					{name : 'modifydate',index : 'modifydate',width : 60,sorttype : "datetime",editable : false,hidden : true},
					{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'belongname',index : 'belongname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'engineno',index : 'engineno',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'frameno',index : 'frameno',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					
					{name : 'enginehao',index : 'enginehao',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'production',hidden : true,index : 'production',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
					{name : 'workhour',hidden : true,index : 'workhour',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
					{name : 'fuelconsumption',hidden : true,index : 'fuelconsumption',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
					{name : 'partsconsumption',hidden : true,index : 'partsconsumption',fixed : true,width : 100,sorttype : 'varchar(500)',editable : false},
					
					{name : 'bughour',hidden : true,index : 'bughour',fixed : true,width : 100,sorttype : 'decimal(16,2)',editable : false},
					{name : 'source',index : 'source',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		infowidth:500,
		//viewurl : rootpath+"/biz/sm/teamstaff/doModify.action",
		//addurl : "/biz/sm/teamstaff/doAddSave.action"//,
		//modifyurl : rootpath+"/biz/sm/teamstaff/doModifySave.action?actiontype=modifysave",
		deleteurl : "/webpage/biz/dm/deviceteam/delete.action"
	});
	
	initgrid("deviceteam");
	$("#deviceteam_grid_pager_left td").slice(0, 1).hide();
	
	function btnformat(cellvalue,options,rowobj){
		del = "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('deviceteam','"+ options.rowId
			+ "')><span class='ui-icon ui-icon-trash'/></div>";
		return del;
	}
	function reloadByTeamid(teamid){
		jQuery("#deviceteam_grid").setGridParam({
				url:rootpath + '/webpage/biz/dm/deviceteam/list.action?bean.teamid='+teamid,
				page : 1
			}).trigger("reloadGrid");
	}

	/**调整左右div宽度*/
	var bizid = new Array();
	bizid[0] = "team";
	bizid[1] = "deviceteam";
	doresizable(bizid);
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {
				"bean.teamid" : str.teamid,
				"bean.devid" : str.devid
		   };
}

//重写新增方法
function _openDialog4Adding(_bizname) {
	var selectedRowId = $("#team_grid").jqGrid("getGridParam", "selrow");
	if(selectedRowId==''||selectedRowId==null){
		alertMsg("请先选择班组!");
		return;
	}
	var rowData = $("#team_grid").jqGrid('getRowData',selectedRowId);
	parent.openwin("deviceteam_selectdevice","/webpage/pub/select/teamdevice/main.jsp?multiselect=true&deptid="+rowData.deptid+'&teamid='+rowData.teamid,dm_deviceteam_deviceselection,805,615);
	parent.PageObject.itemMap['deviceteam_selectdevice'].callback = function(no){
		selectDeviceCallBack(no,rowData.teamid);
	}
}
//选择设备后的回掉函数
function selectDeviceCallBack(arr,teamid){
	var devid='';
	for(var i=0;i<arr.length;i++){
		devid+=(arr[i].devid+',');
	}
	devid=devid.substring(0,devid.length-1);
	var params = { 
		"bean.devid":devid,
		"bean.teamid":teamid	
	};
	//ajax产生消息
	$.ajax({
		url : rootpath+"/webpage/biz/dm/deviceteam/addTeamDevice.action",
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			var info=$.trim(data);
            if(info=='n') { 
                alertMsg("您没有给班组配置任何设备!");  
            }else if(info=='y'){
            	 alertMsg("配置设备成功!");  
	             $("#deviceteam_grid").trigger("reloadGrid");
            }else {  
            	alertErrorMsg(dataJson.errormessage); 
            }  
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
}

//查询
function dosearchbydept(deptID){
	//设置查询参数
	$("#team_grid").setGridParam({postData:{"bean.tdeptid": deptID}}).trigger("reloadGrid");
}
