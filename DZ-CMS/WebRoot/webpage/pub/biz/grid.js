var _formw;//form表单宽度
var _formh;//form表单高度
//初始化grid
var initgrid = function(_bizname) {
	gridid = "#"+_bizname+"_grid";
	grid_pager = "#"+_bizname+"_grid_pager";
	_formw = $(gridid).data("gridOptions").formw==undefined?800:$(gridid).data("gridOptions").formw;
	_formh = $(gridid).data("gridOptions").formh==undefined?400:$(gridid).data("gridOptions").formh;
	_gridheight = $(gridid).data("gridOptions").height==undefined?150:$(gridid).data("gridOptions").height;
	_gridrownum = $(gridid).data("gridOptions").rowNum==undefined?(Math.floor((_gridheight-30)/26)<=0?1:Math.floor((_gridheight-30)/26)):$(gridid).data("gridOptions").rowNum==undefined;
	$(gridid).jqGrid({
		bizname:_bizname,
		url :$(gridid).data("gridOptions").url, // 这是Action的请求地址
		colNames : $(gridid).data("gridOptions").colNames,//列显示名称
		colModel : $(gridid).data("gridOptions").colModel,
		cellEdit:$(gridid).data("gridOptions").cellEdit==undefined?false:$(gridid).data("gridOptions").cellEdit,//是否可编辑列表
		cellsubmit : "rclientArray",
		height : _gridheight,	//表格高度，默认150
		grouping : $(gridid).data("gridOptions").grouping==undefined?false:$(gridid).data("gridOptions").grouping,	//是否分组
		groupingView : $(gridid).data("gridOptions").groupingView==undefined?{}:$(gridid).data("gridOptions").groupingView,	//分组列									
		multiselect : $(gridid).data("gridOptions").multiselect==undefined?false:$(gridid).data("gridOptions").multiselect,	//多选
		onCellSelect :$(gridid).data("gridOptions").onCellSelect=undefined?false:$(gridid).data("gridOptions").onCellSelect,//单元格选择事件
		ondblClickRow :$(gridid).data("gridOptions").ondblClickRow=undefined?false:$(gridid).data("gridOptions").ondblClickRow,//行双击事件
		onSelectRow :$(gridid).data("gridOptions").onSelectRow=undefined?false:$(gridid).data("gridOptions").onSelectRow,//行点击事件
		afterInsertRow :$(gridid).data("gridOptions").afterInsertRow=undefined?false:$(gridid).data("gridOptions").afterInsertRow,//行加载数据事件
		footerrow :$(gridid).data("gridOptions").footerrow=undefined?false:$(gridid).data("gridOptions").footerrow,//footer行
		gridComplete :$(gridid).data("gridOptions").gridComplete=undefined?false:$(gridid).data("gridOptions").gridComplete,//数据加载完成事件
		expfilename :$(gridid).data("gridOptions").expfilename=undefined?false:$(gridid).data("gridOptions").expfilename,//导出文件名称
		exceltemplatefile :$(gridid).data("gridOptions").exceltemplatefile=undefined?false:$(gridid).data("gridOptions").exceltemplatefile,//excel导出模版文件
		excelstartrow :$(gridid).data("gridOptions").excelstartrow=undefined?false:$(gridid).data("gridOptions").excelstartrow,//excel模版导出开始行
		pager : $(grid_pager),//翻页用的导航栏
		datatype : "json", // 将这里改为使用JSON数据	，默认xml
		mtype : 'POST',//ajax提交方式	默认GET
		rowNum : _gridrownum,		//grid 显示记录条数	默认根据高度设置条数
		rowList : [ _gridrownum, 20, 50,100,500 ],	//改变显示记录数	会覆盖rowNum
		rownumbers : true,		//显示行号 默认不显示
		rownumWidth:40,
		autowidth : true,	//根据父元素比例重新调整表格宽度
		viewrecords :$(gridid).data("gridOptions").viewrecords==undefined?true:$(gridid).data("gridOptions").viewrecords,//定义是否要显示总记录数，默认false
		pgbuttons:$(gridid).data("gridOptions").pgbuttons==undefined?true:$(gridid).data("gridOptions").pgbuttons,//是否显示翻页按钮
		pagerpos:$(gridid).data("gridOptions").pagerpos==undefined?"center":$(gridid).data("gridOptions").pagerpos,//翻页按钮位置
		scroll:$(gridid).data("gridOptions").scroll==undefined?0:$(gridid).data("gridOptions").scroll,//是否滚动翻页0、1
		multiSort:$(gridid).data("gridOptions").multiSort==undefined?false:$(gridid).data("gridOptions").multiSort,//是否多个排序
		loadComplete : function() {
			setTimeout(function(){
				updatePagerIcons("#"+_bizname+"_grid");
				
				if($("#"+_bizname+"_grid").data("gridOptions").processbtncol!=undefined){
					if($("#"+_bizname+"_grid").data("gridOptions").isedit!=undefined&&$("#"+_bizname+"_grid").data("gridOptions").isedit){
						updateEditIcons(_bizname);
					}else{
						updateIcons(_bizname);
					}
				}
			}, 0);
			try{
				changeColor(); 
			}catch(e){
			}
		}		
	});

	//filterToolbar
	$("#"+_bizname+"_grid").jqGrid('filterToolbar', {
		searchOperators : true,
		stringResult : true,
		searchOnEnter : false
	});
	$("#"+_bizname+"_grid")[0].toggleToolbar();//显示隐藏grid过滤工具条
	$("#"+_bizname+"_grid_box_filter").click(function(){
		$("#"+_bizname+"_grid")[0].toggleToolbar();//显示隐藏grid过滤工具条
	});
	//add
	$("#"+_bizname+"_grid_btn_add").click(function(){
		_openDialog4Adding(_bizname);
	});
	
	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		$("#"+_bizname+"_grid").jqGrid('setGridWidth', $("#"+_bizname+"_grid_box").width());
	});
	setTimeout(function() {
		$("#"+_bizname+"_grid").jqGrid('setGridWidth', parent_column.width()-2);
	}, 0);
	var parent_column = $("#"+_bizname+"_grid").closest('[class*="col-"]');
	$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
			setTimeout(function() {
				$("#"+_bizname+"_grid").jqGrid('setGridWidth', parent_column.width()-2);
			}, 0);
		}
	});	
	//fullscreen
	$("#"+_bizname+"_grid_box").on('fullscreened.ace.widget' , function(e) {
		setTimeout(function() {
			if($("#"+_bizname+"_grid_box").hasClass('fullscreen')){
				$("#"+_bizname+"_grid").jqGrid( 'setGridWidth', $("#"+_bizname+"_grid_box").width()-2 );
				$("#"+_bizname+"_grid").jqGrid( 'setGridHeight',$("#"+_bizname+"_grid_box").height()-170 );		
				//curindex = parseInt($("#"+_bizname+"_grid_box").css('z-index'))+10;				
				//$.extend($.jqgrid.jqModal,{zIndex:boxindex}); 
			}else{
				$("#"+_bizname+"_grid").jqGrid( 'setGridHeight',$("#"+_bizname+"_grid").data("gridOptions").height==undefined?150:$("#"+_bizname+"_grid").data("gridOptions").height );
				$("#"+_bizname+"_grid").jqGrid( 'setGridWidth', $("#"+_bizname+"_grid_box").width()-2 );	
			}
		}, 0);
	});

	//navButtons
	$("#"+_bizname+"_grid").jqGrid('navGrid',grid_pager,
		{edit: false,add: false,del: false,search: false,refresh: false,view: false});
	var hasSeparatorLine=false;
	if($("#"+_bizname+"_grid").data("gridOptions").addurl!=undefined&&$("#"+_bizname+"_grid").data("gridOptions").addurl!=""){
		$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
		   caption:"", 
		   buttonicon:"ace-icon fa fa-plus-circle purple", 
		   onClickButton: function(){ 
			   _openDialog4Adding(_bizname);
		   }, 
		   position:"last",
		   title : "新增",
	       cursor : "pointer"
		});
		hasSeparatorLine=true;
	}
	if($("#"+_bizname+"_grid").data("gridOptions").modifyurl!=undefined&&$("#"+_bizname+"_grid").data("gridOptions").modifyurl!=""){
		$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
		   caption:"", 
		   buttonicon:"ace-icon fa fa-pencil blue", 
		   onClickButton: function(){ 
			   _openDialog4Modifying(_bizname);
		   }, 
		   position:"last",
		   title : "修改",
	       cursor : "pointer"
		});
		hasSeparatorLine=true;
	}
	if($("#"+_bizname+"_grid").data("gridOptions").viewurl!=undefined&&$("#"+_bizname+"_grid").data("gridOptions").viewurl!=""){
		$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
		   caption:"", 
		   buttonicon : 'ace-icon fa fa-search-plus grey',
		   onClickButton: function(){ 
			   _openDialog4Reading(_bizname);
		   }, 
		   position:"last",
		   title : "查看",
	       cursor : "pointer"
		});
		hasSeparatorLine=true;
	}
	if($("#"+_bizname+"_grid").data("gridOptions").deleteurl!=undefined&&$("#"+_bizname+"_grid").data("gridOptions").deleteurl!=""){
		$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
		   caption:"", 
		   buttonicon:"ace-icon fa fa-trash-o red", 
		   onClickButton: function(){ 
			   _openDialog4Deleting(_bizname);
		   }, 
		   position:"last",
		   title : "删除",
	       cursor : "pointer"
		});
		hasSeparatorLine=true;
	}
	if(hasSeparatorLine){
		$("#"+_bizname+"_grid").navSeparatorAdd(grid_pager,{});  //分割线
	}
	if($("#"+_bizname+"_grid").data("gridOptions").exportexcellocal||$("#"+_bizname+"_grid").data("gridOptions").exportexcellocal==undefined){
		$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
		   caption:"", 
		   buttonicon:"ace-icon fa fa-download blue", 
		   onClickButton: function(){ 
			   _exportExcelLocal(_bizname);
		   }, 
		   position:"last",
		   title : "导出当前显示数据",
	       cursor : "pointer"
		});
	}
	if($("#"+_bizname+"_grid").data("gridOptions").advsearch||$("#"+_bizname+"_grid").data("gridOptions").advsearch==undefined){
		$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
		   caption:"", 
		   buttonicon:"ace-icon fa fa-filter red", 
		   onClickButton: function(){ 
			   _doadvsearch(_bizname);
		   }, 
		   position:"last",
		   title : "高级查询",
	       cursor : "pointer"
		});
	}
	$("#"+_bizname+"_grid").navButtonAdd(grid_pager,{
	   caption:"", 
	   buttonicon:"ace-icon fa fa-refresh green", 
	   onClickButton: function(){ 
		   $("#"+_bizname+"_grid").trigger("reloadGrid");
	   }, 
	   position:"last",
	   title : "刷新",
       cursor : "pointer"
	});
	//导出excel
	function _exportExcelLocal(_bizname) {
		var f = $("#"+_bizname+"_grid");

        if (!confirm("确认导出当前表格数据为Excel下载文件？")) {
            return
        }
        var e = new Array();
        e = $(f).getDataIDs();
        var m = f.data("gridOptions").colModel;
        var p = f.data("gridOptions").colNames;
		var expf = $("#"+_bizname+"_grid").data("gridOptions").expfilename==undefined?"export-data.xls":$("#"+_bizname+"_grid").data("gridOptions").expfilename;
		var etf = $("#"+_bizname+"_grid").data("gridOptions").exceltemplatefile==undefined?"":$("#"+_bizname+"_grid").data("gridOptions").exceltemplatefile;
		var esr = $("#"+_bizname+"_grid").data("gridOptions").excelstartrow==undefined?0:$("#"+_bizname+"_grid").data("gridOptions").excelstartrow;
				
        var pbtncol = $("#"+_bizname+"_grid").data("gridOptions").processbtncol==undefined?-1:$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
        pbtncol = pbtncol -2;//有行号时需要去掉操作按钮列
        
        var h = "";
		//没有设置模版就导出表头，设置了模版就不导表头
		if(esr==0){
			for (k = 0; k < p.length; k++) {
				var n = m[k];
				if (n.hidedlg || n.hidden || n.disableExport||k == pbtncol) {
					continue;
				}
				h = h + p[k] + "\t";
			}
			h = h + "\n";
		}
        for (i = 0; i < e.length; i++) {
            data = $(f).getRowData(e[i]);
            for (j = 0; j < p.length; j++) {
                var n = m[j];
                if (n.hidedlg || n.hidden || n.disableExport||j == pbtncol) {
                    continue;
                }
                var g = data[n.name];
                var l = null;
                if (n.searchoptions && n.searchoptions.value) {
                    l = n.searchoptions.value;
                } else {
                    if (n.editoptions && n.editoptions.value) {
                        l = n.editoptions.value;
                    }
                }
                if (l) {
                    g = l[g];
                }
                //添加undefined的判断，修复在有可编辑下拉列表的grid导出报错问题
                if(g == undefined){
                	g = "";
                } else{
                	if (g.indexOf("<") > -1 && g.indexOf(">") > -1) {
                		g = $(g).text();
                	}
                	if (g == "") {
                		g = data[n.name];
                	}
                	if (g == "null" || g == null) {
                		g = "";
                	}
                }
                g = g.replace(/\&nbsp;/g, "");
                h = h + g + "\t";
            }
            h = h + "\n";
        }
        h = h + "\n";
        
        var c = $('<form method="post" target = "_blank" action="' + rootpath + '/pub/grid/export.action"></form>').appendTo($("body"));
        var o = $('<input type="hidden" name="exportDatas"/>').appendTo(c);
        var d = $('<input type="hidden" name="fileName"/>').appendTo(c);
        var d1 = $('<input type="hidden" name="exceltemplatefile"/>').appendTo(c);
        var d2 = $('<input type="hidden" name="excelstartrow"/>').appendTo(c);
        d.val(expf);
        o.val(h);
        d1.val(etf);
        d2.val(esr);			
				
        c.submit();
        c.remove();
    }

	//设置分页图片
	function updatePagerIcons(gridid) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		});
	}	
	$(document).one('ajaxloadstart.page', function(e) {
		$("#"+_bizname+"_grid").jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	//自定义查询		
	if($("#"+_bizname+"_text_search").length==1){
		$("#"+_bizname+"_text_search").keydown(function(e){
			if(e.keyCode==13){
				_dosimplesearch(_bizname);
			}
		});
	}
	if($("#"+_bizname+"_btn_search").length==1){
		$("#"+_bizname+"_btn_search").click(function() {
			_dosimplesearch(_bizname);
		});
	}
	function _dosimplesearch(_bizname){
		$("#"+_bizname+"_grid").jqGrid('setGridParam', {
			url : $("#"+_bizname+"_grid").data("gridOptions").url,
			postData : {
				'bean.param' : $("#"+_bizname+"_text_search").val()
			},
			page : 1
		}).trigger("reloadGrid"); 
	}
	function _doadvsearch(_bizname){
		$("#"+_bizname+"_grid").jqGrid('searchGrid', {
			multipleSearch : true,
			overlay:false,
			closeAfterSearch : true,
			closeAfterReset : true,
			recreateForm: true,
			afterShowSearch: function(e){
				var form = $(e[0]);
				if(form.closest('.ui-jqdialog').find('.ui-jqdialog-title').parent('.widget-header').length <= 0){
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
				}
				style_search_form(form);
			},
			afterRedraw: function(){
				style_search_filters($(this));
			}
		});
	}
	//高级查询
	if($("#"+_bizname+"_btn_advsearch").length==1){
		$("#"+_bizname+"_btn_advsearch").click(function() {
			_doadvsearch(_bizname);				
		});
	}
};

function btnformatter(cellvalue,options,rowobj){
	return cellvalue;
}
function style_search_filters(form) {
	form.find('.delete-rule').val('X');
	form.find('.add-rule').addClass('btn btn-xs btn-primary');
	form.find('.add-group').addClass('btn btn-xs btn-success');
	form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
function style_search_form(form) {
	var dialog = form.closest('.ui-jqdialog');
	var buttons = dialog.find('.EditTable');
	buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-primary').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
	buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-primary').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
	buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-primary').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
}
//表格中修改图片切换,可编辑列表
function updateEditIcons(_bizname){
	var ids = $("#"+_bizname+"_grid").jqGrid('getDataIDs');
	for ( var i = 0; i < ids.length; i++) {
		var cl = ids[i];
		processbtnstr = getProcessEditIcons(_bizname,cl);
		$("#"+_bizname+"_grid").jqGrid(
				'setRowData',
				ids[i],
				{
					processbtn : processbtnstr
				});
	}
}
function getProcessEditIcons(_bizname,rownum){
	update = "<div class='ui-pg-div ui-inline-edit' style='float: left; cursor: pointer;display:block;' onclick=_doeditmodify('"+_bizname+"','"+ rownum
	+ "')><span class='ui-icon ui-icon-pencil'/></div>";
	del = "<div class='ui-pg-div ui-inline-del' style='float: left; cursor: pointer;display:block;' onclick=_doeditdelete('"+_bizname+"','"+ rownum
		+ "')><span class='ui-icon ui-icon-trash'/></div>";
	submit = "<div class='ui-pg-div ui-inline-save' style='float: left;display:none; margin-left: 8px;' onclick=_doeditsubmit('"+_bizname+"','"+ rownum
		+ "')><span class='ui-icon ui-icon-disk'/></div>";
	cancel = "<div class='ui-pg-div ui-inline-cancel' style='float: left;display:none;margin-left: 5px;' onclick=_doeditcancel('"+_bizname+"','"+ rownum
		+ "')><span class='ui-icon ui-icon-cancel'/></div>";
	return "<div style='margin-left: 8px;'>" + update + del + submit+ cancel + "</div>";
}
//表格中修改图片切换,不可编辑列表
function updateIcons(_bizname){
	var ids = $("#"+_bizname+"_grid").jqGrid('getDataIDs');
	for ( var i = 0; i < ids.length; i++) {
		var cl = ids[i];
		processbtnstr = getProcessIcons(_bizname,cl);
		$("#"+_bizname+"_grid").jqGrid(
				'setRowData',
				ids[i],
				{
					processbtn : processbtnstr
				});
	}
}
function getProcessIcons(_bizname,rownum){
	update = "<div class='ui-pg-div ui-inline-edit' style='float: left; margin-left: 8px;' title='修改' onclick=_doopenmodify('"+_bizname+"','"+ rownum
	+ "')><span class='ui-icon ui-icon-pencil'/></div>";
	del = "<div class='ui-pg-div ui-inline-del' style='float: left; margin-left: 5px;' title='删除' onclick=_doopendelete('"+_bizname+"','"+ rownum
		+ "')><span class='ui-icon ui-icon-trash'/></div>";	
	return update + del;
}
//打开修改窗口
function _doopenmodify(_bizname,selectedRowId) {
	setTimeout(function(){
		_openDialog4Modifying(_bizname);
	},0);
}
//打开删除窗口
function _doopendelete(_bizname,selectedRowId) {
	setTimeout(function(){
		parent.bootbox.confirm("删除所选记录?", function(result) {
			if(result) {_doDeleteSave(_bizname);}
		});
	},0);
}
/** edit 增 删 改 操 作**/
// 删除信息
function _doeditdelete(_bizname,selectedRowId) {
	setTimeout(function(){
		bootbox.confirm("确定删除吗?", function(result) {
			if(result) {
				var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
				//删除保存开始
			    $("#"+_bizname+"_grid").trigger('domi.deletesave.before'); 
			    //提取表单参数
			    var params = getGridParams(_bizname);
			    
			    $.ajax({  
			        url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").deleteurl,
			        data : params,  
			        cache : false,  
			        error : function(data) {  
			        	parent.alertErrorMsg("系统ajax交互错误");  
			        },  
			        success : function(data) {  
			        	var dataJson = eval(data);
			            if(dataJson.success) { 
			                $("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId);  
			                  
			                parent.alertMsg("信息删除成功!");  
			            	//删除保存完成
			                $("#"+_bizname+"_grid").trigger('domi.deletesave.after');
			            } else {  
			            	parent.alertErrorMsg("删除失败:"+dataJson.errorMessage);  
			            	$("#"+_bizname+"_grid").trigger('domi.deletesave.failue');
			            }  
			        }  
			    });  
			}
		});
	},0);
}
// 修改信息
function _doeditmodify(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").editRow(selectedRowId);
   	$("#"+_bizname+"_grid").trigger('domi.modify.before');

	col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").hide();
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").show();
	$("#"+_bizname+"_grid").trigger('domi.modify.after');
}

function _doeditsubmit(_bizname,selectedRowId) {
	//修改保存开始
	$("#"+_bizname+"_grid").trigger('domi.modifysave.before');
	var validflag = $("#"+_bizname+"_grid").saveRow(selectedRowId);
	if(!validflag){
		return;
	}else{
		//去编辑列表行数据
		var params = getEditGridParams(_bizname,selectedRowId);
		$.ajax({
			url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").modifyurl,
			cache : false,
			data : params,
			type : 'post',
			success : function(data) {
				var dataJson = eval(data);
				if(dataJson.success) {
					gridrowdata = initEditGridRowData(_bizname,data,selectedRowId);
					$("#"+_bizname+"_grid").jqGrid("setRowData", selectedRowId, gridrowdata, {color:"#FF0000"});
					parent.alertMsg("信息更新成功!");
					//修改保存完成
					$("#"+_bizname+"_grid").trigger('domi.modifysave.after');
				} else {
					parent.alertErrorMsg("操作失败:"+dataJson.errorMessage);
					$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
				}
			},
			error : function(data) {
				parent.alertErrorMsg("系统ajax交互错误 ");
				$("#"+_bizname+"_grid").trigger('domi.modifysave.failue');
			}
		});
		col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
		$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
	}
}

function _doeditcancel(_bizname,selectedRowId) {
	$("#"+_bizname+"_grid").restoreRow("" + selectedRowId + "");
	col=$("#"+_bizname+"_grid").data("gridOptions").processbtncol;
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-edit,div.ui-inline-del").show();
	$("#"+_bizname+"_grid").find("#"+selectedRowId).find("td:nth-child("+col+")").find("div.ui-inline-save,div.ui-inline-cancel").hide();
}

function doreload(_bizname){
	$("#"+_bizname+"_grid").jqGrid('setGridParam', {
		url : $("#"+_bizname+"_grid").data("gridOptions").url,
		postData : {
			'bean.param' : $("#"+_bizname+"_text_search").val()
		},
		page : 1
	}).trigger("reloadGrid"); 
}

function checkData(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    if (!selectedRowId) {  
    	alertMsg("请先选择需要操作的行!");  
        return false;  
    } else {
    	return true;
    }
}

var _openDialog4Adding = function(_bizname) {
	var url = $("#"+_bizname+"_grid").data("gridOptions").addurl;
	parent.createwindow(_bizname,url,"新增",_formw,_formh,true);
};  
var _openDialog4Modifying = function(_bizname) {  
	var flag = checkData(_bizname);
	if(!flag)return;
	var url = $("#"+_bizname+"_grid").data("gridOptions").viewurl;
	parent.createwindow(_bizname,url+getParams(_bizname)+"&actiontype=modify","修改",_formw,_formh,true);
};

var _openDialog4Reading = function(_bizname) {
	var flag = checkData(_bizname);
	if(!flag)return;
	var url = $("#"+_bizname+"_grid").data("gridOptions").viewurl;
	parent.createwindow(_bizname,url+getParams(_bizname)+"&actiontype=view&load=detail","查看",_formw,_formh,true);
};

var _openDialog4Deleting = function(_bizname) {  
	var flag = checkData(_bizname);
	if(!flag)return;
	var url = $("#"+_bizname+"_grid").data("gridOptions").viewurl;
	parent.createwindow(_bizname,url+getParams(_bizname)+"&actiontype=delete&load=detail","删除",_formw,_formh,true);
};

function _doGridAddSave(_bizname,gridrowdata){
	var srcrowid = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");
    if(srcrowid) {
    	$("#"+_bizname+"_grid").jqGrid("addRowData", gridrowdata._rowid, gridrowdata, "before", srcrowid); 
    } else {
    	$("#"+_bizname+"_grid").jqGrid("addRowData", gridrowdata._rowid, gridrowdata, "first",1);
    }
    if($("#"+_bizname+"_grid").data("gridOptions").processbtncol!=undefined){
    	if($("#"+_bizname+"_grid").data("gridOptions").isedit!=undefined
    			&& $("#"+_bizname+"_grid").data("gridOptions").isedit){
			updateEditIcons(_bizname);
		}else{
			updateIcons(_bizname);
		}
	}
}

function _doGridModifySave(_bizname,gridrowdata){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");
	$("#"+_bizname+"_grid").jqGrid("setRowData", selectedRowId, gridrowdata, {color:"#FF0000"}); 
}

function _doGridDeleteSave(_bizname,gridrowdata){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");
	$("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId);
}

function _doDeleteSave(_bizname) {  
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow"); 
    //提取表单参数
    var params = getGridParams(_bizname);
    
    $.ajax({  
        url : rootpath + $("#"+_bizname+"_grid").data("gridOptions").deleteurl,
        type:'POST',
        data : params,  
        cache : false,  
        error : function(data) {  
        	alertErrorMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
        	var dataJson = eval(data);
            if(dataJson.success) { 
                $("#"+_bizname+"_grid").jqGrid("delRowData", selectedRowId);  
                alertMsg("信息删除成功!");  
            } else {  
            	alertErrorMsg("删除失败:"+dataJson.errorMessage); 
            }  
        }  
    });  
}; 