<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" /> 
		<title>班组选择</title>
	<body>
		<div class="row">
			<div class="col-xs-12">
					<div class="input-group col-sm-8">
						<span class="input-group-addon">
							<i class="ace-icon fa fa-search"></i>
						</span>					
						<input type="text" id="t_sc_teamtechprocess_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sc.teamtechprocess.teamname" />" />
						<span class="input-group-btn">
							<button id="t_sc_teamtechprocess_btn_search" type="button" class="btn btn-purple btn-sm">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
								<s:text name="button.query" /> 
							</button>
							<button id="t_sc_teamtechprocess_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
								<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
								<s:text name="button.Advancedquery" />
							</button>
						</span>
					</div>
							
				</div>
			<!-- /.col -->
			<div class="col-xs-12">				
				<div id="t_sc_teamtechprocess_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.teamtechprocess.teamname" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="orange2">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_sc_teamtechprocess_grid"></table>
					
							<div id="t_sc_teamtechprocess_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
		var sc_teamtechprocess_teamname = '<s:text name="dmmes.sc.teamtechprocess.teamname" />';
	 	var sc_teamtechprocess_teamtypename = '<s:text name="dmmes.sc.teamtechprocess.teamtypename" />';
		var multiselect = false;
		var procid = "<%=request.getParameter("procid")%>";
</script>
<script type="text/javascript" src="./bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script> 
<script type="text/javascript">
 	function onSave() {
	var data = [];
	var rowdata;
	if(multiselect){//多选
		var selectedRowIds = $("#t_sc_teamtechprocess_grid").jqGrid("getGridParam", "selarrrow");
		if(selectedRowIds==''||selectedRowIds==null||selectedRowIds==undefined){
			alertMsg("请至少选择一个班组!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#t_sc_teamtechprocess_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#t_sc_teamtechprocess_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
	    	alertMsg("请选择一个班组!");
	        return false;
	    }
	    rowdata = $("#t_sc_teamtechprocess_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	}
	try{
		parent.PageObject.itemMap['selectGxTeam'].callback(data);
	}catch(e){
		parent.closewin('selectGxTeam');
	}
	parent.closewin('selectGxTeam');
}

function uncheck() {
	parent.PageObject.itemMap['selectGxTeam'].callback([{"teamid":"","teamname":""}]);
	parent.closewin('selectGxTeam');
}
</script>
</html>