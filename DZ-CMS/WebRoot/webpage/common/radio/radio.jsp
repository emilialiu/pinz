<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/jquery/botree.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/botree.js"></script>
<style type="text/css">
	ul.ztree {
		margin-top: 10px;
		border: 1px solid #617775;
		background: #f0f6e4;
		width:220px;
		height:360px;
		overflow-y:scroll;
		overflow-x:auto;
	}
</style>
<script type="text/javascript">
	var scripts = [null, null];

	$('.page-content-area').ace_ajax('loadScripts', scripts, function(){});

	var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};

	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#feetype");
		cityObj.attr("value", v);
	}

	function showMenu() {
		var cityObj = $("#feetype");
		var cityOffset = $("#feetype").offset();
		var leftObj = $("#sidebar");
		var topObj_title = $("#navbar");
		var topObj_navigate = $("#breadcrumbs");
		$("#treediv").css({left:cityOffset.left - leftObj.outerWidth() + "px", top:cityOffset.top + cityObj.outerHeight() - (topObj_title.top + topObj_navigate.top) + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#treediv").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "feetype" || event.target.id == "treediv" || event.target.id == "treedemo" || $(event.target).parents("#treediv").length>0)) {
			hideMenu();
		}
	}

	function dosearch(){
		alertMsg($("#feetype").val());
	}
	
</script>
<table>
	<tr>
		<td>
			下拉单选
			:
		</td>
		<td>
				<dmtag:selectmemumanager table="T_checkbox" cssStyle="width:150px" checkdiv="treediv" checktree="treedemo"
				name="feetype" checkboxvalue="id" checkboxtext="text" onclick="showMenu();" treewidth="150px" treeheight="300px"
				parentid="parentid" parameter="text like '123%'"></dmtag:selectmemumanager>
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

<script type="text/javascript">
	$(document).ready(function(){
		$.fn.zTree.init($("#treedemo"), setting, zNodes);
	});
</script>
