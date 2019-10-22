<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../pub/biz/headinfo.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/chosen.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/chosen.jquery.min.js"></script>
<script type="text/javascript">
	var scripts = [null, null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
	
	//组合框	
	if (!ace.vars['touch']) {
		$('.chosen-select').chosen({
			allow_single_deselect : true
		});
		//resize the chosen on window resize
		$(window).off('resize.chosen').on('resize.chosen', function() {
			$('.chosen-select').each(function() {
				var $this = $(this);
				$this.next().css({
					'width' : 260
				});
			})
		}).trigger('resize.chosen');
		//resize chosen on sidebar collapse/expand
		$(document).on('settings.ace.chosen',
				function(e, event_name, event_val) {
					if (event_name != 'sidebar_collapsed')
						return;
					$('.chosen-select').each(function() {
						var $this = $(this);
						$this.next().css({
							'width' : 260
						});
					})
				});
	}
</script>
<div id="sys_modelinfo_info" style="display:none">
	<form id="sys_modelinfo_form" method="post" class="form-horizontal">
		<input type="hidden" id="modelid" name="modelid"/>
		<input type="hidden" id="targetname" name="targetname"/>
		<input type="hidden" id="exceltb" name="exceltb"/>

		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="modelname">
				模版名称
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="modelname" id="modelname"
						class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="mtable">
				数据库表名称
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<dmtag:combomanager table="SYS_MODELENTITYMAPP" cssStyle="width:150px" cssClass="chosen-select form-control" name="mtable" combovalue="TABLENAME" combotext="TABLENAME" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="exceltb">
				excel模板名称(与本地一致)
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input multiple="" type="file" name="modelfielname" id="modelfielname" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>

	</form>
</div>