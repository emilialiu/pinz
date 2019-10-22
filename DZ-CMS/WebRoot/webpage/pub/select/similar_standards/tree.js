	var BOTree;
	var curStatus = "init", curAsyncCount = 0, asyncForAll = false, goAsync = false;
	var setting = {
		async: {
			enable: true,
			type: "post",
			url: rootpath+'/biz/dm/devicetype/devtypetree.action',
			autoParam:["id=bean.dtid"]
		},
		callback: {
			onClick:BOTreeOnClick,
			beforeAsync: beforeAsync,
			onAsyncSuccess: onAsyncSuccess,
			onAsyncError: onAsyncError
		}
	};
	
	/**
	 * 初始化
	 **/
	$(document).ready(function(){
		var root = [{"id":"0000", "pid":"", "name":"所有设备", "open":true, "isParent":true}];
		BOTree = $.fn.zTree.init($("#device_tree"), setting, root);
		setTimeout("expandAll()",300);
		setTimeout("selectNodeByid()",1000);
	});
	
	//相应节点的点击事件
	function BOTreeOnClick(event, treeId, treeNode) { 
		getNodeInfo(treeNode);
	}
	
	function beforeAsync() {
		curAsyncCount++;
	}
	
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		curAsyncCount--;
		if (curStatus == "expand") {
			expandNodes(treeNode.children);
		} else if (curStatus == "async") {
			asyncNodes(treeNode.children);
		}

		if (curAsyncCount <= 0) {
			if (curStatus != "init" && curStatus != "") {
				asyncForAll = true;
			}
			curStatus = "";
		}
	}

	function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		curAsyncCount--;

		if (curAsyncCount <= 0) {
			curStatus = "";
			if (treeNode!=null) asyncForAll = true;
		}
	}
	
	function expandAll() {
		if (!check()) {
			return;
		}
		if (asyncForAll) {
			BOTree.expandAll(true);
		} else {
			expandNodes(BOTree.getNodes());
			if (!goAsync) {
				curStatus = "";
			}
		}
	}
	function expandNodes(nodes) {
		if (!nodes) return;
		curStatus = "expand";
		for (var i=0, l=nodes.length; i<l; i++) {
			BOTree.expandNode(nodes[i], true, false, false);
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			} else {
				goAsync = true;
			}
		}
	}
	
	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}
	
	//选中树节点上的指定作业地点
	function selectNodeByid(){
		var nodes = BOTree.getNodesByParam("id",dtid,null);
		BOTree.selectNode(nodes[0]);
	}
	