$(function(){
	$('#typegrid').datagrid({
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
		onSelect: function(rowid, data){
			dosearch(data.modelid);
		},
		title:'模板列表',
		url:rootpath+'/manager/xls/modelinfo/modellist.action',
		queryParams:{'rows':15},
		idField:'modelid',
		columns:[[
			{field:'modelname',title:'模板名称',width:100,align:'left'},
			{field:'mtable',title:'数据库表名',width:100,align:'left'},
			{field:'exceltblink',title:'模板下载',width:130,align:'left'},
			{field:'modelid',title:'ID',width:60,align:'left',hidden:true}
		]],
		toolbar:[{
			id:'btnadd',
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				dotypeadd();
			}
		},'-',{
			id:'btnremove',
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				dotypedelete();
			}
		},'-',{
			id:'btnedit',
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				dotypemodify();
			}
		}]
	});
});
//添加信息
function dotypeadd(){
	parent.openwin("type","/manager/xls/modelinfo/modelinfotopageA.action?actiontype=addsave","添加模板信息",500,210,true);
}
//删除信息
function dotypedelete(){
	//获得多条记录
	var records = $('#typegrid').datagrid('getSelections');
	if(records == ''){
		parent.alertInfo('请选择要删除的记录!');
		return ;
	}else{
		parent.alertConfirm("确定要删除选中的记录",function(type){
			if(!type)
				return;
			//这里主要负责和服务器的交互
			$.ajax({
				url: rootpath+'/manager/xls/modelinfo/modellist.action',
				cache:false,
				data: { 'modelidinfo' : records[0].modelid ,'actiontype':'deletesave'},
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertInfo('信息删除成功!');
						dotypereload();
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
function dotypemodify(){
	var selected = $('#typegrid').datagrid('getSelected');
	if (selected){
		parent.openwin("type","/manager/xls/modelinfo/modelinfotopageM.action?actiontype=modifysave&modelid="+selected.modelid,"修改模板信息",500,210,true);
	}else{
		parent.alertWarning('请选择一条您要修改的记录');
		return;
	}
}
//下载模版
function doDown(modelid){
		window.location.href =rootpath+'/manager/xls/excelexport/excelexport.action?modelid='+modelid;
}
//刷新数据
function dotypereload(){
	$("#typegrid").datagrid('reload');
}
//查找数据及刷新GRID
function dotypesearch(){
	//设置查询参数
	$("#typegrid").datagrid({queryParams:{"modelname": $("#name").val()}});
}
