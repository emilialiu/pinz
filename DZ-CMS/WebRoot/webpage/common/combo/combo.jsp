<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/chosen.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/chosen.jquery.min.js"></script>
	<script type="text/javascript">
	var scripts = [null, null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
	
		function dosearch(){
			alertMsg($("#feetype").find("option:selected").text());
		}
		
	if(!ace.vars['touch']) {
			$('.chosen-select').chosen({allow_single_deselect:true}); 
			//resize the chosen on window resize
	
			$(window)
			.off('resize.chosen')
			.on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				})
			}).trigger('resize.chosen');
			//resize chosen on sidebar collapse/expand
			$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
				if(event_name != 'sidebar_collapsed') return;
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				})
			});
		}
		
	</script>

<div style="width: 160px;">
	测试组合框:
	<dmtag:combomanager table="T_combo" cssStyle="width:150px" cssClass="chosen-select form-control" name="feetype" combovalue="id" combotext="text" parameter="text like '123%'"/>
	<!-- table:表名;combovalue:表中主键；combotext：表中名称；parameter：参数 -->
</div>
	<button class="btn btn-xs btn-success bigger no-border" onclick="javascript:dosearch()">
		<i class="ace-icon fa fa-search"></i>
		查询
	</button>
<%@include file="/pub/biz/footerlist.jsp" %>