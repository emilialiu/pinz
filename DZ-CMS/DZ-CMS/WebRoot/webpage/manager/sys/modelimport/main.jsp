<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/pub/biz/headlist.jsp" %>
	<script type="text/javascript">
		var scripts = [null,null];
		$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		//页面脚本
		});
		 function replaceSpace(str){
		        return str.replace(/\s/g,'');
		}
	</script>
	<title>模版导入</title>
	<div class="row">
		<div class="col-sm-8">
				<div class="widget-box widget-color-blue">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-list"></i>导入EXCEL信息</h4>
					</div>
					<div class="widget-body" style="height: 543px;overflow:auto;">
						<div class="widget-main padding-8" id="rightid">
							<%@include file="/manager/sys/modelimport/import.jsp"%>
						</div>
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/manager/sys/modelimport/import.js"></script>
<%@include file="/pub/biz/footerlist.jsp" %>