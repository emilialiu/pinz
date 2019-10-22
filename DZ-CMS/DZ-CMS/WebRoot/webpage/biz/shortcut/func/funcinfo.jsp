<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/head.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<div id="sys_role_func_info" >
	<input type="hidden" id="roleid" value="<%=request.getParameter("roleid") %>"/>
	<div class="col-xs-12">
		<div id="sys_role_func_grid_box" class="col-xs-12 widget-box widget-color-blue">
			<div class="widget-header">
				<h5 class="widget-title"><i class="ace-icon fa fa-sitemap"></i><s:text name="dmmes.shortcut.func.systemFunction" /></h5>
			</div>
			<div>
				<ul id="unfunc_tree" class="ztree" style="height: 350px;overflow:auto;"></ul>
			</div>
		</div>
	</div>
</div>
<%@include file="/webpage/pub/biz/footer.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/shortcut/func/tree1.js"></script>
<script type="text/javascript">	
	$("button:contains('清空选择')", window.parent.document).hide();
	$("button:contains('Empty choice')", window.parent.document).hide();
	var pub_selectLast='<s:text name="dmmes.pub.selectLast" />';
	var pub_one='<s:text name="dmmes.pub.one" />';
	var pub_success='<s:text name="dmmes.pub.success" />';
	function onSave() {
   		var srcNode = BOTree_weishouyu.getCheckedNodes(true);
		if (srcNode.length == 0) {
			alertMsg(pub_one);
			return;
		}
		var boolean=false;
		for(var i=0;i<srcNode.length;i++){
			if(!srcNode[i].isParent){
				boolean=true;
			}
		}
		if(!boolean){
			alertMsg(pub_selectLast);
			return;
		}
		try{
			parent.PageObject.itemMap['sys_role_func'].callback(srcNode);
		}catch(e){
			parent.closewin('sys_role_func');
		}
		parent.closewin('sys_role_func');
	}
	function reloadTree(){
		//初始化未授予功能权限的树
		reoladTree_weishouyu();
	}
</script>