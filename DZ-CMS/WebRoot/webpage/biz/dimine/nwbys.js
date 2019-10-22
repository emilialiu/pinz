var jh_nbysdblayer;
var cc_nbysdblayer;
var jh_wbysdblayer;
var cc_wbysdblayer;
var jh_nbysdbFile;
var cc_nbysdbFile;
var jh_wbysdbFile;
var cc_wbysdbFile;
var showYjhModel = false;
function showYjh(){
	showYjhModel = !showYjhModel;
	if(showYjhModel){
		if($("input[id='jhmodeltype']").is(":checked")){
			loadYjhProjectModel("jh");
		}
		if($("input[id='ccmodeltype']").is(":checked")){
			loadYjhProjectModel("cc");
		}
	}else{
		DataBaseManager.RemoveDB("jh_yjhdbFile.dmf");
		DataBaseManager.RemoveDB("cc_yjhdbFile.dmf");
		delColorLegend("月计划工程量");
		createColorLegend(colorLegend);
	}
	DimineWindow.UpdateView();
}
var showYjhModel2 = false;
function showYjh2(){
	showYjhModel2 = !showYjhModel2;
	if(showYjhModel2){
		if($("input[id='jhmodeltype']").is(":checked")){
			loadYjhProjectModel2("jh");
		}
		if($("input[id='ccmodeltype']").is(":checked")){
			loadYjhProjectModel2("cc");
		}
	}else{
		DataBaseManager.RemoveDB("jh_yjhdbFile.dmf");
		DataBaseManager.RemoveDB("cc_yjhdbFile.dmf");
		delColorLegend("月计划工程量");
		createColorLegend(colorLegend);
	}
	DimineWindow.UpdateView();
}

var showNjhModel2 = false;
function showNjh2(){
	showNjhModel2 = !showNjhModel2;
	if(showNjhModel2){
		if($("input[id='jhmodeltype']").is(":checked")){
			loadNjhProjectModel2("jh");
		}
		if($("input[id='ccmodeltype']").is(":checked")){
			loadNjhProjectModel2("cc");
		}
	}else{
		DataBaseManager.RemoveDB("jh_njhdbFile.dmf");
		DataBaseManager.RemoveDB("cc_njhdbFile.dmf");
		delColorLegend("年计划工程量");
		createColorLegend(colorLegend);
	}
	DimineWindow.UpdateView();
}

var showCaaModel = false;
function showCaa(){
	showCaaModel = !showCaaModel;
	if(showCaaModel){
		if($("input[id='jhmodeltype']").is(":checked")){
			loadMonthcaaFile2("jh");
		}
		if($("input[id='ccmodeltype']").is(":checked")){
			loadMonthcaaFile2("cc");
		}
	}else{
		DataBaseManager.RemoveDB("jh_nbysdbFile.dmf");
		DataBaseManager.RemoveDB("cc_nbysdbFile.dmf");
		delColorLegend("内部验收工程量");
		createColorLegend(colorLegend);
	}
	DimineWindow.UpdateView();
}

