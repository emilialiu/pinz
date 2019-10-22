var scripts = [null, null];

$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	$(function() {
		// grid参数
		$("#${actionName ?lower_case}_grid").data("gridOptions", {
			height : 400,
			url :  rootpath + '/${filePath ?lower_case}/list.action',
			colNames : [ 
						'操作',
						<#list filed as begin>
						<#if begin.funcShow =="true" && begin.isShowPK =="true">
							'${begin.description}'<#if begin_has_next>,</#if>
						<#elseif begin.funcShow =="true" && begin.primaryKey =="false">
							'${begin.description}'<#if begin_has_next>,</#if>
						<#elseif begin.funcShow =="true" && begin.primaryKey =="true">
							'${begin.description}'<#if begin_has_next>,</#if>
						</#if>
						</#list>
						],//列显示名称
			colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
						 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
						{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
						<#list filed as begin>
						<#if begin.funcShow =="true" && begin.isShowPK =="true">
							{name : '${begin.filedName ?lower_case}',index : '${begin.filedName ?lower_case}',fixed : true,width : 100,sorttype : '${begin.data_type ?lower_case}',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}<#if begin_has_next>,</#if>
						<#elseif begin.funcShow =="true" && begin.primaryKey =="false">
							{name : '${begin.filedName ?lower_case}',index : '${begin.filedName ?lower_case}',fixed : true,width : 100,sorttype : '${begin.data_type ?lower_case}',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}<#if begin_has_next>,</#if>
						<#elseif begin.funcShow =="true" && begin.primaryKey =="true">
							{name : '${begin.filedName ?lower_case}',index : '${begin.filedName ?lower_case}',fixed : true,width : 100,sorttype : '${begin.data_type ?lower_case}',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}<#if begin_has_next>,</#if>
						<#else>
							{name : '${begin.filedName ?lower_case}',index : '${begin.filedName ?lower_case}',width : 60,sorttype : "${begin.data_type ?lower_case}",editable : false,hidden : true}<#if begin_has_next>,</#if>
						</#if>
						</#list>
						],
			processbtncol:2,//操作按钮放在第几个列
			isedit:false,//是否可编辑列表
			advsearch:true,
			viewurl : rootpath+"/${filePath ?lower_case}/doModify.action",
			addurl : rootpath+"/${filePath ?lower_case}/doAddSave.action",
			modifyurl : rootpath+"/${filePath ?lower_case}/doModifySave.action?actiontype=modifysave",
			deleteurl : rootpath+"/${filePath ?lower_case}/delete.action?actiontype=delete"
			
		});
		
		initgrid("${actionName ?lower_case}");
		initvalid("${actionName ?lower_case}");
	});
});
//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
	return {  
			<#list filed as begin>
				"bean.${begin.filedName ?lower_case}" : datalist.${begin.filedName ?lower_case}<#if begin_has_next>,</#if>
			</#list>
	    }; 
}
function initEditGridRowData(_bizname,data,selectedRowId){
	var datalist = getEditGridParams(_bizname,selectedRowId);
	var dataJson = eval(data);
	return dataRow = {  
            <#list filed as begin>
		    <#if begin.primaryKey =="true">
			${begin.filedName ?lower_case} : dataJson.id,
			<#else>
			${begin.filedName ?lower_case} : datalist.${begin.filedName ?lower_case}<#if begin_has_next>,</#if>
		    </#if>
			</#list>
        }; 
}
