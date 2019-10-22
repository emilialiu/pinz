	var BOTree1;
	var setting;
	var currentselectnode;

	setting = {
		view:{
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit:{
			enable : true
		},
		checkable : false,
		isSimpleData : true,
		cache:false,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		callback: {
			onClick:BOTreeOnClick,
			beforeExpand:BOTreeBeforeExpand,
			onRename: onRename,
			onDrop: onDrop,
			onRemove : onRemove
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
	
	//重命名节点
	function onRename(e, treeId, treeNode, isCancel){
		var table = $("#"+treeId).attr("table");
		var ztreevalue = $("#"+treeId).attr("ztreevalue");
		var ztreetext = $("#"+treeId).attr("ztreetext");
		
		$.ajax({
			url: rootpath+'/dm/ztree/modify.action',
			data: {"bean.tablename":table,"bean.ztreeid":ztreevalue,"bean.ztreetext":ztreetext,"bean.id":treeNode.id,"bean.name":treeNode.name},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					parent.alert("modifysuccess");
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
	}
	//删除节点
	function onRemove(e, treeId, treeNode){
		var table = $("#"+treeId).attr("table");
		var ztreevalue = $("#"+treeId).attr("ztreevalue");
		
		$.ajax({
			url: rootpath+'/dm/ztree/remove.action',
			data: {"bean.tablename":table,"bean.ztreeid":ztreevalue,"bean.id":treeNode.id},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					parent.alert("deletesuccess");
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
	}
	
	//移动节点
	function onDrop(event, treeId, treeNode, targetNode, moveType, isCopy){
		var table = $("#"+treeId).attr("table");
		var ztreevalue = $("#"+treeId).attr("ztreevalue");
		var parentid = $("#"+treeId).attr("parentid");
		var srcNode = BOTree1.getSelectedNodes();
		
		$.ajax({
			url: rootpath+'/dm/ztree/drop.action',
			data: {"bean.tablename":table,"bean.ztreeid":ztreevalue,"bean.parentid":parentid,"bean.id":srcNode[0].id,"bean.parent":targetNode.id},
			cache: false,
			success: function(data){
				var dataJson = eval(data);
				if(dataJson.success){
					parent.alert("removesuccess");
				}else{
					parent.alertError(dataJson.errormessage);
				}
			},
			error:function(data){
				
			}
		});
		
	}
	
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		
		var table = $("#"+treeId).attr("table");
		var ztreevalue = $("#"+treeId).attr("ztreevalue");
		var ztreetext = $("#"+treeId).attr("ztreetext");
		var parentid = $("#"+treeId).attr("parentid");
				
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var count = newCount++;
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			//异步获取id
			$.ajax({
				url: rootpath+'/dm/ztree/getId.action',
				data: {"bean.tablename":table,"bean.ztreeid":ztreevalue,"bean.ztreetext":ztreetext,"bean.parentid":parentid,"bean.parent":treeNode.id,"bean.name":"新建节点" + (count)},
				cache: false,
				success: function(data){
					var dataJson = eval(data);
					if(dataJson.success){
						zTree.addNodes(treeNode, {id:dataJson.cells[0], pId:treeNode.id, name:"新建节点" + (count)});
						return false;
					}else{
						parent.alertError(dataJson.errormessage);
					}
				},
				error:function(data){
					
				}
			});
		});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};