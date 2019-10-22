$(function() {
	// grid参数
	$("#t_sc_teamtechprocess_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/teamtechprocess/list.action',
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
		multiselect:true,
		multiSort:true,
		//表格加载完后选中数据
		gridComplete: function(){
			if(position.length > 0){
				for(var i in position){
					$("#t_sc_teamtechprocess_grid").jqGrid("setSelection",position[i]);	
				}
			}
		},
	});
	
	initgrid("t_sc_teamtechprocess");
	$("#t_sc_teamtechprocess_grid_pager_left td").slice(0, 2).hide();
	$("#t_sc_teamtechprocess_grid_pager_center td").slice(0, 8).hide();
	
	$("#t_sc_teamtechprocess_grid").jqGrid('setGridParam',{'rowNum':500});
});
//记录选中的位置
var position=[];
//点击节点调用的函数
function getNodeInfo(id){
	position=[];
	$.ajax({
		type : "post",
		cache : false,
		async : false,
		url : rootpath+"/webpage/biz/sc/teamtechprocess/getPosition.action",
		data : "bean.procid="+id,
		success : function(positions) {
			
			no=$.trim(positions);
		}
	});
	if(no!=''){
		position=no.split(',');
	}
	jQuery("#t_sc_teamtechprocess_grid").trigger("reloadGrid");	
}
//重写新增方法
function _openDialog4Adding(_bizname){
	//获取树节点的信息
	var srcNode = BOTree1.getSelectedNodes();
	var procid=BOTree1.getSelectedNodes()[0].id;
	//选择顶级不能维护对应的班组
	if(!srcNode[0].isChild){
		alertMsg("请选择工序的叶子节点!");
		return;
	}
	var teamids='';
	var data = [];
	var rowdata;
	var selectedRowIds = $("#t_sc_teamtechprocess_grid").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedRowIds.length;i++){
		rowdata = $("#t_sc_teamtechprocess_grid").jqGrid("getRowData", selectedRowIds[i]);
		data.push(rowdata);
	}
	for(var i=0;i<data.length;i++){		
		teamids+=(data[i].teamid+',');
	}
	teamids=teamids.substring(0,teamids.length-1);
	
	var params = { 
			"bean.teamid":teamids,
			"bean.procid":procid	
		};
	
	//ajax产生消息
	$.ajax({
		url : rootpath+"/webpage/biz/sc/teamtechprocess/addProcTeam.action",
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			var info=$.trim(data);
            if(info=='n') { 
                alertMsg("配置成功!");  
            }else if(info=='y'){
            	alertMsg("配置成功!");  
            }else {  
            	alertErrorMsg(dataJson.errormessage); 
            }  
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
	
	teamids='';
	procid='';
}
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.teamid" : str.teamid};
		return {"bean.procid" : str.procid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.teamid="+str.teamid;
		return "?bean.procid="+str.procid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.teamid" : datalist.teamid,
			"bean.procid" : datalist.procid,
			"bean.tdeptid" : datalist.tdeptid
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				teamid : dataJson.id,
				procid : dataJson.id,
				tdeptid : datalist.tdeptid
	}; 
}
