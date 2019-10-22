var jh_yjhdblayer;
var cc_yjhdblayer;
var jh_yjhdbFile;
var cc_yjhdbFile;

var showNjhModel = false;
function showNjh(){
	showNjhModel = !showNjhModel;
	if(showNjhModel){
		if($("input[id='jhmodeltype']").is(":checked")){
			loadNjhProjectModel("jh");
		}
		if($("input[id='ccmodeltype']").is(":checked")){
			loadNjhProjectModel("cc");
		}
	}else{
		DataBaseManager.RemoveDB("jh_njhdbFile.dmf");
		DataBaseManager.RemoveDB("cc_njhdbFile.dmf");
		delColorLegend("年计划工程对比");
		createColorLegend(colorLegend);
	}
	DimineWindow.UpdateView();
}


function loadNjhProjectModel(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx =$("#yjh_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#yjh_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#yjh_month .span_month").attr("value")==undefined?"":$("#yjh_month .span_month").attr("value");
	var qdid = $("#yjh_qd .span_month").attr("value")==undefined?"":$("#yjh_qd .span_month").attr("value");
	var devid = $("#yjh_dev .span_month").attr("value")==undefined?"":$("#yjh_dev .span_month").attr("value");
	var teamid = $("#yjh_team .span_month").attr("value")==undefined?"":$("#yjh_team .span_month").attr("value");
	var flagproject = $("#yjh_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#yjh_flagprj input[name='flagproject']:checked").val();

	var _qdid = qdid=="-"?"":qdid;
	var _devid = devid=="-"?"":devid;
	var _teamid = teamid=="-"?"":teamid;
	
	var entity;
	var laneway;
	var njhdblayer;
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_njhdbFile.dmf");
		jh_njhdbFile = DataBaseManager.CreateDataBase("jh_njhdbFile.dmf");
		jh_njhdblayer = jh_njhdbFile.Layer("0");
		njhdblayer = jh_njhdblayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_njhdbFile.dmf");
		cc_njhdbFile = DataBaseManager.CreateDataBase("cc_njhdbFile.dmf");
		cc_njhdblayer = cc_njhdbFile.Layer("0");
		njhdblayer = cc_njhdblayer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_njhdbFile.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_njhdbFile.dmf");
		}
		return;
	}
	delColorLegend("年计划工程对比");
	$.ajax({
		url: rootpath+'/biz/dimine/monthplanmodel/getYMonthPlanModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.qdid":_qdid,"bean.devid":_devid,"bean.teamid":_teamid,"bean.flagproject":flagproject},
		success: function(data){
			var jsondata = eval("("+data+")");
			var showmodeldata = jsondata.showmodeldata;
			var modeldata = jsondata.modeldata;
			for(var i=0; i<modeldata.length; i++){
				entity = njhdblayer.InsertEntityFromWKB(modeldata[i].linegeom);
				entity.Visible = false;
				entity.AddAttValue("id",modeldata[i].id);
				for(var k=0;k<secArray.length;k++){
					if(secArray[k].id == modeldata[i].secid){
						entity.AddAttValue("断面号",secArray[k].secNo);
					}
				}
				if(ywlx == 'YW001'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = njhdblayer.InsertOutLine(entity,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 0x00ff00ff;
							laneway.Display();
							break;
						}
					}
				}else if(ywlx == 'YW002'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = njhdblayer.InsertOutLine(entity,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 0x0000ff00;
							laneway.Display();
						
							break;
						}
					}
				}else{
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = njhdblayer.InsertLaneWay(entity,0x00000062,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
							laneway.AddAttValue("id",modeldata[i].id);
							break;
						}
					}
				}
			}
			clObj = {};
			clObj.color = 16711935;
			clObj.name = "年计划工程对比";
			colorLegend.push(clObj);
			createColorLegend(colorLegend);
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});	
	
}

/**
 * 加载月计划模型
 */
