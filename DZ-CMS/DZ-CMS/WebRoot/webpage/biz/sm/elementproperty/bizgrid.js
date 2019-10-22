$(function() {
	
	// grid参数
	$("#elementproperty_grid").data("gridOptions", {
		height : 400,
		url :  '',
		colNames : [ 
					'操作',
					'要素属性ID',
					'要素分类ID',
					'要素属性编码',
					'要素属性名称',
					'上级要素属性名称',
					'要素属性类型',
					'取值SQL',
					'对应服务名',
					'能否编辑',
					'对应内部属性名',
					'是否联合主键',
					'元素序号',
					'创建人',
					'创建时间',
					'修改人',
					'修改时间',
					'备注',
					'工程级别',
					'是否隐藏'
				],//列显示名称
		colModel : [ //	align:left,center,right,默认left;classes:列的css;datefmt:默认Y-m-d;defval:查询字段的默认值;editable:单元格是否可编辑,默认false;
					 //fixed:列宽是否固定不可变,默认false;search:在搜索模式下，定义此列是否可以作搜索列
					{name : 'processbtn',index:'', width:80, fixed:true, sortable:false, resize:false,search : false,formatter:btnformatter},
					{name : 'eleproid',index : 'eleproid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'eletypeid',index : 'eletypeid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'elecode',index : 'elecode',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'elename',index : 'elename',fixed : true,width : 100,sorttype : 'varchar(100)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'parenteleid',index :'parenteleid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},	
					{name : 'eletype',index : 'eletype',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'valuesql',index : 'valuesql',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'servicename',index : 'servicename',fixed : true,width : 100,sorttype : 'varchar(200)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'isedit',index : 'isedit',fixed : true,width : 100,sorttype : 'varchar(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'iproname',index : 'iproname',fixed : true,width : 100,sorttype : 'varchar(50)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'isprimary',index : 'isprimary',fixed : true,width : 100,sorttype : 'varchar(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'eleno',index : 'eleno',fixed : true,width : 100,sorttype : 'varchar(1)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'createid',index : 'createid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'createdate',index : 'createdate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'modifyid',index : 'modifyid',fixed : true,width : 100,sorttype : 'varchar(40)',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'modifydate',index : 'modifydate',fixed : true,width : 100,sorttype : 'datetime',editable : false,hidden:true,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'memo',index : 'memo',fixed : true,width : 100,sorttype : 'varchar(1000)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'projectlevel',index : 'projiectlevel',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}},
					{name : 'ishide',index : 'ishide',fixed : true,width : 100,sorttype : 'varchar(10)',editable : false,searchoptions : {sopt : [ 'cn', 'eq', 'ne', 'le', 'lt', 'gt', 'ge' ]}}
				],
		processbtncol:2,//操作按钮放在第几个列
		isedit:false,//是否可编辑列表
		advsearch:true,
		viewurl : "/biz/sm/elementproperty/doModify.action",
		addurl : "/biz/sm/elementproperty/doAdd.action",
		modifyurl : "/biz/sm/elementproperty/doModify.action",
		deleteurl : "/biz/sm/elementproperty/delete.action"
	});
	
	initgrid("elementproperty");
});
