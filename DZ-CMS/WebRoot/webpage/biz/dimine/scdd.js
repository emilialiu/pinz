
/**
 * 加载生产日报模型
 */
function loadProdDayReptFile(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx = $("#scrb_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#scrb_ywlx input[name='ywlx']:checked").val();
	var daytime = $("#daytime").val()==undefined||$("#daytime").val()==null?"": $("#daytime").val();
	var startday = $("#startday").text()==undefined||$("#startday").text()==null?"": $("#startday").text();
	var endday = $("#endday").text()==undefined||$("#endday").text()==null?"": $("#endday").text();
	var sdate = $("#daystart").val()==undefined||$("#daystart").val()==null?"": $("#daystart").val();
	var edate = $("#dayend").val()==undefined||$("#dayend").val()==null?"": $("#dayend").val();
	var qdid = $("#scrb_qd .span_month").attr("value")==undefined?"":$("#scrb_qd .span_month").attr("value");
	var devid = $("#scrb_dev .span_month").attr("value")==undefined?"":$("#scrb_dev .span_month").attr("value");
	var teamid = $("#scrb_team .span_month").attr("value")==undefined?"":$("#scrb_team .span_month").attr("value");
	var flagproject = $("#scrb_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#scrb_flagprj input[name='flagproject']:checked").val();
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#scrb_ywlx select[name='scrb_minemethod']").val()==undefined?"":$("#scrb_ywlx select[name='scrb_minemethod']").val();
	}
	var entity;
	var laneway;
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
		url: rootpath+'/biz/dimine/productmodel/getProductModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.productdate":daytime,"bean.weekstart":startday,"bean.weekend":endday,"bean.sdate":sdate,"bean.edate":edate,"bean.qdid":qdid,"bean.devid":devid,"bean.teamid":teamid,"bean.flagproject":flagproject,"bean.minemethod":minemethod},
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
					if(qdid == '-'){
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW002'){
					if(qdid == '-'){
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
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
										laneway = layer.InsertLaneWay(entity,color,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
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
								laneway = layer.InsertLaneWay(entity,0x00000062,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
								laneway.AddAttValue("id",modeldata[i].id);
								break;
							}
						}
					}
				}else if(ywlx == 'YW003'){
					if(qdid == '-'){
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
					if(qdid == '-'){
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
					if(qdid == '-'){
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
				}else if(ywlx == 'YW006'){
					if(qdid == '-'){
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
					if(qdid == '-'){
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
			if(qdid == '-'){
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
			DimineWindow.InsertTitle(0x00000062,daytime+"生产调度");//控件标题
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});
	
	$(window.frames[0].document).find(".tab-content>div").removeClass("active");
	var _teamid = teamid=="-"?"":teamid;
	var _qdid = qdid=="-"?"":qdid;
	var _devid = devid=="-"?"":devid;
	if(ywlx=='YW001'){
		window.frames[0].dimensions_jj(deptid,daytime,startday,endday,sdate,edate,_qdid,_devid,_teamid,flagproject,prmstr);
		$(window.frames[0].document).find(".tab-content>div:eq(0)").addClass("active");
	}else if(ywlx=='YW002'){
		window.frames[0].dimensions_cck(deptid,daytime,startday,endday,sdate,edate,_qdid,_devid,_teamid,flagproject,prmstr,minemethod);
		$(window.frames[0].document).find(".tab-content>div:eq(1)").addClass("active");
	}else if(ywlx=='YW003'){
		window.frames[0].dimensions_zh(deptid,daytime,startday,endday,sdate,edate,_qdid,_devid,_teamid,flagproject,prmstr);
		$(window.frames[0].document).find(".tab-content>div:eq(2)").addClass("active");
	}else if(ywlx=='YW004'){
		window.frames[0].dimensions_ct(deptid,daytime,startday,endday,sdate,edate,_qdid,_devid,_teamid,flagproject,prmstr);
		$(window.frames[0].document).find(".tab-content>div:eq(3)").addClass("active");
	}else if(ywlx=='YW005'){
		window.frames[0].dimensions_az(deptid,daytime,startday,endday,sdate,edate,_qdid,_devid,_teamid,flagproject,prmstr);
		$(window.frames[0].document).find(".tab-content>div:eq(4)").addClass("active");
	}else if(ywlx=='YW006'){
		window.frames[0].dimensions_zsk(deptid,daytime,startday,endday,sdate,edate,_qdid,_devid,_teamid,flagproject,prmstr);
		$(window.frames[0].document).find(".tab-content>div:eq(5)").addClass("active");
	}
}