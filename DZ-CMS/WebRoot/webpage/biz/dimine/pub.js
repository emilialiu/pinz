
var tabindex = 0;
var navyw = '';
var gclx = "jh";
$(function() {
	$("#jhmodeltype").attr("checked",true);
	slide('right');
	navyw = 'njh';
	getDeptName();
	//loadYearPlanFile('jh');
	
	$(document).on("click","#njh ._month, #yjh ._month, #scrb ._month, #nbys ._month, #wbys ._month",function(){
		if($(this).hasClass("span_month")){
			$(this).removeClass("span_month");
		}else{
			$(this).parent().parent().find("span").removeClass("span_month");
			$(this).addClass("span_month");
			if($(this).attr("value") == '-'){
				$("#"+navyw).find("span[value='-']").removeClass("span_month");
				$(this).addClass("span_month");
			}
		}
		loadModel(gclx);
	});

	$("#daytime").change(function() {
		loadModel();
	});
	$(".yearrpt").click(function(){
		window.open(rootpath+"/main/adminindex.jsp#dimine/biz/bp/yearplan/yearproductplanquery/main.jsp");
	});
	/*余强*/
	$(".monthrpt").click(function(){
		window.open(rootpath+"/main/adminindex.jsp#dimine/biz/bp/monthplan/monthplanck/main.jsp");
	});
	$("#prmstr_btn_search").click(function(){
		loadModel();
	});
	//全部工程
	$("#allPro").click(function(){
		if($(this).hasClass("curbtn")){
			$(this).removeClass("curbtn");
		}else{
			$(this).addClass("curbtn");
		}
		showAll();
	});
	//年计划工程对比
	$("#yearPlanPro").click(function(){
		if($(this).hasClass("curbtn")){
			$(this).removeClass("curbtn");
		}else{
			$(this).addClass("curbtn");
		}
		showNjh();
	});
	//年计划工程对比
	$("#yearPlanPro2").click(function(){
		if($(this).hasClass("curbtn")){
			$(this).removeClass("curbtn");
		}else{
			$(this).addClass("curbtn");
		}
		showNjh2();
	});
	//月计划工程对比
	$("#mthPlanPro").click(function(){
		if($(this).hasClass("curbtn")){
			$(this).removeClass("curbtn");
		}else{
			$(this).addClass("curbtn");
		}
		showYjh();
	});
	//月计划工程对比
	$("#mthPlanPro2").click(function(){
		if($(this).hasClass("curbtn")){
			$(this).removeClass("curbtn");
		}else{
			$(this).addClass("curbtn");
		}
		showYjh2();
	});
	//月验收工程对比
	$("#mthcaaPro").click(function(){
		if($(this).hasClass("curbtn")){
			$(this).removeClass("curbtn");
		}else{
			$(this).addClass("curbtn");
		}
		showCaa();
	});
});

