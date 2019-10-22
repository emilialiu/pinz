<%@ page language="java" pageEncoding="utf-8"%>
<title>EXCEL导入</title>
<div>
	<form method="post" class="form-horizontal">
		<table>
			<tr>
				<td>模板名称:</td>
				<td>
					<div id="modelidT" class="col-xs-12" />
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td>Excel文件:</td>
				<td><input type="file" name="filen" id="filen" style="margin-left:12px;width: 280px;"></input></td>
				<td>
					<div style="margin-left:30px;">
						<input type="button" value="导入" onclick="doImport();"/>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>