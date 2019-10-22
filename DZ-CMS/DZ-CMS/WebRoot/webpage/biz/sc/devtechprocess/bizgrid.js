var sbzl={};
$(function() {
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'SBZL'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			sbzl = eval('({'+data+'})');	
		}  
	 });
	// grid参数
	$("#t_sc_devtechprocess_grid").data("gridOptions", {
		height : 529,//parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/devtechprocess/list.action',
		colNames : [ 
					pub_operation,
					'工序ID',
					'设备ID',
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
						{name : 'procid',index : 'procid',fixed : true,hidden:true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					    {name : 'devid',index : 'devid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
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
		multiSort:true,
		viewurl : "/webpage/biz/sc/devtechprocess/doModify.action",
		addurl : "/webpage/biz/sc/devtechprocess/doAdd.action",
		modifyurl : "/webpage/biz/sc/devtechprocess/doModifySave.action",
		deleteurl : "/webpage/biz/sc/devtechprocess/delete.action"
		
	});
	
	initgrid("t_sc_devtechprocess");
	$("#t_sc_devtechprocess_grid_pager_left td").slice(0,5).hide();
	function btnformat(cellvalue,options,rowobj){
		del = "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('t_sc_devtechprocess','"+ options.rowId
			+ "')><span class='ui-icon ui-icon-trash'/></div>";
		return del;
	}
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.procid" : str.procid,
			"bean.devid" : str.devid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.procid="+str.procid;
		return "?bean.devid="+str.devid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.procid" : datalist.procid,
			"bean.devid" : datalist.devid
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				procid : dataJson.id,
				devid : dataJson.id,
				tdeptid : datalist.tdeptid
	}; 
}
function _openDialog4Adding(_bizname) {
	var srcNode = BOTree1.getSelectedNodes();
	if (!srcNode[0].isChild) {
		alertMsg(pub_selectLastProcess);
		$("#renameTxt").attr("value", "");
		return;
	}
	parent.openwin("deviceprocess_selectdevice","/webpage/pub/select/processdevice/main.jsp?multiselect=true&deptid="+srcNode[0].deptid+'&procid='+srcNode[0].id,button_add,805,615);
	parent.PageObject.itemMap['deviceprocess_selectdevice'].callback = function(no){
		selectDeviceCallBack(no,srcNode[0].id,srcNode[0].deptid);
	}
}

function selectDeviceCallBack(arr,procid,deptid){
	var devid='';
	for(var i=0;i<arr.length;i++){
		devid+=(arr[i].devid+',');
	}
	devid=devid.substring(0,devid.length-1);
	var params = { 
		"bean.devid":devid,
		"bean.procid":procid,
		"bean.tdeptid":deptid		
	};
	//ajax产生消息
	$.ajax({
		url : rootpath+"/webpage/biz/sc/devtechprocess/addprocessDevice.action",
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			var info=$.trim(data);
            if(info=='n') { 
                //alertMsg("您没有给工序配置任何设备!");  
            }else if(info=='y'){
            	 //alertMsg("配置设备成功!");  
	             $("#t_sc_devtechprocess_grid").trigger("reloadGrid");
            }else {  
            	alertErrorMsg(dataJson.errormessage); 
            }  
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
}
