var dw={};
$(function() {
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'dw'},  
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
	$("#t_bi_target_grid").data("gridOptions", { 
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/bi/target/list.action',
		colNames : [ 
					'操作',
					'指标ID',
					'工序ID',
					'指标名称',
					'简称',
					'指标单位',
					'是否启用，0,未启用;1表示启用',
					'是否删除，0,未删除;1表示删除',
					'是否可编辑,0,不可编辑;1表示能编辑',
					'是否汇总，0,不能汇总;1表示能汇总',
					'是否必填，0,不必填;1表示必填',
					'数据类型',
					'年计划阶段使用：0表示不使用，1表示使用',
					'月计划阶段使用：0表示不使用，1表示使用',
					'生产台账阶段使用：0表示不使用，1表示使用',
					'验收阶段使用：0表示不使用，1表示使用',
					'备注',
					'创建人',
					'创建日期',
					'修改人',
					'修改日期'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'targetid',index : 'targetid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'procid',index : 'procid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'targetname',index : 'targetname',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'shortname',index : 'shortname',width : 60,sorttype : "varchar(20)",editable : false,hidden : true},
						{name : 'targetunit',index : 'targetunit',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,editoptions:{value:dw},formatter:function(v){return dw[v];},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isused',index : 'isused',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isdel',index : 'isdel',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isedit',index : 'isedit',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'issum',index : 'issum',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ismust',index : 'ismust',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'datatype',index : 'datatype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isyplan',index : 'isyplan',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'ismplan',index : 'ismplan',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isproduce',index : 'isproduce',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isaccept',index : 'isaccept',fixed : true,width : 100,sorttype : 'char(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',width : 60,sorttype : "varchar(200)",editable : false,hidden : true},
						{name : 'createid',index : 'createid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'createdate',index : 'createdate',width : 60,sorttype : "datetime",editable : false,hidden : true},
						{name : 'modifyid',index : 'modifyid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'modifydate',index : 'modifydate',width : 60,sorttype : "datetime",editable : false,hidden : true}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/bi/target/doModify.action",
		addurl : "/webpage/biz/bi/target/doAdd.action",
		modifyurl : "/webpage/biz/bi/target/doModifySave.action",
		deleteurl : "/webpage/biz/bi/target/delete.action"
		
	});
	
	initgrid("t_bi_target");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.targetid" : str.targetid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.targetid="+str.targetid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.targetid" : datalist.targetid,
			"bean.procid" : datalist.procid,
			"bean.targetname" : datalist.targetname,
			"bean.shortname" : datalist.shortname,
			"bean.targetunit" : datalist.targetunit,
			"bean.isused" : datalist.isused,
			"bean.isdel" : datalist.isdel,
			"bean.isedit" : datalist.isedit,
			"bean.issum" : datalist.issum,
			"bean.ismust" : datalist.ismust,
			"bean.datatype" : datalist.datatype,
			"bean.isyplan" : datalist.isyplan,
			"bean.ismplan" : datalist.ismplan,
			"bean.isproduce" : datalist.isproduce,
			"bean.isaccept" : datalist.isaccept,
			"bean.memo" : datalist.memo,
			"bean.createid" : datalist.createid,
			"bean.createdate" : datalist.createdate,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				targetid : dataJson.id,
				procid : datalist.procid,
				targetname : datalist.targetname,
				shortname : datalist.shortname,
				targetunit : datalist.targetunit,
				isused : datalist.isused,
				isdel : datalist.isdel,
				isedit : datalist.isedit,
				issum : datalist.issum,
				ismust : datalist.ismust,
				datatype : datalist.datatype,
				isyplan : datalist.isyplan,
				ismplan : datalist.ismplan,
				isproduce : datalist.isproduce,
				isaccept : datalist.isaccept,
				memo : datalist.memo,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate
	}; 
}
