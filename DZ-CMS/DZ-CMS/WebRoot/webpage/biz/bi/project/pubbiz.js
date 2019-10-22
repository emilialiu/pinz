function getGridParams(_bizname){
	var selectedRowId = $("#"+_bizname+"_grid").jqGrid("getGridParam", "selrow");  
    var str = $("#"+_bizname+"_grid").jqGrid("getRowData", selectedRowId); // 根据行ID，获取选中行的数据
    if(_bizname=="projectsupport" || _bizname=="stope_projectsupport"){
    	return {
	        "bean.psid" : str.psid
	    };
    }else if(_bizname=="projectfill"){
    	return {
	        "bean.pfid" : str.pfid
	    };
    }else{
    	return {
	        "bean.projectid" : str.projectid
	    };
    }
}

//取编辑grid列表数据
function getEditGridParams(_bizname,selectedRowId){
	if(_bizname=="roadway"){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			jjtype:$('#'+selectedRowId+"_jjtype").val(),
			rdtype:$('#'+selectedRowId+"_rdtype").val(),
			rockhardness:$('#'+selectedRowId+"_rockhardness").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {  
			"bean.parentid" : datalist.parentid,
			"bean.projectid" : datalist.projectid,
			"bean.rdname" : datalist.rdname,
			"bean.jjtype" : datalist.jjtype,
			"bean.rdtype" : datalist.rdtype,
			"bean.rockhardness" : datalist.rockhardness,
			"bean.section" : datalist.section,
			"bean.onlysection" : datalist.onlysection==""?"0":datalist.onlysection,
			"bean.wateryield" : datalist.wateryield==""?"0":datalist.wateryield,
			"bean.temperature" : datalist.temperature==""?"0":datalist.temperature,
			"bean.diameter" : datalist.diameter==""?"0":datalist.diameter,
			"bean.designlen" : datalist.designlen,
			"bean.rddia" : datalist.rddia,
			"bean.memo" : datalist.memo
	    };
	}
	if(_bizname=="projectsupport" || _bizname=="stope_projectsupport"){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			gsid:$('#'+selectedRowId+"_gsid").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {  
			"bean.psid" : datalist.psid,
			"bean.projectid" : datalist.projectid,
			"bean.pslength" : datalist.pslength
		}; 
	}
	if(_bizname=="stope"){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			minemethod:$('#'+selectedRowId+"_minemethod").val(),
			oretype:$('#'+selectedRowId+"_oretype").val(),
			rockhardness:$('#'+selectedRowId+"_rockhardness").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {
			"bean.parentid" : datalist.parentid,
			"bean.projectid" : datalist.projectid,
			"bean.minename" : datalist.minename,
			"bean.minemethod" : datalist.minemethod,
			"bean.oretype" : datalist.oretype,
			"bean.rockhardness" : datalist.rockhardness,
			"bean.georeserve" : datalist.georeserve==""?"0":datalist.georeserve,
			"bean.geograde" : datalist.geograde==""?"0":datalist.geograde,
			"bean.lostratio" : datalist.lostratio==""?"0":datalist.lostratio,
			"bean.ratedilution" : datalist.ratedilution==""?"0":datalist.ratedilution,
			"bean.minereserve" : datalist.minereserve==""?"0":datalist.minereserve,
			"bean.designgrade" : datalist.designgrade==""?"0":datalist.designgrade,
			"bean.prodcapacity" : datalist.prodcapacity==""?"0":datalist.prodcapacity,
			"bean.perreserve" : datalist.perreserve==""?"0":datalist.perreserve,
			"bean.holereserve" : datalist.holereserve==""?"0":datalist.holereserve,
			"bean.memo" : datalist.memo
	    };
	}
	if(_bizname=="projectfill"){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			filltype:$('#'+selectedRowId+"_filltype").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {
			"bean.pfid" : datalist.pfid,
			"bean.projectid" : datalist.projectid,
			"bean.filltype" : datalist.filltype,
			"bean.fillreserve" : datalist.fillreserve,
			"bean.demo" : datalist.demo
	    };
	}
	if(_bizname=="roadway_installinfo" || _bizname=="stope_installinfo"){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			installtype:$('#'+selectedRowId+"_installtype").val(),
			units:$('#'+selectedRowId+"_units").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {
			"bean.parentid" : datalist.parentid,
			"bean.projectid" : datalist.projectid,
			"bean.projectname" : datalist.projectname,
			"bean.installtype" : datalist.installtype,
			"bean.installcontent" : datalist.installcontent,
			"bean.units" : datalist.units,
			"bean.projectamount" : datalist.projectamount==""?"0":datalist.projectamount,
			"bean.demo" : datalist.demo
		}; 
	}
	if(_bizname=="other"){
		$("#"+_bizname+"_grid").jqGrid('setRowData',selectedRowId,{
			worktype:$('#'+selectedRowId+"_worktype").val(),
			units:$('#'+selectedRowId+"_units").val()
		});
		var datalist = $("#"+_bizname+"_grid").jqGrid('getRowData', selectedRowId);
		return {
			"bean.parentid" : datalist.parentid,
			"bean.projectid" : datalist.projectid,
			"bean.itemname" : datalist.itemname,
			"bean.projectname" : datalist.projectname,
			"bean.worktype" : datalist.worktype,
			"bean.workdesc" : datalist.workdesc,
			"bean.projectamount" : datalist.projectamount==""?"0":datalist.projectamount,
			"bean.units" : datalist.units,
			"bean.memo" : datalist.memo
	    };
	}
}
function initEditGridRowData(_bizname,data,selectedRowId){
	if(_bizname=="roadway"){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			parentid : dataJson.parentid,
			projectid : dataJson.projectid,
			rdname : datalist.rdname,
			jjtype : datalist.jjtype,
			rdtype : datalist.rdtype,
			rockhardness : datalist.rockhardness,
			section : datalist.section,
			onlysection : datalist.onlysection,
			sectionshape : datalist.sectionshape,
			wateryield : datalist.wateryield,
			temperature : datalist.temperature,
			diameter : datalist.diameter,
			designlen : datalist.designlen,
			rddia : datalist.rddia,
			memo : datalist.memo
	    };
	}
	if(_bizname=="projectsupport" || _bizname=="stope_projectsupport"){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			psid : dataJson.psid,
			projectid : datalist.projectid,
			pslength : datalist.pslength
        };
	}
	if(_bizname=="stope"){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			parentid : dataJson.parentid,
			projectid : dataJson.projectid,
			minename : datalist.minename,
			minemethod : datalist.minemethod,
			oretype : datalist.oretype,
			rockhardness : datalist.rockhardness,
			georeserve : datalist.georeserve,
			geograde : datalist.geograde,
			lostratio : datalist.lostratio,
			ratedilution : datalist.ratedilution,
			minereserve : datalist.minereserve,
			designgrade : datalist.designgrade,
			prodcapacity : datalist.prodcapacity,
			perreserve : datalist.perreserve,
			holereserve : datalist.holereserve,
			memo : datalist.memo
	    };
	}
	if(_bizname=="projectfill"){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			pfid : dataJson.pfid,
			projectid : datalist.projectid,
			filltype : datalist.filltype,
			fillreserve : datalist.fillreserve,
			demo : datalist.demo
        };
	}
	if(_bizname=="roadway_installinfo" || _bizname=="stope_installinfo"){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			parentid : dataJson.parentid,
			projectid : dataJson.projectid,
			projectname : datalist.projectname,
			installtype : datalist.installtype,
			installcontent : datalist.installcontent,
			units : datalist.units,
			projectamount : datalist.projectamount,
			demo : datalist.demo
	    };
	}
	if(_bizname=="other"){
		var datalist = getEditGridParams(_bizname,selectedRowId);
		var dataJson = eval(data);
		return dataRow = {
			parentid : dataJson.parentid,
			projectid : dataJson.projectid,
			itemname : datalist.itemname,
			projectname : datalist.projectname,
			worktype : datalist.worktype,
			workdesc : datalist.workdesc,
			projectamount : datalist.projectamount,
			units : datalist.units,
			memo : datalist.memo
	    };
	}
}

