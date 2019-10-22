	var BOTree_yifenpei;
	var setting_yifenpei;

	setting_yifenpei = {
		check: {
			enable: true
		},
		expandSpeed : false,
		isSimpleData : true,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		
		callback: {
			beforeClick: BOTreeBeforeClick_yifenpei,
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
	function getTreeNodes_yifenpei(node) {
		if(node.children){
			return;
		}
		$.ajax({
			url: rootpath+'/biz/bi/technologyProject/rolepopedomlist.action',
			data: {'bean.projectid':node.id, 'bean.techid':techid},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					var length = dataJson.cells.length;
					for(var i = 0; i < length; i++){
						var obj = dataJson.cells[i];
						addNode_yifenpei(obj);
					}
				}else{
					alertErrorMsg(dataJson.errormessage);
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
	function BOTreeBeforeClick_yifenpei(event, treeId, treeNode) {
		if(treeNode.isParent){
			getTreeNodes_yifenpei(treeNode);
		}
	}
	//展开事件--加载数据
	function BOTreeBeforeExpand_yi(treeId, treeNode) {
		if(treeNode.isParent){
			BOTree_yifenpei.selectNode(treeNode);
			getTreeNodes_yifenpei(treeNode);
		}
		return true;
	}
	
	function addNode_yifenpei(node) {
		var srcNode = BOTree_yifenpei.getSelectedNodes();
		BOTree_yifenpei.addNodes(srcNode[0], node);
	}
	
	function reoladTree_yifenpei(){
		var root = [{"id":"0000", "pid":"", "name":projectinfo, "open":true, "isParent":true}];
		BOTree_yifenpei = $.fn.zTree.init($("#project_tree"), setting_yifenpei, root);
		var treeRootNode = BOTree_yifenpei.getNodesByParamFuzzy("id", '0000');
		//选中根节点
		BOTree_yifenpei.selectNode(treeRootNode[0]);
		getTreeNodes_yifenpei(treeRootNode[0]);
	}
	