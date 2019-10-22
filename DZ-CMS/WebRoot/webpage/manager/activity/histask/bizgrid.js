var scripts = [ null, null ];

$('.page-content-area')
		.ace_ajax(
				'loadScripts',
				scripts,
				function() {
					$(function() {
						// grid参数
						$("#act_histask_grid")
								.data(
										"gridOptions",
										{
											height : 400,
											url : rootpath
													+ '/manager/activity/taskList.action',
											colNames : [ '操作',
													'任务id',
													'任务key', '任务名称','流程定义id',
													'流程实例id', '优先级'
													, '任务创建日期', '任务逾期日期'
													, '任务描述', '任务所属人','assignee'
													 ],// 列显示名称
											colModel : [ // align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
											// fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
											{
												name : 'processbtn',
												index : '',
												width : 100,
												fixed : true,
												sortable : false,
												resize : false,
												search : false,
												formatter : btnformat
											}, {
												name : 'id',
												index : 'id',
												width : 100,
												sorttype : "int",
												editable : false,
												fixed : true
											}, {
												name : 'taskDefinitionKey',
												index : 'taskDefinitionKey',
												width : 80,
												sorttype : "int",
												editable : false,
												fixed : true
											},

											{
												name : 'name',
												index : 'name',
												fixed : true,
												width : 100,
												sorttype : 'int',
												editable : false,
												fixed:true
											}, {
												name : 'processDefinitionId',
												index : 'processDefinitionId',
												fixed : true,
												width : 160,
												sorttype : 'string',
												editable : false,
												searchoptions : {
													sopt : [ 'cn', 'eq', 'ne' ]
												}
											}, {
												name : 'processInstanceId',
												index : 'processInstanceId',
												fixed : true,
												width : 100,
												sortable : false,
												editable : false,
												searchoptions : {
													sopt : [ 'cn' ]
												}
											}
											, {
												name : 'priority',
												index : 'priority',
												fixed : true,
												width : 50,
												sortable : false,
												editable : false,
												searchoptions : {
													sopt : [ 'cn' ]
												}
											}, {
												name : 'createTime',
												index : 'createTime',
												fixed : true,
												width : 160,
												sortable : false,
												editable : false,
												searchoptions : {
													sopt : [ 'cn' ]
												}
											}, {
												name : 'dueDate',
												index : 'dueDate',
												fixed : true,
												width : 160,
												sortable : false,
												editable : false,
												searchoptions : {
													sopt : [ 'cn' ]
												}
											}, {
												name : 'description',
												index : 'description',
												fixed : true,
												width : 160,
												sortable : false,
												editable : false,
												searchoptions : {
													sopt : [ 'cn' ]
												}
											}, {
												name : 'owner',
												index : 'owner',
												fixed : true,
												width : 160,
												sortable : false,
												editable : false,
												searchoptions : {
													sopt : [ 'cn' ]
												}
											
											},{
												name : 'assignee',
												index : '',
												width : 100,
												fixed : true,
												sortable : false,
												resize : false,
												search : false,
												hidden:true
											}
											],
											// processbtncol:2,//操作按钮放在第几个列
											isedit : true,// 是否可编辑列表
											advsearch : true,
											multiSort : true
											
										});

						initgrid("act_task");
						initvalid("act_task");

						
						// 弹出表单
						$("#act_histask_dyform_info").dialog({
							autoOpen : false,
							modal : true,
							resizable : true,
							width : 500,
							
							buttons : { // 为对话框添加按钮
								"取消" : function() {
									$("#act_histask_dyform_info").dialog("close");
								},
								"提交" : function() {
									subdata();
								}
							}
						});
					});
				});

function btnformat(cellvalue, options, rowobj) {
	if(rowobj.assignee==""){
		edit = "<a class='ui-pg-div' href='#' onClick=claim('"+rowobj.id+"') style='float: left; margin-left: 23px;'   title='' >签收</a>";
	}else{
		edit = "<a class='ui-pg-div' href='#' onClick=handle('"+rowobj.taskDefinitionKey+"','"+rowobj.name+"','"+rowobj.id+"') style='float: left; margin-left: 23px;'   title='' >办理</a>";
	}
	return edit;
}
function nameformat(cellvalue, options, rowobj){
	edit = "<a  pid='"+rowobj.id+"' pdid='"+rowobj.processDefinitionId+"' title=\"点击查看流程图\" href='#' onclick='graphTrace(this)'  >"+rowobj.name+"</a>";
	return edit;
}
/**
 * 签收任务
 * @param taskid
 */
function claim(taskid){
	 $.ajax({  
	        url : rootpath+'/manager/activity/claim.action',
	        data : {"taskId":taskid},  
	        cache : false,  
	        error : function(data) {  
	        	alertMsg("系统ajax交互错误");  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
	                alertMsg(dataJson.id);  
	                $("#act_histask_grid").trigger("reloadGrid");
	            } else {  
	            	alertMsg("操作失败"+dataJson.errormessage); 
	            	$("#act_histask_grid").trigger("reloadGrid");
	            }  
	        }  
	    });  
}
/**
 * 任务处理
 * @param taskDefinitionKey
 * @param name
 * @param id
 */
