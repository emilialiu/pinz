	var BOTree1;
	var setting;
	var currentselectnode;

	setting = {
		checkable : false,
		isSimpleData : true,
		cache:false,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		callback: {
			onClick:	BOTreeOnClick,
			beforeExpand:BOTreeBeforeExpand
		}
	};
	//展开事件--加载数据
	function BOTreeBeforeExpand(treeId, treeNode) {
		if(!treeNode.nodes & treeNode.isParent){	
			currentselectnode = treeNode;
			getTreeNodes(treeNode);
		}
		return true;
	}

	//相应节点的点击事件
	function BOTreeOnClick(event, treeId, treeNode) { 
		currentselectnode = treeNode;
		getNodeInfo(treeNode.id);
		clearMsg();
	}
	
	//初始化加载数据
	function reloadTree(node) {
		var treeData = [];
		$.ajax({
			url: rootpath+'/manager/sys/func/tree.action',
			cache:false,
			data: {"bean.isRoot":'true'},
			success: function(data){
				var dataJson = eval('('+data+')');
				if(dataJson.success){
					var root = dataJson.cells[0];
					BOTree1 = $.fn.zTree.init($("#func_tree"), setting, root);


					//选中根节点
					var treeRootNode = BOTree1.getNodesByParamFuzzy("id", root.id);
					currentselectnode = treeRootNode[0];
					BOTree1.selectNode(currentselectnode);
					getTreeNodes(currentselectnode);
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		})
	}

	/**
	 *获得树的子节点信息
	 *
	 * @param 
	 *
	 *
	 **/
	function getTreeNodes(node) {
		if(node.children){
			return;
		}
		var treeData = [];
		$.ajax({
			url: rootpath+'/manager/sys/func/tree.action',
			data: {'bean.funccode':node.id,'bean.isParent':node.isParent,'bean.level':node.level},
			cache: false,
			success: function(data){
				var dataJson = eval('('+data+')');
				if(dataJson.success){
					var treeNodes = dataJson.cells;	
					
					for (var i = 0; i < treeNodes.length; i++)
					{
						var node = treeNodes[i];
						addNode(node);
					}
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		})
	}	

	/**
	 * 初始化
	 **/
	$(document).ready(function(){
		reloadTree();
	});
	
	//添加节点
	function addNode(node) {
		BOTree1.addNodes(currentselectnode, node);
	}
	
	function renameTreeNode(txtObj) {
		var srcNode = BOTree1.getSelectedNodes();
		if (!srcNode) {
			alert("请先选中一个节点");
			return;
		}
		if (srcNode[0].name != txtObj.value) {
			srcNode[0].name = txtObj.value;
			BOTree1.updateNode(srcNode[0]);
			BOTree1.selectNode(srcNode[0]);
		}
		
	}
	
	function removeTreeNode() {
		var srcNode = BOTree1.getSelectedNodes();
		if (srcNode) {
			BOTree1.removeNode(srcNode[0]);
		}
	}