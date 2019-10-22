<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" /> 
		<title>班组选择</title>
	<body>
		<div class="row">
			<div class="col-xs-12">
					设备类型:<input name="catName" id="catName"  type="text" class="dfinput" style="width: 300px;" value=""  onfocus="showMenu()" onclick="showMenu()" readonly="readonly"/>  
               		<input type="hidden" name="catId" id="catId"/> 
					<div class="input-group col-sm-8">
						<!-- 设备类型:<input name="catName" id="catName"  type="text" class="dfinput" style="width: 200px;" value=""  onfocus="showMenu()" onclick="showMenu()" readonly="readonly"/>  
                		<span id="catIdTip"></span>  
                		<input type="hidden" name="catId" id="catId"/>  -->
						<%-- <span class="input-group-addon">
							<i class="ace-icon fa fa-search"></i>
						</span>					
						<input type="text" id="t_sc_teamtechprocess_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.pd.drugbill.teamName" />" />
						<span class="input-group-btn">
							<button id="t_sc_teamtechprocess_btn_search" type="button" class="btn btn-purple btn-sm">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
								<s:text name="button.query" />
							</button>
							<button id="t_sc_teamtechprocess_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
								<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
								<s:text name="button.Advancedquery" />
							</button>
						</span> --%>
					</div>
							
				</div>
			<!-- /.col -->
			<div class="col-xs-12">				
				<div id="t_dm_csdetail_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i>设备点检标准详情</h5>
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
							<table id="t_dm_csdetail_grid"></table>
					
							<div id="t_dm_csdetail_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
				<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
		<div id="menuContent" class="menuContent"  
       		style="width:200;display: none; position: absolute;background-color: #F5F3F3;max-height:300px;height:expression(height>300?300px:height+px);overflow:auto;z-index:9999">  
	        <ul id="device_tree" class="ztree selectZtree" style="margin-top: 0; width: 300px;"></ul>  
	   </div> 
	</body>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 var pd_drugbill_teamName ='<s:text name="dmmes.pd.drugbill.teamName" />';
 var pd_drugbill_teamType ='<s:text name="dmmes.pd.drugbill.teamType" />';
 var pd_drugbill_team ='<s:text name="dmmes.pd.drugbill.team" />';
</script>
<script type="text/javascript">
		var multiselect =<%=request.getParameter("multiselect")%>;
		var dtname="<%=request.getParameter("dtname")%>";
		var dtid="<%=request.getParameter("dtid")%>";
		var vdevid="<%=request.getParameter("vdevid")%>";
		var devmodel="<%=request.getParameter("devmodel")%>";
		//给控件赋值
		$("#catName").val(dtname);
		$("#catId").val(dtid);
		//加载规格型号
		$.ajax( {  
			url : rootpath+"/webpage/biz/dm/checkstandard/selectDevModel.action", 
			type:'POST',
			data : {
				"bean.dtid":dtid,
				"bean.devmodel":devmodel
			},
			cache : false,
			async : false,
			error : function(data) {  
				alertErrorMsg("系统ajax交互错误");   
			},  
			success : function(data) {
				devmodel1 = eval('({'+data+'})');	
				for(var i in devmodel1){
					var objOption = new Option(devmodel1[i],i); 
					$("#devmodel")[0].add(objOption);
				}
			}  
		 });
		//alert(dtname+'*'+dtid+'*'+vdevid);
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/pub/select/similar_standards/bizgrid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/pub/select/similar_standards/tree.js"></script>
<script type="text/javascript">
 	function onSave() {
	var data = [];
	var rowdata;
	if(multiselect){ //多选
		var selectedRowIds = $("#t_dm_csdetail_grid").jqGrid("getGridParam", "selarrrow");
		if(selectedRowIds==''||selectedRowIds==null||selectedRowIds==undefined){
			alertMsg("请至少选择一个标准详情!");
			return false;
		}
		for(var i=0;i<selectedRowIds.length;i++){
			rowdata = $("#t_dm_csdetail_grid").jqGrid("getRowData", selectedRowIds[i]);
			data.push(rowdata);
		}
	}else{//单选
		var selectedRowId = $("#t_dm_csdetail_grid").jqGrid("getGridParam", "selrow");
		if(selectedRowId==''||selectedRowId==null||selectedRowId==undefined){
	    	alertMsg("请选择一个标准详情!");
	        return false;
	    }
	    rowdata = $("#t_dm_csdetail_grid").jqGrid("getRowData", selectedRowId);
		data.push(rowdata);
	} 
	try{
		parent.PageObject.itemMap['select_standarts'].callback(dtid,vdevid,data);
	}catch(e){
		parent.closewin('select_standarts');
	}
	parent.closewin('select_standarts');
}

function uncheck() {
	//parent.PageObject.itemMap['selectTeam'].callback([{"drugbill_bz":""}]);
	parent.closewin('select_standarts');
}
</script>
</html>