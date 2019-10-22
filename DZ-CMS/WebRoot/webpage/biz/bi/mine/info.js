var addsaveurl = rootpath+"/webpage/biz/bi/mine/doAddSave.action";
var modifysaveurl = rootpath+"/webpage/biz/bi/mine/doModifySave.action";
var deletesaveurl = rootpath+"/webpage/biz/bi/mine/delete.action";

//删除修改进入表单时传递参数
function getIDVal(){
		return {"bean.mineid" : mineid};
}

//给表单赋值
function setInfoData(_bizname,data){  
	var dataJson = eval(data);
	var rowData = dataJson[0];
    var consoleDlg = $("#"+_bizname+"_info");
	consoleDlg.find("#mineid").val(rowData.mineid);
	consoleDlg.find("#orgid").val(rowData.orgid);
	consoleDlg.find("#mineno").val(rowData.mineno);
	consoleDlg.find("#minename").val(rowData.minename);
	consoleDlg.find("#oremanutype").val(rowData.oremanutype);
	consoleDlg.find("#builddate").val(rowData.builddate);
	consoleDlg.find("#startingdate").val(rowData.startingdate);
	consoleDlg.find("#productivepower").val(rowData.productivepower);
	consoleDlg.find("#siftpower").val(rowData.siftpower);
	consoleDlg.find("#transportation").val(rowData.transportation);
	consoleDlg.find("#miningmethod").val(rowData.miningmethod);
	consoleDlg.find("#country").val(rowData.country);
	consoleDlg.find("#province").val(rowData.province);
	consoleDlg.find("#city").val(rowData.city);
	consoleDlg.find("#address").val(rowData.address);
	consoleDlg.find("#area").val(rowData.area);
	consoleDlg.find("#minewidth").val(rowData.minewidth);
	consoleDlg.find("#minelength").val(rowData.minelength);
	consoleDlg.find("#mineheight").val(rowData.mineheight);
	consoleDlg.find("#eastlognstart").val(rowData.eastlognstart);
	consoleDlg.find("#eastlognend").val(rowData.eastlognend);
	consoleDlg.find("#northlatstart").val(rowData.northlatstart);
	consoleDlg.find("#northlatend").val(rowData.northlatend);
	consoleDlg.find("#coordinatex").val(rowData.coordinatex);
	consoleDlg.find("#coordinatey").val(rowData.coordinatey);
	consoleDlg.find("#productivestatus").val(rowData.productivestatus);
	consoleDlg.find("#serviceyear").val(rowData.serviceyear);
	consoleDlg.find("#strippingratio").val(rowData.strippingratio);
	consoleDlg.find("#explorationratio").val(rowData.explorationratio);
	consoleDlg.find("#workusercount").val(rowData.workusercount);
	consoleDlg.find("#techusercount").val(rowData.techusercount);
	consoleDlg.find("#remark").val(rowData.remark);
	consoleDlg.find("#createid").val(rowData.createid);
	consoleDlg.find("#createdate").val(rowData.createdate);
	consoleDlg.find("#modifyid").val(rowData.modifyid);
	consoleDlg.find("#modifydate").val(rowData.modifydate);
}