//内部验收对比月计划
function loadYjhProjectModel(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx = $("#nbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#nbys_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#nbys_month .span_month").attr("value")==undefined?"":$("#nbys_month .span_month").attr("value");
	var qdid = $("#nbys_qd .span_month").attr("value")==undefined?"":$("#nbys_qd .span_month").attr("value");
	var devid = $("#nbys_dev .span_month").attr("value")==undefined?"":$("#nbys_dev .span_month").attr("value");
	var flagproject = $("#nbys_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#nbys_flagprj input[name='flagproject']:checked").val();
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var teamid = $("#nbys_team .span_month").attr("value")==undefined?"":$("#nbys_team .span_month").attr("value");
	
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
	delColorLegend("月计划工程量");
	$.ajax({
		url: rootpath+'/biz/dimine/monthplanmodel/getMonthPlanModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.qdid":qdid,"bean.devid":devid,"bean.teamid":teamid,"bean.flagproject":flagproject},
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
				if(ywlx == 'YW001'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 16776960;
							laneway.Display();
							break;
						}
					}
				}else if(ywlx == 'YW002'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,true);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 16776960;
							laneway.Display();
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
			}
			clObj = {};
			clObj.color = 16776960;
			clObj.name = "月计划工程量";
			colorLegend.push(clObj);
			createColorLegend(colorLegend);
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});	
	
}
//外部验收对比月计划
function loadYjhProjectModel2(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx =$("#wbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#wbys_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#wbys_month .span_month").attr("value")==undefined?"":$("#wbys_month .span_month").attr("value");
	var flagproject = $("#wbys_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#wbys_flagprj input[name='flagproject']:checked").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#wbys_ywlx select[name='wbys_minemethod']").val()==undefined?"":$("#wbys_ywlx select[name='wbys_minemethod']").val();
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
	delColorLegend("月计划工程量");
	$.ajax({
		url: rootpath+'/biz/dimine/monthplanmodel/getMonthPlanModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.flagproject":flagproject},
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
				if(ywlx == 'YW001'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 16776960;
							laneway.Display();
							break;
						}
					}
				}else if(ywlx == 'YW002'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 16776960;
							laneway.Display();
							
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
			}

			clObj = {};
			clObj.color = 16776960;
			clObj.name = "月计划工程量";
			colorLegend.push(clObj);
			createColorLegend(colorLegend);
			DimineWindow.UpdateView();
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});	
	
}
//外部验收对比内部验收
function loadMonthcaaFile2(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var ywlx =$("#wbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#wbys_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#wbys_month .span_month").attr("value")==undefined?"":$("#wbys_month .span_month").attr("value");
	var flagproject = $("#wbys_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#wbys_flagprj input[name='flagproject']:checked").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#wbys_ywlx select[name='wbys_minemethod']").val()==undefined?"":$("#wbys_ywlx select[name='wbys_minemethod']").val();
	}
	var entity;
	var laneway;
	var layer;
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_nbysdbFile.dmf");
		jh_nbysdbFile = DataBaseManager.CreateDataBase("jh_nbysdbFile.dmf");
		jh_nbysdblayer = jh_nbysdbFile.Layer("0");
		layer = jh_nbysdblayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_nbysdbFile.dmf");
		cc_nbysdbFile = DataBaseManager.CreateDataBase("cc_nbysdbFile.dmf");
		cc_nbysdblayer = cc_nbysdbFile.Layer("0");
		layer = cc_nbysdblayer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_nbysdbFile.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_nbysdbFile.dmf");
		}
		return;
	}
	delColorLegend("内部验收工程量");
	$.ajax({
		url: rootpath+'/biz/dimine/monthcaaModel/getMonthcaaModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.flagproject":flagproject,"bean.prmstr":prmstr,"bean.minemethod":minemethod,"bean.mcaatype":"1"},
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
				if(ywlx == 'YW001'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 0x00ff00ff;
							laneway.Display();
							break;
						}
					}
				}else if(ywlx == 'YW002'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 0x0000ff00;
							laneway.Display();
							
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
			}

			clObj = {};
			clObj.color = 16711935;
			clObj.name = "内部验收工程量";
			colorLegend.push(clObj);
			createColorLegend(colorLegend);
			DimineWindow.UpdateView();
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});	
	
}
//内部验收对比年计划
function loadNjhProjectModel2(gclx){
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx = $("#nbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#nbys_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#nbys_month .span_month").attr("value")==undefined?"":$("#nbys_month .span_month").attr("value");
	var qdid = $("#nbys_qd .span_month").attr("value")==undefined?"":$("#nbys_qd .span_month").attr("value");
	var devid = $("#nbys_dev .span_month").attr("value")==undefined?"":$("#nbys_dev .span_month").attr("value");
	var flagproject = $("#nbys_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#nbys_flagprj input[name='flagproject']:checked").val();
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#nbys_ywlx select[name='nbys_minemethod']").val()==undefined?"":$("#nbys_ywlx select[name='nbys_minemethod']").val();
	}
	
	var entity;
	var laneway;
	var layer;
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_njhdbFile.dmf");
		jh_njhdbFile = DataBaseManager.CreateDataBase("jh_njhdbFile.dmf");
		jh_njhdblayer = jh_njhdbFile.Layer("0");
		layer = jh_njhdblayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_njhdbFile.dmf");
		cc_njhdbFile = DataBaseManager.CreateDataBase("cc_njhdbFile.dmf");
		cc_njhdblayer = cc_njhdbFile.Layer("0");
		layer = cc_njhdblayer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_njhdbFile.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_njhdbFile.dmf");
		}
		return;
	}
	delColorLegend("年计划工程量");
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
				if(ywlx == 'YW001'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].startlocation,showmodeldata[j].jjlen,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 0x00ff00ff;
							laneway.Display();
							break;
						}
					}
				}else if(ywlx == 'YW002'){
					for(var j=0; j<showmodeldata.length; j++){
						if(showmodeldata[j].id == modeldata[i].id){
							laneway = layer.InsertOutLine(entity,showmodeldata[j].totalreserve,showmodeldata[j].minereserve,false);
							laneway.AddAttValue("id",modeldata[i].id);
							laneway.EttColor = 0x0000ff00;
							laneway.Display();
						
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
			}
			clObj = {};
			clObj.color = 16711935;
			clObj.name = "年计划工程量";
			colorLegend.push(clObj);
			createColorLegend(colorLegend);
			DimineWindow.UpdateView();
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});	
	
}


/**
 * 加载内部验收模型
 */
