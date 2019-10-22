//下拉多选树
	var checksetting = {
		check: {
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};

	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		var cityObj = $("#checktree");
		cityObj.attr("value", v);
	}

	function showCheckMenu() {
//		$("#treediv").css({left:cityOffset.left - leftObj.outerWidth() + "px", top:cityOffset.top + cityObj.outerHeight() - (topObj_title.top + topObj_navigate.top) + "px"}).slideDown("fast");
		$("#checktreediv").slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#checktreediv").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "checktree" || event.target.id == "checktreediv" || event.target.id == "checktreedemo" || $(event.target).parents("#checktreediv").length>0)) {
			hideMenu();
		}
	}