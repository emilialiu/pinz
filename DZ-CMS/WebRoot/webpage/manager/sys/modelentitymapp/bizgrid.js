var scripts = [null,rootpath+"/manager/sys/modelentitymapp/info.js", null];

$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	$(function() {
		// grid参数
		$("#sys_modelentitymapp_grid").data("gridOptions", {
			height : 400,
			url :  rootpath + '/manager/sys/modelentitymapp/list.action',
			colNames : [ 
						'操作',
							'表名称',
							'实体类名',
							'是否指定自动生成列值',
							'自动生成键列'
						],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
							{name : 'tablename',index : 'tablename',fixed : true,width : 150,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'entityclass',index : 'entityclass',fixed : true,width : 300,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'isauto',index : 'isauto',fixed : true,width : 150,sorttype : 'char',editable : false,formatter:sfformatter,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'dbkey',index : 'dbkey',fixed : true,width : 200,sorttype : 'varchar2',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
						],
			processbtncol:2,//操作按钮放在第几个列
			isedit:false,//是否可编辑列表
			advsearch:true,
			viewurl : rootpath+"/manager/sys/modelentitymapp/doModify.action",
			addurl : rootpath+"/manager/sys/modelentitymapp/doAddSave.action",
			modifyurl : rootpath+"/manager/sys/modelentitymapp/doModifySave.action?actiontype=modifysave",
			deleteurl : rootpath+"/manager/sys/modelentitymapp/delete.action?actiontype=delete"
			
		});

		function sfformatter(cellvalue,options,rowobj){
			if(cellvalue==1){
				return '是';
			}else{
				return '否';
			}
		}
		
		initgrid("sys_modelentitymapp");
		initvalid("sys_modelentitymapp");
	});
});
//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
				"bean.tablename" : datalist.tablename,
				"bean.entityclass" : datalist.entityclass,
				"bean.dbkey" : datalist.dbkey,
				"bean.isauto" : datalist.isauto
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			tablename : dataJson.id,
			entityclass : datalist.entityclass,
			dbkey : datalist.dbkey,
			isauto : datalist.isauto
        }; 
}
