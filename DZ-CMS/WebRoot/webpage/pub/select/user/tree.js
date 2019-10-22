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
		//刷新用户数据表
		dosearchbydept(treeNode.id);
	}
	function reloadTree(node) {
		var treeData = [];
		$.ajax({
			url: rootpath+'/pub/select/dept/getRootDept.action',
			cache:false,
			data: {"bean.isRoot":'true'},
			success: function(data){
				var dataJson = eval('('+data+')');
				if(dataJson.success){
					var root = dataJson.cells[0];
					BOTree1 = $.fn.zTree.init($("#dept_tree"), setting, root)

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
	
	// 获得树的子节点信息
	function getTreeNodes(node) {
		if(node.children){
			return;
		}
		var treeData = [];
		var data;
		var url = rootpath+'/manager/sys/dept/tree.action';
		$.ajax({
			url: url,
			data: {"bean.deptid":node.id,"bean.isParent":node.isParent,"bean.level":node.level},
			cache: false,
			success: function(data){
				var dataJson = eval('('+data+')');
				if(dataJson.success){
					var treeNodes = dataJson.cells;	
					
					for (var i = 0; i < treeNodes.length; i++){
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

	//初始化
	$(document).ready(function(){
		 reloadTree();
	});
	
	//添加节点
	function addNode(node) {
		BOTree1.addNodes(currentselectnode, node);
	}