function loadMonthcaaFile(gclx){
	if(showNjhModel2){
		loadNjhProjectModel2(gclx);
	}
	if(showYjhModel){
		loadYjhProjectModel(gclx);
	}
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var ywlx = $("#nbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#nbys_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#nbys_month .span_month").attr("value")==undefined?"":$("#nbys_month .span_month").attr("value");
	var qdid = $("#nbys_qd .span_month").attr("value")==undefined?"":$("#nbys_qd .span_month").attr("value");
	var devid = $("#nbys_dev .span_month").attr("value")==undefined?"":$("#nbys_dev .span_month").attr("value");
	var teamid = $("#nbys_team .span_month").attr("value")==undefined?"":$("#nbys_team .span_month").attr("value");
	var flagproject = $("#nbys_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#nbys_flagprj input[name='flagproject']:checked").val();
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#nbys_ywlx select[name='nbys_minemethod']").val()==undefined?"":$("#nbys_ywlx select[name='nbys_minemethod']").val();
	}
	var entity;
	var laneway;
	var layer;
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_nbysdbFile.dmf");
		jh_nbysdbFile = DataBaseManager.CreateDataBase("jh_nbysdbFile.dmf");
		jh_nbysdblayer = jh_nbysdbFile.Layer("0");
		layer = jh_nbysdblayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_nbysdbFile.dmf");
		cc_nbysdbFile = DataBaseManager.CreateDataBase("cc_nbysdbFile.dmf");
		cc_nbysdblayer = cc_nbysdbFile.Layer("0");
		layer = cc_nbysdblayer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_nbysdbFile.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_nbysdbFile.dmf");
		}
		return;
	}
	$.ajax({
		url: rootpath+'/biz/dimine/monthcaaModel/getMonthcaaModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.qdid":qdid,"bean.devid":devid,"bean.teamid":teamid,"bean.flagproject":flagproject,"bean.prmstr":prmstr,"bean.minemethod":minemethod,"bean.mcaatype":"1"},
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
								laneway = layer.InsertLaneWay(entity,0x00000062,0,0,false);
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
				delColorLegend("验收量");
				addColorLegend(0x00000062,"验收量");//图例
				createColorLegend(colorLegend);
			}
			DimineWindow.InsertTitle(0x00000062,yearvalue+"年"+monthvalue+"月验收");//控件标题
			DimineWindow.UpdateView();
		},
		error:function(data){
		}
	});
	
	$(window.frames[0].document).find(".tab-content>div").removeClass("active");
	var _monthvalue = monthvalue=="-"?"":monthvalue;
	var _qdid = qdid=="-"?"":qdid;
	var _devid = devid=="-"?"":devid;
	var _teamid = teamid=="-"?"":teamid;
	if(ywlx=='YW001'){
		$(window.frames[0].document).find(".tab-content>div:eq(0)").addClass("active");
		window.frames[0].dimensional_jjjj(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,_teamid,'1');
	}else if(ywlx=='YW002'){
		$(window.frames[0].document).find(".tab-content>div:eq(1)").addClass("active");
		window.frames[0].dimensional_cck(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,_teamid,minemethod,'1');
	}else if(ywlx=='YW003'){
		$(window.frames[0].document).find(".tab-content>div:eq(2)").addClass("active");
		window.frames[0].dimensional_zh(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,modeltype,_teamid,'1');
	}else if(ywlx=='YW004'){
		$(window.frames[0].document).find(".tab-content>div:eq(3)").addClass("active");
		window.frames[0].dimensional_ct(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,modeltype,_teamid,'1');
	}else if(ywlx=='YW005'){
		$(window.frames[0].document).find(".tab-content>div:eq(4)").addClass("active");
		window.frames[0].dimensional_az(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,_teamid,'1');
	}else if(ywlx=='YW006'){
		$(window.frames[0].document).find(".tab-content>div:eq(5)").addClass("active");
		window.frames[0].dimensional_qt(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,_teamid,'1');
	}else if(ywlx=='YWZSK'){
		$(window.frames[0].document).find(".tab-content>div:eq(6)").addClass("active");
		window.frames[0].dimensional_zsk(deptid,yearvalue,_monthvalue,_qdid,_devid,flagproject,'1',prmstr,_teamid,'1');
	}
}

/**
 * 加载外部验收模型
 */
