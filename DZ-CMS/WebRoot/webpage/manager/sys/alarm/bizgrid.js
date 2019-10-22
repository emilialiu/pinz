$(function() {
	// grid参数
	$("#sys_alarm_grid").data("gridOptions", {
		height : 525,//parent.getClientHeights(),
		url :  rootpath + '/manager/sys/alarm/list.action?bean.alarmid='+alarmid,
		colNames : [  '操作','告警ID', sys_alarm_name,'告警类别', sys_alarm_type, sys_alarm_level, sys_alarm_publish, qt_assaysample_memo ],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					 {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:myformatter},
					 {name : 'alarmid',index : 'alarmid',width : 80,sorttype : "int",editable : true,hidden : true}, 
					 {name : 'alarmtitle',index : 'alarmtitle',fixed : true,width : 460,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}},
					 {name : 'alarmtype',index : 'alarmtype',fixed : true,width : 65,sorttype : 'string',search:false,hidden:true},
					 {name : 'alarmtypename',index : 'alarmtypename',fixed : true,width : 65,sorttype : 'string',search:false}, 
					 {name : 'alarmlevel',index : 'alarmlevel',fixed : true,width : 65,sorttype : 'string',search:false
						 ,formatter:function(cellvalue, options, rowobj){
								if(rowobj.alarmtype=="GJLB002" && cellvalue!=""){
									return "<div style='background:red;width:100%;height:100%'></div>";
								}else{
									return "<div style='background:yellow;width:100%;height:100%'></div>";
								}
								
							}}, 
					 {name : 'addtime',index : 'addtime',fixed : true,width : 140,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}, 	
					 {name : 'memo',index : 'memo',fixed : true,width : 380,sorttype : 'string',searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
				],
/*	    onSelectRow : function(rowid,status) {
			var rowData = $("#sys_alarm_grid").jqGrid('getRowData',rowid);
			//var funccode=rowData.alarmid;
			var funccode='sys_alarm_grid';
			parent.closeTab("tab_" + funccode);
			parent.addTabs({id:funccode,title:home_alarmDetail,close:true,url:rowData.handleaddres});
		},*/
		processbtncol:2,//第几个列
		isedit:true,//是否可编辑列表
		advsearch:true
	});
	initgrid("sys_alarm");
});

function myformatter(cellvalue,options,rowobj){
	var handle = "<div class='ui-pg-div' style='float: left; margin-left: 8px;' title='处理' onclick=handle('sys_alarm','"+ options.rowId
		+ "')><span class='ui-icon  fa-pencil-square-o'/></div>";
	return handle;
}

/**
 * 处理事件
 * @param _bizname
 * @param rownum
 */
function handle(_bizname,rownum){
	var str = $("#"+_bizname+"_grid").jqGrid("getRowData", rownum); // 根据行ID，获取选中行的数据 
	$.ajax({
		url : rootpath + "/webpage/biz/pub/alarm/doModifySave.action",
		cache : false,
		data : {"bean.alarmid":str.alarmid},
		type : 'post',
		success : function(data) {
			var dataJson = eval(data);
			if(dataJson.success) {
				parent.alertMsg("信息处理成功!");
				//修改保存完成
				$("#"+_bizname+"_grid").trigger('domi.modifysave.after');
				$("#"+_bizname+"_grid").setGridParam({page:1}).trigger("reloadGrid");
			} else {
				parent.alertErrorMsg("操作失败:"+dataJson.errorMessage);
				$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
				$("#"+_bizname+"_grid").setGridParam({page:1}).trigger("reloadGrid");
			}
		},
		error : function(data) {
			parent.alertErrorMsg("系统ajax交互错误 ");
			$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
			$("#"+_bizname+"_grid").setGridParam({page:1}).trigger("reloadGrid");
		}
	});
}