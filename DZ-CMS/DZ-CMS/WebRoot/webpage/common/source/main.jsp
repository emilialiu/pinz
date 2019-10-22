<%@include file="/pub/biz/headlist.jsp" %>
<%@ include file="/taglibs.jsp"%>
	<script type="text/javascript">
		var scripts = [null, null]
		$('.page-content-area').ace_ajax('loadScripts', scripts, function() {});
	</script>
	<div class="row">
		<div class="col-xs-12">
			<label class="col-sm-1">
				资源控件:
			</label>
			<div class="col-sm-4">
				<dmtag:sourcemanager sourcename="XXLX" name="source"
					cssClass="text" cssStyle="width:180px" ></dmtag:sourcemanager>
			</div>
		</div>
	</div>
<%@include file="/pub/biz/footerlist.jsp" %>

