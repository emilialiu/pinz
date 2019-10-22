//$("#staff_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#staff_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
var addsaveurl = rootpath+"/biz/sm/staff/doAddSave.action";
var modifysaveurl = rootpath+"/biz/sm/staff/doModifySave.action";
var deletesaveurl = rootpath+"/biz/sm/staff/delete.action";
//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.staffid" : staffid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#staffid").val(rowData.staffid);
	consoleDlg.find("#deptid").val(rowData.deptid);
	consoleDlg.find("#staffcode").val(rowData.staffcode);
	consoleDlg.find("#staffname").val(rowData.staffname);
	consoleDlg.find("#sex").val(rowData.sex);
	consoleDlg.find("#nation").val(rowData.nation);
	consoleDlg.find("#age").val(rowData.age);
	consoleDlg.find("#idcard").val(rowData.idcard);
	consoleDlg.find("#jobtitle").val(rowData.jobtitle);
	consoleDlg.find("#tptitle").val(rowData.tptitle);
	consoleDlg.find("#entrydate").val(rowData.entrydate);
	consoleDlg.find("#station").val(rowData.station);
	consoleDlg.find("#address").val(rowData.address);
	consoleDlg.find("#relaphone").val(rowData.relaphone);
	consoleDlg.find("#lodate").val(rowData.lodate);
	consoleDlg.find("#loreason").val(rowData.loreason);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#memo").val(rowData.memo);
    consoleDlg.find("#loginname").val(rowData.loginname);
    consoleDlg.find("#loginpwd").val("123"); //此处没任何意义，只为了保存验证不报错。没做数据库交互
    consoleDlg.find("#operatetype").val(rowData.operatetype);
    consoleDlg.find("#education").val(rowData.education);
    
    if(rowData.isleaveoffice == '1'){
    	consoleDlg.find("input:radio[name='isleaveoffice']").eq(0).attr("checked",false);
    	consoleDlg.find("input:radio[name='isleaveoffice']").eq(1).attr("checked",true);
    	consoleDlg.find("input:radio[name='isleaveoffice']").eq(1).click();
    	consoleDlg.find("#lodate_div").css('display','block');
    	consoleDlg.find("#loreason_div").css('display','block');
    }else{
    	consoleDlg.find("input:radio[name='isleaveoffice']").eq(0).attr("checked",true);
    	consoleDlg.find("input:radio[name='isleaveoffice']").eq(1).attr("checked",false);
    	consoleDlg.find("input:radio[name='isleaveoffice']").eq(0).click();
    	consoleDlg.find("#lodate_div").css('display','none');
    	consoleDlg.find("#loreason_div").css('display','none');
    }
    if(rowData.ismanager == '1'){
    	consoleDlg.find("input:radio[name='ismanager']").eq(0).attr("checked",false);
    	consoleDlg.find("input:radio[name='ismanager']").eq(1).attr("checked",true);
    	consoleDlg.find("input:radio[name='ismanager']").eq(1).click();
    }else{
    	consoleDlg.find("input:radio[name='ismanager']").eq(0).attr("checked",true);
    	consoleDlg.find("input:radio[name='ismanager']").eq(1).attr("checked",false);
    	consoleDlg.find("input:radio[name='ismanager']").eq(0).click();
    }
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var staffid = $.trim(consoleDlg.find("#staffid").val());
	var deptid = $.trim(consoleDlg.find("#deptid").val());
	var staffcode = $.trim(consoleDlg.find("#staffcode").val());
	var staffname = $.trim(consoleDlg.find("#staffname").val());
	var sex = $.trim(consoleDlg.find("#sex").val());
	var nation = $.trim(consoleDlg.find("#nation").val());
	var age = $.trim(consoleDlg.find("#age").val());
	var idcard = $.trim(consoleDlg.find("#idcard").val());
	var jobtitle = $.trim(consoleDlg.find("#jobtitle").val());
	var tptitle = $.trim(consoleDlg.find("#tptitle").val());
	var entrydate = $.trim(consoleDlg.find("#entrydate").val());
	var station = $.trim(consoleDlg.find("#station").val());
	var address = $.trim(consoleDlg.find("#address").val());
	var relaphone = $.trim(consoleDlg.find("#relaphone").val());
	var isleaveoffice = consoleDlg.find("input:radio[name='isleaveoffice']:checked").val();
	var ismanager = consoleDlg.find("input:radio[name='ismanager']:checked").val();
	var lodate = $.trim(consoleDlg.find("#lodate").val());
	var loreason = $.trim(consoleDlg.find("#loreason").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var loginname = $.trim(consoleDlg.find("#loginname").val());
	var loginpwd = $.trim(consoleDlg.find("#loginpwd").val());
	var operatetype = $.trim(consoleDlg.find("#operatetype").val());
	var education = $.trim(consoleDlg.find("#education").val());
      
    return {   
        "bean.staffid" : staffid,
        "bean.deptid" : deptid,
        "bean.staffcode" : staffcode,
        "bean.staffname" : staffname,
        "bean.sex" : sex,
        "bean.nation" : nation,
        "bean.age" : age,
        "bean.idcard" : idcard,
        "bean.jobtitle" : jobtitle,
        "bean.tptitle" : tptitle,
        "bean.entrydate" : entrydate,
        "bean.station" : station,
        "bean.address" : address,
        "bean.relaphone" : relaphone,
        "bean.isleaveoffice" : isleaveoffice,
        "bean.lodate" : lodate,
        "bean.loreason" : loreason,
        "bean.ismanager" : ismanager,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.memo" : memo,
        "bean.loginname" : loginname,
        "bean.loginpwd" : loginpwd,
        "bean.operatetype" : operatetype,
        "bean.education" : education
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");
	var deptid = $.trim(consoleDlg.find("#deptid").val());
    var deptname = $.trim(consoleDlg.find("#deptname").val());
	var staffcode = $.trim(consoleDlg.find("#staffcode").val());
	var staffname = $.trim(consoleDlg.find("#staffname").val());
	var sex = $.trim($("#sex option:selected").text());
	var nation = $.trim(consoleDlg.find("#nation").val());
	var age = $.trim(consoleDlg.find("#age").val());
	var idcard = $.trim(consoleDlg.find("#idcard").val());
	var jobtitle = $.trim(consoleDlg.find("#jobtitle").val());
	var tptitle = $.trim(consoleDlg.find("#tptitle").val());
	var entrydate = $.trim(consoleDlg.find("#entrydate").val());
	var station = $.trim(consoleDlg.find("#station").val());
	var address = $.trim(consoleDlg.find("#address").val());
	var relaphone = $.trim(consoleDlg.find("#relaphone").val());
	var isleaveoffice = $.trim(consoleDlg.find("isleaveoffice").val());
	var lodate = $.trim(consoleDlg.find("#lodate").val());
	var loreason = $.trim(consoleDlg.find("#loreason").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var dataRow = {
		staffid : dataJson.staffid,
		deptid : deptid,
		deptname : dataJson.deptname,
		staffcode : staffcode,
		staffname : staffname,
		sexname : sex,
		nation : nation,
		age : age,
		idcard : idcard,
		jobtitle : jobtitle,
		tptitle : tptitle,
		entrydate : entrydate,
		station : station,
		address : address,
		relaphone : relaphone,
		isleaveoffice : isleaveoffice,
		lodate : lodate,
		loreason : loreason,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		memo : memo
	}; 
	return dataRow;
}

$("#staff_info").on('domi.add.after', function() {
	var srcNode = BOTree1.getSelectedNodes();
	$("#staff_info").find("#deptid").val(srcNode[0].id);
	$("#staff_info").find("#deptname").val(srcNode[0].name);
	$("#staff_info").find("#sex")[0].selectedIndex = 0;
	$("#staff_info").find("#nation")[0].selectedIndex = 0;
	$("#staff_info").find("#operatetype")[0].selectedIndex = 0;
	$("#staff_info").find("#education")[0].selectedIndex = 0;
	$("#staff_info").find(".radioItem")[0].value="0";
	$("#staff_info").find(".radioItem")[1].value="1";
	$("#staff_info").find(".mngItem")[0].value="0";
	$("#staff_info").find(".mngItem")[1].value="1";
	$("#staff_info").find("input:radio[name='isleaveoffice']").eq(0).attr("checked",true);
	$("#staff_info").find("input:radio[name='isleaveoffice']").eq(1).attr("checked",false);
	$("#staff_info").find("#lodate_div").css('display','none');
	$("#staff_info").find("#loreason_div").css('display','none');
	$("#staff_info").find("input:radio[name='ismanager']").eq(0).attr("checked",true);
	$("#staff_info").find("input:radio[name='ismanager']").eq(1).attr("checked",false);
});
$("#staff_info").on('domi.modify.after' , function() {
	$("#staff_info").find("#password").css('display','none');
});
$("#staff_info").on('domi.viewinfo' , function() {
	$("#staff_info").find("#password").css('display','none');
});
$("#staff_info").on('domi.add.before' , function() {
	$("#staff_info").find("#password").css('display','block');
});