function _openDialog4Adding(_bizname){
	if(_bizname=="roadway" || _bizname=="stope" || _bizname=="other"){
		var srcNode = BOTree1.getSelectedNodes();
		if(srcNode == null||srcNode==''||srcNode[0].name==undefined||srcNode[0].level=='0'){
			alertMsg('请选择中段或分段');
			return;
		}
		addrow(_bizname);
	}else if(_bizname=="projectsupport"){
		var selectedRowId = $("#roadway_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
			alertMsg("请先选择一条巷道信息!");
			return;
		}
		var rowData = $("#roadway_grid").jqGrid('getRowData',selectedRowId);
		if(rowData.projectid == ''){
			alertMsg("选择的巷道信息未保存!");
			return;
		}
		/*
		var ids = $("#projectsupport_grid").getDataIDs();
		if(ids.length >= 1){
			alertMsg("只能添加一条支护信息!");
			return;
		}*/
		addrow(_bizname);
	}else if(_bizname=="stope_projectsupport"){
		var selectedRowId = $("#stope_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
			alertMsg("请先选择一条采场信息!");
			return;
		}
		var rowData = $("#stope_grid").jqGrid('getRowData',selectedRowId);
		if(rowData.projectid == ''){
			alertMsg("选择的采场信息未保存!");
			return;
		}
		/*
		var ids = $("#stope_projectsupport_grid").getDataIDs();
		if(ids.length >= 1){
			alertMsg("只能添加一条支护信息!");
			return;
		}*/
		addrow(_bizname);
	}else if(_bizname=="projectfill"){
		var selectedRowId = $("#stope_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
			alertMsg("请先选择一条采场信息!");
			return;
		}
		var rowData = $("#stope_grid").jqGrid('getRowData',selectedRowId);
		if(rowData.projectid == ''){
			alertMsg("选择的采场信息未保存!");
			return;
		}
		var ids = $("#projectfill_grid").getDataIDs();
		if(ids.length >= 1){
			alertMsg("只能添加一条充填信息!");
			return;
		}
		addrow(_bizname);
	}else if(_bizname=="roadway_installinfo"){
		var selectedRowId = $("#roadway_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
			alertMsg("请先选择一条巷道信息!");
			return;
		}
		var rowData = $("#roadway_grid").jqGrid('getRowData',selectedRowId);
		if(rowData.projectid == ''){
			alertMsg("选择的巷道信息未保存!");
			return;
		}
		addrow(_bizname);
	}else if(_bizname=="stope_installinfo"){
		var selectedRowId = $("#stope_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
			alertMsg("请先选择一条采场信息!");
			return;
		}
		var rowData = $("#stope_grid").jqGrid('getRowData',selectedRowId);
		if(rowData.projectid == ''){
			alertMsg("选择的采场信息未保存!");
			return;
		}
		addrow(_bizname);
	}else{
		var consoleDlg = $("#"+_bizname+"_info");  
		var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane"); 
		
		$("#"+_bizname+"_form").Validform().resetForm();
		consoleDlg.find(".info").css('display','none');
		consoleDlg.find("input").removeAttr("disabled").val("");  
		consoleDlg.find("select").removeAttr("disabled").val("");//20150417罗涛添加限制下拉
		consoleDlg.find("textarea").removeAttr("disabled").val("");
		consoleDlg.trigger('domi.add.before');
		
		dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
		dialogButtonPanel.find("button:contains('创建')").show();  
		dialogButtonPanel.find("button").addClass('btn btn-xs btn-primary');
		
		consoleDlg.dialog("option", "title", "创建信息").dialog("open");  
		consoleDlg.trigger('domi.add.after');
	}
}

