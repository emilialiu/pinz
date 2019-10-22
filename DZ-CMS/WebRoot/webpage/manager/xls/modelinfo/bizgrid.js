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
		title:'模板列对应关系',
		url:rootpath+'/manager/xls/modelinfo/modelmapplist.action',
		queryParams:{'rows':15},
		idField:'mid',
		columns:[[
			{field:'ecolname',title:'Excel列名',width:80,align:'left'},
			{field:'ecolindex',title:'Excel列号',width:60,align:'left'},
			{field:'mfield',title:'表字段',width:105,align:'left'},
			{field:'isnullName',title:'是否为空',width:60,align:'left'},
			{field:'isscodeName',title:'是否转码',width:60,align:'left'},
			{field:'dicttypeid',title:'转码父类',width:60,align:'left'},
			{field:'dtype',title:'数据类型',width:70,align:'left'},
			{field:'modelid',title:'模板ID',width:135,align:'left'}
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
	var records = $('#typegrid').datagrid('getSelections');
	if(records == ''){
		parent.alertInfo('请选择模版!');
		return ;
	}
	parent.openwin("info","/manager/xls/modelinfo/modelmapptopageA.action?actiontype=addsave&bean.modelid="+records[0].modelid+"&current_dbname="+records[0].mtable,"添加数据库表与实体类对应信息",500,240,true);
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
				url: rootpath+'/manager/xls/modelinfo/modelmapplist.action',
				cache:false,
				data: { 'midinfo' : records[0].mid ,'actiontype':'deletesave'},
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
	var records = $('#typegrid').datagrid('getSelections');
	if(records == ''){
		parent.alertInfo('请选择模版!');
		return ;
	}
	var selected = $('#grid').datagrid('getSelected');
	if (selected){
		parent.openwin("info","/manager/xls/modelinfo/modelmapptopageM.action?actiontype=modifysave&mid="+selected.mid+"&modelid="+records[0].modelid+"&current_dbname="+records[0].mtable,"修改数据库表与实体类对应信息",500,240,true);
	}else{
		parent.alertWarning('请选择一条您要修改的记录');
		return;
	}
}
//刷新数据
function doreload(){
	$("#grid").datagrid('reload');
}
//查找数据及刷新GRID
function dosearch(modelid){
	//设置查询参数
	$("#grid").datagrid({queryParams:{"modelid": modelid}});
}
