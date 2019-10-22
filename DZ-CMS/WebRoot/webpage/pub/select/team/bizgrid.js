$(function() {
	// grid参数
	$("#t_sc_teamtechprocess_grid").data("gridOptions", {
		height : 160,
		url :  rootpath + '/webpage/biz/sc/teamtechprocess/list2.action?bean.procid='+procid,
		colNames : [ 
					'操作',
					'班组ID',
					'工序ID',
					'所属矿山',
					sc_teamtechprocess_teamname,
					sc_teamtechprocess_teamtypename
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter,hidden:true},
						{name : 'teamid',index : 'teamid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true},
						{name : 'procid',index : 'procid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true},
						{name : 'tdeptid',index : 'tdeptid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true},
						{name : 'teamname',index : 'teamname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false},
						{name : 'teamtypename',index : 'teamtypename',fixed : true,width : 200,sorttype : 'varchar(40)',editable : false}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		ondblClickRow:function(data){
			onSave();
		},
		multiselect:false
	});
	
	initgrid("t_sc_teamtechprocess");
//	if(multiselect){
//		$("button:contains('清空选择')", window.parent.document).hide();
//	}else{
//		$("button:contains('清空选择')", window.parent.document).show();
//	}
	//$("#t_sc_teamtechprocess_grid_pager_left td").slice(0, 5).hide();
});
