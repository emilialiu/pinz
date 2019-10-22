<%@ page language="java" pageEncoding="utf-8"%>
<style type="text/css">
.dropdown-menu {
min-width: 42px;
}
div#f_funcnameMsg { color: #f2a696;display: inline }
</style>
<script>
function onchangeTblx(obj){
	var tblxid = obj.id;
	  var consoleDlg = $("#"+obj.id);
	  //consoleDlg.html();
	  $('#funcicon').html(consoleDlg.html());
	//console.info(consoleDlg.html());	
}
/*
$(function() {
	var u_tblx = $("#u_tblx").html();
	if(u_tblx.trim()==''){
		$("#u_tblx").html("<i class='menu-icon fa fa-desktop'></i>");
	}
});
*/
function onchangeTbjb(obj){
	if($("#s_tbjb").val()>0){
		var tbjb = $("#s_tbjb").val();
		$.ajax({
			url:rootpath + "/manager/sys/func/changetbjb.action", 
			cache: false,
			data:{"tbjb": tbjb}, 
			type: 'post',
			success:function (data) {
				$("#u_tblx").html(data);
			}, 
			error:function (data) {
				alertMsg("操作失败");
			}
		});
	}
}
</script>
<div id="biz_info" >
	<form id="form" method="post" class="form-horizontal">
		<table width="100%" border="0" align="center">
			<tr>
				<td width="6px">
					<nobr> 上级功能：</nobr>
				</td>
				<td>
					<input type="text" id="parentfuncname" style="width:280px" readonly="readonly"/>
					<input name="bean.funccode" id="funccode" hidden="hidden"></input>
					<input name="actiontype" id="actiontype" hidden="hidden"></input>
					<input name="bean.parentfunccode" id="parentfunccode" hidden="hidden"></input>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> 功能名称：</nobr>
				</td>
				<td>
					<input maxlength="25" id="funcname" style="width:280px"
						name="bean.funcname" type="text"
						></input><font color="#f2a696">*</font><div id="f_funcnameMsg" style="display: none">功能名称信息必填</div>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> 功能英文名称：</nobr>
				</td>
				<td>
					<input maxlength="100" id="funcnameen" style="width:280px"
						name="bean.funcnameen" type="text"
						></input><font color="#f2a696">*</font><div id="f_funcnameMsg" style="display: none">功能英文名称信息必填</div>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> 功能路径：</nobr>
				</td>
				<td>
					<input maxlength="100" type="text" id="url" style="width:280px"
						name="bean.url"></input>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> 图标级别：</nobr>
				</td>
				<td>
				<select id="s_tbjb" onclick="onchangeTbjb(this)">
						<option value="0">请选择</option>
						<option value="1">一级图标</option>
						<option value="2">二级图标</option>
						<option value="3">三级图标</option>				
				
				</select>&nbsp;&nbsp;&nbsp;&nbsp;
				 图标样式：
				
				<!-- 	<input maxlength="100" type="text" id="funcicon" style="width:280px"
						name="bean.funcicon" ></input>-->
					<div class="btn-group">
						<a data-toggle="dropdown" id="funcicon" name="bean.funcicon" class="btn btn-primary btn-white dropdown-toggle" id="funciconShow" aria-expanded="false">
							<i class="menu-icon fa fa-desktop"></i>
						</a>
						<ul class="dropdown-menu" id="u_tblx">
							<li>
								<a onclick="onchangeTblx(this)" id="TBLX001"><i class="menu-icon fa fa-desktop"></i></a>
							</li>
							<li>
								<a onclick="onchangeTblx(this)" id="TBLX002"><i class="menu-icon fa fa-cog"></i></a>
							</li>
						</ul>
					</div>  
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td width="6px">
					<nobr> 功能类型：</nobr>
				</td>
				<td>
				<select id="functype" name="bean.functype">
				<option value="0" selected = "selected">功能</option>
				<option value="1">操作</option>
				</select>
				</td>
			</tr>
			<tr>
			<td height="10px"></td>
			</tr>
			<tr>
				<td>
					<nobr> 排列顺序：</nobr>
				</td>
				<td>
					<input  name="bean.orderno" id="orderno" style="width:61px"
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
					<nobr> 备注：</nobr>
				</td>
				<td>
					<textarea  name="bean.memo" id="memo" style="width:280px;height:80px"
						onkeyup="if(this.value.length > 200)
    								   {this.value=this.value.substr(0,200)}"
						></textarea>
				</td>
			</tr>
		</table>
		<input name="bean.funcnamepy" id="funcnamepy" hidden="hidden" ></input>
		<input name="bean.funcnamepyshort" id="funcnamepyshort"  hidden="hidden" ></input>
		<input name="bean.memopy" id="memopy"  hidden="hidden" ></input>
		<input name="bean.memopyshort" id="memopyshort" hidden="hidden" ></input>
	</form>
</div>
<br>
<div region="south" border="false" style="text-align: left">
<a href="javascript:addChildNode()" class="btn btn-sm btn-primary"
		iconCls="icon-add">
		<i class="ace-icon fa fa-plus bigger-100"></i>
		新增</a>
	<a href="javascript:submit_delete()" class="btn btn-sm btn-primary"
		iconCls="icon-remove">
		<i class="ace-icon fa fa-trash-o bigger-100"></i>
		删除</a>
	<a href="javascript:onSave()" class="btn btn-sm btn-primary"
		iconCls="icon-save">
		<i class="ace-icon fa fa-check bigger-100"></i>
		保存</a>
</div>
