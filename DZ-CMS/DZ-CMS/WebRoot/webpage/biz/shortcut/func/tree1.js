	var BOTree_weishouyu;
	var setting_weishouyu;

	setting_weishouyu = {
		check: {
			enable: true
		},
		expandSpeed : false,
		isSimpleData : true,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		
		callback: {
			beforeClick: BOTreeBeforeClick_weishouyu,
			beforeExpand:BOTreeBeforeExpand
		}
	};
	
	/**
	 *获得树的子节点信息
	 *
	 * @param 
	 *
	 *
	 **/
	function getTreeNodes_weishouyu(node) {
		if(node.children){
			return;
		}
		$.ajax({
			url: rootpath+'/manager/sys/rolepopedom/rolepopedomunlist1.action',
			data: {'bean.funccode':node.id},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					var length = dataJson.cells.length;
					for(var i = 0; i < length; i++){
						var obj = dataJson.cells[i];
						addNode_weishouyu(obj);
					}
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
	}
	
	/**
	 * 初始化
	 **/
	$(document).ready(function(){
		//初始化未授予功能权限的树
		reoladTree_weishouyu();
	});
	
	function reoladTree_weishouyu(){
		var root = [{"id":"100000", "pid":"", "name":"管理平台", "open":true, "isParent":true}];
		BOTree_weishouyu = $.fn.zTree.init($("#unfunc_tree"), setting_weishouyu, root);
		var treeRootNode = BOTree_weishouyu.getNodesByParamFuzzy("id", '100000');
		//选中根节点
		BOTree_weishouyu.selectNode(treeRootNode[0]);
		getTreeNodes_weishouyu(treeRootNode[0]);
	}
	
	//点击节点之前相应的事件--在这里通过AJAX获取子节点的数据
	/**
	 * 相应点击节点事件之前的时间
	 *@param treeId, html控件的ID
	 *@param treeNode  节点
     **/
	function BOTreeBeforeClick_weishouyu(treeId, treeNode) {
		if(treeNode.isParent){
			getTreeNodes_weishouyu(treeNode);
		}
	}
	//展开事件--加载数据
	function BOTreeBeforeExpand(treeId, treeNode) {
		if(treeNode.isParent){
			BOTree_weishouyu.selectNode(treeNode);
			getTreeNodes_weishouyu(treeNode);
		}
		return true;
	}
	
	function addNode_weishouyu(node) {
		var srcNode = BOTree_weishouyu.getSelectedNodes();
		BOTree_weishouyu.addNodes(srcNode[0], node);
	}
	