<%@ page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.dimine.base.util.WebUtil"%>
<%@ page import="com.dimine.security.entity.LoginUserEntity"%> 
<%@ include file="../../pub/biz/head.jsp"%>
<%@ include file="/taglibs.jsp"%>
<%
LoginUserEntity loginuser = WebUtil.getLoginUser(session);	
String deptid = "";
if(loginuser != null){
	deptid = loginuser.getOrgid();
	if(deptid!=null &&"1001".equals(deptid)){
		deptid = "";
	}
}
 %>	
<body>
<title>日报</title>
<div class="row">
	<div class="col-xs-12">
		<form id="reportforms_pdrpt" action="<%=request.getContextPath()%>/pub/report/doReport.action" target="reportframe" >
			<input type="hidden" name="reportclass" value="pdrptReport"/>
 			<input type="hidden" name="reportformat" value="html"/>
			<input type="hidden" name="reporttitle" value="日报"/>
 			<input type="hidden" name="techid" id="techid"/> 			
 			<input type="hidden" name="deptid" value="<%=deptid%>"/>			
 			<input type="hidden" name="yearmonth" id="yearmonth"/> 			
 			<input type="hidden" name="SUBREPORT_DIR" value="<%=request.getRealPath("webpage/report/jasper/")%>/"/>	
			<div class="col-sm-9">
			 <span style=""><s:text name="dmmes.pd.productdayrpt.techname" /></span> 
				<select style="width:200px;height:33px" name="techids" id="techids"></select> 
			 <span style=""><s:text name="dmmes.pd.productdayrpt.pdate" /></span> 
		    	<input type="text" name="datavalues" id="datavalues" style="width:200px;height:33px"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" readonly="readonly"  
						value="<%=DateUtil.getYear() + "-" + DateUtil.getMonth()+"-"+DateUtil.getDay() %>"/>
				<button id="btn_search" type="button"
					class="btn btn-primary btn-sm" onclick="doSearch()">
					<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
					<s:text name="button.search" />
				</button>
			</div>		
		</form>
		<iframe name="reportframe" id="reportframe" src="" frameborder="0" style="border:0;width:100%;height:300px;"></iframe>
	</div>
</div>
<%@ include file="../../pub/biz/footer.jsp"%>
<script type="text/javascript">
var error_ajax = '<s:text name="error.ajax" />';
var dates={};
$(function(){
	 $.ajax({  
        url :rootpath+'/webpage/biz/sc/technology/selectByReport.action',
        data : {},  
        cache : false,  
        async: false ,
        error : function(data) {  
        	alertMsg(error_ajax);  
        },  
	    success : function(data) {
		    dates = eval('({'+data+'})');	
			for(var i in dates){
				var objOption = new Option(dates[i],i);
				$("#techids")[0].add(objOption);
			}
		}  
	});
});
function doSearch(){
	var yearmonth=$.trim($("#datavalues").val());
	var techids=$.trim($("#techids").val());
	$("#reportforms_pdrpt").find("#yearmonth").val(yearmonth);
	$("#reportforms_pdrpt").find("#techid").val(techids);
	$("#reportforms_pdrpt")[0].submit();
}	
</script>
</body>
</html>