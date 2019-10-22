var scripts = [null, null];

$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	$(function() {
		// grid参数
		$("#act_feeborrow_grid").data("gridOptions", {
			height : 400,
			url :  rootpath + '/manager/activity/feeborrow/list.action',
			colNames : [ '操作', '借支ID', '用户id','用户名称',  '借支金额','借支日期','借支原因', '审批状态','审批状态隐藏' ],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						 {name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformat},
						 {name : 'borrowid',index : 'borrowid',width : 60,sorttype : "int",editable : true,hidden : true}, 
						 {name : 'userid',index : 'userid',fixed : true,width : 150,sorttype : 'string',editable : true,search : false,hidden:true}, 
						 {name : 'username',index : 'username',fixed : true,width : 150,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
						 {name : 'borrowamount',index : 'borrowamount',fixed : true,width : 150,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
						 {name : 'borrowdate',index : 'borrowdate',fixed : true,width : 150,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}},
						 {name : 'cause',index : 'cause',fixed : true,width : 150,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
						 {name : 'statename',index : 'statename',fixed : true,width : 150,sorttype : 'string',editable : true,searchoptions : {sopt : [ 'cn', 'eq', 'ne']}}, 
						 {name : 'state',index : 'state',width : 60,sorttype : "int",editable : true,hidden : true}
						
						],
//			processbtncol:2,//操作按钮放在第几个列
			isedit:true,//是否可编辑列表
			advsearch:true,
			multiSort:true,
			viewurl : rootpath+"/manager/activity/feeborrow/doModify.action",
			addurl : rootpath+"/manager/activity/feeborrow/doAddSave.action",
			modifyurl : rootpath+"/manager/activity/feeborrow/doModifySave.action?actiontype=modifysave",
			deleteurl : rootpath+"/manager/activity/feeborrow/delete.action?actiontype=delete"
			
		});
		
		initgrid("act_feeborrow");
		initvalid("act_feeborrow");
	});
});

function btnformat(cellvalue,options,rowobj){
	update = "<div class='ui-pg-div ui-inline-edit' style='float: left; margin-left: 8px;' title='修改' onclick=_doopenmodify('act_feeborrow','"+ options.rowId
	+ "')><span class='ui-icon ui-icon-pencil'/></div>";
	exa ="<div class='btn btn-xs btn-success' style='float: left; margin-left: 8px;' title='提交审批' onclick=doexa_('"+ options.rowId
	+ "')><span class='ace-icon fa fa-check bigger-120'/></div>";
	return update+exa;
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
	        "bean.roleid" : datalist.roleid,  
	        "bean.rolename" : datalist.rolename,  
	        "bean.memo" : datalist.memo,  
	        "bean.deptid" : datalist.deptid,  
	        "bean.acginame" : datalist.acginame
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
        	roleid : dataJson.id,  
            rolename : datalist.rolename,  
            memo : datalist.memo,  
            deptid : datalist.deptid,  
            acginame : datalist.acginame  
            
        }; 
}


// 弹出功能表单
$("#act_feeborrow_info").dialog({
	autoOpen : false,
	modal : true,
	resizable : true,
	width : 800,
	buttons : { // 为对话框添加按钮
		"取消" : function() {
			$("#act_feeborrow_info").dialog("close");
		},
		"创建" : function() {
			_doAddSave('act_feeborrow');
		}
	}
});


//新增保存
var _doAddSave = function(_bizname) {
	//检查不成功，跳出
	if(!$("#"+_bizname+"_form").Validform().checkForm()) return;
	
    var consoleDlg = $("#"+_bizname+"_info");  
	//新增保存开始
    consoleDlg.trigger('domi.addsave.before');
    
    //提取表单参数
    var params = getInfoData(_bizname);
      
    $.ajax( {  
        url : $("#"+_bizname+"_grid").data("gridOptions").addurl,  
        data : params,  
        cache : false,  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {  
                gridrowdata = initGridRowData1(_bizname,data);
                  
                var srcrowid = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
                
                if(srcrowid) {  
                    $("#"+_bizname+"_grid").jqGrid("addRowData", dataJson.id, gridrowdata, "before", srcrowid);  
                } else {
                    $("#"+_bizname+"_grid").jqGrid("addRowData", dataJson.id, gridrowdata, "first",1);  
                }  
                if($("#"+_bizname+"_grid").data("gridOptions").processbtncol!=undefined){
					if($(gridid).data("gridOptions").isedit!=undefined&&$(gridid).data("gridOptions").isedit){
						updateEditIcons(_bizname);
					}else{
						updateIcons(_bizname);
					}
				}
                
                consoleDlg.dialog("close");  

                alertMsg("信息新增成功!");  
            	//新增保存完成
                consoleDlg.trigger('domi.addsave.after');
                  
            } else {  
            	alertMsg("新增失败"+dataJson.errormessage);  
                consoleDlg.trigger('domi.addsave.failue');
            }  
        },
        error : function(data) {  
        	alertMsg("系统ajax交互错误" );  
        } 
    });  
};  

function doexa_(selectedRowId){
	var rowData = $("#act_feeborrow_grid").jqGrid('getRowData',selectedRowId);
	var rowData = $("#act_feeborrow_grid").jqGrid('getRowData',selectedRowId);
	if(rowData.state == 'SPZT001' || rowData.state == 'SPZT002'){
		alertMsg("审批中和审批通过的数据不能重新提交审批！");
		return;
	}
	setTimeout(function(){
		bootbox.confirm("确定提交审批?", function(result) {
			if(result) {
				$.ajax({  
			        url : rootpath + '/manager/activity/feeborrow/doexp.action',
			        type:'POST',
			        data : {'bean.borrowid':rowData.borrowid},  
			        cache : false,  
			        error : function(data) {  
			        	alertErrorMsg("系统ajax交互错误");  
			        },  
			        success : function(data) {  
			        	var dataJson = eval(data);
			            if(dataJson.success) { 
			            	 $("#act_feeborrow_grid").trigger("reloadGrid");
			                alertMsg("提交成功!");  
			            } else {  
			            	alertErrorMsg("提交失败:"+dataJson.errorMessage); 
			            }  
			        }  
			    });  
			}
		});
	},0);
}