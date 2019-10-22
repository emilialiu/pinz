
var entityArr = new Array();
var layer;
var jh_layer;
var cc_layer;
var jh_alllayer;
var cc_alllayer;
var jh_njhdblayer;
var cc_njhdblayer;
var jh_file;
var cc_file;
var jh_allFile;
var cc_allFile;
var jh_njhdbFile;
var cc_njhdbFile;
var DataBaseManager;
var entity;

var colorArray = [255,65280,65535,14221312,16720032,128];
var color;

var secArray = new Array();//断面数组
var colorLegend = new Array();//图例数组
var monthArray = new Array();//月份数组
var qdArray = new Array();//区队数组
var teamArray = new Array();//区队数组
var devArray = new Array();//数组数组
monthArray.push("1月份","2月份","3月份","4月份","5月份","6月份","7月份","8月份","9月份","10月份","11月份","12月份");
DataBaseManager = DimineWindow.DataBaseManager;
function Init(){
    try{
        DimineWindow.TopBackColor = 0xFF0000 ;//蓝色
        DimineWindow.BottomBackColor = 0xFFFFFF;//白色
    	
        $.ajax({
    		url: rootpath+'/biz/dimine/threedmodel/getSectionInfo.action',
    		cache:false,
    		data : {},
    		success: function(data){
    			var jsondata = eval("("+data+")");
    			var section = jsondata.section;

    			var secid;
    			var type;
    			var geom;
    			var data;
    			for(var i=0; i<section.length; i++){
    				secid = section[i].id;
    				type = section[i].type;
    				geom = section[i].geom;
    				data = JSON.stringify(section[i].data);
    				var secNo;
    				
    				if(type == "userDefined"){
    					secNo = DimineWindow.CreateSection(type,"",geom);
    				}else{
    					secNo = DimineWindow.CreateSection(type,data,"");
    				}
    				var secObj = {};
    				secObj.id = secid;
    				secObj.secNo = secNo;
    				secArray[i] = secObj;
    			}
    		},
    		error:function(data){
    			
    		}
    	});
    }catch (e){
        alert("Error: " + e.description);
    }
}

function DimineWindow::EntitySelected(){
	var selectionSet = DimineWindow.SelectionSet;
	var feature = selectionSet.Item(0);
	if(feature != null){
		loadEntityInfo(feature.GetFieldByName("id"));
	}
}

function DimineWindow::ToolTipSelected(){
	var EntRecord = DimineWindow.ToolTipEntAttRecord;
	loadEntityInfo(EntRecord.GetFieldByName("id"));
}

//设置某个实体选中
function showEntity(id){
	DimineWindow.SelectEntities("id",id);
}

//旋转
function orbit(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.Orbit();
}
//平移
function pan(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.Pan();
}
//复位视图
function zoomToExtents(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.ZoomToExtents();
}
//从上往下看
function xZPlane(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.XZPlane();
}
//从前向后看
function yxPlane(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.YxPlane();
}
//从右向左看
function yzPlane(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.YzPlane();
}
//选择
function select(obj){
	$(".kjbtn > div").removeClass("active_yw");
	$(obj).parent("div").addClass("active_yw");
	DimineWindow.Select();
}
//是否显示ToolTip浮动面板
var showToolTip = true;
function showToolTip(){
	showToolTip = !showToolTip;
	if(showToolTip){
		DimineWindow.ShowToolTip = true;
	}else{
		DimineWindow.ShowToolTip = false;
	}
}

var showAllModel = false;
//展示全部工程
function showAll(){
	showAllModel = !showAllModel;
	if(showAllModel){
		if($("input[id='jhmodeltype']").is(":checked")){
			loadAllProjectModel("jh");
		}
		if($("input[id='ccmodeltype']").is(":checked")){
			loadAllProjectModel("cc");
		}
	}else{
		DataBaseManager.RemoveDB("jh_allFile.dmf");
		DataBaseManager.RemoveDB("cc_allFile.dmf");
		/*jh_allFile.Visible = false;
		jh_allFile.Display();
		cc_allFile.Visible = false;
		cc_allFile.Display();*/
		delColorLegend("全部工程");
		createColorLegend(colorLegend);
	}
	DimineWindow.UpdateView();
}

