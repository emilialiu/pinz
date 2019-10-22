<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">
	var scripts = [null, null];

	$('.page-content-area').ace_ajax('loadScripts', scripts, function(){});
	
	function dosearch(){
		alertMsg($('input[name="feetype"]:checked').val());
	}
</script>
<table>
	<tr>
		<td>
			测试单选框
			:
		</td>
		<td>
			<dmtag:radiomanager table="T_combo" sclass="boui-radio" name="feetype" radiovalue="id" radiotext="text" parameter="text like '123%'"/>
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
