	var BOTree;
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
	}
	function reloadTree(node) {
		$.ajax({
			url: rootpath+'/webpage/biz/sc/technology/tree.action',
			cache:false,
			data: {"bean.isRoot":'true'},
			success: function(data){
				var dataJson = eval('('+data+')');
				if(dataJson.success){
					var root = dataJson.cells[0];
					BOTree = $.fn.zTree.init($("#technology_tree"), setting, root);

					//选中根节点
					var treeRootNode = BOTree.getNodesByParamFuzzy("id", root.id);
					currentselectnode = treeRootNode[0];
					BOTree.selectNode(currentselectnode);
					//调用默认展开第一个结点
					getTreeNodes(currentselectnode);
				}else{
					alertErrorMsg(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
	}
	
	// 获得树的子节点信息
	function getTreeNodes(node) {
		if(node.children){
			return;
		}
		var url = rootpath+'/webpage/biz/sc/technology/tree.action';
		$.ajax({
			url: url,
			data: {"bean.techid":node.id,"bean.isParent":node.isParent,"bean.level":node.level},
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
					alertErrorMsg(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
	}	

	//初始化
	$(document).ready(function(){
		 reloadTree();
	});
	
	//添加节点
	function addNode(node) {
		BOTree.addNodes(currentselectnode, node);
		//BOTree.selectNode(node);
	}