//tab点击事件
$(".type > div").click(function() {
	DataBaseManager.RemoveAll();
	//DataBaseManager.RemoveDB("file.dmf");
	//DataBaseManager.RemoveDB("allFile.dmf");
	//DataBaseManager.RemoveDB("njhdbFile.dmf");
	showAllModel = false;
	showNjhModel = false;
	showYjhModel = false;
	showYjhModel2 = false;
	showNjhModel2 = false;
	showCaaModel = false;
	colorLegend = new Array();
	$(".ladda-button").removeClass("curbtn");
	
	$(".type > div").removeClass("active_yw");
	$(this).addClass("active_yw");
	// 选取第一个标签页
	$('#myTab3 a:first').tab('show');
	tabindex = $(".type > div").index(this);
	$("#cxtj > div").hide();
	if(tabindex==0){
		navyw = 'njh';
		$("#njh").show();
		$("#impPro,#yearPlanPro,#mthPlanPro,#mthPlanPro2,#yearPlanPro2,#mthcaaPro").hide();
		//loadConditions(tabindex);
		$("#datagrid_ifr").attr("src",rootpath+"/biz/bp/yearplan/threedimensional/bizmain.jsp");
	}else if(tabindex==1){
		navyw = 'yjh';
		$("#yjh").show();
		$("#yearPlanPro").show();
		$("#impPro,#mthPlanPro,#mthPlanPro2,#yearPlanPro2,#mthcaaPro").hide();
		//loadConditions(tabindex);
		/*余强*/
		
		$("#datagrid_ifr").attr("src",rootpath+"/biz/bp/monthplan/threedimensional/bizmain.jsp");
		var isclickmonthvalue = $("#yjh_month .span_month").attr("value")==undefined?"":$("#yjh_month .span_month").attr("value");
		if(isclickmonthvalue==""){
			//$("#yjh_month").children().eq(1).children().eq(0).trigger("click");
			$("#yjh_month").find("._month").eq(time_month-1).trigger("click");
		}
	}else if(tabindex==2){
		navyw = 'scrb';
		$("#scrb").show();
		$("#impPro,#yearPlanPro,#yearPlanPro2,#mthPlanPro,#mthPlanPro2,#mthcaaPro").hide();
		//设置iframe的src
		$("#datagrid_ifr").attr('src',rootpath+'/biz/pd/threedimensions/bizmain.jsp');
		//loadConditions(tabindex);
		
	}else if(tabindex==3){
		navyw = 'nbys';
		$("#nbys").show();
		$("#mthPlanPro,#yearPlanPro2").show();
		$("#impPro,#mthPlanPro2,#yearPlanPro,#mthcaaPro").hide();
		$("#datagrid_ifr").attr('src',rootpath+'/biz/bp/monthcaa/threedimensional/bizmain.jsp');
		//loadConditions(tabindex);
	}else if(tabindex==4){
		navyw = 'wbys';
		$("#wbys").show();
		$("#mthPlanPro2,#mthcaaPro").show();
		$("#impPro,#mthPlanPro,#yearPlanPro,#yearPlanPro2").hide();
		$("#datagrid_ifr").attr('src',rootpath+'/biz/bp/monthcaa/threedimensional/bizmain.jsp');
		//loadConditions(tabindex);
	}

});
function loadConditions(tabindex){
	$("#"+navyw+"_ywlx p.ckff").hide();
	if(tabindex==0){
		//加载区队条件
		loadAreaCon();
		//加载设备条件
		getDevTypeSelect();
	}else if(tabindex==1){
		//加载区队条件
		loadAreaCon();
		//加载设备条件
		getDevTypeSelect();
		//加载班组条件
		loadTeamSelect();
	}else if(tabindex==2){
		//加载周次信息列表
		weeknumList(deptid);
		//加载区队条件
		loadAreaCon();
		//加载设备条件
		getDevTypeSelect();
		//加载班组条件
		loadTeamSelect();
		
	}else if(tabindex==3){
		//加载区队条件
		loadAreaCon();
		//加载设备条件
		getDevTypeSelect();
		//加载班组条件
		loadTeamSelect();
	}else if(tabindex==4){
	}
}

$("#deptsearch").click(function(){
	var obj = window.showModalDialog(rootpath+"/pub/select/dept/showDeptModelDlg.action","open","dialogHeight=400px;dialogWidth=350px;dialogTop=5;status=no;scroll=no;");
	if(obj){
		$('#deptname').html(obj.name);
		deptid = obj.id;
	}
});

