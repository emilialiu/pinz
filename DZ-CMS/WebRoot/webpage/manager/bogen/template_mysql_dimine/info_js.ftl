var addsaveurl = rootpath+"/${filePath ?lower_case}/doAddSave.action";
var modifysaveurl = rootpath+"/${filePath ?lower_case}/doModifySave.action";
var deletesaveurl = rootpath+"/${filePath ?lower_case}/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
	<#list primaryKey as primaryKey>
		return {"bean.${primaryKey ?lower_case}" : ${primaryKey ?lower_case}};
	</#list>    
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
	    _rowid : dataJson.${begin.filedName ?lower_case},// 从Server端得到系统分配的id
		${begin.filedName ?lower_case} : dataJson.${begin.filedName ?lower_case},// 从Server端得到系统分配的id
		<#else>
		${begin.filedName ?lower_case} : ${begin.filedName ?lower_case}<#if begin_has_next>,</#if>
	    </#if>
		</#list>
	};
	return dataRow;
}
