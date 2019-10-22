<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>
<style type="text/css">
div#f_deptname { color: #f2a696;display: inline }
div#f_deptcode { color: #f2a696;display: inline }
</style>
<div region="center" border="false">
	<form method="post" id="dept">
		<table border="0">
			<input name="actiontype" id="actiontype" hidden="hidden"></input>
			<input name="deptid" id="deptid" hidden="hidden"></input>
			<input name="orglevel" id="orglevel" type="hidden"/>
			<tr>
				<td align="right">
					上级机构：
				</td>
				<td>
					<input hidden="hidden" name="bean.parentdeptid" id="parentdeptid"></input>
					<input type="text" id="parentName" style="width: 320px" readonly="true" />
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					机构名称：
				</td>
				<td>
					<input name="deptname" style="width:320px" max="25" id="deptname" type="text" valid="required|limit"
						onkeyup="if(this.value.length > 25){this.value=this.value.substr(0,25)}"></input>
					<font color="#f2a696">*</font><div id="f_deptname" style="visibility: hidden">机构名称信息必填</div>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					机构编码：
				</td>
				<td>
					<input name="deptCode" style="width:320px" max="50" id="deptcode" type="text" 
						onkeyup="if(this.value.length > 10){this.value=this.value.substr(0,10)}"></input>
					<font color="#f2a696">*</font><div id="f_deptcode" style="visibility: hidden">机构编码信息必填</div>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					联系电话：
				</td>
				<td>
					<input name="tel" style="width:320px" maxlength="11" onkeyup="this.value=this.value.replace(/\D/g,'')" 
						id="tel" type="text"></input>
				</td>
				</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					传真：
				</td>
				<td>
					<input name="fax" style="width:320px" id="fax" 
						type="text"></input>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					排序：
				</td>
				<td>
					<input name="orderno" id="orderno" style="width:60px"
						onkeyup="this.value=this.value.replace(/\D/g,'')"  type="text"></input>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right">
					 机构类型： 
				</td>
				<td>
					<dmtag:sourcemanager sourcename="ZZJGLX" name="orgtype"
						cssStyle="width:320px" isnull="false"></dmtag:sourcemanager>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td align="right" valign="top">
					备注：
				</td>
				<td colspan="3">
					<textarea  name="bean.memo" id="memo" style="width:380px;height:80px"
						onkeyup="if(this.value.length > 200){this.value=this.value.substr(0,200)}"
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
		新增</a>
	<a href="javascript:submit_delete()" class="btn btn-sm btn-primary" iconCls="icon-remove">
		<i class="ace-icon fa fa-trash-o bigger-100"></i>
		删除</a>
	<a href="javascript:submitme()" class="btn btn-sm btn-primary" iconCls="icon-save">
		<i class="ace-icon fa fa-check bigger-100"></i>
		保存</a>
</div>
