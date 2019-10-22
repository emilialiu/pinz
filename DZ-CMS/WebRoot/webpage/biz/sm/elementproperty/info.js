//$("#elementproperty_info").on('domi.modifysave.before' , function() {alert("测试修改保存之前事件")});
//$("#elementproperty_info").on('domi.modifysave.after' , function() {alert("测试修改保存之后事件")});
//删除修改进入表单时传递参数
var addsaveurl = rootpath+"/biz/sm/elementproperty/doAddSave.action";
var modifysaveurl = rootpath+"/biz/sm/elementproperty/doModifySave.action";
var deletesaveurl = rootpath+"/biz/sm/elementproperty/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
    return {"bean.eleproid" : eleproid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
    consoleDlg.find("#eleproid").val(rowData.eleproid);
	consoleDlg.find("#eletypeid").val(rowData.eletypeid);
	consoleDlg.find("#eleno").val(rowData.eleno);
	consoleDlg.find("#elecode").val(rowData.elecode);
	consoleDlg.find("#elename").val(rowData.elename);
	consoleDlg.find("#parenteleid").val(rowData.parenteleid);
	consoleDlg.find("#eletype").val(rowData.eletype);
	consoleDlg.find("#valuesql").val(rowData.valuesql);
	consoleDlg.find("#servicename").val(rowData.servicename);
	consoleDlg.find("#isedit").val(rowData.isedit);
	consoleDlg.find("#iproname").val(rowData.iproname);
	consoleDlg.find("#isprimary").val(rowData.isprimary);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#memo").val(rowData.memo);
	consoleDlg.find("#ishide").val(rowData.demo);
	consoleDlg.find("#projectlevel").val(rowData.demo);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
	var consoleDlg = $("#"+_bizname+"_info");
	var eletypeid =$.trim(consoleDlg.find("#eletypeid").val());;
	var eleproid = $.trim(consoleDlg.find("#eleproid").val());
	var elecode = $.trim(consoleDlg.find("#elecode").val());
	var eleno = $.trim(consoleDlg.find("#eleno").val());
	var elename = $.trim(consoleDlg.find("#elename").val());
	var parenteleid =$.trim(consoleDlg.find("#parenteleid").val());
	var eletype = $.trim(consoleDlg.find("#eletype").val());
	var valuesql = $.trim(consoleDlg.find("#valuesql").val());
	var servicename = $.trim(consoleDlg.find("#servicename").val());
	var isedit = $.trim(consoleDlg.find("#isedit").val());
	var iproname = $.trim(consoleDlg.find("#iproname").val());
	var isprimary = $.trim(consoleDlg.find("#isprimary").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
	var memo = $.trim(consoleDlg.find("#memo").val());
	var projectlevel = $.trim(consoleDlg.find("#projectlevel").val());
	var ishide = $.trim(consoleDlg.find("#ishide").val());
    return { 
    	"bean.eleproid" : eleproid,
        "bean.eletypeid" : eletypeid,
        "bean.elecode" : elecode,
        "bean.eleno" : eleno,
        "bean.elename" : elename,
        "bean.parenteleid" : parenteleid,
        "bean.eletype" : eletype,
        "bean.valuesql" : valuesql,
        "bean.servicename" : servicename,
        "bean.isedit" : isedit,
        "bean.iproname" : iproname,
        "bean.isprimary" : isprimary,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate,
        "bean.memo" : memo,
        "bean.projectlevel":projectlevel,
        "bean.ishide":ishide
    };
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    var eletypeid = $.trim(consoleDlg.find("#eletypeid").val());	
	var elecode = $.trim(consoleDlg.find("#elecode").val());
	var eleno = $.trim(consoleDlg.find("#eleno").val());
	var elename = $.trim(consoleDlg.find("#elename").val());
	var parenteleid = $.trim(consoleDlg.find("#parenteleid").val());	
	var eletype =  $.trim($("#eletype option:selected").text());	
	var parenteleid =$.trim($("#parenteleid option:selected").text());
	var valuesql = $.trim(consoleDlg.find("#valuesql").val());	
	var servicename = $.trim(consoleDlg.find("#servicename").val());	
	var isedit = $.trim(consoleDlg.find("#isedit").val());	
	var iproname = $.trim(consoleDlg.find("#iproname").val());	
	var isprimary = $.trim(consoleDlg.find("#isprimary").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var memo = $.trim(consoleDlg.find("#memo").val());	
	var projectlevel =$.trim($("#projectlevel option:selected").text());
	var ishide = $.trim(consoleDlg.find("#ishide").val());	
	var dataRow = { 			
		eleproid : dataJson.id,// 从Server端得到系统分配的id
		eletypeid : eletypeid,
		elecode : elecode,
		eleno : eleno,
		elename : elename,
		parenteleid : parenteleid,
		eletype : eletype,
		valuesql : valuesql,
		servicename : servicename,
		isedit : isedit,
		iproname : iproname,
		isprimary : isprimary,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate,
		memo : memo,
		projectlevel:projectlevel,
		ishide:ishide
	}; 
	return dataRow;
}

$("#elementproperty_info").on('domi.add.after', function() {
	$("#elementproperty_info").find("#isedit")[0].selectedIndex = 0;
	$("#elementproperty_info").find("#isprimary")[0].selectedIndex = 0;
	$("#elementproperty_info").find("#ishide")[0].selectedIndex = 0;
});