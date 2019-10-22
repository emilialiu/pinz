var eletypeid='';
$(function() {
	// grid参数
	$("#elementtype_grid").data("gridOptions", {
		height : 400,
		url :  rootpath + '/biz/sm/elementtype/list.action',
		colNames : [ 
					'操作',
						'要素分类ID',
						'要素分类类型',
						'要素名称',
						'所属项目部',
						'创建人',
						'创建时间',
						'修改人',
						'修改时间',
						'备注'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'eletypeid',index : 'eletypeid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'eletypekind',index : 'eletypekind',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'eletypename',index : 'eletypename',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'deptid',index : 'deptid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createid',index : 'createid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createdate',index : 'createdate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifyid',index : 'modifyid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifydate',index : 'modifydate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 200,sorttype : 'varchar(1000)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		onSelectRow: function(rowid){
		 	var rowData = $("#elementtype_grid").jqGrid('getRowData',rowid);
		 	eletypeid =rowData.eletypeid;
		 	dosearchbyeletypekind(eletypeid);
		},
		viewurl : "/biz/sm/elementtype/doModify.action",
		addurl : "/biz/sm/elementtype/doAdd.action",
		modifyurl : "/biz/sm/elementtype/doModify.action",
		deleteurl : "/biz/sm/elementtype/delete.action"
	});
	
	initgrid("elementtype");
	$("#elementtype_grid_pager_center").hide();
	$("#elementtype_grid_pager_right").hide();
});

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	if(_bizname=='elementtype'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return {"bean.eletypeid" : str.eletypeid};
	}else if(_bizname=='elementproperty'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return {"bean.eleproid" : str.eleproid};
	}
}

function getParams(_bizname){
	if(_bizname=='elementtype'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return "?bean.eletypeid="+str.eletypeid;
	}else if(_bizname=='elementproperty'){
		var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
	    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	    return "?bean.eleproid="+str.eleproid;
	}
}

function dosearchbyeletypekind(eletypeid){
	jQuery("#elementproperty_grid").setGridParam({url:rootpath + '/biz/sm/elementproperty/listbyeletype.action?bean.eletypeid='+eletypeid}).trigger("reloadGrid");
}

var _openDialog4Adding = function(_bizname) {
	if(_bizname=='elementproperty'){
		var selectedRowId = $("#elementtype_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null){
			alertMsg("请先选择一个要素分类 !");
			return;
		}
		var rowData = $("#elementtype_grid").jqGrid('getRowData',selectedRowId);
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl+"?bean.eletypeid="+rowData.eletypeid;
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}else{
		var url = $("#"+_bizname+"_grid").data("gridOptions").addurl;
		parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
	}
};

function allelementproperty_import(){
	var selectedRowId = $("#elementtype_grid").jqGrid("getGridParam","selrow");
	var datalist = $("#elementtype_grid").jqGrid('getRowData', selectedRowId);
	var v_eletypekind=datalist.eletypekind;
	if(v_eletypekind==undefined||v_eletypekind=='undefined'||v_eletypekind==''){
		alertMsg("请先选择一个要素类型!");
		return;
	}else{
		$.ajax({
			url : rootpath+'/biz/sm/elementtype/allinsert.action',
			cache : false,
			data : "bean.eletypekind="+v_eletypekind,
			async: false,
			success : function(data) {
				inf = $.trim(data);
				if(inf!='no'){
					jQuery("#elementproperty_grid").setGridParam({url:rootpath + '/biz/sm/elementproperty/listbyeletype.action?bean.eletypeid='+datalist.eletypeid}).trigger("reloadGrid");
					alertMsg("批量导入成功!");
				}else{
					alertMsg("要素分类数据已存在!");
				}
			}
		});	
	}
}