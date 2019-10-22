//$("#${actionName ?lower_case}_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#${actionName ?lower_case}_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
	return {
        <#list primaryKey as primaryKey>
        <#if primaryKey_has_next>
        "bean.${primaryKey ?lower_case}" : str.${primaryKey ?lower_case},
        <#else>
        "bean.${primaryKey ?lower_case}" : str.${primaryKey ?lower_case}
        </#if>
		</#list>
    };  
}
//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
    <#list filed as begin>
	consoleDlg.find("#${begin.filedName ?lower_case}").val(rowData.${begin.filedName ?lower_case});
	</#list>
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
    <#list filed as begin>	
	var ${begin.filedName ?lower_case} = $.trim(consoleDlg.find("#${begin.filedName ?lower_case}").val());
	</#list>
      
    return {   
	    <#list filed as begin>
        "bean.${begin.filedName ?lower_case}" : ${begin.filedName ?lower_case}<#if begin_has_next>,</#if>
		</#list>
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    <#list filed as begin>	
    <#if begin.primaryKey =="false">
	var ${begin.filedName ?lower_case} = $.trim(consoleDlg.find("#${begin.filedName ?lower_case}").val());	
    </#if>
	</#list>
	var dataRow = { 			
		<#list filed as begin>	
	    <#if begin.primaryKey =="true">
		${begin.filedName ?lower_case} : dataJson.id,// 从Server端得到系统分配的id
		<#else>
		${begin.filedName ?lower_case} : ${begin.filedName ?lower_case}<#if begin_has_next>,</#if>
	    </#if>
		</#list>
	}; 
	return dataRow;
}