//新增修改删除保存是获取表单参数
function getInfoData(_bizname){
    var consoleDlg = $("#"+_bizname+"_info");  
	var mineid = $.trim(consoleDlg.find("#mineid").val());
	var orgid = $.trim(consoleDlg.find("#orgid").val());
	var mineno = $.trim(consoleDlg.find("#mineno").val());
	var minename = $.trim(consoleDlg.find("#minename").val());
	var oremanutype = $.trim(consoleDlg.find("#oremanutype").val());
	var builddate = $.trim(consoleDlg.find("#builddate").val());
	var startingdate = $.trim(consoleDlg.find("#startingdate").val());
	var productivepower = $.trim(consoleDlg.find("#productivepower").val());
	var siftpower = $.trim(consoleDlg.find("#siftpower").val());
	var transportation = $.trim(consoleDlg.find("#transportation").val());
	var miningmethod = $.trim(consoleDlg.find("#miningmethod").val());
	var country = $.trim(consoleDlg.find("#country").val());
	var province = $.trim(consoleDlg.find("#province").val());
	var city = $.trim(consoleDlg.find("#city").val());
	var address = $.trim(consoleDlg.find("#address").val());
	var area = $.trim(consoleDlg.find("#area").val());
	var minewidth = $.trim(consoleDlg.find("#minewidth").val());
	var minelength = $.trim(consoleDlg.find("#minelength").val());
	var mineheight = $.trim(consoleDlg.find("#mineheight").val());
	var eastlognstart = $.trim(consoleDlg.find("#eastlognstart").val());
	var eastlognend = $.trim(consoleDlg.find("#eastlognend").val());
	var northlatstart = $.trim(consoleDlg.find("#northlatstart").val());
	var northlatend = $.trim(consoleDlg.find("#northlatend").val());
	var coordinatex = $.trim(consoleDlg.find("#coordinatex").val());
	var coordinatey = $.trim(consoleDlg.find("#coordinatey").val());
	var productivestatus = $.trim(consoleDlg.find("#productivestatus").val());
	var serviceyear = $.trim(consoleDlg.find("#serviceyear").val());
	var strippingratio = $.trim(consoleDlg.find("#strippingratio").val());
	var explorationratio = $.trim(consoleDlg.find("#explorationratio").val());
	var workusercount = $.trim(consoleDlg.find("#workusercount").val());
	var techusercount = $.trim(consoleDlg.find("#techusercount").val());
	var remark = $.trim(consoleDlg.find("#remark").val());
	var createid = $.trim(consoleDlg.find("#createid").val());
	var createdate = $.trim(consoleDlg.find("#createdate").val());
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());
      
    return {   
        "bean.mineid" : mineid,
        "bean.orgid" : orgid,
        "bean.mineno" : mineno,
        "bean.minename" : minename,
        "bean.oremanutype" : oremanutype,
        "bean.builddate" : builddate,
        "bean.startingdate" : startingdate,
        "bean.productivepower" : productivepower,
        "bean.siftpower" : siftpower,
        "bean.transportation" : transportation,
        "bean.miningmethod" : miningmethod,
        "bean.country" : country,
        "bean.province" : province,
        "bean.city" : city,
        "bean.address" : address,
        "bean.area" : area,
        "bean.minewidth" : minewidth,
        "bean.minelength" : minelength,
        "bean.mineheight" : mineheight,
        "bean.eastlognstart" : eastlognstart,
        "bean.eastlognend" : eastlognend,
        "bean.northlatstart" : northlatstart,
        "bean.northlatend" : northlatend,
        "bean.coordinatex" : coordinatex,
        "bean.coordinatey" : coordinatey,
        "bean.productivestatus" : productivestatus,
        "bean.serviceyear" : serviceyear,
        "bean.strippingratio" : strippingratio,
        "bean.explorationratio" : explorationratio,
        "bean.workusercount" : workusercount,
        "bean.techusercount" : techusercount,
        "bean.remark" : remark,
        "bean.createid" : createid,
        "bean.createdate" : createdate,
        "bean.modifyid" : modifyid,
        "bean.modifydate" : modifydate
    };      
}
//根据后台数据，拼装grid行数据
function initGridRowData(_bizname,data){
	var dataJson = eval(data);
    var consoleDlg = $("#"+_bizname+"_info");  
    var orgid = $.trim(consoleDlg.find("#orgid").val());	
	var orgname = $.trim(consoleDlg.find("#orgname").val());	
	var mineno = $.trim(consoleDlg.find("#mineno").val());	
	var minename = $.trim(consoleDlg.find("#minename").val());	
	var oremanutypename = $.trim($("#oremanutype option:selected").text());
	var builddate = $.trim(consoleDlg.find("#builddate").val());	
	var startingdate = $.trim(consoleDlg.find("#startingdate").val());	
	var productivepower = $.trim(consoleDlg.find("#productivepower").val());	
	var siftpower = $.trim(consoleDlg.find("#siftpower").val());	
	var transportation = $.trim(consoleDlg.find("#transportation").val());	
	var miningmethodname = $.trim($("#miningmethod option:selected").text());
	var country = $.trim(consoleDlg.find("#country").val());	
	var province = $.trim(consoleDlg.find("#province").val());	
	var city = $.trim(consoleDlg.find("#city").val());	
	var address = $.trim(consoleDlg.find("#address").val());	
	var area = $.trim(consoleDlg.find("#area").val());	
	var minewidth = $.trim(consoleDlg.find("#minewidth").val());	
	var minelength = $.trim(consoleDlg.find("#minelength").val());	
	var mineheight = $.trim(consoleDlg.find("#mineheight").val());	
	var eastlognstart = $.trim(consoleDlg.find("#eastlognstart").val());	
	var eastlognend = $.trim(consoleDlg.find("#eastlognend").val());	
	var northlatstart = $.trim(consoleDlg.find("#northlatstart").val());	
	var northlatend = $.trim(consoleDlg.find("#northlatend").val());	
	var coordinatex = $.trim(consoleDlg.find("#coordinatex").val());	
	var coordinatey = $.trim(consoleDlg.find("#coordinatey").val());	
	var productivestatusname = $.trim($("#productivestatus option:selected").text());
	var serviceyear = $.trim(consoleDlg.find("#serviceyear").val());	
	var strippingratio = $.trim(consoleDlg.find("#strippingratio").val());	
	var explorationratio = $.trim(consoleDlg.find("#explorationratio").val());	
	var workusercount = $.trim(consoleDlg.find("#workusercount").val());	
	var techusercount = $.trim(consoleDlg.find("#techusercount").val());	
	var remark = $.trim(consoleDlg.find("#remark").val());	
	var createid = $.trim(consoleDlg.find("#createid").val());	
	var createdate = $.trim(consoleDlg.find("#createdate").val());	
	var modifyid = $.trim(consoleDlg.find("#modifyid").val());	
	var modifydate = $.trim(consoleDlg.find("#modifydate").val());	
	
	var countryname = $.trim($("#country option:selected").text());
	var provincename = $.trim($("#province option:selected").text());
	var cityname = $.trim($("#city option:selected").text());
	
	var dataRow = { 			
	    _rowid : dataJson.mineid,// 从Server端得到系统分配的id
		mineid : dataJson.mineid,// 从Server端得到系统分配的id
		orgid : orgid,
		orgname : orgname,
		mineno : mineno,
		minename : minename,
		oremanutypename : oremanutypename,
		builddate : builddate,
		startingdate : startingdate,
		productivepower : productivepower,
		siftpower : siftpower,
		transportation : transportation,
		miningmethodname : miningmethodname,
		country : country,
		countryname : countryname,
		province : province,
		provincename : provincename,
		city : city,
		cityname : cityname,
		address : address,
		area : area,
		minewidth : minewidth,
		minelength : minelength,
		mineheight : mineheight,
		eastlognstart : eastlognstart,
		eastlognend : eastlognend,
		northlatstart : northlatstart,
		northlatend : northlatend,
		coordinatex : coordinatex,
		coordinatey : coordinatey,
		productivestatusname : productivestatusname,
		serviceyear : serviceyear,
		strippingratio : strippingratio,
		explorationratio : explorationratio,
		workusercount : workusercount,
		techusercount : techusercount,
		remark : remark,
		createid : createid,
		createdate : createdate,
		modifyid : modifyid,
		modifydate : modifydate
	};
	return dataRow;
}
