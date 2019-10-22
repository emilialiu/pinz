var bcstr={};
$(function() {
	//班次类别下拉列表值
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'BC'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			bcstr = eval('({'+data+'})');				
		}  
	 });
	
	// grid参数
	$("#classcfg_grid").data("gridOptions", {
		height : parent.getClientHeights(),
		url :  rootpath + '/biz/sm/classcfg/listdata.action',
		colNames : [ 
					pub_operation,
						'班次配置ID',
						'项目部ID',
						
						sm_classcfg_dept,							
						qt_samplebook_classes,
						sm_classcfg__start,
						sm_classcfg__end,
					
						'创建人',
						'创建时间',
						'修改人',
						'修改时间'
						
					],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						{name : 'classcfgid',index : 'classcfgid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : true,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},			
						{name : 'deptid',index : 'deptid',fixed : true,width : 150,sorttype : 'varchar(100)',editable : true,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						
						{name : 'demo',index : 'demo',fixed : true,width : 150,sorttype : 'varchar(40)',editable : true,edittype:'text', editrules:{ required:true},
								editoptions:{readonly:true,dataEvents:[{type:'click',fn:showDept}]},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},							
						{name : 'classtype',index : 'classtype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : true,edittype:'select', editrules:{ required:true},editoptions:{dataEvents:[{type:'change',fn:setZero}],value:bcstr},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]},
							formatter:function(v){return bcstr[v];}, unformat:function(v){for(k in bcstr){if(v == bcstr[k])return k;}}},
						{name : 'starttime',index : 'starttime',fixed : true,width : 120,sorttype : 'time',editable : true,edittype:'text', editrules:{ required:true},
								editoptions:{readonly:true,dataEvents:[{type:'click',fn:showDate}]},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'endtime',index : 'endtime',fixed : true,width : 120,sorttype : 'time',editable : true,edittype:'text', editrules:{ required:true,custom:true,custom_func:checktime},
								editoptions:{readonly:true,dataEvents:[{type:'click',fn:showDate}]},searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
								
																	
						{name : 'createid',index : 'createid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : true,  editrules:{custom:true,custom_func:timeisover},hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'createdate',index : 'createdate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifyid',index : 'modifyid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
						{name : 'modifydate',index : 'modifydate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
						
					],
		processbtncol:2,//操作按钮放在第几个列
		isedit:true,//是否可编辑列表
		advsearch:true,
		modifyurl : "/biz/sm/classcfg/doModifySave.action?actiontype=modifysave",
		deleteurl : "/biz/sm/classcfg/delete.action?actiontype=delete"
		
	});
	
	initgrid("classcfg");
	$("#classcfg_grid_pager_left td").slice(0, 3).hide();
});

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
		classtype:$('#'+selectedRowId+"_classtype").val()
	});
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
				"bean.classcfgid" : datalist.classcfgid,
				"bean.deptid" : datalist.deptid,
				"bean.classtype" : datalist.classtype,
				"bean.starttime" : datalist.starttime,
				"bean.endtime" : datalist.endtime,
				"bean.createid" : datalist.createid,
				"bean.createdate" : datalist.createdate,
				"bean.modifyid" : datalist.modifyid,
				"bean.modifydate" : datalist.modifydate,
				"bean.demo" : datalist.demo
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
			classcfgid : dataJson.classcfgid,
			deptid : datalist.deptid,
			classtype : datalist.classtype,
			starttime : datalist.starttime,
			endtime : datalist.endtime,
			createid : datalist.createid,
			createdate : datalist.createdate,
			modifyid : datalist.modifyid,
			modifydate : datalist.modifydate,
			demo : datalist.demo
        }; 
}
//删除时
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {
        "bean.classcfgid" : str.classcfgid
    };  
}
//添加一行
function addnullrow(_bizname){
	//获取表格的初始化model  
	var colModel = $("#"+_bizname+"_grid").jqGrid().getGridParam("colModel") ;  
	var cellLenth = colModel.length;
	//设置所有列可编辑（如果特定的列不可编辑，则需判断）  
	/*for (var i = 0; i < cellLenth; i++) {
		if(i==1 || i==14)
			colModel[i].editable = false;
		else
			colModel[i].editable = true;
	}*/
    var newRow = JSON.stringify(colModel);
	//获得所有行的ID数组
    var ids = $("#"+_bizname+"_grid").jqGrid("getDataIDs");
    //如果jqgrid中没有数据 定义行号为1 ，否则取当前最大行号+1
    var newrowid = (ids.length ==0 ? 1: Math.max.apply(Math,ids)+1);
    //设置grid单元格不可编辑 （防止在添加时，用户修改其他非添加行的数据）  
    $("#"+_bizname+"_grid").setGridParam({cellEdit:false});
    //将新行追加到表格头部  
    $("#"+_bizname+"_grid").jqGrid("addRowData", newrowid, newRow, "first");
    //设置grid单元格可编辑（防止追加行后，可编辑列无法编辑）  
    $("#"+_bizname+"_grid").jqGrid("editRow", newrowid, false);
    updateEditIcons(_bizname);
    _doeditmodify(_bizname,newrowid);
    $("#"+_bizname+"_grid").setSelection(newrowid,true);

}
//重写添加方法
var _openDialog4Adding = function(_bizname) {  
	if(_bizname=='classcfg'){
		addnullrow(_bizname);
	}else{
		var consoleDlg = $("#"+_bizname+"_info");  
	    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane"); 
	    
	    $("#"+_bizname+"_form").Validform().resetForm();
	    consoleDlg.find(".info").css('display','none');
	    consoleDlg.find("input").removeAttr("disabled").val("");  
	    consoleDlg.find("select").removeAttr("disabled").val("");
	    consoleDlg.find("textarea").removeAttr("disabled").val("");
	    
	    consoleDlg.trigger('domi.add.before');
	    
	    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
	    dialogButtonPanel.find("button:contains('创建')").show();  
	    dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
	    
	    consoleDlg.dialog("option", "title", "创建信息").dialog("open"); 
	    consoleDlg.trigger('domi.add.after');
	}
    
}; 

