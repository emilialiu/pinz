<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../pub/biz/head.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
<body>
<title>人员产量统计</title>
<div class="row">
	<div class="col-xs-12">
		<form id="reportforms_materialCost" action="<%=request.getContextPath()%>/pub/report/doReport.action" target="reportframe" >
			<input type="hidden" name="reportclass" value="pdryreport"/>
 			<input type="hidden" name="reportformat" value="html"/>
			<input type="hidden" name="reporttitle" value="人员产量统计"/>
 			<input type="hidden" name="startDate" id="startDate"/> 		
 			<input type="hidden" name="endDate" id="endDate"/>
 			<input type="hidden" name="teamgroupid" id="teamgroupid"/> 		
 			
			<table>
				<tr>
					<td>
						<s:text name="dmmes.am.acceptance.starttime" />&nbsp;
					</td>
					<td style="display: table">
						<input type="text" name="startDateValue" id="startDateValue" class="col-xs-12" 
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td>
						<s:text name="dmmes.am.acceptance.endtime" />&nbsp;
					</td>
					<td style="display: table">
						<input type="text" name="endDateValue" id="endDateValue" class="col-xs-12" 
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					</td>
					<td>
						<s:text name="dmmes.pd.pbdetail.teamname" />&nbsp;
					</td>
					<td style="display: table">
						<select style="height: 35px" role="select" id="pbdetail_bz"  name="classes" class="col-xs-12">
								
						</select>
					</td>
					<td>
					<div class="toolbar">
						<button id="btn_search" type="button" class="btn btn-primary btn-sm" onclick="doSearch()">
							<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
							<s:text name="button.generatingReports" />
						</button>
					</div>
					</td>
				</tr>
			</table>		
		</form>
		<iframe name="reportframe" id="reportframe" src="" frameborder="0" style="border:0;width:100%;height:600px;"></iframe>
	</div>
</div>
<%@ include file="../../pub/biz/footer.jsp"%>
<script type="text/javascript">
 	var pd_pbdetail_teamSelect='<s:text name="dmmes.pd.drugbill.selection" />';
	var pd_pbdetail_pleaseSelect='<s:text name="dmmes.pd.pbdetail.pleaseselect" />';
	 //页签的点击
	var tabindex = 0;
function doSearch(){
	var startDate=$.trim($("#startDateValue").val());
	var endDate=$.trim($("#endDateValue").val());
	var teamgroupid = $.trim($("#pbdetail_bz").val());
	$("#reportforms_materialCost").find("#startDate").val(startDate);
	$("#reportforms_materialCost").find("#endDate").val(endDate);
	$("#reportforms_materialCost").find("#teamgroupid").val(teamgroupid);
	$("#reportforms_materialCost")[0].submit();
}

//加载班次
	$.ajax( {  
		url : rootpath+"/pub/select/selectList/list.action", 
		type:'POST',
		data : {dicttypeid:'BC'},  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			bc = eval('({'+data+'})');	
			for(var i in bc){
				var objOption = new Option(bc[i],i); 
				$("#pbdetail_classes")[0].add(objOption);
			}
		}  
	 });
	 var n=0;
	 //加载班组
	 $.ajax( {  
		url : rootpath+"/webpage/biz/pd/pbdetail/selectTeamByLoginUser.action", 
		type:'POST',
		data : null,  
		cache : false,
		async : false,
		error : function(data) {  
			alertErrorMsg("系统ajax交互错误");   
		},  
		success : function(data) {
			bz = eval('({'+data+'})');	
			//清空下拉列表的值
	        $("#pbdetail_bz").html("");
	        if(data.replace(/\s+/g,"")==''){
	        	var objOption1 = new Option("--"+pd_pbdetail_pleaseSelect+"--",""); 
				$("#pbdetail_bz")[0].add(objOption1); 
	        }
			for(var i in bz){
				var objOption = new Option(bz[i],i); 
				$("#pbdetail_bz")[0].add(objOption);
				n+=1;
			}
			if(n>1){
				$("#pbdetail_bz").empty();
				$("#pbdetail_bz").prepend("<option value=''>--"+pd_pbdetail_pleaseSelect+"--</option>");
				$("#pbdetail_bz").click(function(){
					selectGxTeam();
				});
			}
		}  
	 });
	 
function selectGxTeam(){
	//获取树节点的信息
	parent.openwin("selectTeam","/webpage/biz/pd/team/main.jsp",pd_pbdetail_teamSelect,850,600);
	parent.PageObject.itemMap['selectTeam'].callback = function(no){
		selectGxTeamCallBack(no);
	};
}

