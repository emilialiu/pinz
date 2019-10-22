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
			onClick:BOTreeOnClick,
			beforeExpand:BOTreeBeforeExpand
		}
	};
	//展开事件--加载数据
	function BOTreeBeforeExpand(treeId, treeNode) {
		if(treeNode.children){
			return;
		} else {
			currentselectnode = treeNode;
			getTreeNodes(treeNode,treeId);
		}
		return true;
	}

	//相应节点的点击事件
	function BOTreeOnClick(event, treeId, treeNode) { 
		currentselectnode = treeNode;
		
//		$("#log").append("<li>"+treeNode.id+"</li>");
		
	}
	
	function loadTree(node,controlid){
		var root = node;
		BOTree1 = $.fn.zTree.init($("#"+controlid+""), setting, root);
		
		//选中根节点
		var treeRootNode = BOTree1.getNodesByParamFuzzy("id", "00001");
		currentselectnode = treeRootNode[0];
		BOTree1.selectNode(currentselectnode);
		getTreeNodes(currentselectnode,controlid);
	}
	
	// 获得树的子节点信息
	function getTreeNodes(node,controlid) {
		var table = $("#"+controlid).attr("table");
		var ztreevalue = $("#"+controlid).attr("ztreevalue");
		var ztreetext = $("#"+controlid).attr("ztreetext");
		var parentid = $("#"+controlid).attr("parentid");
		var treeData = [];
		var data;
		var url = rootpath+'/dm/ztree/list.action';
		$.ajax({
			url: url,
			data: {"bean.parent":node.id,"bean.tablename":table,"bean.ztreeid":ztreevalue,"bean.ztreetext":ztreetext,"bean.parentid":parentid},
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
		});
	}	
	
	//添加节点
	function addNode(node) {
		BOTree1.addNodes(currentselectnode, node);
	}
//	//重命名节点
//	function renameTreeNode(txtObj) {
//		var srcNode = BOTree1.getSelectedNodes();
//		if (!srcNode) {
//			alert("请先选中一个节点");
//			return;
//		}
//		if (srcNode[0].name != txtObj) {
//			srcNode[0].name = txtObj;
//			BOTree1.updateNode(srcNode[0]);
//			BOTree1.selectNode(srcNode[0]);
//		}
//	}
//	//删除节点
//	function removeTreeNode() {
//		var srcNode = BOTree1.getSelectedNodes();
//		if (srcNode) {
//			BOTree1.removeNode(srcNode[0]);
//		}
//	}