//查询该工序下的指标
$.ajax( {  
	url : rootpath+"/webpage/biz/bi/target/selectTarget.action", 
	type:'POST',
	data :"bean.procid="+procid,
	cache : false,
	async : false,
	error : function(data) {  
		alertErrorMsg("系统ajax交互错误");   
	},  
	success : function(data) {  
		var dataJson=eval('('+data+')');
		 for(var i=0;i<dataJson.rows.length;i++){
             var order=i;
             var targetName=dataJson.rows[i].targetname;
             $("ul").append('<li id='+dataJson.rows[i].serialno+'>'+targetName+'</li>');
         }
	}  
 });

function onSave(){
	var data1 = $("#talgorithm").val();
	var data2=	$("#input1").val();
//	var rowdata;
//	if(multiselect == 'true'){//多选
//		var selectedRowIds = $("#process_grid").jqGrid("getGridParam", "selarrrow");
//		if (!selectedRowIds) {
//			alertMsg("请至少选择一个节点!");
//			return false;
//		}
//		for(var i=0;i<selectedRowIds.length;i++){
//			rowdata = $("#process_grid").jqGrid("getRowData", selectedRowIds[i]);
//			data.push(rowdata);
//		}
//	}else{//单选
//		var selectedRowId = $("#process_grid").jqGrid("getGridParam", "selrow");
//	    if (!selectedRowId) {
//	    	alertMsg("请选择一个节点!");
//	        return false;
//	    }
//	    rowdata = $("#process_grid").jqGrid("getRowData", selectedRowId);
//		data.push(rowdata);
//	}
	try{
		parent.PageObject.itemMap['talgorithm'].callback(data1,data2);
	}catch(e){
		parent.closewin('talgorithm');
	}
	parent.closewin('talgorithm');
}
function uncheck() {
	parent.PageObject.itemMap['talgorithm'].callback("","");
	parent.closewin('talgorithm');
}