//重写打开编辑
function _doeditmodify(_bizname,selectedRowId) {
	if(_bizname=='classcfg'){
		$("#"+_bizname+"_grid").editRow(selectedRowId);
	   	$("#"+_bizname+"_grid").trigger('domi.modify.before');

		col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").hide();
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").show();
		
		//筛选班次信息
		getBcByDeptId(selectedRowId,$("#"+selectedRowId+"_deptid").val(),$("#"+selectedRowId+"_classtype")[0],"");
		$("#"+_bizname+"_grid").trigger('domi.modify.after');
	}else{
		$("#"+_bizname+"_grid").editRow(selectedRowId);
	   	$("#"+_bizname+"_grid").trigger('domi.modify.before');

		col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").hide();
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").show();
		$("#"+_bizname+"_grid").trigger('domi.modify.after');
	}
	
}
//显示部门
function showDept(event){
	event = event ? event : windpw.event;
	var obj = event.srcElement ? event.srcElement : event.target;
	var $obj = $(obj);
	var rowid = ($(obj).attr("id")).split("_")[0];
	selectdept(rowid);
}

//选择部门
function selectdept(rowid){
	parent.openwin("selectdept","/pub/select/dept/selectDept.action",button_add,325,450);
	parent.PageObject.itemMap['selectdept'].callback = function(no){
		selectaccdeptCallBack(no,rowid);
	}
}
function selectaccdeptCallBack(arr,rowid){

	//联动改变    筛选班次
	var resl=getBcByDeptId(rowid,arr[0].id,$("#"+rowid+"_classtype")[0],"");
	if(resl){
		$("#"+rowid+"_deptid").val(arr[0].id);
		$("#"+rowid+"_demo").val(arr[0].name);
	}

}
//显示时间
function showDate(){
	WdatePicker({dateFmt:'HH:mm:ss'});
}

//显示时间
function setZero(){
	//正在操作的哪一行
	var selectedRowId = $("#classcfg_grid").jqGrid("getGridParam", "selrow"); 
	var classtype=$("#"+selectedRowId+"_classtype").val();
	if(classtype=='BC001'){
		var starttime=$("#"+selectedRowId+"_starttime").val("00:00:00");
		var endtime=$("#"+selectedRowId+"_endtime").val("00:00:00");
	}
	
}

