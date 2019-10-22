	var BOTree_yishouyu;
	var setting_yishouyu;

	setting_yishouyu = {
		check: {
			enable: true
		},
		expandSpeed : false,
		isSimpleData : true,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		
		callback: {
			beforeClick: BOTreeBeforeClick_yishouyu,
			beforeExpand:BOTreeBeforeExpand_yi
		}
		
	};
	
	
	/**
	 *获得树的子节点信息
	 *
	 * @param 
	 *
	 *
	 **/
	function getTreeNodes_yishouyu(node) {
		if(node.children){
			return;
		}
		$.ajax({
			url: rootpath+'/manager/sys/rolepopedom/rolepopedomlist.action',
			data: {'bean.funccode':node.id, 'bean.roleid': $("#roleid").val()},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					var length = dataJson.cells.length;
					for(var i = 0; i < length; i++){
						var obj = dataJson.cells[i];
						addNode_yishouyu(obj);
					}
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
	}


	//点击节点之前相应的事件--在这里通过AJAX获取子节点的数据
	/**
	 * 相应点击节点事件之前的时间
	 *@param treeId, html控件的ID
	 *@param treeNode  节点
     **/
	function BOTreeBeforeClick_yishouyu(treeId, treeNode) {
		if(treeNode.isParent){
			getTreeNodes_yishouyu(treeNode);
		}
	}
	//展开事件--加载数据
	function BOTreeBeforeExpand_yi(treeId, treeNode) {
		if(treeNode.isParent){
			BOTree_yishouyu.selectNode(treeNode);
			getTreeNodes_yishouyu(treeNode);
		}
		return true;
	}
	
	function addNode_yishouyu(node) {
		var srcNode = BOTree_yishouyu.getSelectedNodes();
		BOTree_yishouyu.addNodes(srcNode[0], node);
	}
	
	function reoladTree_yishouyu(){
		var root = [{"id":"100000", "pid":"", "name":"管理平台", "open":true, "isParent":true}];
		BOTree_yishouyu = $.fn.zTree.init($("#func_tree"), setting_yishouyu, root);
		var treeRootNode = BOTree_yishouyu.getNodesByParamFuzzy("id", '100000');
		//选中根节点
		BOTree_yishouyu.selectNode(treeRootNode[0]);
		getTreeNodes_yishouyu(treeRootNode[0]);
	}


	