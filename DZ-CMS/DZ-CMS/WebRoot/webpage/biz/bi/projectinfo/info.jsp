<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>
<style type="text/css">
div#f_projectname { color: #f2a696;display: inline }
input[type=checkbox].ace.ace-switch.ace-switch-lt+.lbl::before {
	content: "未完成\a0\a0已完成";
}
input[type=checkbox].ace.ace-switch.ace-switch-leaf+.lbl::before {
	content: "否\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0\a0是";
}
</style>
<div region="center" border="false">
	<form method="post" id="dept">
		<table border="0">
			<input name="actiontype" id="actiontype" hidden="hidden" />
			<input name="projectid" id="projectid" hidden="hidden" />
			<input name="parentitemid" id="parentitemid" type="hidden" />
			<input name="projectlevel" id="projectlevel" type="hidden"/>
			<tr>
				<td align="right">
					<s:text name="dmmes.bi.projectinfo.parentname" />：
				</td>
				<td>
					<input type="text" id="parentname" style="width: 320px" readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					<s:text name="dmmes.bi.projectinfo.projectname" />：
				</td>
				<td>
					<input name="projectname" style="width:320px" max="50"
						id="projectname" type="text" valid="required|limit"
						onkeyup="if(this.value.length > 50){this.value=this.value.substr(0,50)}"
						></input>
					<font color="#f2a696">*</font><div id="f_projectname" style="visibility: hidden">工程名称信息必填</div>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
				<td height="10px"><font color="#ff0000">(提示：只有最后一个节点才能设置为叶子节点，比如爆堆；爆堆的命名规则为 BD201801-10001* )</font></td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr><%-- 
			<tr>
				<td align="right">
					<s:text name="dmmes.bi.projectinfo.projectcode" />：
				</td>
				<td>
					<input name="projectcode" style="width:320px" max="50"
						id="projectcode" type="text" valid="required|limit"
						onkeyup="if(this.value.length > 50){this.value=this.value.substr(0,50)}"
						></input>
					<font color="#f2a696">*</font><div id="f_projectname" style="visibility: hidden">工程编码信息必填</div>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr> --%>
			<tr>
				<td align="right">
					是否叶子节点：
				</td>
				<td>
					<input type="radio" name="isleaf" value="0"/>否
					<input type="radio" name="isleaf" value="1" checked />是
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					工程状态：
				</td>
				<td>
					<input type="checkbox" checked="" id="projectstatus" class="FormElement ace ace-switch ace-switch-lt"><span class="lbl"></span> 
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					<s:text name="dmmes.bi.projectinfo.indexno" />：
				</td>
				<td>
					<input name="indexno" id="indexno" style="width:60px"
						onkeyup="this.value=this.value.replace(/\D/g,'')" type="text"></input>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right" valign="top">
					<s:text name="dmmes.bi.projectinfo.memo" />：
				</td>
				<td colspan="3">
					<textarea name="memo" id="memo" style="width:380px;height:80px"
						onkeyup="if(this.value.length > 500){this.value=this.value.substr(0,500)}"
					></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
<br>
<div region="south" border="false" style="text-align: left">
	<a href="javascript:addChildNode()" class="btn btn-sm btn-primary" iconCls="icon-add">
		<i class="ace-icon fa fa-plus bigger-100"></i>
		<s:text name="button.add" /></a>
	<a href="javascript:submit_delete()" class="btn btn-sm btn-primary" iconCls="icon-remove">
		<i class="ace-icon fa fa-trash-o bigger-100"></i>
		<s:text name="button.delete" /></a>
	<a href="javascript:submitme()" class="btn btn-sm btn-primary" iconCls="icon-save">
		<i class="ace-icon fa fa-check bigger-100"></i>
		<s:text name="button.save" /></a>
</div>
