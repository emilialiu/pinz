var addsaveurl = rootpath+"/webpage/biz/pub/tomatter/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/pub/tomatter/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/pub/tomatter/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.matterid" : matterid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#matterid").val(rowData.matterid);
	consoleDlg.find("#mattername").val(rowData.mattername);
	consoleDlg.find("#addtime").val(rowData.addtime);
	consoleDlg.find("#mattertype").val(rowData.mattertype);
	consoleDlg.find("#memo").val(rowData.memo);
	consoleDlg.find("#linkurl").val(rowData.linkurl);
	consoleDlg.find("#handletime").val(rowData.handletime);
	consoleDlg.find("#handlename").val(rowData.handlename);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var matterid = $.trim(consoleDlg.find("#matterid").val());
	var mattername = $.trim(consoleDlg.find("#mattername").val());
	var addtime = $.trim(consoleDlg.find("#addtime").val());
	var mattertype = $.trim(consoleDlg.find("#mattertype").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var linkurl = $.trim(consoleDlg.find("#linkurl").val());
	var handletime = $.trim(consoleDlg.find("#handletime").val());
	var handlename = $.trim(consoleDlg.find("#handlename").val());
      
    return {   
        "bean.matterid" : matterid,
        "bean.mattername" : mattername,
        "bean.addtime" : addtime,
        "bean.mattertype" : mattertype,
        "bean.memo" : memo,
        "bean.linkurl" : linkurl,
        "bean.handletime" : handletime,
        "bean.handlename" : handlename
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var mattername = $.trim(consoleDlg.find("#mattername").val());	
	var addtime = $.trim(consoleDlg.find("#addtime").val());	
	var mattertype = $.trim(consoleDlg.find("#mattertype").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var linkurl = $.trim(consoleDlg.find("#linkurl").val());	
	var handletime = $.trim(consoleDlg.find("#handletime").val());	
	var handlename = $.trim(consoleDlg.find("#handlename").val());	
	var dataRow = { 			
	    _rowid : dataJson.matterid,// 从Server端得到系统分配的id
		matterid : dataJson.matterid,// 从Server端得到系统分配的id
		mattername : mattername,
		addtime : addtime,
		mattertype : mattertype,
		memo : memo,
		linkurl : linkurl,
		handletime : handletime,
		handlename : handlename
	};
	return dataRow;
}
