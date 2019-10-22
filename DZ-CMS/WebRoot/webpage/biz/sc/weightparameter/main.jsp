<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
		<title>业务参数配置</title>
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->				
				<div class="input-group col-sm-8">
					<span class="input-group-addon">
						<i class="ace-icon fa fa-search"></i>
					</span>					
					<input type="text" id="t_sc_weightparameter_text_search" class="form-control search-query" placeholder="<s:text name="dmmes.sc.weightparameter.parametersName" />" />
					<span class="input-group-btn">
						<button id="t_sc_weightparameter_btn_search" type="button" class="btn btn-purple btn-sm">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.query" />
						</button>
						<button id="t_sc_weightparameter_btn_advsearch" style="margin-left:10px;" class="btn btn-pink btn-sm">
							<span class="ace-icon fa fa-filter icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.Advancedquery" />
						</button>
					</span>
				</div>
			</div><!-- /.col -->
			<div class="col-xs-12">				
				<div id="t_sc_weightparameter_grid_box" class="widget-box widget-color-blue">
					<div class="widget-header">
						<h5 class="widget-title"><i class="ace-icon fa fa-list"></i><s:text name="dmmes.sc.weightparameter.title" /></h5>
						<div class="widget-toolbar">
							<a href="#" data-action="fullscreen" class="white">
								<i class="ace-icon fa fa-expand"></i>
							</a>
							<a href="#" id="t_sc_weightparameter_grid_box_filter" class="white" data-action="filter">
								<i class="ace-icon fa fa-filter bigger"></i>
							</a>
							<a href="#" data-action="collapse" class="white">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
						<div id="t_sc_weightparameter_grid_btn_add" class="widget-toolbar no-border">
							<button class="btn btn-xs btn-primary bigger no-border">
								<i class="ace-icon fa fa-plus"></i>
								<s:text name="button.add" />
							</button>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main no-padding">
							<table id="t_sc_weightparameter_grid"></table>
					
							<div id="t_sc_weightparameter_grid_pager"></div>
						</div>
					</div>
				</div>
			</div><!-- /.col -->
				
			<!-- PAGE CONTENT ENDS -->
		</div><!-- /.row -->
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript">
 var pub_operation='<s:text name="pub.operation" />';
 var sc_weightparameter_parametersName ='<s:text name="dmmes.sc.weightparameter.parametersName" />';
 var sc_weightparameter_parametersValues ='<s:text name="dmmes.sc.weightparameter.parametersValues" />';
 var sc_weightparameter_parametersMust ='<s:text name="dmmes.sc.weightparameter.parametersMust" />';
 var sc_weightparameter_parametersValid ='<s:text name="dmmes.sc.weightparameter.parametersValid" />';
 var qt_assaysample_unit='<s:text name="dmmes.qt.assaysample.unit" />';
 var qt_assaysample_memo='<s:text name="dmmes.qt.assaysample.memo" />';
 var qt_assaysample_modifydate='<s:text name="dmmes.qt.assaysample.modifydate" />';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sc/weightparameter/bizgrid.js"></script>
