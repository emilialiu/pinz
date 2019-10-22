function loadGrid(deptid) {
	// grid参数
	$("#staff_grid").data("gridOptions", {
		height : 250,
		url :  rootpath + '/biz/sm/staff/list.action?bean.deptid='+deptid+"&bean.teamid="+teamgroup,
		colNames : [ 
					//'操作',
					'员工ID',
					'部门id',
					'部门名称',
					'员工编码',
					'姓名',
					'性别',
					'民族',
					'年龄',
					'身份证号码',
					'职务',
					'职称',
					'入职时间',
					'联系电话',
					'家庭住址',
					'学历',
					'学历',
					'操作类别',
					'操作类别',
					'岗位'
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					//{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
					{name : 'staffid',index : 'staffid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,hidden : true},
					{name : 'deptname',index : 'deptname',fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'staffcode',index : 'staffcode',fixed : true,width : 80,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'staffname',index : 'staffname',fixed : true,width : 80,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'sex',index : 'sex',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,hidden : true},
					{name : 'nation',index : 'nation',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,hidden : true},
					{name : 'age',index : 'age',fixed : true,width : 100,sorttype : 'int(11)',editable : false,hidden : true},
					{name : 'idcard',index : 'idcard',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'jobtitle',index : 'jobtitle',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'tptitle',index : 'tptitle',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					{name : 'entrydate',index : 'entrydate',fixed : true,width : 80,sorttype : 'date',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'relaphone',index : 'relaphone',fixed : true,width : 100,sorttype : 'date',editable : false,hidden : true},
					{name : 'address',index : 'address',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},
					{name : 'education',index : 'education',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},				
					{name : 'educationname',index : 'educationname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},				
					{name : 'operatetype',index : 'operatetype',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},				
					{name : 'operatetypename',index : 'operatetypename',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true},				
					{name : 'station',index : 'station',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
				],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		ondblClickRow:function(data){
			onSave();
		},
		multiselect:multiselect=="null"?false:multiselect
	});
	
	initgrid("staff");
	if(multiselect){
		$("button:contains('清空选择')", window.parent.document).hide();
	}else{
		$("button:contains('清空选择')", window.parent.document).show();
	}
	if(hideClearBtn){
		$("button:contains('清空选择')", window.parent.document).hide();
	}
}

//查询
function dosearchbydept(deptID){
	//设置查询参数
	//$("#staff_grid").setGridParam({postData:{"bean.deptid": deptID}}).trigger("reloadGrid");
	$("#staff_grid").jqGrid('setGridParam',{url:rootpath + "/biz/sm/staff/list.action?bean.deptid="+deptID+"&bean.teamid="+teamgroup}).trigger("reloadGrid");
}

function onSave() {
	var data = [];
	var rowdata;
	if(multiselect){//多选
		var selectedRowIds = $("#staff_grid").jqGrid("getGridParam", "selarrrow");
		if(selectedRowIds==''||selectedRowIds==null||selectedRowIds==undefined){
			alertMsg("请至少选择一个员工!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#staff_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#staff_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
	    	alertMsg("请选择一个员工!");
	        return false;
	    }
	    rowdata = $("#staff_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['selectperson'].callback(data);
	}catch(e){
		parent.closewin('selectperson');
	}
	parent.closewin('selectperson');
}

function uncheck() {
	parent.PageObject.itemMap['selectperson'].callback([{"staffid":"","staffname":"","station":""}]);
	//parent.closewin('selectperson');
}