function loadMonthccaOutFile(gclx){
	if(showYjhModel2){
		loadYjhProjectModel2(gclx);
	}
	if(showCaaModel){
		loadMonthcaaFile2(gclx);
	}
	var modeltype = $("input[id='"+gclx+"modeltype']:checked").val();
	modeltype = modeltype==undefined?"":modeltype;
	var prmstr = $("#prmstr").val()==""?"":$("#prmstr").val();
	var ywlx =$("#wbys_ywlx input[name='ywlx']:checked").val()==undefined?"":$("#wbys_ywlx input[name='ywlx']:checked").val();
	var yearvalue = $("#yearvalue").val()==""?year:$("#yearvalue").val();
	var monthvalue = $("#wbys_month .span_month").attr("value")==undefined?"":$("#wbys_month .span_month").attr("value");
	var flagproject = $("#wbys_flagprj input[name='flagproject']:checked").val()==undefined?"":$("#wbys_flagprj input[name='flagproject']:checked").val();
	var minemethod = "";
	if(ywlx == 'YW002'){
		minemethod = $("#wbys_ywlx select[name='wbys_minemethod']").val()==undefined?"":$("#wbys_ywlx select[name='wbys_minemethod']").val();
	}

	var entity;
	var laneway;
	var layer;
	if(modeltype == 'GCLX001'){
		DataBaseManager.RemoveDB("jh_wbysdbFile.dmf");
		jh_wbysdbFile = DataBaseManager.CreateDataBase("jh_wbysdbFile.dmf");
		jh_wbysdblayer = jh_wbysdbFile.Layer("0");
		layer = jh_wbysdblayer;
	}
	else if(modeltype == 'GCLX002'){
		DataBaseManager.RemoveDB("cc_wbysdbFile.dmf");
		cc_wbysdbFile = DataBaseManager.CreateDataBase("cc_wbysdbFile.dmf");
		cc_wbysdblayer = cc_wbysdbFile.Layer("0");
		layer = cc_wbysdblayer;
	}else{
		if(!$("input[id='jhmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("jh_wbysdbFile.dmf");
		}
		if(!$("input[id='ccmodeltype']").is(":checked")){
			DataBaseManager.RemoveDB("cc_wbysdbFile.dmf");
		}
		return;
	}
	$.ajax({
		url: rootpath+'/biz/dimine/monthcaaModel/getMonthCcaOutModel.action',
		cache:false,
		data : {"bean.deptid":deptid,"bean.modeltype":modeltype,"bean.ywlx":ywlx,"bean.yearvalue":yearvalue,"bean.monthvalue":monthvalue,"bean.flagproject":flagproject,"bean.minemethod":minemethod,"bean.mcaatype":"0"},
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
									entity.AddLaneWay(0,0,showmodeldata[j].month[k].color,false);
								}
								entity.ShowLaneWay();
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
			if(ywlx == 'YW001'){
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
				}else{
					removeColorLegend();
					delColorLegend("验收量");
					addColorLegend(0x00000062,"验收量");//图例
					createColorLegend(colorLegend);
				}
			}else if(ywlx == 'YW002'){
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
				}else{
					removeColorLegend();
					delColorLegend("验收量");
					addColorLegend(0x00000062,"验收量");//图例
					createColorLegend(colorLegend);
				}
			}
			//DimineWindow.AddColorLegend(0x00643666,"xx巷道");//图例
			//DimineWindow.CreateColorLegend(2,"图例");//创建图例
			DimineWindow.InsertTitle(0x00000062,yearvalue+"年"+monthvalue+"月外部验收");//控件标题
			DimineWindow.UpdateView();
		},
		error:function(data){
			
		}
	});
	
	$(window.frames[0].document).find(".tab-content>div").removeClass("active");
	var _monthvalue = monthvalue=="-"?"":monthvalue;

	if(ywlx=='YW001'){
		$(window.frames[0].document).find(".tab-content>div:eq(0)").addClass("active");
		window.frames[0].dimensional_jjjj(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,'','0');
	}else if(ywlx=='YW002'){
		$(window.frames[0].document).find(".tab-content>div:eq(1)").addClass("active");
		window.frames[0].dimensional_cck(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,'',minemethod,'0');
	}else if(ywlx=='YW003'){
		$(window.frames[0].document).find(".tab-content>div:eq(2)").addClass("active");
		window.frames[0].dimensional_zh(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,modeltype,'','0');
	}else if(ywlx=='YW004'){
		$(window.frames[0].document).find(".tab-content>div:eq(3)").addClass("active");
		window.frames[0].dimensional_ct(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,modeltype,'','0');
	}else if(ywlx=='YW005'){
		$(window.frames[0].document).find(".tab-content>div:eq(4)").addClass("active");
		window.frames[0].dimensional_az(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,'','0');
	}else if(ywlx=='YW006'){
		$(window.frames[0].document).find(".tab-content>div:eq(5)").addClass("active");
		window.frames[0].dimensional_qt(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,'','0');
	}else if(ywlx=='YWZSK'){
		$(window.frames[0].document).find(".tab-content>div:eq(6)").addClass("active");
		window.frames[0].dimensional_zsk(deptid,yearvalue,_monthvalue,'','',flagproject,'0',prmstr,'','0');
	}
}