/**
 * 新增行
 * @param _bizname
 */
function addrow(_bizname){
	//获取表格的初始化model  
	var colModel = $("#"+_bizname+"_grid").jqGrid().getGridParam("colModel") ;  
	var cellLenth = colModel.length;
	//设置所有列可编辑（如果特定的列不可编辑，则需判断）  
	/*for (var i = 0; i < cellLenth; i++) {
		if(i==1 || i==14)
			colModel[i].editable = false;
		else
			colModel[i].editable = true;
	}*/
    var newRow = JSON.stringify(colModel);
	//获得所有行的ID数组
    var ids = $("#"+_bizname+"_grid").jqGrid("getDataIDs");
    //如果jqgrid中没有数据 定义行号为1 ，否则取当前最大行号+1
    var newrowid = (ids.length ==0 ? 1: Math.max.apply(Math,ids)+1);
    //设置grid单元格不可编辑 （防止在添加时，用户修改其他非添加行的数据）  
    $("#"+_bizname+"_grid").setGridParam({cellEdit:false});
    //将新行追加到表格头部  
    $("#"+_bizname+"_grid").jqGrid("addRowData", newrowid, newRow, "first");
    //设置单元格的值
    if(_bizname=="roadway" || _bizname=="stope" || _bizname=="other"){
    	var srcNode = BOTree1.getSelectedNodes();
	    $("#"+_bizname+"_grid").setCell(newrowid,2,srcNode[0].id);
    }else if(_bizname=="projectsupport"){
    	var selectedRowId = $("#roadway_grid").jqGrid("getGridParam", "selrow");
    	var rowData = $("#roadway_grid").jqGrid('getRowData',selectedRowId);
    	$("#"+_bizname+"_grid").setCell(newrowid,3,rowData.projectid);
    }else if(_bizname=="stope_projectsupport" || _bizname=="projectfill"){
    	var selectedRowId = $("#stope_grid").jqGrid("getGridParam", "selrow");
    	var rowData = $("#stope_grid").jqGrid('getRowData',selectedRowId);
    	$("#"+_bizname+"_grid").setCell(newrowid,3,rowData.projectid);
    }else if(_bizname=="roadway_installinfo"){
    	var selectedRowId = $("#roadway_grid").jqGrid("getGridParam", "selrow");
    	var rowData = $("#roadway_grid").jqGrid('getRowData',selectedRowId);
    	$("#"+_bizname+"_grid").setCell(newrowid,2,rowData.projectid);
    }else if(_bizname=="stope_installinfo"){
    	var selectedRowId = $("#stope_grid").jqGrid("getGridParam", "selrow");
    	var rowData = $("#stope_grid").jqGrid('getRowData',selectedRowId);
    	$("#"+_bizname+"_grid").setCell(newrowid,2,rowData.projectid);
    }
    //设置grid单元格可编辑（防止追加行后，可编辑列无法编辑）  
    $("#"+_bizname+"_grid").jqGrid("editRow", newrowid, false);
    updateEditIcons(_bizname);
    _doeditmodify(_bizname,newrowid);
    $("#"+_bizname+"_grid").setSelection(newrowid,true);
}