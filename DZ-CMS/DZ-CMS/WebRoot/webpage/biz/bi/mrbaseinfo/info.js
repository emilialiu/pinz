var addsaveurl = rootpath+"/webpage/biz/bi/mrbaseinfo/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/mrbaseinfo/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/mrbaseinfo/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.rightid" : rightid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#rightid").val(rowData.rightid);
	consoleDlg.find("#mineid").val(rowData.mineid);
	consoleDlg.find("#mrcategory").val(rowData.mrcategory);
	consoleDlg.find("#projectname").val(rowData.projectname);
	consoleDlg.find("#projectquality").val(rowData.projectquality);
	consoleDlg.find("#mrstate").val(rowData.mrstate);
	consoleDlg.find("#ownway").val(rowData.ownway);
	consoleDlg.find("#economictype").val(rowData.economictype);
	consoleDlg.find("#companyname").val(rowData.companyname);
	consoleDlg.find("#companyaddr").val(rowData.companyaddr);
	consoleDlg.find("#companypost").val(rowData.companypost);
	consoleDlg.find("#registeredfund").val(rowData.registeredfund);
	consoleDlg.find("#legalrepresentative").val(rowData.legalrepresentative);
	consoleDlg.find("#accountnum").val(rowData.accountnum);
	consoleDlg.find("#bank").val(rowData.bank);
	consoleDlg.find("#mrcardid").val(rowData.mrcardid);
	consoleDlg.find("#eaststart").val(rowData.eaststart);
	consoleDlg.find("#eastend").val(rowData.eastend);
	consoleDlg.find("#northstart").val(rowData.northstart);
	consoleDlg.find("#northend").val(rowData.northend);
	consoleDlg.find("#geoposition").val(rowData.geoposition);
	consoleDlg.find("#mrarea").val(rowData.mrarea);
	consoleDlg.find("#expstartdate").val(rowData.expstartdate);
	consoleDlg.find("#expenddate").val(rowData.expenddate);
	consoleDlg.find("#dutyman").val(rowData.dutyman);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
	consoleDlg.find("#remark").val(rowData.remark);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var rightid = $.trim(consoleDlg.find("#rightid").val());
	var mineid = $.trim(consoleDlg.find("#mineid").val());
	var mrcategory = $.trim(consoleDlg.find("#mrcategory").val());
	var projectname = $.trim(consoleDlg.find("#projectname").val());
	var projectquality = $.trim(consoleDlg.find("#projectquality").val());
	var mrstate = $.trim(consoleDlg.find("#mrstate").val());
	var ownway = $.trim(consoleDlg.find("#ownway").val());
	var economictype = $.trim(consoleDlg.find("#economictype").val());
	var companyname = $.trim(consoleDlg.find("#companyname").val());
	var companyaddr = $.trim(consoleDlg.find("#companyaddr").val());
	var companypost = $.trim(consoleDlg.find("#companypost").val());
	var registeredfund = $.trim(consoleDlg.find("#registeredfund").val());
	var legalrepresentative = $.trim(consoleDlg.find("#legalrepresentative").val());
	var accountnum = $.trim(consoleDlg.find("#accountnum").val());
	var bank = $.trim(consoleDlg.find("#bank").val());
	var mrcardid = $.trim(consoleDlg.find("#mrcardid").val());
	var eaststart = $.trim(consoleDlg.find("#eaststart").val());
	var eastend = $.trim(consoleDlg.find("#eastend").val());
	var northstart = $.trim(consoleDlg.find("#northstart").val());
	var northend = $.trim(consoleDlg.find("#northend").val());
	var geoposition = $.trim(consoleDlg.find("#geoposition").val());
	var mrarea = $.trim(consoleDlg.find("#mrarea").val());
	var expstartdate = $.trim(consoleDlg.find("#expstartdate").val());
	var expenddate = $.trim(consoleDlg.find("#expenddate").val());
	var dutyman = $.trim(consoleDlg.find("#dutyman").val());
	var remark = $.trim(consoleDlg.find("#remark").val());
	
	// 探矿权信息
	var cooperateent = $.trim(consoleDlg.find("#cooperateent").val());
	var mineralstage = $.trim(consoleDlg.find("#mineralstage").val());
	var mineraltype = $.trim(consoleDlg.find("#mineraltype").val());
	var rightgetway = $.trim(consoleDlg.find("#rightgetway").val());
	var totalamount = $.trim(consoleDlg.find("#totalamount").val());
	var year1amount = $.trim(consoleDlg.find("#year1amount").val());
	var year2amount = $.trim(consoleDlg.find("#year2amount").val());
	var year3amount = $.trim(consoleDlg.find("#year3amount").val());
	var stateamount = $.trim(consoleDlg.find("#stateamount").val());
	var localamount = $.trim(consoleDlg.find("#localamount").val());
	var enterpriseamount = $.trim(consoleDlg.find("#enterpriseamount").val());
	var foreignamount = $.trim(consoleDlg.find("#foreignamount").val());
	var personalamount = $.trim(consoleDlg.find("#personalamount").val());
	var otheramount = $.trim(consoleDlg.find("#otheramount").val());
	var result = $.trim(consoleDlg.find("#result").val());
      
    return {   
        "bean.rightid" : rightid,
        "bean.mineid" : mineid,
        "bean.mrcategory" : mrcategory,
        "bean.projectname" : projectname,
        "bean.projectquality" : projectquality,
        "bean.mrstate" : mrstate,
        "bean.ownway" : ownway,
        "bean.economictype" : economictype,
        "bean.companyname" : companyname,
        "bean.companyaddr" : companyaddr,
        "bean.companypost" : companypost,
        "bean.registeredfund" : registeredfund,
        "bean.legalrepresentative" : legalrepresentative,
        "bean.accountnum" : accountnum,
        "bean.bank" : bank,
        "bean.mrcardid" : mrcardid,
        "bean.eaststart" : eaststart,
        "bean.eastend" : eastend,
        "bean.northstart" : northstart,
        "bean.northend" : northend,
        "bean.geoposition" : geoposition,
        "bean.mrarea" : mrarea,
        "bean.expstartdate" : expstartdate,
        "bean.expenddate" : expenddate,
        "bean.dutyman" : dutyman,
        "bean.remark" : remark,
        "tkqbean.cooperateent" : cooperateent,
        "tkqbean.mineralstage" : mineralstage,
        "tkqbean.mineraltype" : mineraltype,
        "tkqbean.rightgetway" : rightgetway,
        "tkqbean.totalamount" : totalamount,
        "tkqbean.year1amount" : year1amount,
        "tkqbean.year2amount" : year2amount,
        "tkqbean.year3amount" : year3amount,
        "tkqbean.stateamount" : stateamount,
        "tkqbean.localamount" : localamount,
        "tkqbean.enterpriseamount" : enterpriseamount,
        "tkqbean.foreignamount" : foreignamount,
        "tkqbean.personalamount" : personalamount,
        "tkqbean.otheramount" : otheramount,
        "tkqbean.result" : result
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
	var mineid = $.trim(consoleDlg.find("#mineid").val());	
	var mrcategory = $.trim(consoleDlg.find("#mrcategory").val());	
	var mrcategoryname = $.trim($("#mrcategory option:selected").text());
	var projectname = $.trim(consoleDlg.find("#projectname").val());	
	var projectquality = $.trim(consoleDlg.find("#projectquality").val());	
	var projectqualityname = $.trim($("#projectquality option:selected").text());
	var mrstate = $.trim(consoleDlg.find("#mrstate").val());
	var mrstatename = $.trim($("#mrstate option:selected").text());	
	var ownway = $.trim(consoleDlg.find("#ownway").val());	
	var ownwayname = $.trim($("#ownway option:selected").text());
	var economictype = $.trim(consoleDlg.find("#economictype").val());	
	var economictypename = $.trim($("#economictype option:selected").text());
	var companyname = $.trim(consoleDlg.find("#companyname").val());	
	var companyaddr = $.trim(consoleDlg.find("#companyaddr").val());	
	var companypost = $.trim(consoleDlg.find("#companypost").val());	
	var registeredfund = $.trim(consoleDlg.find("#registeredfund").val());	
	var legalrepresentative = $.trim(consoleDlg.find("#legalrepresentative").val());	
	var accountnum = $.trim(consoleDlg.find("#accountnum").val());	
	var bank = $.trim(consoleDlg.find("#bank").val());	
	var mrcardid = $.trim(consoleDlg.find("#mrcardid").val());	
	var eaststart = $.trim(consoleDlg.find("#eaststart").val());	
	var eastend = $.trim(consoleDlg.find("#eastend").val());	
	var northstart = $.trim(consoleDlg.find("#northstart").val());	
	var northend = $.trim(consoleDlg.find("#northend").val());	
	var geoposition = $.trim(consoleDlg.find("#geoposition").val());	
	var mrarea = $.trim(consoleDlg.find("#mrarea").val());	
	var expstartdate = $.trim(consoleDlg.find("#expstartdate").val());	
	var expenddate = $.trim(consoleDlg.find("#expenddate").val());	
	var dutyman = $.trim(consoleDlg.find("#dutyman").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	var remark = $.trim(consoleDlg.find("#remark").val());	
	var dataRow = { 			
	    _rowid : dataJson.rightid,// 从Server端得到系统分配的id
		rightid : dataJson.rightid,// 从Server端得到系统分配的id
		mineid : mineid,
		mrcategory : mrcategory,
		mrcategoryname : mrcategoryname,
		projectname : projectname,
		projectquality : projectquality,
		projectqualityname : projectqualityname,
		mrstate : mrstate,
		mrstatename : mrstatename,
		ownway : ownway,
		ownwayname : ownwayname,
		economictype : economictype,
		economictypename : economictypename,
		companyname : companyname,
		companyaddr : companyaddr,
		companypost : companypost,
		registeredfund : registeredfund,
		legalrepresentative : legalrepresentative,
		accountnum : accountnum,
		bank : bank,
		mrcardid : mrcardid,
		eaststart : eaststart,
		eastend : eastend,
		northstart : northstart,
		northend : northend,
		geoposition : geoposition,
		mrarea : mrarea,
		expstartdate : expstartdate,
		expenddate : expenddate,
		dutyman : dutyman,
		createdate : createdate,
		createid : createid,
		modifyid : modifyid,
		modifydate : modifydate,
		remark : remark
	};
	return dataRow;
}
