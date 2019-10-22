	$(function() {
		// grid参数
		$("#act_model_grid").data("gridOptions",
		{	height :parent.getClientHeights(),
			url : rootpath+ '/manager/model/list.action',
			colNames : [ pub_operation,sys_model_id,sys_model_key, sys_model_name,sys_model_version, sys_model_createTime, sys_model_lastUpdateTime ],// 列显示名称
			colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
							// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
							{name : 'processbtn',index : '',width : 150,fixed : true,sortable : false,resize : false,search : false,formatter : btnformat}
							, {name : 'id',index : 'id',width : 100,sorttype : "int",editable : false,fixed : true}
							, {name : 'key',index : 'key',width : 80,sorttype : "string",editable : false,fixed : true}
							,{name : 'name',index : 'name',fixed : true,width : 150,sorttype : 'string',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
							, {name : 'version',index : 'version',fixed : true,width : 100,sorttype : 'string',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne' ]}}
							, {name : 'createTime',index : 'createTime',fixed : true,width : 160,sortable : false,editable : false,searchoptions : {sopt : [ 'cn' ]},edittype : "textarea",editoptions : {rows : "2",cols : "10"}}
							, {name : 'lastUpdateTime',index : 'lastUpdateTime',width : 170,fixed : true}
						    ],
							// processbtncol:2,//操作按钮放在第几个列
							isedit : true,// 是否可编辑列表
							advsearch : true,
							multiSort : true,
							viewurl : "/manager/activity/doModify.action",
							deleteurl : "/manager/model/delete.action?actiontype=delete",
							addurl : "/manager/model/doAdd.action",

		});

		initgrid("act_model");
		initvalid("act_model");
		$("#act_model_grid_pager_left td").slice(1, 3).hide();

		// 弹出表单
		/*$("#act_model_info").dialog({
			autoOpen : false,
			modal : true,
			resizable : true,
			width : 800,
			buttons : { // 为对话框添加按钮
				"取消" : function() {
					$("#act_model_info").dialog("close");
				},
				"创建" : function() {
					LdoAddSave('act_model');
				}
			}
		});
		
		// 弹出表单
		$("#act_workflow_info").dialog({
			autoOpen : false,
			modal : true,
			resizable : true,
			width : 800,
			buttons : { // 为对话框添加按钮
				"取消" : function() {
					$("#act_workflow_info").dialog("close");
				},
				"部署" : function() {
					wrokAddSave();
				}
			}
		});*/
		
	});

function btnformat(cellvalue, options, rowobj) {
	edit = "<a class='ui-pg-div' style='float: left; margin-left: 5px;' href='"+rootpath+"/service/editor?id="+rowobj.id+"' target=\"_blank\" title='"+sys_model_btnedit+"' ><span class='ui-icon fa-edit'/></a>";
	edit+= "<div class='ui-pg-div' style='float: left; margin-left:5px;' onclick=Deployment('"+rowobj.id+"')  title='"+sys_model_deploy+"' ><span class='ui-icon fa fa-cog'/></div>";
	edit+= "<div class='ui-pg-div' style='float: left; margin-left:5px;' onclick=exportEmp('"+rowobj.id+"')  title='"+sys_model_export+"' ><span class='ui-icon fa-file-o'/></div>";
	edit+= "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='"+button_delete+"' onclick=_doopendelete('act_model','"+ rowobj.id
			+ "')><span class='ui-icon ui-icon-trash'/></div>"
	return edit;
}

/**
 * 模型部署
 * @param id
 */
function Deployment(id){
	$.ajax({  
        url : rootpath+'/manager/model/deploy.action',
        data : {"modelId":id},  
        cache : false,  
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {
                alertMsg(dataJson.id);  
            } else {  
            	alertMsg("操作失败"+dataJson.errormessage); 
            }  
        }  
    });  
}

/**
 * 模型XML下载
 * @param id
 */
function exportEmp(id){
	location.href=rootpath+'/manager/model/export.action?modelId='+id;
	
}
//新增保存
var LdoAddSave = function(_bizname) {
	//检查不成功，跳出
	if(!$("#"+_bizname+"_form").Validform().checkForm()) return;
	
    var consoleDlg = $("#"+_bizname+"_info");  
	//新增保存开始
    consoleDlg.trigger('domi.addsave.before');
    
    //提取表单参数
    var params = getInfoData(_bizname);
   
    $.ajax( {  
        url : rootpath+'/manager/model/doAddSave.action',  
        data : params,  
        cache : false,
        type:'post',
        async:false,
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {  
                gridrowdata = initGridRowData(_bizname,data);
                  
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
                //window.location.href=rootpath+"/service/editor?id="+dataJson.id;
                window.open(rootpath+"/service/editor?id="+dataJson.id,'_blank');
              

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
/**
 * 流程添加部署
 */
function doimport(){
	parent.createwindow('act_workflow','/manager/model/doImport.action',"导入部署",800,200,true);
}