function loadMonthPlanFile(gclx){
	if(showNjhModel){
		loadNjhProjectModel(gclx);
	}
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx =$("#yjh_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#yjh_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#yjh_month .span_month").attr("value")==undefined?"":$("#yjh_month .span_month").attr("value");
	var qdid = $("#yjh_qd .span_month").attr("value")==undefined?"":$("#yjh_qd .span_month").attr("value");
	var devid = $("#yjh_dev .span_month").attr("value")==undefined?"":$("#yjh_dev .span_month").attr("value");
	var teamid = $("#yjh_team .span_month").attr("value")==undefined?"":$("#yjh_team .span_month").attr("value");
	var flagproject = $("#yjh_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#yjh_flagprj input[name='flagproject']:checked").val();
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#yjh_ywlx select[name='yjh_minemethod']").val()==undefined?"":$("#yjh_ywlx select[name='yjh_minemethod']").val();
	}

	var entity;
	var laneway;
	var layer;
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_yjhdbFile.dmf");
		jh_yjhdbFile = DataBaseManager.CreateDataBase("jh_yjhdbFile.dmf");
		jh_yjhdblayer = jh_yjhdbFile.Layer("0");
		layer = jh_yjhdblayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_yjhdbFile.dmf");
		cc_yjhdbFile = DataBaseManager.CreateDataBase("cc_yjhdbFile.dmf");
		cc_yjhdblayer = cc_yjhdbFile.Layer("0");
		layer = cc_yjhdblayer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_yjhdbFile.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_yjhdbFile.dmf");
		}
		return;
	}
	
	$.ajax({
		url: rootpath+'/biz/dimine/monthplanmodel/getMonthPlanModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.qdid":qdid,"bean.devid":devid,"bean.teamid":teamid,"bean.flagproject":flagproject,"bean.projectname":prmstr,"bean.minemethod":minemethod},
		complete:function(){
		},
		success: function(data){
			var jsondata = eval("("+data+")");
			var showmodeldata = jsondata.showmodeldata;
			var modeldata = jsondata.modeldata;
			for(var i=0; i<modeldata.length; i++){
				entity = layer.InsertEntityFromWKB(modeldata[i].linegeom);
				entity.AddAttValue("id",modeldata[i].id);
				for(var k=0;k<secArray.length;k++){
					if(secArray[k].id == modeldata[i].secid){
						entity.AddAttValue("断面号",secArray[k].secNo);
					}
				}
				if(ywlx == 'YW001'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(showmodeldata[j].month[k].startlocation,showmodeldata[j].month[k].jjlen,showmodeldata[j].month[k].color,false);
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
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
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
				}else if(ywlx == 'YW002'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(showmodeldata[j].month[k].totalreserve,showmodeldata[j].month[k].minereserve,showmodeldata[j].month[k].color,true);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YWZSK'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(showmodeldata[j].month[k].totalreserve,showmodeldata[j].month[k].minereserve,showmodeldata[j].month[k].color,true);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW003'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(0,0,showmodeldata[j].month[k].color,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW004'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(0,showmodeldata[j].month[k].ctreserve,showmodeldata[j].month[k].color,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW005'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(0,0,showmodeldata[j].month[k].color,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,0,0,true);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW006'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(0,0,showmodeldata[j].month[k].color,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW007'){
					if(monthvalue == '-'){
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<12;k++){
									entity.AddLaneWay(0,0,showmodeldata[j].month[k].color,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
										laneway.AddAttValue("id",modeldata[i].id);
										break;
									}
								}
								break;
							}
						}
					}else if(teamid == '-'){
						var teamnames = jsondata.teamnames;
						for(var j=0; j<showmodeldata.length; j++){
							if(showmodeldata[j].id == modeldata[i].id){
								for(var k=0;k<teamnames.length;k++){
									if(teamnames[k].teamname == showmodeldata[j].teamname){
										if(k >= 6){
											color = colorArray[k%6];
										}else{
											color = colorArray[k];
										}
										laneway = layer.InsertLaneWay(entity,color,0,0,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}
			}
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
				var areanames = jsondata.areanames;
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
				var devnames = jsondata.devnames;
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
			}else if(teamid == '-'){
				removeColorLegend();
				teamArray = new Array();
				var teamnames = jsondata.teamnames;
				for(var k=0;k<teamnames.length;k++){
					if(k >= 6){
						color = colorArray[k%6];
					}else{
						color = colorArray[k];
					}
					addColorLegend(color,teamnames[k].teamname);//图例
					teamArray.push(teamnames[k].teamname);
				}
				createColorLegend(colorLegend);
			}else{
				removeColorLegend();
				addColorLegend(0x00000062,"计划量");//图例
				createColorLegend(colorLegend);
			}
			DimineWindow.InsertTitle(0x00000062,yearvalue+"年"+monthvalue+"月计划");//控件标题
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});
	
	$(window.frames[0].document).find(".tab-content>div").removeClass("active");

	var _qdid = qdid=="-"?"":qdid;
	var _devid = devid=="-"?"":devid;
	var _teamid = teamid=="-"?"":teamid;
	if(ywlx=='YW001'){
		window.frames[0].dimensional_jjjj(deptid,yearvalue+"-"+monthvalue,_qdid,_devid,_teamid,flagproject);
		$(window.frames[0].document).find(".tab-content>div:eq(0)").addClass("active");
	}else if(ywlx=='YW002'){
		window.frames[0].dimensional_cck(deptid,yearvalue+"-"+monthvalue,_qdid,_devid,_teamid,flagproject,prmstr,minemethod);
		$(window.frames[0].document).find(".tab-content>div:eq(1)").addClass("active");
	}else if(ywlx=='YW003'){
		$(window.frames[0].document).find(".tab-content>div:eq(2)").addClass("active");
		window.frames[0].dimensional_zh(deptid,yearvalue+"-"+monthvalue,modeltype,_qdid,_devid,_teamid,flagproject);
	}else if(ywlx=='YW004'){
		$(window.frames[0].document).find(".tab-content>div:eq(3)").addClass("active");
		window.frames[0].dimensional_ct(deptid,yearvalue+"-"+monthvalue,_qdid,_devid,_teamid,flagproject);
	}else if(ywlx=='YW005'){
		$(window.frames[0].document).find(".tab-content>div:eq(4)").addClass("active");
		window.frames[0].dimensional_az(deptid,yearvalue+"-"+monthvalue,_qdid,_devid,_teamid,flagproject);
	}else if(ywlx=='YW006'){
		$(window.frames[0].document).find(".tab-content>div:eq(5)").addClass("active");
		window.frames[0].dimensional_qt(deptid,yearvalue+"-"+monthvalue,_qdid,_devid,_teamid,flagproject);
	}else if(ywlx=='YWZSK'){
		window.frames[0].dimensional_zsktc(deptid,yearvalue+"-"+monthvalue,_qdid,_devid,_teamid,flagproject);
		$(window.frames[0].document).find(".tab-content>div:eq(6)").addClass("active");
	}
}