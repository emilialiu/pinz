<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.autocomplete.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/jquery.autocomplete.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/common/autocomplete/auto.js"></script>

<script>
	var scripts = [null, null];

	$('.page-content-area').ace_ajax('loadScripts', scripts, function(){});
	function dosearch(){
		alertMsg(document.getElementById("feetype").value);
	}
</script>

<table>
	<tr>
		<td>
			测试自动完成
			:
		</td>
		<td>
				<dmtag:automanager name="feetype" table="T_auto" cssClass="text"
				cssStyle="width:150px" maxsize="10" cssWidth="150" parameter=""
				autotext="name"></dmtag:automanager>
			<!-- table:表名;combovalue:表中主键；combotext：表中名称；parameter：参数 -->
		</td>
		<td>
			<button class="btn btn-xs btn-success bigger no-border" onclick="javascript:dosearch()">
				<i class="ace-icon fa fa-search"></i>
				查询
			</button>
		</td>
	</tr>
</table>
<%@include file="/pub/biz/footerlist.jsp" %>