$(document).on("change","#njh input[name='ywlx'], #yjh input[name='ywlx'], #scrb input[name='ywlx'], #nbys input[name='ywlx'], #wbys input[name='ywlx']",function(){
	index = $("#"+navyw+" input[name='ywlx']").index(this);
	if ($(this).is(":checked")) {
		$("#"+navyw+" input[name='ywlx']").each(function(i,e){
			if(i!=index)
				$("#"+navyw+" input[name='ywlx']:eq("+i+")").attr("checked",false);
		});
	}
	$("#"+navyw+"_devclass").val("");
	$("#"+navyw+"_minemethod").val("");
	$("#"+navyw+"_teamtype").val("");
	$("#"+navyw+" #"+navyw+"_dev").html("");
	$("#"+navyw+" #"+navyw+"_team").html("");
	$("#"+navyw+" input[name='flagproject']").each(function(i,e){
		$("#"+navyw+" input[name='flagproject']:eq("+i+")").attr("checked",false);
	});
	var ywlx = $("#"+navyw+" input[name='ywlx']:checked").val();
	if(ywlx == undefined){
	}else{
		loadConditions(tabindex);
		$("input[name='modeltype']").prop("checked",false);
		if(ywlx == 'YW001'){
			gclx = "jh";
		}else if(ywlx == 'YW002' || ywlx == 'YW004' || ywlx == 'YWZSK'){
			gclx = "cc";
		}
		if(ywlx == 'YW002'){
			if(tabindex!=2){
				$("#"+navyw+"_ywlx p.ckff").show();
			}
		}else{
			$("#"+navyw+"_ywlx p.ckff").hide();
		}
		if(ywlx == 'YW003' || ywlx == 'YW004' || ywlx == 'YW006'){
			$("#"+navyw+"_flagprj").parent("div").hide();
			$("#"+navyw+" input[name='flagproject']").each(function(i,e){
				$("#"+navyw+" input[name='flagproject']:eq("+i+")").attr("checked",false);
			});
		}else{
			$("#"+navyw+"_flagprj").parent("div").show();
		}
		
		if($("#scrb").is(":visible")){
			if(ywlx == 'YW006'){
				gclx = "cc";
			}
		}
		if(tabindex==0){
			if(ywlx == 'YW003' || ywlx == 'YW004' || ywlx == 'YW005'){
				$("#"+navyw+" #"+navyw+"_dev").html("");
				$("#"+navyw+" #"+navyw+"_devclass").parent("div").hide();
			}else{
				$("#"+navyw+" #"+navyw+"_devclass").parent("div").show();
			}
			if(ywlx == 'YW006'){
				gclx = "cc";
			}
		}
		if(tabindex==2){
			if(ywlx == 'YW002'){
				$("#"+navyw+"_flagprj").parent("div").hide();
				$("#"+navyw+" input[name='flagproject']").each(function(i,e){
					$("#"+navyw+" input[name='flagproject']:eq("+i+")").attr("checked",false);
				});
			}
		}
	}
	if(gclx == "jh"){
		$("#jhmodeltype").prop("checked",true);
	}else{
		$("#ccmodeltype").prop("checked",true);
	}
	DataBaseManager.RemoveAll();
	//loadModel(gclx);
});

$("#njh input[name='flagproject'], #yjh input[name='flagproject'], #scrb input[name='flagproject'], #nbys input[name='flagproject'], #wbys input[name='flagproject']").change(function(){
	index = $("#"+navyw+" input[name='flagproject']").index(this);
	if ($(this).is(":checked")) {
		$("#"+navyw+" input[name='flagproject']").each(function(i,e){
			if(i!=index)
				$("#"+navyw+" input[name='flagproject']:eq("+i+")").attr("checked",false);
		});
	}
	loadModel(gclx);
});

$("#jhmodeltype").change(function() {
	loadModel('jh');
});
$("#ccmodeltype").change(function() {
	loadModel('cc');
});

//加载项目名称
function getDeptName(){
	$.ajax( {
		url : rootpath+"/pub/select/dept/getDeptName.action",
		type:'POST',
		data : {},
		cache : true,
		async : true,
		error : function(data) {
			alertErrorMsg("系统ajax交互错误");
		},
		success : function(data) {
			$("#deptname").html(data);
		}
	});
}

//加载工序
function loadTech(techid){
	$.ajax({  
        url :rootpath+'/webpage/biz/pm/ypdetail/genProcBytechId.action',
        data : {'bean.techid':techid},  
        cache : false,  
        async: false,
        error : function(data) {
        	alertErrorMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
        	var dataJson=eval(data);
        	var htmlstr="";
        	for(var i=0;i<dataJson.length;i++){
        		if(i%2 == 0){
        			htmlstr+="<p><input type=\"checkbox\" name=\"ywlx\" value=\""+dataJson[i].procid+"\"/>"+dataJson[i].proctname+"&nbsp;&nbsp;";
				}else{
					htmlstr+="<input type=\"checkbox\" name=\"ywlx\" value=\""+dataJson[i].procid+"\"/>"+dataJson[i].proctname+"</p>";
				}
        	}
        	$("#njh_gx").html(htmlstr);
        }  
	});
}

