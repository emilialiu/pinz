//$("#team_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#team_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
var addsaveurl = rootpath+"/biz/sm/team/doAddSave.action";
var modifysaveurl = rootpath+"/biz/sm/team/doModifySave.action";
var deletesaveurl = rootpath+"/biz/sm/team/delete.action";
//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.teamid" : teamid};
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");
	var teamid = $.trim(consoleDlg.find("#teamid").val());
	var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());
	var deptid = $.trim(consoleDlg.find("#deptid").val());
	var teamcode = $.trim(consoleDlg.find("#teamcode").val());
	var teamtype = $.trim(consoleDlg.find("#teamtype").val());
	var teamname = $.trim(consoleDlg.find("#teamname").val());
	var dutyman = $.trim(consoleDlg.find("#dutyman").val());
	var peoplenum = $.trim(consoleDlg.find("#peoplenum").val());
	var phone = $.trim(consoleDlg.find("#phone").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());

    return {
        "bean.teamid" : teamid,
        "bean.tdeptid" : tdeptid,
        "bean.deptid" : deptid,
        "bean.teamcode" : teamcode,
        "bean.teamtype" : teamtype,
        "bean.teamname" : teamname,
        "bean.dutyman" : dutyman,
        "bean.peoplenum" : peoplenum,
        "bean.phone" : phone,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.memo" : memo
    };
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");
    var tdeptid = $.trim(consoleDlg.find("#tdeptid").val());
    var deptid = $.trim(consoleDlg.find("#deptid").val());
	var deptname = $.trim(consoleDlg.find("#deptname").val());
	var teamcode = $.trim(consoleDlg.find("#teamcode").val());
	var teamtype = $.trim(consoleDlg.find("#teamtype").val());
	var teamtypename = $.trim(consoleDlg.find("#teamtype option:selected").text());
	var teamname = $.trim(consoleDlg.find("#teamname").val());
	var dutyman = $.trim(consoleDlg.find("#dutyman").val());
	var dutymanname = $.trim(consoleDlg.find("#dutymanname").val());
	var peoplenum = $.trim(consoleDlg.find("#peoplenum").val());
	var phone = $.trim(consoleDlg.find("#phone").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var dataRow = {
		teamid : dataJson.teamid,
		tdeptid : tdeptid,
		deptid : deptid,
		deptname : deptname,
		teamcode : teamcode,
		teamtype : teamtype,
		teamtypename : teamtypename,
		teamname : teamname,
		dutyman : dutyman,
		dutymanname : dutymanname,
		peoplenum : peoplenum,
		phone : phone,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		memo : memo
	};
	return dataRow;
}

//选择人员
function selectPerson(){
	parent.openwin("selectperson","/webpage/pub/select/staff/main.jsp","人员选择",window.screen.availWidth*0.8,565);
	parent.PageObject.itemMap['selectperson'].callback = function(no){
		selectpersonCallBack(no);
	}
}
function selectpersonCallBack(arr){
	$('#dutymanname')[0].value = arr[0].staffname;
	$("#dutyman").val(arr[0].staffid);
	$("#phone").val(arr[0].relaphone);
}