/**
 * 加载所有的工程模型
 */
var isCreateAllFile = false;
function loadAllProjectModel(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;

	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_allFile.dmf");
		jh_allFile = DataBaseManager.CreateDataBase("jh_allFile.dmf");
		jh_alllayer = jh_allFile.Layer("0");
		layer = jh_alllayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_allFile.dmf");
		cc_allFile = DataBaseManager.CreateDataBase("cc_allFile.dmf");
		cc_alllayer = cc_allFile.Layer("0");
		layer = cc_alllayer;
	}
	$.ajax({
		url: rootpath+'/biz/dimine/threedmodel/loadAllProjectModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype},
		success: function(data){
			var jsondata = eval("("+data+")");
			var modeldata = jsondata.modeldata;
			for(var i=0; i<modeldata.length; i++){
				entity = layer.InsertEntityFromWKB(modeldata[i].linegeom);
				entity.Visible = false;
				entity.AddAttValue("id",modeldata[i].id);
				
				for(var k=0;k<secArray.length;k++){
					if(secArray[k].id == modeldata[i].secid){
						entity.AddAttValue("断面号",secArray[k].secNo);
					}
				}
				laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
				laneway.Transparency = 0.7;
				laneway.AddAttValue("id",modeldata[i].id);
			}
			clObj = {};
			clObj.color = 12632256;
			clObj.name = "全部工程";
			colorLegend.push(clObj);
			createColorLegend(colorLegend);
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});
}

//创建图例
function createColorLegend(){
	DimineWindow.ClearColorLegend();
	for(var i=0;i<colorLegend.length;i++){
		DimineWindow.AddColorLegend(colorLegend[i].color,colorLegend[i].name);//图例
	}
	DimineWindow.CreateColorLegend(2,"图例");//创建图例
}
//添加图例
function addColorLegend(color, name){
	clObj = {};
	clObj.color = color;
	clObj.name = name;
	colorLegend.push(clObj);
}

//根据某个图例名称删除该图例
function delColorLegend(name){
	var delName = name;
	for(var i=0;i<colorLegend.length;i++){
		var nameTemp = colorLegend[i].name;
		if(nameTemp == delName){
			colorLegend.splice(i,1);
			break;
		}
	}
}
//删除所有图例
function removeColorLegend(){
	delColorLegend("计划量");
	for(var i=0;i<monthArray.length;i++){
		delColorLegend(monthArray[i]);
	}
	for(var i=0;i<qdArray.length;i++){
		delColorLegend(qdArray[i]);
	}
	for(var i=0;i<teamArray.length;i++){
		delColorLegend(teamArray[i]);
	}
	for(var i=0;i<devArray.length;i++){
		delColorLegend(devArray[i]);
	}
}

/**
 * 加载年计划模型
 */
