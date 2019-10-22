<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<div id="act_nodytask_info">
	<form id="act_nodytaskform" method="post" class="form-horizontal" >
		<input type="hidden" id="taskId" name="taskId"/>
		<input type="hidden" id="taskDefinitionKey" name="taskDefinitionKey"/>
		<input type="hidden" id="nodeaction" name="nodeaction"/>
		<input type="hidden" id="bussinesskey" name="bussinesskey"/>
		<input type="hidden" id="processInstanceId" name="processInstanceId"/>
		<input type="hidden" id="processDefinitionId" name="processDefinitionId"/>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="approval ">
				<s:text name="dmmes.sys.task.approval" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6""><!-- class="clearfix" -->
					<select id="approval" name="approval">
						<option value="true">同意</option>
						<option value="false">驳回</option>
					</select>
				</div>
				<div class="col-sm-3">
					<div class="info"><span class="Validform_checktip"></span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>
				</div>
			</div>
		</div>
		
		<div class="space-2"></div>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="comments">
				<s:text name="dmmes.sys.task.approvalcomments" />:
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6"><!-- class="clearfix" -->
					<textarea name="comments" id="comments" class="col-sm-12"></textarea>
				</div>
			</div>
		</div>
	</form>
	<div class="widget-content" style="padding:4px;">
		<div class="tabbable box-tabs">
			<ul class="nav nav-tabs">
				<li><a href="#box_tab1" data-toggle="tab"><s:text name="dmmes.sys.task.tabserviceinformation" /></a></li>
				<li class="active"><a href="#box_tab2" data-toggle="tab"> <s:text name="dmmes.sys.task.approvalhis" /> </a></li>
			</ul>
			<div class="tab-content" style="padding:4px;">
				<div class="tab-pane" id="box_tab1">
					  <iframe src="" frameborder="0" style="border:0;width:100%;height:350px;" id="buniessinfo" name="buniessinfo"></iframe>
				</div>
				<div class="tab-pane active" id="box_tab2" >
					 <!-- <table id="act_nodytaskhis_grid"></table>
				    <div id="act_nodytaskhis_grid_pager"></div>-->
				     <iframe src="" frameborder="0" style="border:0;width:100%;height:350px;" id="hismain" name="hismain"></iframe>
				</div>
			</div>
		</div>
	</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
	<script>
		var processDefinitionId='<%=request.getParameter("processDefinitionId")%>';
		var processInstanceId='<%=request.getParameter("processInstanceId")%>';
		var bussinesskey='<%=request.getParameter("bussinesskey")%>';
		var taskDefinitionKey='<%=request.getParameter("taskDefinitionKey")%>';
		var taskId='<%=request.getParameter("taskId")%>';
		var url='<%=request.getParameter("url")%>';
		var nodeaction='<%=request.getParameter("nodeaction")%>';
		$(function(){
			$("#buniessinfo").attr("src",url);
			$("#hismain").attr("src",rootpath+'/webpage/manager/activity/nodytask/hismain.jsp?processDefinitionId='+processDefinitionId+'&bussinesskey='+bussinesskey);
			parent.setDialogButtons("act_nodytask",{"<s:text name="button.cancel"/>": function() {parent.closewin("act_nodytask");},"<s:text name="button.submit"/>": function() {parent.window.frames["act_nodytask_frame"].subdata();}});
		});
		var tabindex = 0;
		//tab点击事件
		$(".nav-tabs li a").click(function() {
			tabindex = $(".nav-tabs li a").index(this);
			if(tabindex==1){
				setTimeout(function() {
					window.frames[1].changwidth();
				}, 0);
			}
			if(tabindex==0){
				try{
					window.frames[0].changeGridWidth();
				}catch(e){
				}
			}
		});
		/**
 			* 提交审批
 		*/
	function subdata(){
		var consng=$('#act_nodytask_info');
		var variables = getvarias();
		if(!variables){
			parent.alertMsg("<s:text name="dmmes.sys.task.messageapproval"/>");
			return;
		}
		// 转换JSON为字符串
	    var keys = "", values = "", types = "";
		if (variables) {
			$.each(variables, function() {
				if (keys != "") {
					keys += ",";
					values += ",";
					types += ",";
				}
				keys += this.key;
				values += this.value;
				types += this.type;
			});
		}
		$.ajax({  
	        url : rootpath+nodeaction,
	        type: "POST",
	        data : {"variable.keys":keys,"variable.values":values,"variable.types":types,'taskId':taskId,'taskDefinitionKey':taskDefinitionKey,'bussinessid':bussinesskey,'processInstanceId':processInstanceId},  
	        cache : false,  
	        error : function(data) {  
	        	parent.alertMsg("<s:text name="pub.messageajaxerror"/>");  
	        },  
	        success : function(data) {  
	        	var dataJson = eval(data);
	            if(dataJson.success) {
            		parent.alertMsg(dataJson.id); 
            		var currid = $("#tabs", parent.document).find("li[class='active']").children("a").attr("aria-controls");
	                if(currid==undefined){
	               		currid="tab_0";
	                }
                	var iframe = $("#content_"+currid, parent.document);
					$(iframe)[0].contentWindow.refresh();
                	parent.closewin("act_nodytask");
	            } else {  
	            	parent.alertMsg("<s:text name="dmmes.sys.task.handleerror"/>"+dataJson.errormessage);
	                parent.closewin("act_nodytask");
	            }  
	        }  
	    });  
	}
	function getvarias(){
		var consng=$('#act_nodytask_info');
		var approval = consng.find("#approval").val();
		var comments = consng.find("#comments").val();
		if(comments == null||comments == ""){
			return false;
		}
		//key为判断条件的参数值，value：是父页面的值，type为字段的类型，B为布尔型，详情请见PropertyType类
		return [{key:'nodepass',value:approval,type:'B'},
	        	{key:'comments',value:comments,type:'S'},
		   	   ];
	}
	</script>
</div>


