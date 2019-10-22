$(function(){
	$('#grid').datagrid({
		fit:true,
		nowrap: false,
		striped: true,
		collapsible:false,
		remoteSort: false,
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		frozenColumns:[[
        		{field:'ck',checkbox:true}
		]],
		title:'实体映射信息',
		url:rootpath+'/manager/xls/modelentitymapp/modelentitymapplist.action',
		queryParams:{'rows':15},
		idField:'tablename',
		columns:[[
			{field:'tablename',title:'数据库表名',width:140,align:'left'},
			{field:'isautoName',title:'是否指定自动生成列值',width:130,align:'center'},
			{field:'dbkey',title:'对应自动键值列',width:200,align:'left'},
			{field:'entityclass',title:'实体类(包)完整引用',width:240,align:'left'}
		]],
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				doadd();
			}
		},'-',{
			id:'btnremove',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				dodelete();
			}
		},'-',{
			id:'btnedit',
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				domodify();
			}
		}]
	});
});
//添加信息
function doadd(){
	parent.openwin("modelentitymapp","/manager/xls/modelentitymapp/modelentitymapptopageA.action?actiontype=addsave","添加数据库表与实体类对应信息",550,260,true);
}
//删除信息
function dodelete(){
	//获得多条记录
	var records = $('#grid').datagrid('getSelections');
	if(records == ''){
		parent.alertInfo('请选择要删除的记录!');
		return ;
	}else{
		parent.alertConfirm("确定要删除选中的记录",function(type){
			if(!type)
				return;
			//这里主要负责和服务器的交互
			$.ajax({
				url: rootpath+'/manager/xls/modelentitymapp/modelentitymapplist.action',
				cache:false,
				data: { 'modeentityinfo' : records[0].tablename ,'actiontype':'deletesave'},
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertInfo('信息删除成功!');
						doreload();
					}else{
						parent.alertError(dataJson.errormessage);
					}
				},
				error: function(data) {
					parent.alertError('信息删除失败！');
				}
			});
		});
	}
}
//修改信息
function domodify(){
	var selected = $('#grid').datagrid('getSelected');
	if (selected){
		parent.openwin("modelentitymapp","/manager/xls/modelentitymapp/modelentitymapptopageM.action?actiontype=modifysave&tablename="+selected.tablename,"修改数据库表与实体类对应信息",550,260,true);
	}else{
		parent.alertWarning('请选择一条您要修改的记录');
		return;
	}
}
//刷新数据
function doreload(){
	$("#grid").datagrid('reload');
}
