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
			beforeClick: BOTreeBeforeClick_yishouyu
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
						addNode_yishouyu(obj.id, obj.name, obj.pid);
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
		if(!treeNode.isParent){
			getTreeNodes_yishouyu(treeNode);
		}
	}

	
	function addNode_yishouyu(nodeID, nodeName) {
		if(!nodeID){
			nodeID = '';
		}
		if(!nodeName){
			nodeName = '';
		}
		var srcNode = BOTree_yishouyu.getSelectedNodes();
		var newNode = [{ id:nodeID,pid:0,name:nodeName}];
		BOTree_yishouyu.addNodes(srcNode[0], newNode);
	}
	
	
	function reoladTree_yishouyu(){
		var root = [{"id":"100000", "pid":"", "name":"管理平台", "open":true, "isParent":true}];
		BOTree_yishouyu = $.fn.zTree.init($("#func_tree"), setting_yishouyu, root);
		var treeRootNode = BOTree_yishouyu.getNodesByParamFuzzy("id", '100000');
		//选中根节点
		BOTree_yishouyu.selectNode(treeRootNode[0]);
		getTreeNodes_yishouyu(treeRootNode[0]);
	}


	