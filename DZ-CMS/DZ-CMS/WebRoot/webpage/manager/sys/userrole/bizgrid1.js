$(function(){
	$('#grid1').datagrid({
		fit:true,
		nowrap: false,
		striped: true,
		collapsible:false,
		remoteSort: false,
		singleSelect:false,
		pagination:true,
		rownumbers:true,
		frozenColumns:[[
        		{field:'ck',checkbox:true}
		]],
		title:'未授予角色列表',
		url:rootpath+'/manager/sys/userrole/userroleunlist.action',
		queryParams:{'rows':15, 'bean.userid':$("#userid").val()},
		idField:'roleid',
		columns:[[
			//{field:'roleid',title:'隐藏列(角色ID）',width:140,align:'left',hidden:true},
			{field:'rolename',title:'未授予的角色',width:140,align:'left'}
		]]
	});
});

//刷新数据
function doreload(){
	$("#grid1").datagrid('reload');
	$("#grid2").datagrid('reload');
}
