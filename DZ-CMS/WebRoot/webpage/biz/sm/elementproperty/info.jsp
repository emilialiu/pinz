<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body>
	<div id="elementproperty_info">
		<form id="elementproperty_form" method="post" class="form-horizontal">
			<input type="hidden" id="eletypeid" name="eletypeid" value="${bean.eletypeid}"/>
			<input type="hidden" id="eleproid" name="eleproid" value="${bean.eleproid}"/>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="elecode">要素属性编码</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="elecode" id="elecode" value="${bean.elecode}" class="col-xs-12"
							nullmsg="参数值信息必填!" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="elename">要素属性名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="elename" id="elename" value="${bean.elename}" class="col-xs-12"
							nullmsg="参数值信息必填!" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="parenteleid">父元素名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="parenteleid" name="parenteleid">

						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="eletype"> 要素属性类型 </label>
				<div class="col-xs-12 col-sm-9" style="width:50%">
					<div class="col-sm-9">
						<dmtag:sourcemanager sourcename="YSSXLX" name="eletype"
							cssClass="form-control" isnull="false" fixValue="${bean.eletype}"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="valuesql">取值SQL</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="valuesql" id="valuesql" value="${bean.valuesql}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="servicename">对应服务名</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="servicename" id="servicename" value="${bean.servicename}" 
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="isedit">是否编辑</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="isedit" name="isedit">
							<option value="是">是</option>
							<option value="否">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="iproname">对应内部属性名</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="iproname" id="iproname" value="${bean.iproname}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="isprimary">是否联合主键</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="isprimary" name="isprimary">
							<option value="是">是</option>
							<option value="否">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="eleno">元素序号</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="eleno" id="eleno" value="${bean.eleno}" class="col-xs-12"
							datatype="n" nullmsg="参数值信息必填!" class="Validform_error" />
					</div>
					<div class="col-sm-3">
						<span class="Validform_checktip"></span><span class="dec">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="memo">备注</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="memo" id="memo" value="${bean.memo}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="projectlevel">工程级别</label>
				<div class="col-xs-12 col-sm-9" style="width:50%">
					<div class="col-sm-9">
						<dmtag:sourcemanager sourcename="GCJB" name="projectlevel"
							cssClass="form-control" isnull="false" fixValue="${bean.projectlevel}"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="ishide">是否隐藏</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="ishide" name="ishide">
							<option value="是">是</option>
							<option value="否">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
		</form>
	</div>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/sm/elementproperty/info.js"></script>
<script type="text/javascript">
	var eleproid = '<%=request.getAttribute("bean.eleproid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("elementproperty");
		else if(actiontype=='modifysave')
			modifySave("elementproperty");
		else if(actiontype=='deletesave')
			deleteSave("elementproperty");
	}
	initvalid("elementproperty");
	$(function(){
		if(actiontype=='view')
			parent.setDialogButtons("elementproperty",{"取消": function() {parent.closewin("elementproperty");}});
		else 
			parent.setDialogButtons("elementproperty",{"取消": function() {parent.closewin("elementproperty");},"确定": function() {parent.window.frames["elementproperty_frame"].onSave();}});
	
		if(actiontype=='modifysave'){
			$("#isedit").val('<%=request.getAttribute("bean.isedit")%>');
			$("#isprimary").val('<%=request.getAttribute("bean.isprimary")%>');
			$("#ishide").val('<%=request.getAttribute("bean.ishide")%>');
		}
		getListelename();
	});
	
	function getListelename(){
		var parentname=[];
		$.ajax( {  			
			url : rootpath+"/biz/sm/elementproperty/listelename.action?bean.eletypeid=<%=request.getAttribute("bean.eletypeid")%>",
			type:'POST', 
			cache : false,
			async : false,
			error : function(data) {  
				alertErrorMsg("系统ajax交互错误");   
			},  
			success : function(data) {
				parentname = eval('({'+data+'})');
				$("#parenteleid").find("option").remove();
				$("#parenteleid").append("<option value=''></option>");
				for(i in parentname){
					$("#parenteleid").append("<option value="+i+">"+parentname[i]+"</option>");   
				}
				$("#parenteleid").val('<%=request.getAttribute("bean.parenteleid")%>');
			}  
		});
	}
</script>
</body>
</html>