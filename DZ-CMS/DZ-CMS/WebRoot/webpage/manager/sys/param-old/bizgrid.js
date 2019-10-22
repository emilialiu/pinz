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
		title:'参数信息',
		url:rootpath+'/manager/sys/param/list.action',
		queryParams:{'rows':15},
		idField:'paramid',
		columns:[[
			{field:'parameterid',title:'隐藏列(参数ID）',width:140,align:'left'},
			{field:'loginlogo',title:'登陆页logo',width:140,align:'left'},
			{field:'loginname',title:'登陆页名字',width:140,align:'left'},
			{field:'firstlogo',title:'首页logo',width:200,align:'left'},
			{field:'firstname',title:'首页名字',width:200,align:'left'},
			{field:'iscaptcha',title:'是否开启验证码',width:200,align:'left'}
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
	//parent.openwin("syspram","/manager/sys/systemparam/add.jsp","添加系统参数信息",380,230,true);
	parent.setTabspara();
	parent.addTabs("添加参数信息",rootpath+"/manager/sys/param/add.jsp","","",parent.subtitle1,parent.url1);
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
				url: rootpath+'/manager/sys/param/delete.action',
				cache: false,
				data: { 'bean.parameterid' : records[0].parameterid },
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						parent.alertInfo('信息删除成功!');
						doreload('');
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
		parent.setTabspara();
		parent.addTabs("修改参数信息",rootpath+"/manager/sys/param/modify.action?actiontype=modify&bean.parameterid="+selected.parameterid,"","",parent.subtitle1,parent.url1);
	}else{
		parent.alertWarning('请选择一条您要修改的记录');
		return;
	}
}

//刷新数据
function doreload(){
	$("#grid").datagrid('reload');
}
