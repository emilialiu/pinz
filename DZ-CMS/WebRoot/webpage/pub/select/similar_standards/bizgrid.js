$(function() {
	// grid参数
	$("#t_dm_csdetail_grid").data("gridOptions", {
		height : 320,//parent.getClientHeights()-360,
		url :  rootpath + '/webpage/biz/dm/csdetail/standardList.action?bean.dtid='+$("#catId").val(),
		colNames : [ 
						'操作',
						'详情ID',
						'标准ID',
						'点检部位',
						'点检内容',
						'点检周期（小时)',
						'备注',
						'供应商设备id',
						'设备类型ID'
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
		             	{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,hidden:true,formatter:btnformatter},
						{name : 'detailid',index : 'detailid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'csid',index : 'csid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'position',index : 'position',fixed : true,width : 100,sorttype : 'varchar(100)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'content',index : 'content',fixed : true,width : 300,sorttype : 'varchar(200)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'period',index : 'period',fixed : true,width : 120,sorttype : 'decimal(16,2)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 300,sorttype : 'varchar(1000)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'vdevid',index : 'vdevid',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
						{name : 'dtid',index : 'dtid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiselect:multiselect=="null"?false:multiselect
	});
	
	initgrid("t_dm_csdetail");
	if(multiselect){
		$("button:contains('清空选择')", window.parent.document).hide();
		$("button:contains('Empty choice')", window.parent.document).hide();
	}else{
		$("button:contains('清空选择')", window.parent.document).show();
		$("button:contains('Empty choice')", window.parent.document).show();
	}
	$("#t_dm_csdetail_grid_pager_left td").slice(0, 5).hide();
});

function showMenu() {  
    var cityObj = $("#catName");  
    var cityOffset = $("#catName").offset();  
    $("#menuContent").css({  
        left : cityOffset.left + "px",  
        top : cityOffset.top + cityObj.outerHeight() + "px"  
   }).slideDown("fast");  

    $("body").bind("mousedown", onBodyDown); 
}  

function hideMenu() {  
    $("#menuContent").fadeOut("fast");  
    $("body").unbind("mousedown", onBodyDown);  
}  


function onBodyDown(event) {  
           if (!(event.target.id == "menuBtn"  
                    || event.target.id == "menuContent" || $(event.target)  
                   .parents("#menuContent").length > 0)) {  
                hideMenu();  
            }  
        }

function getNodeInfo(treeNode){
	var catObj = $("#catName"),catId = $("#catId");  
	if(treeNode.isChild){
		catObj.val(treeNode.name);
		catId.val(treeNode.id);
		//排除自己本身的规格型号
		if(treeNode.id!=dtid){
			var param={
				"bean.dtid":treeNode.id,
				"bean.devmodel":null	
			};
			selectDevmodel(param);
		}else{
			var param={
					"bean.dtid":treeNode.id,
					"bean.devmodel":devmodel	
				};
			selectDevmodel(param);
		}
		 hideMenu();
		 //刷新表格数据
		 $("#t_dm_csdetail_grid").jqGrid('setGridParam', {
				url : rootpath + '/webpage/biz/dm/csdetail/standardList.action?bean.dtid='+catId.val(),
				page : 1
		 }).trigger("reloadGrid");
		 
	}else{
		alertMsg("请选择叶子节点!");
	}
}
//加载规格型号
function selectDevmodel(param){
	$.ajax( {  
		url : rootpath+"/webpage/biz/dm/checkstandard/selectDevModel.action", 
		type:'POST',
		data : param,  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			devmodel1 = eval('({'+data+'})');
			//清空前面的数据
			$("#devmodel").html("");
			for(var i in devmodel1){
				var objOption = new Option(devmodel1[i],i); 
				$("#devmodel")[0].add(objOption);
			}
		}  
	 });
}
//规格型号change事件
function selectByModel(){
	//刷新表格数据
	 $("#t_dm_csdetail_grid").jqGrid('setGridParam', {
			url : rootpath + '/webpage/biz/dm/csdetail/standardList.action?bean.dtid='+$("#catId").val()+'&bean.devmodel='+$("#devmodel").val(),
			page : 1
	 }).trigger("reloadGrid");
}
