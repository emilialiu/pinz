var scripts = [null, null];

$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	$(function() {
		// grid参数
		$("#teamstaff_grid").data("gridOptions", {
			height : 300,
			url :  rootpath + '/biz/sm/teamstaff/list.action',
			colNames : [ 
						'操作',
							'班组ID',
							'员工ID'
						],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
							{name : 'teamid',index : 'teamid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
							{name : 'staffid',index : 'staffid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
						],
			processbtncol:2,//操作按钮放在第几个列
			isedit:false,//是否可编辑列表
			advsearch:true,
			viewurl : rootpath+"/biz/sm/teamstaff/doModify.action",
			addurl : rootpath+"/biz/sm/teamstaff/doAddSave.action",
			modifyurl : rootpath+"/biz/sm/teamstaff/doModifySave.action?actiontype=modifysave",
			deleteurl : rootpath+"/biz/sm/teamstaff/delete.action?actiontype=delete"
			
		});
		
		initgrid("teamstaff");
		initvalid("teamstaff");
	});
});
//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
				"bean.teamid" : datalist.teamid,
				"bean.staffid" : datalist.staffid
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			teamid : dataJson.teamid,
			staffid : dataJson.staffid,
        }; 
}