function selectGxTeamCallBack(arr){
	if(arr[0].teamid!=undefined){
		$("#pbdetail_bz").empty();
		$("#pbdetail_bz").prepend("<option value='"+arr[0].teamid+"'>"+arr[0].teamname+"</option>");
	}else{
		$("#pbdetail_bz").empty();
		$("#pbdetail_bz").prepend("<option value='"+""+"'>"+"--"+pd_pbdetail_pleaseSelect+"--"+"</option>");
	}
	
	setGxByTeam();
}
	//标志
	var clockflag=0;
	var shift=0;
	
	
	$(".nav-tabs li a").eq(0).trigger("click");

		$(".nav-tabs li a").click(function() {
					tabindex = $(".nav-tabs li a").index(this);
					if(tabindex==0){
						$("#jobsite").show();
						$("#jobcopy").show();
						$("#jobsynchro").show();
						//$("#saveclock").hide();
						$("#addshift").hide();
						setTimeout(function() {
							loadeiframe();
							//iframeSearch();
						}, 0);
					}else if(tabindex==1){
						$("#jobsite").hide();
						$("#jobcopy").hide();
						$("#jobsynchro").hide();
						//$("#saveclock").show();
						$("#addshift").hide();
						setTimeout(function() {
							$("#t_pd_clockingbill_grid").jqGrid('setGridWidth', $("#t_pd_clockingbill_grid_box").width());
							if(clockflag==0){
								clocking($("#pbdetaildate").val(),$("#pbdetail_classes").val(),$("#pbdetail_bz").val());
								clockflag=1;
							}else{
								$("#t_pd_clockingbill_grid").jqGrid('setGridParam', {
									url : rootpath + '/webpage/biz/pd/clockingbill/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
									page : 1
								}).trigger("reloadGrid");
							}
						}, 0);
					}else if(tabindex==2){
						$("#jobsite").hide();
						$("#jobcopy").hide();
						$("#jobsynchro").hide();
						//$("#saveclock").hide();
						$("#addshift").show();
						setTimeout(function() {
							$("#t_pd_changeshifts_grid").jqGrid('setGridWidth', $("#t_pd_changeshifts_grid_box").width());
							 if(shift==0){
								changeshifts($("#pbdetaildate").val(),$("#pbdetail_classes").val(),$("#pbdetail_bz").val());
								shift=1;
							}else{
								$("#t_pd_changeshifts_grid").jqGrid('setGridParam', {
									url : rootpath + '/webpage/biz/pd/changeshifts/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
									page : 1
								}).trigger("reloadGrid");
							} 
						}, 0);
					}
		});
		
		$(function(){
		setGxByTeam();
	});
	
	//通过班组加载工序名称的下拉框
	function setGxByTeam(){
//		if(tabindex==0){
			 $.ajax({  
			        url :rootpath+'/webpage/biz/pd/pbdetail/genProcByTeamId.action' ,
			        data : {'bean.teamgroupid':$("#pbdetail_bz").val()},  
			        cache : false,  
			        async: false,
			        error : function(data) {  
			        	alertMsg("系统ajax交互错误");  
			        },  
			        success : function(data) {  
			        	var dataJson=eval(data);
			        	var htmlstr="";		 
			        	//清空下拉列表的值
			        	$("#pbdetail_prcoid").html("");
			        	for(var i=0;i<dataJson.length;i++){
			        		if(i==0){
			        			htmlstr+="<option selected=\"selected\" value=\""+dataJson[i].procid+"\">"+dataJson[i].proctname+"</option>";
			        		}else{
			        			htmlstr+="<option value=\""+dataJson[i].procid+"\">"+dataJson[i].proctname+"</option>";
			        		}
			        	}
			        	$("#pbdetail_prcoid").append(htmlstr);
			        	loadeiframe();
			        }  
			});
		/* }else  */
		if(tabindex==1){
			//刷新考勤信息列表
			$("#t_pd_clockingbill_grid").jqGrid('setGridParam', {
				url : rootpath + '/webpage/biz/pd/clockingbill/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
				page : 1
			}).trigger("reloadGrid");
		}else if(tabindex==2){
			//刷新交接班记录
			$("#t_pd_changeshifts_grid").jqGrid('setGridParam', {
				url : rootpath + '/webpage/biz/pd/changeshifts/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
				page : 1
			}).trigger("reloadGrid");
		}
		
		/* //刷新考勤信息列表
		$("#t_pd_clockingbill_grid").jqGrid('setGridParam', {
			url : rootpath + '/webpage/biz/pd/clockingbill/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
			page : 1
		}).trigger("reloadGrid");
		
		//刷新交接班记录
		$("#t_pd_changeshifts_grid").jqGrid('setGridParam', {
			url : rootpath + '/webpage/biz/pd/changeshifts/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
			page : 1
		}).trigger("reloadGrid"); */
		
	}
	
	//iframe的数据加载
	function loadeiframe(){
		$("#pbdetail_iframe").attr("src","<%=request.getContextPath()%>/webpage/biz/pd/pbdetail/tobizMani.action?fault=fault&procid="+$("#pbdetail_prcoid").val()+"&reportdate="+$("#pbdetaildate").val()+"&classes="+$("#pbdetail_classes").val()+"&teamgroupid="+$("#pbdetail_bz").val());
		
	}
	
	//根据条件查询数据
	function selectInfoByCondition(){
		if(tabindex==0){
			iframeSearch();
		}else if(tabindex==1){
			$("#t_pd_clockingbill_grid").jqGrid('setGridParam', {
				url : rootpath + '/webpage/biz/pd/clockingbill/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
				page : 1
			}).trigger("reloadGrid");
		}else if(tabindex==2){
			$("#t_pd_changeshifts_grid").jqGrid('setGridParam', {
				url : rootpath + '/webpage/biz/pd/changeshifts/list.action?bean.reportdate='+$("#pbdetaildate").val()+'&bean.classes='+$("#pbdetail_classes").val()+'&bean.teamid='+$("#pbdetail_bz").val(),
				page : 1
			}).trigger("reloadGrid");
		}
	}
	

</script>
</body>
</html>