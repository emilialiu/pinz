<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>

<script type="text/javascript">
/*	var scripts = [null, null],
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
	
	$(function(){
		$('#cc').combogrid({ 
			panelWidth:450, 
			value:'1', 
			idField:'id', 
			textField:'name', 
			pagination:true,
			url:rootpath+'/dm/combogrid/gridlist.action', 
			columns:[[ 
				{field:'id',title:'Id',width:60}, 
				{field:'name',title:'Name',width:100}, 
				{field:'remark',title:'Remark',width:120}
			]] 
		}); 
	});*/
	
	function dosearch(){
		alert($("#cc").combogrid('getValue'));
	}
</script>
<table>
	<tr>
		<td>
			下拉列表
			:
		</td>
		<td>
			<dmtag:combogridmanager name="cc" cssStyle="width:250px;"></dmtag:combogridmanager>
		</td>
	</tr>
</table>