//加载区队条件
function loadAreaCon(){
	$.ajax( {
		url : rootpath+"/manager/sys/dept/getGgByParentDeptId.action",
		type:'POST',
		data : {"bean.deptid":deptid},
		cache : true,
		async : true,
		error : function(data) {
			alertErrorMsg("系统ajax交互错误");
		},
		success : function(data) {
			var jsondata = eval('('+data+')');
			//<span class="_month">掘进班组</span>&nbsp;&nbsp;
			var qdhtml = "<p><span class='_month' value='-'>全部</span></p>";
			for(var i=0;i<jsondata.length;i++){
				if(i%2 == 0){
					qdhtml +="<p><span class='_month' value='"+jsondata[i].deptid+"'>"+jsondata[i].deptname+"</span>&nbsp;&nbsp;";
				}else{
					qdhtml +="<span class='_month' value='"+jsondata[i].deptid+"'>"+jsondata[i].deptname+"</span></p>";
				}
			}
			if(jsondata.length%2 ==1){
				qdhtml += "</p>";
			}
			$("#"+navyw+" #"+navyw+"_qd").html(qdhtml);
		}
	});
}

//加载设备下拉列表
function getDevTypeSelect(){
	$("#"+navyw+"_devclass")[0].options.length = 0;
	var procid = $("#"+navyw+" input[name='ywlx']:checked").val();
	$.ajax({
		url: rootpath+"/webpage/biz/dm/device/getDevTypeSelect.action",
		data : {"bean.procid":procid},
	    cache : false,
	    async : false,
		success: function(data){			
			classtype = eval('({'+data+'})');
			var objOption = new Option("","");
			$("#"+navyw+"_devclass")[0].add(objOption);
			for(var i in classtype){
				objOption = new Option(classtype[i],i);
				$("#"+navyw+"_devclass")[0].add(objOption);
			}
			if(classtype.length > 0){
				reloadDevName();
			}
		},
		error: function(data){
			alert(1);
			alertErrorMsg('信息列表获取失败！');
		}
	});
}
//加载设备条件
function reloadDevName(){
	$("#"+navyw+"_devname")[0].options.length = 0;
	var devclass = $("#"+navyw+"_devclass").val();
	$.ajax({
		url: rootpath+"/webpage/biz/dm/device/selectDevclass.action",
		data: {"dictbean.parentid": devclass==''?'-':devclass},//键值对.传递的参数,如果是多个参数，写法就是这样[{ name : 'bean.id', value : ll[0].id }]
	    cache : false,
	    async : false,
		success: function(data){			
			classtype = eval('({'+data+'})');
			var objOption = new Option("","");
			$("#"+navyw+"_devname")[0].add(objOption);
			for(var i in classtype){
				objOption = new Option(classtype[i],i);
				$("#"+navyw+"_devname")[0].add(objOption);
			}
			//loadDevCon();
		},
		error: function(data){
			alertErrorMsg('信息列表获取失败！');
		}
	});
}
//加载设备条件
function loadDevCon(){
	var procid = $("#"+navyw+" input[name='ywlx']:checked").val();
	var devname = $("#"+navyw+"_devname").val();
	$.ajax( {
		url : rootpath+"/webpage/biz/dm/device/getDevs.action",
		type:'POST',
		data : {"bean.deptid":deptid,"bean.procid":procid,"bean.devname":(devname==''||devname==undefined)?'-':devname},
		cache : true,
		async : true,
		error : function(data) {
			alertErrorMsg("系统ajax交互错误");
		},
		success : function(data) {
			var jsondata = eval('('+data+')');
			//<span class="_month">掘进班组</span>&nbsp;&nbsp;
			var devhtml = "<p><span class='_month' value='-'>全部</span></p>";
			for(var i=0;i<jsondata.length;i++){
				if(i%2 == 0){
					devhtml +="<p><span class='_month' value='"+jsondata[i].value+"'>"+jsondata[i].text+"</span>&nbsp;&nbsp;";
				}else{
					devhtml +="<span class='_month' value='"+jsondata[i].value+"'>"+jsondata[i].text+"</span></p>";
				}
			}
			if(jsondata.length%2 ==1){
				devhtml += "</p>";
			}
			$("#"+navyw+" #"+navyw+"_dev").html(devhtml);
		}
	});
}
//加载班组下拉列表
function loadTeamSelect(){
	$("#"+navyw+"_teamtype")[0].options.length = 0;
	var ywlx = $("#"+navyw+" input[name='ywlx']:checked").val();
	if(ywlx == "YWZSK"){
		ywlx = "YW002";
	}
	$.ajax({
		url : rootpath+"/biz/sm/team/getTeamSelect.action",
		data : {"bean.tdeptid":deptid,"bean.bistype":ywlx},
	    cache : false,
	    async : false,
		success: function(data){			
			classtype = eval('({'+data+'})');
			var objOption = new Option("","");
			$("#"+navyw+"_teamtype")[0].add(objOption);
			for(var i in classtype){
				objOption = new Option(classtype[i],i);
				$("#"+navyw+"_teamtype")[0].add(objOption);
			}
		},
		error: function(data){
			alertErrorMsg('信息列表获取失败！');
		}
	});
}
//加载班组条件
function loadTeamCon(){
	var ywlx = $("#"+navyw+" input[name='ywlx']:checked").val();
	if(ywlx=="YWZSK"){
		ywlx = "YW002";
	}
	var teamtype = $("#"+navyw+"_teamtype").val();
	if(teamtype==''||teamtype=='-'){
		$("#"+navyw+" #"+navyw+"_team").html("");
	}else{
		$.ajax( {
			url : rootpath+"/biz/sm/team/getTeams.action",
			type:'POST',
			data : {"bean.tdeptid":deptid,"bean.teamtype":teamtype,"bean.bistype":ywlx},
			cache : true,
			async : true,
			error : function(data) {
				alertErrorMsg("系统ajax交互错误");
			},
			success : function(data) {
				var jsondata = eval('('+data+')');
				//<span class="_month">掘进班组</span>&nbsp;&nbsp;
				var teamhtml = "<p><span class='_month' value='-'>全部</span></p>";
				for(var i=0;i<jsondata.length;i++){
					if(i%2 == 0){
						teamhtml +="<p><span class='_month' value='"+jsondata[i].teamid+"'>"+jsondata[i].teamname+"</span>&nbsp;&nbsp;";
					}else{
						teamhtml +="<span class='_month' value='"+jsondata[i].teamid+"'>"+jsondata[i].teamname+"</span></p>";
					}
				}
				if(jsondata.length%2 ==1){
					teamhtml += "</p>";
				}
				$("#"+navyw+" #"+navyw+"_team").html(teamhtml);
			}
		});
	}
}
/*
 * 加载模型
 */
