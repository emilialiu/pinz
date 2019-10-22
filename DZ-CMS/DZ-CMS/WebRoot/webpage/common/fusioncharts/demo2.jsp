<HTML>
<HEAD>
	<TITLE>FusionCharts Free - Client Side Chart Plotting</TITLE>	
	<style type="text/css">
	<!--
	body {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
	}
	-->
	</style>
	<script type="text/javascript" src="../../js/chart/FusionCharts.js"></script>
</HEAD>
<BODY>
	<CENTER>
		<div id="chart1div">
			FusionCharts
		</div>
		<script language="JavaScript">
			var chart1 = new FusionCharts("../../swf/MSColumn3D.swf", "chart1Id", "600", "400");
			chart1.setDataURL("./data1.xml");
			chart1.render("chart1div");
		</script>
	</CENTER>
</BODY>
</HTML>
