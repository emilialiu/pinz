$(function(){
	$('#grid2').datagrid({
		fit:true,
		nowrap: false,
		striped: true,
		collapsible:false,
		remoteSort: false,
		singleSelect: false,
		pagination:true,
		rownumbers:true,
		frozenColumns:[[
        		{field:'ck',checkbox:true}
		]],
		title:'已授予角色列表',
		url:rootpath+'/manager/sys/userrole/userrolelist.action',
		queryParams:{'rows':15, 'bean.userid':$("#userid").val()},
		idField:'roleid',
		columns:[[
			//{field:'roleid',title:'隐藏列(角色ID）',width:140,align:'left',hidden:true},
			{field:'rolename',title:'已授予角色',width:140,align:'left'}
		]]
	});
});