function loadModel(gclx){
	if(tabindex==0){
		loadYearPlanFile(gclx);
	}else if(tabindex==1){
		loadMonthPlanFile(gclx);
	}else if(tabindex==2){
		loadProdDayReptFile(gclx);
	}else if(tabindex==3){
		loadMonthcaaFile(gclx);
	}else if(tabindex==4){
		loadMonthccaOutFile(gclx);
	}
}

//隐藏、显示左右侧边栏
function slide(sidebarSide){
	if(sidebarSide=="left"){
		if($("#leftslide").is(":hidden")){//如果是隐藏的，就打开
			$("#leftslide").css("display","block");
			$("#modelcol").parent().parent().removeClass();//"col-sm-7 col-sm-9 col-sm-10 col-sm-12"
			$(".left>i").removeClass("fa-angle-double-right").addClass("fa-angle-double-left");
			if($("#rightslide").is(":hidden")){
				$("#modelcol").parent().parent().addClass("col-xs-12 col-sm-10");
				$("#modelcol").addClass("col-xs-12 col-sm-12");
			}else{
				$("#modelcol").parent().parent().addClass("col-xs-12 col-sm-10");
			}
		}else{//如果是打开的，就隐藏
			$("#leftslide").css("display","none");
			$("#modelcol").parent().parent().removeClass();//"col-sm-7 col-sm-9 col-sm-10 col-sm-12"
			$(".left>i").removeClass("fa-angle-double-left").addClass("fa-angle-double-right");
			if($("#rightslide").is(":hidden")){
				$("#modelcol").parent().parent().addClass("col-xs-12 col-sm-12");
				$("#modelcol").addClass("col-xs-12 col-sm-12");
			}else{
				$("#modelcol").parent().parent().addClass("col-xs-12 col-sm-12");
			}
		}
	}else{
		if($("#rightslide").is(":hidden")){//如果是隐藏的，就打开
			$("#rightslide").css("display","block");
			$("#modelcol").removeClass();//"col-sm-7 col-sm-9 col-sm-10 col-sm-12"
			$(".right>i").removeClass("fa-angle-double-left").addClass("fa-angle-double-right");
			if($("#leftslide").is(":hidden")){
				$("#modelcol").parent().parent().addClass("col-xs-12 col-sm-12");
				$("#modelcol").addClass("col-xs-12 col-sm-9");
			}else{
				$("#modelcol").addClass("col-xs-12 col-sm-9");
			}
		}else{//如果是打开的，就隐藏
			$("#rightslide").css("display","none");
			$("#modelcol").removeClass();//"col-sm-7 col-sm-9 col-sm-10 col-sm-12"
			$(".right>i").removeClass("fa-angle-double-right").addClass("fa-angle-double-left");
			if($("#leftslide").is(":hidden")){
				$("#modelcol").parent().parent().addClass("col-xs-12 col-sm-12");
				$("#modelcol").addClass("col-xs-12 col-sm-12");
			}else{
				$("#modelcol").addClass("col-xs-12 col-sm-12");
			}
		}
	}
}

