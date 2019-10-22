var radiosetting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
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
			onClick: onClick,
			onCheck: onRadio
		}
	};

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("radiotreedemo");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onRadio(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		$("#radiotree").attr("value", nodes[0].name);
		$("#radiotree_id").attr("value", nodes[0].id);
	}

	function showRadioMenu() {
		$("#radiotreediv").slideDown("fast");

		$("body").bind("mousedown", onRadioBodyDown);
	}
	function onRadioBodyDown(event) {
		if (!(event.target.id == "radiotree" || event.target.id == "radiotreediv" || event.target.id == "radiotreedemo" || $(event.target).parents("#radiotreediv").length>0)) {
			hideRadioMenu();
		}
	}
	function hideRadioMenu() {
		$("#radiotreediv").fadeOut("fast");
		$("body").unbind("mousedown", onRadioBodyDown);
		loadTech($("#radiotree_id").val());
	}