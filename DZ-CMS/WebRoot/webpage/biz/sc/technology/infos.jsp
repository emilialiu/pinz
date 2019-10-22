<%@ page language="java" pageEncoding="utf-8"%>
<style type="text/css">
.dropdown-menu {
min-width: 42px;
}
div#f_funcnameMsg { color: #f2a696;display: inline }
</style>
<script>
</script>
<div id="biz_info" >
	<form id="form" method="post" class="form-horizontal">
		<table width="100%" border="0" align="center">
			<tr>
				<td width="6px">
					<nobr> <s:text name="dmmes.sc.technology.parentname" />：</nobr>
				</td>
				<td>
				<input type="text" name="parentname" id="parentname"  style="width:280px" readonly="readonly" />
				<input name="actiontype" id="actiontype" hidden="hidden"></input>
				<input name="bean.parentid" id="parentid" hidden="hidden"></input>
				<input name="bean.level" id="level" hidden="hidden"></input>
				<input name="bean.techid" id="techid" hidden="hidden"></input>
				<input name="bean.shortname" id="shortname" hidden="hidden"></input>
			
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> <s:text name="dmmes.sc.technology.techname" />：</nobr>
				</td>
				<td>
					<input maxlength="25" id="techname" style="width:280px"
						name="bean.techname" type="text"
						></input><font color="#f2a696">*</font><div id="f_funcnameMsg" style="display: none">工艺名称信息必填</div>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> <s:text name="dmmes.sc.technology.technameen" />：</nobr>
				</td>
				<td>
					<input maxlength="25" id="technameen" style="width:280px"
						name="bean.technameen" type="text"
						></input><font color="#f2a696">*</font><div id="f_funcnameMsg" style="display: none">工艺名称信息必填</div>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> <s:text name="dmmes.sc.technology.techcode" />：</nobr>
				</td>
				<td>
					<input maxlength="20" type="text" id="techcode" style="width:280px"
						name="bean.techcode"></input>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td>
					<nobr> <s:text name="dmmes.sc.technology.serialno" />：</nobr>
				</td>
				<td>
					<input  maxlength="9" name="bean.serialno" id="serialno" style="width:61px"
						onkeyup="this.value=this.value.replace(/\D/g,'')" 
						data-options="required:true" type="input"></input>
				</td>
				<td width="6px">
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td valign="top">
					<nobr> <s:text name="dmmes.sc.technology.memo" />：</nobr>
				</td>
				<td>
					<textarea  name="bean.memo" id="memo" style="width:280px;height:80px"
						onkeyup="if(this.value.length > 200)
    								   {this.value=this.value.substr(0,200)}"
						></textarea>
				</td>
			</tr>
		</table>
		
	</form>
</div>
<br>
<div region="south" border="false" style="text-align: left">
<a href="javascript:addChildNode()" class="btn btn-sm btn-primary"
		iconCls="icon-add">
		<i class="ace-icon fa fa-plus bigger-100"></i>
		<s:text name="button.add" /></a>
	<a href="javascript:submit_delete()" class="btn btn-sm btn-primary"
		iconCls="icon-remove">
		<i class="ace-icon fa fa-trash-o bigger-100"></i>
		<s:text name="button.delete" /></a>
	<a href="javascript:onSave()" class="btn btn-sm btn-primary"
		iconCls="icon-save">
		<i class="ace-icon fa fa-check bigger-100"></i>
		<s:text name="dmmes.sc.technology.save" /></a>
</div>