//周次改变事件
function changeweekvalue(value){
	if(value == 0){
		$("#daytime").val(now);
		$("#startendAll").hide();
		$("#startend").text("");
		$("#endtend").text("");
	}else{
		$("#daytime").val("");
		$("#startendAll").show();
		$("#startend").html("");
		var start = data_week.data[value-1].startdate;
		var end = data_week.data[value-1].enddate;
		$("#startend").html("<div id='startday'>"+start+"</div><div id='endday'>"+end+"</div>");
	}
	//下面调用查询模型数据js
	loadProdDayReptFile();
}

//日期改变事件
function changedayvalue(){
	$("#weektime").val(0);
	$("#startday").html("");
	$("#endday").html("");
	$("#startendAll").hide();
	$("#daystart").val("");
	$("#dayend").val("");
	weeknumList(deptid);
	//下面调用查询模型数据js
	loadProdDayReptFile();
}

//周次下拉列表点击事件
function clearday(value){
	if(value != 0){
		$("#daytime").val("");
		$("#daystart").val("");
		$("#dayend").val("");
	}
}
//初始化周次下拉列表值
function weeknumList(deptid){
	var month = $("#daytime").val();
	if(month != undefined && month != ""){
		var monthvalue = month.substring(0,7);
		$.ajax( {  
			url : rootpath+"/biz/pd/productweekrpt/selectWeekInfo.action", 
			type:'POST',
			data : {'bean.enddate':monthvalue,'bean.deptid':deptid},  
			cache : false,
			async : false,
			error : function(data) {  
				alertErrorMsg("系统ajax交互错误");   
			},  
			success : function(data) {
				data_week = eval('('+data+')');
				var osle=$("#weektime");
				$(osle)[0].options.length = 0;
				$(osle)[0].add(new Option("         ",0));
				for(var i=0;i<data_week.data.length;i++){
					var objOption = new Option(data_week.data[i].weekname,data_week.data[i].weekvalue);
					$(osle)[0].add(objOption);
				}
				//setweekinfo();
			}  
		 });	
	}
}

/**
 * 开始时间和结束时间查询
 */
function changedatevalue(){
	$("#weektime").val(0);
	$("#startday").html("");
	$("#endday").html("");
	$("#startendAll").hide();
	$("#daytime").val("");
	
	var start = $("#daystart").val();
	var end = $("#dayend").val();
	if(start != undefined && start != "" && end != undefined && end != ""){
		var s1 = start.split("-");
		var s2 = end.split("-");
		if(new Date(s1[0],s1[1],s1[2]) < new Date(s2[0],s2[1],s2[2])){
			loadProdDayReptFile();
		}else{
			alert("开始日期必须小于结束日期");
			return;
		}
	}
	
}