function handle(taskDefinitionKey,name,id){
	genform(id,name);
}
/***
 * 生成动态的表单
 * @param id
 */
function genform(id,name){
	// 从Server读取对应ID的JSON数据，生成动态表单
	var trs="<input type=\"hidden\" name=\"taskId\" id=\"taskId\" value=\""+id+"\">";
    $.ajax( {  
        url : rootpath+'/manager/activity/getTaskForm.action', 
        data : {taskId:id},  
        cache : false, 
        async:false,
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
            var dataJson = eval(data); 
            var rowData = dataJson[0]; 
            $.each(rowData.taskFormData.formProperties, function() {
   			var className = this.required === true ? "required" : "";
   			trs +="<div class=\"space-2\"></div> <div class=\"form-group\">";
   			trs += createFieldHtml(rowData, this, className);
   			trs +="</div>";
   		  });
           
        }  
    });  
    $('#act_histask_dyform_form').html(trs);
    $("#act_histask_dyform_info").dialog("option", "title","处理"+name).dialog("open");
  
}
/**
 * 生成一个field的html代码
 */

function createFieldHtml(formData, prop, className) {
	return formFieldCreator[prop.type.name](formData, prop, className);
}
/**
 * form对应的string/date/long/enum/boolean类型表单组件生成器
 * fp_的意思是form paremeter
 */
var formFieldCreator = {
	'string': function(formData, prop, className) {
		var result = "<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\""+prop.id+"\">"+prop.name+":</label>";
		result+="<div class=\"col-xs-12 col-sm-9\"> <div class=\"col-sm-6\"> <input type=\"text\" name=\""+prop.id+"\" datatype=\"*\" ";
		if(className!=""){
			result+= "nullmsg=\""+prop.name+"必填！\" ";
		}
		if(prop.writable!=true){
			result+= "disabled=\"disabled\" value=\""+prop.value+"\" ";
		}
		result+="id=\"fp_"+prop.id+"\" class=\"col-xs-12\" /> </div>";	
		result+="<div class=\"col-sm-3\"> <div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div> </div></div>";	
		return result;
	},
	'date': function(formData, prop, className) {
		var result = "<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\""+prop.id+"\">"+prop.name+":</label>";
		result+="<div class=\"col-xs-12 col-sm-9\"> <div class=\"col-sm-6\"> <input type=\"text\" name=\""+prop.id+"\" datatype=\"*\" ";
		if(className!=""){
			result+= "nullmsg=\""+prop.name+"必填！\" "
		}
		if(prop.writable!=true){
			result+= "disabled=\"disabled\" value=\""+prop.value+"\" ";
		}
		result+="id=\"fp_"+prop.id+"\" class=\"col-xs-12\" /> </div>";	
		result+="<div class=\"col-sm-3\"> <div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div> </div></div>";	
		return result;
	},
	'enum': function(formData, prop, className) {
		var result = "<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\""+prop.id+"\">"+prop.name+":</label>";
		result+="<div class=\"col-xs-12 col-sm-9\"> <div class=\"col-sm-6\">  ";
		
		if(prop.writable === true) {
			result += "<select id='fp_" + prop.id + "' name='" + prop.id + "'>";
			//result += "<option>" + datas + "</option>";
			
			$.each(formData[prop.id], function(k, v) {
				result += "<option value='" + k + "'>" + v + "</option>";
			});
			 
			result += "</select>";
		} else {
			result += "" + prop.value;
		}
		result+="<div class=\"col-sm-3\"> <div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div> </div></div>";	
		return result;
	},
	'users': function(formData, prop, className) {
		var result = "<td width='120'>" + prop.name + "：</td><td><input type='text' id='" + prop.id + "' name='" + prop.id + "' class='" + className + "' />";
		return result;
	}
}
/**
 * 提交审批
 */
function subdata(){
	var consng=$('#act_histask_dyform_form');
	var obj=consng.find("[id*='fp_'][disabled!='disabled']");
	var taskId=consng.find("#taskId").val();
	var param="";
	for(var i=0;i<obj.length;i++){
		param+=obj[i].name+"@#$%^"+obj[i].value;
		if(i<obj.length-1){
			param+="^%$#^";
		}
	}
	
	$.ajax({  
        url : rootpath+'/manager/activity/completeTask.action?taskId='+taskId,
        data : {"formdata":param},  
        cache : false,  
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) {
                alertMsg(dataJson.id); 
                $("#act_histask_grid").trigger("reloadGrid");
                $("#act_histask_dyform_info").dialog("close");
               
            } else {  
            	alertMsg("操作失败"+dataJson.errormessage);
            	$("#act_histask_dyform_info").dialog("close");
            }  
        }  
    });  
}