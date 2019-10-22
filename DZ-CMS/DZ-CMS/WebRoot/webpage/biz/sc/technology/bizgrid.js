$(function() {
	// grid参数
	$("#t_sc_technology_grid").data("gridOptions", { 
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/technology/list.action',
		colNames : [ 
					//'操作',
					  sc_technology_techid, 
					  sc_technology_tdeptid, 
					  sc_technology_techname, 
					  sc_technology_techcode, 
					  sc_technology_technameen, 
					  sc_technology_shortname, 
					  sc_technology_parentname, 
					  sc_technology_parentid, 
					  sc_technology_isplan, 
					  sc_technology_isaccept, 
					  sc_technology_isleaf, 
					  sc_technology_parentstr, 
					  sc_technology_serialno, 
					  sc_technology_level, 
					  sc_technology_memo 
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					//{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'techid',index : 'techid',width : 100,sorttype : "varchar(40)",editable : false,hidden : true,key:true},
						{name : 'tdeptid',index : 'tdeptid',width : 60,sorttype : "varchar(40)",editable : false,hidden : true},
						{name : 'techname',index : 'techname',fixed : true,width : 200,sorttype : 'varchar(100)',editable : false,search:false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'techcode',index : 'techcode',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'technameen',index : 'technameen',fixed : true,width : 200,sorttype : 'varchar(100)',editable : false,search:false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'shortname',index : 'shortname',width : 60,sorttype : "varchar(20)",editable : false,hidden : true},
						{name : 'parentname',index : 'parentname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'parentid',index : 'parentid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isplan',index : 'isplan',fixed : true,width : 100,sorttype : 'char(1)',editable : false,formatter:ischeckPlan,formatoptions:{disabled:false},search:false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isaccept',index : 'isaccept',fixed : true,width : 100,sorttype : 'char(1)',editable : false,formatter: ischeckAccept,formatoptions:{disabled:false},search:false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isleaf',index : 'isleaf',fixed : true,width : 100,sorttype : 'char(1)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'parentstr',index : 'parentstr',width : 60,sorttype : "varchar(500)",editable : false,hidden : true},
						{name : 'serialno',index : 'serialno',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'level',index : 'level',width : 60,sorttype : "int(11)",editable : false,hidden : true},
						{name : 'memo',index : 'memo',width : 60,sorttype : "varchar(1000)",editable : false,hidden : true}
					],
		//选择某一行的点击事件
		onSelectRow: function(rowid){
		 	var rowData = $("#t_sc_technology_grid").jqGrid('getRowData',rowid);
		 	getNodeInfo(rowData);
		},
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,

		treeGrid: true,
		treeGridModel: "adjacency",
		ExpandColumn: "techcode",
		ExpandColClick: true,
		jsonReader: {
			repeatitems: true
		},
		treeReader : {  
			level_field: "level",  
			parent_id_field: "parentid",   
			leaf_field: "leaf",
			expanded_field: "expanded"  
		},
		gridComplete: function(){
			
		},
		viewurl : "/webpage/biz/sc/technology/doModify.action",
		addurl : "/webpage/biz/sc/technology/doAdd.action",
		modifyurl : "/webpage/biz/sc/technology/doModifySave.action",
		deleteurl : "/webpage/biz/sc/technology/delete.action"
	});
	initgrid("t_sc_technology");
	$("#t_sc_technology_grid_pager_left td").slice(0, 8).hide();
	
	function ischeckPlan(cellvalue,options,rowobj){	
		if(rowobj.isleaf=='1'){
			if(rowobj.isplan=='1'){
				return "<input type='checkbox' checked='checked' id='"+options.rowId+"_isplan' />";
			}else{
				return "<input type='checkbox' id='"+options.rowId+"_isplan' />";
			}
		}else{
			return '';
		}
	}
	function ischeckAccept(cellvalue,options,rowobj){	
		if(rowobj.isleaf=='1'){
			if(rowobj.isaccept=='1'){
				return "<input type='checkbox' checked='checked' id='"+options.rowId+"_isaccept' />";
			}else{
				return "<input type='checkbox' id='"+options.rowId+"_isaccept' />";
			}
		}else{
			return '';
		}
	}
});
//点击保存按钮
function saveAll(){
	//获取当前grid中的所有数据
	 var obj = $("#t_sc_technology_grid").jqGrid("getRowData");
	 var isplan='';
	 var isaccept='';
	 var techid='';
	 for(var i=0;i<obj.length;i++){
		 if(obj[i].isleaf=='1'){
			 if(document.getElementById(obj[i].techid+'_isplan').checked==true){
				 isplan+=('1'+',');
			 }else{
				 isplan+=('0'+',');
			 }
			 if(document.getElementById(obj[i].techid+'_isaccept').checked==true){
				 isaccept+=('1'+',');
			 }else{
				 isaccept+=('0'+',');
			 }
			 techid+=obj[i].techid+',';
		 }
	 }
	 isplan=isplan.substring(0,isplan.length-1);
	 isaccept=isaccept.substring(0,isaccept.length-1);
	 techid=techid.substring(0,techid.length-1);
	 var params = { 
				"bean.techid":techid,
				"bean.isplan":isplan,
				"bean.isaccept":isaccept
			};
			$.ajax({
				url : rootpath+"/webpage/biz/sc/technology/updateTech.action",
				cache : false,
				data : params,
				async: false,
				success : function(data) {
					var info=$.trim(data);
					if(info=='y'){
		            	 alertMsg("保存成功!");  
		            }else {  
		            	alertErrorMsg(dataJson.errormessage); 
		            }  
				},
				error : function(data) {  
					alertMsg("系统ajax交互错误 ");  
		        }
			}); 
}

//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {"bean.techid" : str.techid};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.techid="+str.techid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.techid" : datalist.techid,
			"bean.tdeptid" : datalist.tdeptid,
			"bean.techname" : datalist.techname,
			"bean.techcode" : datalist.techcode,
			"bean.shortname" : datalist.shortname,
			"bean.parentid" : datalist.parentid,
			"bean.isplan" : datalist.isplan,
			"bean.isaccept" : datalist.isaccept,
			"bean.isdel" : datalist.isdel,
			"bean.isleaf" : datalist.isleaf,
			"bean.parentstr" : datalist.parentstr,
			"bean.serialno" : datalist.serialno,
			"bean.createid" : datalist.createid,
			"bean.createdate" : datalist.createdate,
			"bean.modifyid" : datalist.modifyid,
			"bean.modifydate" : datalist.modifydate,
			"bean.memo" : datalist.memo
	}; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
				techid : dataJson.id,
				tdeptid : datalist.tdeptid,
				techname : datalist.techname,
				techcode : datalist.techcode,
				shortname : datalist.shortname,
				parentid : datalist.parentid,
				isplan : datalist.isplan,
				isaccept : datalist.isaccept,
				isdel : datalist.isdel,
				isleaf : datalist.isleaf,
				parentstr : datalist.parentstr,
				serialno : datalist.serialno,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate,
				memo : datalist.memo
	}; 
}