function loadYearPlanFile(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx = $("#njh_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#njh_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#njh_month .span_month").attr("value")==undefined?"":$("#njh_month .span_month").attr("value");
	var qdid = $("#njh_qd .span_month").attr("value")==undefined?"":$("#njh_qd .span_month").attr("value");
	var devid = $("#njh_dev .span_month").attr("value")==undefined?"":$("#njh_dev .span_month").attr("value");
	var flagproject = $("#njh_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#njh_flagprj input[name='flagproject']:checked").val();
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#njh_ywlx select[name='njh_minemethod']").val()==undefined?"":$("#njh_ywlx select[name='njh_minemethod']").val();
	}
	var entity;
	var laneway;
	/*if(showAllModel){
		showAllModel = false;
		allFile.Visible = false;
		allFile.Display();
	}*/
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_file.dmf");
		jh_file = DataBaseManager.CreateDataBase("jh_file.dmf");
		jh_layer = jh_file.Layer("0");
		layer = jh_layer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_file.dmf");
		cc_file = DataBaseManager.CreateDataBase("cc_file.dmf");
		cc_layer = cc_file.Layer("0");
		layer = cc_layer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_file.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_file.dmf");
		}
		return;
	}
	
	$.ajax({
		url: rootpath+'/biz/dimine/threedmodel/getYearPlanModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.qdid":qdid,"bean.devid":devid,"bean.flagproject":flagproject,"bean.prmstr":prmstr,"bean.minemethod":minemethod},
		success: function(data){
			var jsondata = eval("("+data+")");
			var showmodeldata = jsondata.showmodeldata;
			var modeldata = jsondata.modeldata;
			
			for(var i=0; i<modeldata.length; i++){
				entity = layer.InsertEntityFromWKB(modeldata[i].linegeom);
				entity.Visible = false;
				entity.AddAttValue("id",modeldata[i].id);
				for(var k=0;k<secArray.length;k++){
					if(secArray[k].id == modeldata[i].secid){
						entity.AddAttValue("断面号",secArray[k].secNo);
					}
				}
				
				if(ywlx == 'YW001' || ywlx == 'YW002'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									if(ywlx == 'YW002'){
										entity.AddLaneWay(showmodeldata[j].month[k].startlocation,showmodeldata[j].month[k].jjlen,showmodeldata[j].month[k].color,true);
									}else{
										entity.AddLaneWay(showmodeldata[j].month[k].startlocation,showmodeldata[j].month[k].jjlen,showmodeldata[j].month[k].color,false);
									}
								}
								entity.ShowLaneWay();
								break;
							}
						}
					}else if(qdid == '-'){
						var areanames = jsondata.areanames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<areanames.length;k++){
									if(areanames[k].areaname == showmodeldata[j].areaname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										if(ywlx == 'YW002'){
											laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,true);
										}else{
											laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
										}
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(devid == '-'){
						var devnames = jsondata.devnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<devnames.length;k++){
									if(devnames[k].devname == showmodeldata[j].devname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										if(ywlx == 'YW002'){
											laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,true);
										}else{
											laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
										}
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else{
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								if(ywlx == 'YW002'){
									laneway = layer.InsertLaneWay(entity,0x00000062,showmodeldata[j].startlocation,showmodeldata[j].jjlen,true);
								}else{
									laneway = layer.InsertLaneWay(entity,0x00000062,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
								}
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW003'){//支护
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							
							laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
							laneway.AddAttValue("id",modeldata[i].id);
							break;
						}
					}
				}else if(ywlx == 'YW004'){//充填
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							
							laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
							laneway.AddAttValue("id",modeldata[i].id);
							break;
						}
					}
				}else if(ywlx == 'YW005'){//安装
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							
							laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
							laneway.AddAttValue("id",modeldata[i].id);
							break;
						}
					}
				}else if(ywlx == 'YW006'){//其他
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							
							laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
							laneway.AddAttValue("id",modeldata[i].id);
							break;
						}
					}
				}
			}
			//for循环结束
			if(ywlx == 'YW001' || ywlx == 'YW002'){
				if(monthvalue == '-'){
					removeColorLegend();
					addColorLegend(255,"1月份");//图例
					addColorLegend(65280,"2月份");//图例
					addColorLegend(65535,"3月份");//图例
					addColorLegend(14221312,"4月份");//图例
					addColorLegend(16720032,"5月份");//图例
					addColorLegend(128,"6月份");//图例
					addColorLegend(5592320,"7月份");//图例
					addColorLegend(33023,"8月份");//图例
					addColorLegend(16776960,"9月份");//图例
					addColorLegend(8409343,"10月份");//图例
					addColorLegend(15527148,"11月份");//图例
					addColorLegend(3487029,"12月份");//图例
					createColorLegend(colorLegend);
				}else if(qdid == '-'){
					removeColorLegend();
					qdArray = new Array();
					for(var k=0;k<areanames.length;k++){
						if(k >= 6){
							color = colorArray[k%6];
						}else{
							color = colorArray[k];
						}
						addColorLegend(color,areanames[k].areaname);//图例
						qdArray.push(areanames[k].areaname);
					}
					createColorLegend(colorLegend);
				}else if(devid == '-'){
					removeColorLegend();
					devArray = new Array();
					for(var k=0;k<devnames.length;k++){
						if(k >= 6){
							color = colorArray[k%6];
						}else{
							color = colorArray[k];
						}
						addColorLegend(color,devnames[k].devname);//图例
						devArray.push(devnames[k].devname);
					}
					createColorLegend(colorLegend);
				}else{
					removeColorLegend();
					addColorLegend(0x00000062,"计划量");//图例
					createColorLegend(colorLegend);
				}
			}else{
				DimineWindow.ClearColorLegend();
			}

			DimineWindow.InsertTitle(0x00000062,yearvalue+"年计划");//控件标题
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});
	//return;
	$(window.frames[0].document).find(".tab-content>div").removeClass("active");
	var _monthvalue = monthvalue=="-"?"":monthvalue;
	var _qdid = qdid=="-"?"":qdid;
	var _devid = devid=="-"?"":devid;
	if(ywlx=='YW001'){
		$(window.frames[0].document).find(".tab-content>div:eq(0)").addClass("active");
		window.frames[0].dimensional_jjjj(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr);
	}else if(ywlx=='YW002'){
		$(window.frames[0].document).find(".tab-content>div:eq(1)").addClass("active");
		window.frames[0].dimensional_cck(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,minemethod);
	}else if(ywlx=='YW003'){
		$(window.frames[0].document).find(".tab-content>div:eq(2)").addClass("active");
		window.frames[0].dimensional_zh(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,modeltype);
	}else if(ywlx=='YW004'){
		$(window.frames[0].document).find(".tab-content>div:eq(3)").addClass("active");
		window.frames[0].dimensional_ct(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,modeltype);
	}else if(ywlx=='YW005'){
		$(window.frames[0].document).find(".tab-content>div:eq(4)").addClass("active");
		window.frames[0].dimensional_az(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr);
	}else if(ywlx=='YW006'){
		$(window.frames[0].document).find(".tab-content>div:eq(5)").addClass("active");
		window.frames[0].dimensional_zsk(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,minemethod);
	}
}