//时间字段规则验证
function checktime(value, colname) {
	//正在操作的哪一行
	var selectedRowId = $("#classcfg_grid").jqGrid("getGridParam", "selrow"); 
	var starttime=$("#"+selectedRowId+"_starttime").val();
	var endtime=$("#"+selectedRowId+"_endtime").val();
	if(starttime>endtime){
		 return [false,"开始时间应小于结束时间!"];
	}else{
		return [true,""];
	}
		 
}
//判断时间是否重叠规则验证
function timeisover(value, colname) {
	var reval=0;
	var selectedRowId = $("#classcfg_grid").jqGrid("getGridParam", "selrow"); 
	var deptid=$("#"+selectedRowId+"_deptid").val();
	var classcfgid=$("#"+selectedRowId+"_classcfgid").val();	
	var start=$("#"+selectedRowId+"_starttime").val();
	var end=$("#"+selectedRowId+"_endtime").val();
 	
	//得到其它时间  并比较
	$.ajax({
		url : rootpath + "/biz/sm/classcfg/timelist.action", 
		data : {"bean.deptid" : deptid,"bean.classcfgid":classcfgid},
		async : false,  
		cache : false,  
		success : function(data) { 
			var dataJson = eval(data);			
			if(dataJson.length>0){
				for(var i = 0;i<dataJson.length;i++){
					var starttime=dataJson[i].starttime;
					var endtime=dataJson[i].endtime;	
					if((start>starttime&&start>=endtime)||(end<=starttime&&end<endtime)){					 					
					}else{				
						reval++;
						if(start>=starttime&&start<=endtime){
							//alert("去掉开始");
							$("#"+selectedRowId+"_starttime").val('');
						}
						if(end>=starttime&&end<=endtime){
							//alert("去掉结束");
							$("#"+selectedRowId+"_endtime").val('');
						}
						if(start<=starttime&&end>=endtime){
							//alert("去掉all");
							$("#"+selectedRowId+"_starttime").val('');
							$("#"+selectedRowId+"_endtime").val('');
						}
					}					
				}
			}			
				
		},
		error : function(data){
			reval=-1;
			alert("ajax交互错误");
		}
	});
	
	if(reval==0){
		return [true,""];
	}else{
		return [false,"此修改会使该项目部班次时间重叠!"];
	}
	
}

/**
 * 筛选设置班次选项 
 * @param selectedRowId 作为参数的当前操作行ID
 * @param deptid 作为参数的deptid
 * @param objSelect 当前控件
 * @param selectValue 当前控件需要选中的值
 */

//筛选设置班次选项
function getBcByDeptId(selectedRowId,deptid,objSelect,selectValue){
	
	var resl=true;
	var isMA='';//判断是新增还是修改
	var value='';//班次实际值
	var text='';//班次显示值
	var classcfgid=$("#"+selectedRowId+"_classcfgid").val();
	if(classcfgid=='undefined'||classcfgid==null||classcfgid==""){
		isMA='A';
	}else{
		isMA='M';
	}
	$.ajax({
		url : rootpath + "/biz/sm/classcfg/bclist.action", 
		data : {"bean.deptid" : deptid},
		async : false,  
		cache : false,  
		success : function(data) { 
			var dataJson = eval(data);	
			objSelect.options.length = 0;
			if(isMA=='M'){
				value = selectbcbyid(classcfgid);
				if(value!=''){			
					text=bcstr[value];
					var objOption = new Option(text,value);
					objSelect.add(objOption);
				}							
			}
			if(dataJson.length>0){
				for(var i = 0;i<dataJson.length;i++){
					var objOption = new Option(dataJson[i].text,dataJson[i].value);
					objSelect.add(objOption);
				}
				//去除select中重复的元素
				var i=0;  
				var exist=false;  
		        while(i<$("#"+selectedRowId+"_classtype option").length){   
			        var j=i+1;   
			        while(j<$("#"+selectedRowId+"_classtype option").length){
				        if($("#"+selectedRowId+"_classtype option")[i].value==$("#"+selectedRowId+"_classtype option")[j].value){   
				        	exist=true;
				            j++;   
				        }else{  
				            j++;   
				        }   
			        }  
			        i++;   
		        }  
		        if(exist){
		        	$("#"+selectedRowId+"_classtype option")[0].remove();   
		        }
			}else{				
				if(isMA=='M'){
									
				}else{
					resl=false;				
					alertMsg("该项目部班次已完成配置！请重新选择项目部！");
				}				
			}
			
			if(selectValue != null && selectValue != ''){
				objSelect.value = selectValue;
			}
		},
		error : function(data){
			alert("ajax交互错误");
		}
	});
	return resl;
}

//通过id查classtype
function selectbcbyid(classcfgid){
	var inf='';
	$.ajax({
			type : "post",
			cache : false,
			async : false,
			url : rootpath+"/biz/sm/classcfg/selectbcbyid.action",
			data : "bean.classcfgid="+classcfgid,
			success : function(info) {
				inf=$.trim(info);
				if(inf=="no"||inf==""){					
					inf='';
				}
			}
		});
	return inf;
}
//并发新增失败后，刷新数据
$("#classcfg_grid").on('domi.modifysave.failue', function() {	
	$("#classcfg_grid").trigger("reloadGrid");  
});

function _doeditcancel(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").restoreRow("" + selectedRowId + "");
	col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
	$("#"+_bizname+"_grid").trigger("reloadGrid");
}
