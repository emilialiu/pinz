	var BOTree_weifenpei;
	var setting_weifenpei;

	setting_weifenpei = {
		check: {
			enable: true
		},
		expandSpeed : false,
		isSimpleData : true,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		
		callback: {
			onClick: BOTreeBeforeClick_weifenpei,
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
	function getTreeNodes_weifenpei(node) {
		if(node.children){
			return;
		}
		$.ajax({
			url: rootpath+'/biz/bi/areaproject/rolepopedomunlist.action',
			data: {'bean.projectid':node.id, 'bean.qdid':qdid, 'bean.projectname':$("#projectname").val(), 'bean.projecttype':$("#projecttype").val()},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					var length = dataJson.cells.length;
					for(var i = 0; i < length; i++){
						var obj = dataJson.cells[i];
						addNode_weifenpei(obj);
					}
				}else{
					alertErrorMsg(dataJson.errormessage);
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
		//初始化未分配的作业地点的树
		reoladTree_weifenpei();
		//初始化已分配的作业地点的树
		reoladTree_yifenpei();
		
		$("#pro_btn_search").on("click", function(){
			reoladTree_weifenpei();
		})
	});
	
	function reoladTree_weifenpei(){
		var root = [{"id":"0000", "pid":"", "name":projectinfo, "open":true, "isParent":true}];
		BOTree_weifenpei = $.fn.zTree.init($("#unproject_tree"), setting_weifenpei, root);
		var treeRootNode = BOTree_weifenpei.getNodesByParamFuzzy("id", '0000');
		//选中根节点
		BOTree_weifenpei.selectNode(treeRootNode[0]);
		getTreeNodes_weifenpei(treeRootNode[0]);
	}
	
	//点击节点之前相应的事件--在这里通过AJAX获取子节点的数据
	/**
	 * 相应点击节点事件之前的时间
	 *@param treeId, html控件的ID
	 *@param treeNode  节点
     **/
	function BOTreeBeforeClick_weifenpei(event, treeId, treeNode) {
		if(treeNode.isParent){
			getTreeNodes_weifenpei(treeNode);
		}
	}
	//展开事件--加载数据
	function BOTreeBeforeExpand(treeId, treeNode) {
		if(treeNode.isParent){
			BOTree_weifenpei.selectNode(treeNode);
			getTreeNodes_weifenpei(treeNode);
		}
		return true;
	}
	
	function addNode_weifenpei(node) {
		var srcNode = BOTree_weifenpei.getSelectedNodes();
		BOTree_weifenpei.addNodes(srcNode[0], node);
	}
	