/*
 * 根据属性id查询详情
 */
function loadEntityInfo(id){
	$.ajax({
		url: rootpath+'/biz/dimine/threedmodel/getProjectInfo.action?bean.projectid='+id,
		cache:false,
		data : {},
		success: function(data){
			var jsondata = eval("["+data+"]");
			var htmls = "";
			var EntRecord = DimineWindow.ToolTipEntAttRecord;
			for(var i=0; i<jsondata.length; i++){
				if("工程信息" == jsondata[i].key || "支护信息" == jsondata[i].key){
					htmls = htmls + "<tr><th width=\"40%\">"+jsondata[i].key+"</th><th width=\"60%\">"+jsondata[i].val+"</th></tr>";
				}else{
					htmls = htmls + "<tr><td>"+jsondata[i].key+"</td><td>"+jsondata[i].val+"</td></tr>";
				}
			}
			$("#propertiesvalue").html(htmls);
		},
		error:function(data){
			
		}
	});
	//余强
	if(tabindex==0){//年计划
		var ywlx = $("#njh_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#njh_ywlx input[name='ywlx']:checked").val();
		var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
		var monthvalue = $("#njh_month .span_month").attr("value")==undefined?"":$("#njh_month .span_month").attr("value");
		monthvalue = monthvalue=="-"?"":monthvalue;

		$.ajax({
			url: rootpath+'/biz/bp/yearplanStatistics/getThreedimineInfo.action',
			cache:false,
			data : {"bean.buinesstype":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.projectid":id},
			success: function(data){
				var jsondata = eval("["+data+"]");
				var EntRecord = DimineWindow.ToolTipEntAttRecord;
				for(var i=0; i<jsondata.length; i++){
					EntRecord.SetFieldByName(jsondata[i].key,jsondata[i].val);
				}
			},
			error:function(data){
				
			}
		});
	}else if(tabindex==1){//月计划
		var ywlx = $("#yjh_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#yjh_ywlx input[name='ywlx']:checked").val();
		var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
		var monthvalue = $("#yjh_month .span_month").attr("value")==undefined?"":$("#yjh_month .span_month").attr("value");
		$.ajax({
			url: rootpath+'/biz/bp/monthplanstatistics/getThreedimineInfo.action',
			cache:false,
			data : {"bean.buinesstype":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.projectid":id},
			success: function(data){
				var jsondata = eval("["+data+"]");
				var EntRecord = DimineWindow.ToolTipEntAttRecord;
				for(var i=0; i<jsondata.length; i++){
					EntRecord.SetFieldByName(jsondata[i].key,jsondata[i].val);
				}
			},
			error:function(data){
				
			}
		});
	}else if(tabindex==2){//生产台账
		var ywlx = $("#scrb_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#scrb_ywlx input[name='ywlx']:checked").val();
		var daytime = $("#daytime").val()==undefined||$("#daytime").val()==null?"": $("#daytime").val();
		var startday = $("#startday").text()==undefined||$("#startday").text()==null?"": $("#startday").text();
		var endday = $("#endday").text()==undefined||$("#endday").text()==null?"": $("#endday").text();
		var sdate = $("#daystart").val()==undefined||$("#daystart").val()==null?"": $("#daystart").val();
		var edate = $("#dayend").val()==undefined||$("#dayend").val()==null?"": $("#dayend").val();

		$.ajax({
			url: rootpath+'/biz/pd/dimensions/getThreedimineInfo.action',
			cache:false,
			data : {"bean.bussinesstype":ywlx,"bean.rdate":daytime,"bean.weekstart":startday,"bean.weekend":endday,"bean.sdate":sdate,"bean.edate":edate,"bean.projectid":id},
			success: function(data){
				var jsondata = eval("["+data+"]");
				var EntRecord = DimineWindow.ToolTipEntAttRecord;
				for(var i=0; i<jsondata.length; i++){
					EntRecord.SetFieldByName(jsondata[i].key,jsondata[i].val);
				}
			},
			error:function(data){
				
			}
		});
	}else if(tabindex==3){//内部验收
		var ywlx = $("#nbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#nbys_ywlx input[name='ywlx']:checked").val();
		var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
		var monthvalue = $("#nbys_month .span_month").attr("value")==undefined?"":$("#nbys_month .span_month").attr("value");
		$.ajax({
			url: rootpath+'/biz/bp/monthcaaStatistics/getThreedimineInfo.action',
			cache:false,
			data : {"bean.buinesstype":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.projectid":id,"bean.mcaatype":"1"},
			success: function(data){
				var jsondata = eval("["+data+"]");
				var EntRecord = DimineWindow.ToolTipEntAttRecord;
				for(var i=0; i<jsondata.length; i++){
					EntRecord.SetFieldByName(jsondata[i].key,jsondata[i].val);
				}
			},
			error:function(data){
				
			}
		});
	}else if(tabindex==4){//外部验收
		var ywlx = $("#wbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#wbys_ywlx input[name='ywlx']:checked").val();
		var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
		var monthvalue = $("#wbys_month .span_month").attr("value")==undefined?"":$("#wbys_month .span_month").attr("value");
		$.ajax({
			url: rootpath+'/biz/bp/monthcaaStatistics/getThreedimineInfo.action',
			cache:false,
			data : {"bean.buinesstype":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.projectid":id,"bean.mcaatype":"0"},
			success: function(data){
				var jsondata = eval("["+data+"]");
				var EntRecord = DimineWindow.ToolTipEntAttRecord;
				for(var i=0; i<jsondata.length; i++){
					EntRecord.SetFieldByName(jsondata[i].key,jsondata[i].val);
				}
			},
			error:function(data){
				
			}
		});
	}
}
