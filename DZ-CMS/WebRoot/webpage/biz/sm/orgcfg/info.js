//$("#orgcfg_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#orgcfg_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
var addsaveurl = rootpath+"/biz/sm/orgcfg/doAddSave.action";
var modifysaveurl = rootpath+"/biz/sm/orgcfg/doModifySave.action";
var deletesaveurl = rootpath+"/biz/sm/orgcfg/delete.action";
//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.orgcfgid" : orgcfgid};
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var orgcfgid = $.trim(consoleDlg.find("#orgcfgid").val());
	var orgtype = $.trim(consoleDlg.find("#orgtype").val());
	var isorg = $.trim(consoleDlg.find("#isorg").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var demo = $.trim(consoleDlg.find("#demo").val());
      
    return {   
        "bean.orgcfgid" : orgcfgid,
        "bean.orgtype" : orgtype,
        "bean.isorg" : isorg,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.demo" : demo
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");
	var orgtype = $.trim(consoleDlg.find("#orgtype").val());	
	var orgtypename = $.trim($("#orgtype option:selected").text());
	var isorg = $.trim(consoleDlg.find("#isorg").val());
	var isorgname = $.trim($("#isorg option:selected").text());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var demo = $.trim(consoleDlg.find("#demo").val());
	var dataRow = {
		orgcfgid : dataJson.orgcfgid,
		orgtype : orgtype,
		orgtypename : orgtypename,
		isorg : isorg,
		isorgname : isorgname,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		demo : demo
	}; 
	return dataRow;
}
