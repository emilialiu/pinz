<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body style="background-color: #fff;overflow:hidden;">
	<div class="row"
		style="padding:0px;padding-left:10px;padding-right:10px;height:520px"
		id="flex">
		<!-- PAGE CONTENT ENDS -->
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/flex/js/flexpaper.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/flex/js/flexpaper_handlers.js"></script>
<script type="text/javascript">
			var fileurl = '<%=request.getAttribute("fileurl")%>';
			$(function(){
				$('#flex').FlexPaperViewer(
	                { config : {
	                    SWFFile : '<%=request.getContextPath()%>/upfile'+fileurl,
	                    Scale :1,
	                    ZoomTransition : 'easeOut',
	                    ZoomTime : 0.5,
	                    ZoomInterval : 0.2,
	                    FitPageOnLoad : false,
	                    FitWidthOnLoad : false,
	                    FullScreenAsMaxWindow : false,
	                    ProgressiveLoading : false,
	                    MinZoomSize : 0.2,
	                    MaxZoomSize : 5,
	                    SearchMatchAll : false,
	                    InitViewMode : 'Portrait',
	                    RenderingOrder : 'flash',
	                    StartAtPage : '',
	                    DOC:'<%=request.getContextPath()%>',
						jsDirectory:'<%=request.getContextPath()%>/plugins/flex/js/',
						cssDirectory:'<%=request.getContextPath()%>>/plugins/flex/css/',
						localeDirectory:'<%=request.getContextPath()%>>/plugins/flex/locale/',
						ViewModeToolsVisible : true,
						ZoomToolsVisible : true,
						NavToolsVisible : true,
						CursorToolsVisible : true,
						SearchToolsVisible : true,
						WMode : 'window',
						localeChain : 'zh_CN'
								}
							});
		});
</script>

