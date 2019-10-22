//var srcNode = BOTree1.getSelectedNodes();
$(function() {
	// grid参数
	$("#t_sc_techprocess_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/webpage/biz/sc/techprocess/list.action?bean.techid='+'1',
		colNames : [ 
					pub_operation,
					'工艺ID',
					'工序ID',
					sc_techprocess_processname,
					sc_techprocess_parentprocess,
					'所属矿山',
					sc_techprocess_isplan,
					sc_techprocess_isaccept,
					sc_techprocess_serialno,
					'创建人',
					'创建日期',
					'修改人',
					'修改日期',
					'备注'
					],//列显示名称 formatter: "checkbox",formatoptions:{disabled:false},  
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:60, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
						{name : 'techid',index : 'techid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'procid',index : 'procid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'processname',index : 'processname',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
						{name : 'parentprocess',index : 'parentprocess',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
						{name : 'tdeptid',index : 'tdeptid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isplan',index : 'isplan',fixed : true,width : 100,sorttype : 'char(1)',editable : false,search:false,formatter: "checkbox",formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'isaccept',index : 'isaccept',fixed : true,width : 100,sorttype : 'char(1)',editable : false,search:false,formatter: "checkbox",formatoptions:{disabled:false},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'serialno',index : 'serialno',fixed : true,width : 100,sorttype : 'int(11)',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createid',index : 'createid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createdate',index : 'createdate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifyid',index : 'modifyid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifydate',index : 'modifydate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
					],
					onSelectRow: function(rowid){
					 	doeditmodify("t_sc_techprocess",rowid);
					},
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		multiSort:true,
		viewurl : "/webpage/biz/sc/techprocess/doModify.action",
		addurl : "/webpage/biz/sc/techprocess/doAdd.action",
		modifyurl : "/webpage/biz/sc/techprocess/doModifySave.action",
		deleteurl : "/webpage/biz/sc/techprocess/delete.action"
		
	});
	
	initgrid("t_sc_techprocess");
	$("#t_sc_techprocess_grid_pager_left td").slice(0, 4).hide();
	function btnformat(cellvalue,options,rowobj){
		del = "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('t_sc_techprocess','"+ options.rowId
			+ "')><span class='ui-icon ui-icon-trash'/></div>";
		return del;
	}
});

//变成可编辑
function doeditmodify(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").editRow(selectedRowId);
   	$("#"+_bizname+"_grid").trigger('domi.modify.before');
	$("#"+_bizname+"_grid").trigger('domi.modify.after');
}

//自定义查询		
if($("#t_sc_techprocess_text_search").length==1){
	$("#t_sc_techprocess_text_search").keydown(function(e){
		if(e.keyCode==13){
			dosimplesearch("t_sc_techprocess");
		}
	});
}
if($("#t_sc_techprocess_btn_search").length==1){
	$("#t_sc_techprocess_btn_search").click(function() {
		dosimplesearch("t_sc_techprocess");
	});
}
function dosimplesearch(_bizname){
	//获取树节点的信息
	var srcNode = BOTree1.getSelectedNodes();
	$("#t_sc_techprocess_grid").jqGrid('setGridParam', {
		url : encodeURI(rootpath + '/webpage/biz/sc/techprocess/list.action?bean.param='+$("#t_sc_techprocess_text_search").val()+'&bean.techid='+srcNode[0].id),
		page : 1
	}).trigger("reloadGrid"); 
}
//点击保存按钮
function saveAll(){
	//获取当前grid中的所有数据
	 var obj = $("#t_sc_techprocess_grid").jqGrid("getRowData");
	 var techid='';
	 var procid='';
	 var isplan='';
	 var isaccept='';
	 var serialno='';
	 for(var i=0;i<obj.length;i++){
		//如果行处于编辑状态$("#"+(i+1)+"_serialno").val()不为undefined，否则为undefined
		 if($("#"+(i+1)+"_serialno").val()==undefined){
			 if(obj[i].serialno==""){
				 alertMsg("序号必填！");
				 return;
			 }
			 if(isNaN(Number(obj[i].serialno))){  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。 
				 alertMsg("序号必需是数字！");
				 return; 
		     }
			 serialno+=(obj[i].serialno+',');
		 }else{
			 if($("#"+(i+1)+"_serialno").val()==""){
				 alertMsg("序号必填！");
				 return;
			 }
			 if(isNaN(Number($("#"+(i+1)+"_serialno").val()))){  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。 
				 alertMsg("序号必需是数字！");
				 return; 
		     }
			 serialno+=($("#"+(i+1)+"_serialno").val()+',');
		 }
		 
		 techid+=(obj[i].techid+',');
		 procid+=(obj[i].procid+',');
		 if(obj[i].isplan=='Yes'){
			 isplan+=('1'+',');
		 }else{
			 isplan+=('0'+',');
		 }
		 if(obj[i].isaccept=='Yes'){
			 isaccept+=('1'+',');
		 }else{
			 isaccept+=('0'+',');
		 }
	 }
	 techid=techid.substring(0,techid.length-1);
	 procid=procid.substring(0,procid.length-1);
	 isplan=isplan.substring(0,isplan.length-1);
	 isaccept=isaccept.substring(0,isaccept.length-1);
	 serialno=serialno.substring(0,serialno.length-1);
	 var params = { 
				"bean.techid":techid,
				"bean.procid":procid,	
				"bean.isplan":isplan,
				"bean.isaccept":isaccept,
				"bean.serialno":serialno
			};
			//ajax产生消息
			$.ajax({
				url : rootpath+"/webpage/biz/sc/techprocess/updateTechProc.action",
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
//选择工序
function selectGx(){
	//获取树节点的信息
	var srcNode = BOTree1.getSelectedNodes();
	//选择顶级不能维护对应的工序
	if(!srcNode[0].isChild){
		alertMsg("请选择工艺的叶子节点!");
		return;
	}
	parent.openwin("techprocess_selectgx","/webpage/pub/select/process/main.jsp?multiselect=true&techid="+srcNode[0].id,sc_techprocess_processselection,805,615);
	parent.PageObject.itemMap['techprocess_selectgx'].callback = function(no){
		selectGxCallBack(no,srcNode[0].id);
	}
}
//选择工序后的回掉函数
function selectGxCallBack(arr,techid){
	var procid='';
	for(var i=0;i<arr.length;i++){
		procid+=(arr[i].procid+',');
	}
	procid=procid.substring(0,procid.length-1);
	var params = { 
		"bean.techid":techid,
		"bean.procid":procid	
	};
	//ajax产生消息
	$.ajax({
		url : rootpath+"/webpage/biz/sc/techprocess/addTechProc.action",
		cache : false,
		data : params,
		async: false,
		success : function(data) {
			var info=$.trim(data);
            if(info=='n') { 
                alertMsg("您没有配置任何工序!");  
            }else if(info=='y'){
            	 alertMsg("配置工序成功!");  
	             $("#t_sc_techprocess_grid").trigger("reloadGrid");
            }else {  
            	alertErrorMsg(dataJson.errormessage); 
            }  
		},
		error : function(data) {  
			alertMsg("系统ajax交互错误 ");  
        }
	}); 
}
//工艺树的点击事件
function getNodeInfo(techid){
	$("#t_sc_techprocess_grid").jqGrid('setGridParam', {
		url : rootpath + '/webpage/biz/sc/techprocess/list.action?bean.techid='+techid,
		page : 1
	}).trigger("reloadGrid"); 
}
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return {
				"bean.techid" : str.techid,
				"bean.procid" : str.procid
				};
}

function getParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
		return "?bean.techid="+str.techid;
		return "?bean.procid="+str.procid;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			"bean.techid" : datalist.techid,
			"bean.procid" : datalist.procid,
			"bean.tdeptid" : datalist.tdeptid,
			"bean.serialno" : datalist.serialno,
			"bean.isplan" : datalist.isplan,
			"bean.isaccept" : datalist.isaccept,
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
				procid : dataJson.id,
				tdeptid : datalist.tdeptid,
				serialno : datalist.serialno,
				isplan : datalist.isplan,
				isaccept : datalist.isaccept,
				createid : datalist.createid,
				createdate : datalist.createdate,
				modifyid : datalist.modifyid,
				modifydate : datalist.modifydate,
				memo : datalist.memo
	}; 
}
