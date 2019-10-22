<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>
<script type="text/javascript">
	var scripts = [null, null];

	$('.page-content-area').ace_ajax('loadScripts', scripts, function(){});

	function dosearch(){
		var checked=[];
		$('input:checkbox:checked').each(function() { 
          		checked.push($(this).val()); 
     			}); 
     			alertMsg(checked);
	}
</script>

<table>
	<tr>
		<td>
			测试复选框
			:
		</td>
		<td>
			<dmtag:checkboxmanager table="T_combo" style="width:30px" sclass="boui-checkbox" name="feetype" checkboxvalue="id" checkboxtext="text" parameter="text like '123%'"/>
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