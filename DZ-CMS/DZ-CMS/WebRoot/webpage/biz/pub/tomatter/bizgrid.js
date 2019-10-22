$(function() {
	// grid参数
	$("#t_pub_tomatter_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/pub/tomatter/list.action',
		colNames : [ 
					'操作',
					'id',
					'mid',
					'事项名称',
					'发起时间',
					'事项状态',
					'事项状态',
					'备注',
					'链接地址',
					'处理时间',
					'处理人'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             	{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'matterid',index : 'matterid',width : 60,sorttype : "varchar(42)",editable : false,hidden : true},
						{name : 'mid',index : 'mid',width : 60,sorttype : "varchar(42)",editable : false,hidden : true},
						{name : 'mattername',index : 'mattername',fixed : true,width : 200,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'addtime',index : 'addtime',fixed : true,width : 150,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mattertype',index : 'mattertype',hidden:true,fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'mattertypename',index : 'mattertypename',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 200,sorttype : 'text',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'linkurl',index : 'linkurl',fixed : true,hidden:true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'handletime',index : 'handletime',hidden : true,fixed : true,width : 100,sorttype : 'datetime',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'handlename',index : 'handlename',hidden : true,fixed : true,width : 100,sorttype : 'varchar(42)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		//viewurl : "/webpage/biz/pub/tomatter/doModify.action",
		//addurl : "/webpage/biz/pub/tomatter/doAdd.action",
		//modifyurl : "/webpage/biz/pub/tomatter/doModifySave.action",
		//deleteurl : "/webpage/biz/pub/tomatter/delete.action"
		
	});
	
	initgrid("t_pub_tomatter");
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.matterid" : str.matterid};
}
function getProcessIcons(_bizname,rownum){
   var str = $("#"+_bizname+"_grid").jqGrid("getRowData", rownum); // 根据行ID，获取选中行的数据
  var 	handle =  "<div class='ui-pg-div' style='float: left; margin-left: 8px;' title='处理事件' onclick=handle('"+_bizname+"','"+ rownum
	+ "')><span class='ui-icon  fa-pencil-square-o'/></div>";
  var 	receive =  "<div class='ui-pg-div' style='float: left; margin-left: 8px;' title='接受事件' onclick=receive('"+_bizname+"','"+ rownum
	+ "')><span class='ui-icon  fa-check-square-o'/></div>";
  if(str.mattertype == 'CLZT001'){
	  return  receive;
  }else{
	  return handle;
  }
}
function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return "?bean.matterid="+str.matterid;
}
/**
 * 接受事件
 * @param _bizname
 * @param rownum
 */
function receive(_bizname,rownum){
	   var str = $("#"+_bizname+"_grid").jqGrid("getRowData", rownum); // 根据行ID，获取选中行的数据 
	   
		$.ajax({
			url : rootpath + "/webpage/biz/pub/tomatter/doModifySave.action",
			cache : false,
			data : {"bean.matterid":str.matterid,"bean.mattertype":"CLZT003"},
			type : 'post',
			success : function(data) {
				var dataJson = eval(data);
				if(dataJson.success) {
					parent.alertMsg("信息接收成功!");
					//修改保存完成
					$("#"+_bizname+"_grid").trigger('domi.modifysave.after');
					load(_bizname);
				} else {
					parent.alertErrorMsg("操作失败:"+dataJson.errorMessage);
					$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
					load(_bizname);
				}
			},
			error : function(data) {
				parent.alertErrorMsg("系统ajax交互错误 ");
				$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
				load(_bizname);
			}
		});
}
/**
 * 处理事件
 * @param _bizname
 * @param rownum
 */
function handle(_bizname,rownum){
	   var str = $("#"+_bizname+"_grid").jqGrid("getRowData", rownum); // 根据行ID，获取选中行的数据 
	   
		$.ajax({
			url : rootpath + "/webpage/biz/pub/tomatter/doModifySave.action",
			cache : false,
			data : {"bean.matterid":str.matterid,"bean.mattertype":"CLZT002"},
			type : 'post',
			success : function(data) {
				var dataJson = eval(data);
				if(dataJson.success) {
					parent.alertMsg("信息处理成功!");
					//修改保存完成
					$("#"+_bizname+"_grid").trigger('domi.modifysave.after');
					load(_bizname);
				} else {
					parent.alertErrorMsg("操作失败:"+dataJson.errorMessage);
					$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
					load(_bizname);
				}
			},
			error : function(data) {
				parent.alertErrorMsg("系统ajax交互错误 ");
				$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
				load(_bizname);
			}
		});
}
//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.matterid" : datalist.matterid,
			"bean.mattername" : datalist.mattername,
			"bean.addtime" : datalist.addtime,
			"bean.mattertype" : datalist.mattertype,
			"bean.memo" : datalist.memo,
			"bean.linkurl" : datalist.linkurl,
			"bean.handletime" : datalist.handletime,
			"bean.handlename" : datalist.handlename
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				matterid : dataJson.id,
				mattername : datalist.mattername,
				addtime : datalist.addtime,
				mattertype : datalist.mattertype,
				memo : datalist.memo,
				linkurl : datalist.linkurl,
				handletime : datalist.handletime,
				handlename : datalist.handlename
	}; 
}
function load(_bizname){
	 $("#"+_bizname+"_grid").setGridParam({page:1}).trigger("reloadGrid");      
}