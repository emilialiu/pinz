<%@page import="com.dimine.base.util.WebUtil"%>
<%@page import="com.dimine.security.entity.LoginUserEntity"%>
<%@page import="com.dimine.sys.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/taglibs.jsp"%>
<%
LoginUserEntity loginuser = WebUtil.getLoginUser(session);	
String deptid = "";
if(loginuser != null){
	deptid = loginuser.getOrgid();
}
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>金诚信生产执行系统-三维展示</title>

		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/font-awesome/css/font-awesome.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/_mod/jquery-ui/jquery-ui.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/jquery.gritter/css/jquery.gritter.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/components/_mod/jqgrid/ui.jqgrid.css" />
		
		<!-- text fonts -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-fonts.min.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace.dm.css" />
		<!-- valid styles -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/validform/style.css"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/jquery/jquery.contextmenu.css"/>
		
		<!--[if lte IE 9]>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- ace settings handler -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="<%=request.getContextPath()%>/plugins/components/html5shiv/dist/html5shiv.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/respond/dest/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/botree.css" type="text/css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style/jquery/demo.css" type="text/css" />
		<style>
			body{background-color: #FFFFFF;}
			.page-content {padding: 0px 14px 24px 8px;}
			.tab-content{border: 1px solid #307ecc;}
			.ace-nav>li {border-left: 1px solid #FFFFFF;}
			.ace-nav > li > a{background-color: #E2EAF1;color:#000;}
			.ace-nav>li.grey>a {background-color: #E2EAF1;}
			.ace-nav>li.purple>a {background-color: #E2EAF1;}
			.ace-nav>li.green>a {background-color: #E2EAF1;}
			.tabs-left>.nav-tabs>li>a,.tabs-left>.nav-tabs>li>a:focus,.tabs-left>.nav-tabs>li>a:hover{border-color: #307ecc;border-right-color: transparent;}
			.tabs-left>.nav-tabs>li.active>a,.tabs-left>.nav-tabs>li.active>a:focus,.tabs-left>.nav-tabs>li.active>a:hover{border-color: #307ecc;border-right-color: transparent;}
			hr{margin:7px 0px;border-top:1px solid #000;}
			.con_h{font-weight: bold;}
			.dtable{width:100%;}
			.dtable th{border:1px solid #307ecc;text-align: center;background-color:#95CAFF;}
			.dtable td{border:1px solid #307ecc;text-align: center;}
			._month,._qd,._dev,._team,.yearrpt{cursor:pointer;}
			.span_month,.span_qd,.span_dev,.span_team{background-color:#B3B3B3;}
			#njh_dev,#njh_team,#yjh_dev,#yjh_team,#scrb_dev,#scrb_team,#nbys_dev,#nbys_team{word-wrap: break-word;}
			#deptsearch,.type > div,.left,.right{cursor:pointer;}
			.ui-dialog .ui-dialog-titlebar{padding: .4em 1em;position: relative;background-color:#307ecc;}
			.nav_yw{border-bottom: 1px solid #FFFFFF;padding: 5px 0px;}
			.active_yw{background-color:#0505FF;}
			.kjbtn div{float:right;width:30px;text-align:center;}
			.curbtn{background-color:#0505FF !important;}
		</style>
	</head>

	<body class="no-skin" onload="Init()">
		<div id="navbar" class="navbar navbar-default" style="background-color:#E2EAF1;height:70px;padding-top:5px;">
			<div class="navbar-container" id="navbar-container">

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<img src="../../../images/logo.png" width="240" height="58">

					<!-- /section:basics/navbar.layout.brand -->

					<!-- #section:basics/navbar.toggle -->

					<!-- /section:basics/navbar.toggle -->
				</div>
				<div class="navbar-header pull-left">
					<div class="nav-search" id="nav-search" style="padding:0px 0px;left:400px;">
						<form class="form-inline">
							<input type="text" class="input-mini" id="yearvalue" style="height:30px;"/>
							<input type="text" placeholder="Search ..." class="nav-search-input" id="prmstr" style="width:300px;" autocomplete="off">
							<button id="prmstr_btn_search" type="button" class="btn btn-primary btn-sm">
								<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>&nbsp;&nbsp;
								查询
							</button>
						</form>
					</div>
				</div>

				<!-- #section:basics/navbar.dropdown
				<div class="navbar-buttons navbar-header pull-right" role="navigation" style="padding: 7px 0px;">
					<ul class="nav ace-nav">
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<div>
									<img src="../../style/images/menu_1.png" width="30" height="29">
									设备信息
								</div>
							</a>
						</li>

						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<div>
									<img src="../../style/images/menu_2.png" width="30" height="29">
									进度告警
								</div>
							</a>
						</li>

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<div>
									<img src="../../style/images/menu_3.png" width="30" height="29">
									班组信息
								</div>
							</a>
						</li>
					</ul>
				</div> -->

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<!-- #section:basics/content.breadcrumbs -->

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<!-- #section:settings.box -->

					<!-- /section:settings.box -->
					<div class="page-content-area" data-ajax-content="false">
						
						<div class="row" style="padding:0px;">
							<div class="col-xs-12">
								<div class="col-xs-12 col-sm-2" id="leftslide">
									<div class="col-xs-12 col-sm-2" style="padding:0px;border:0px solid #307ecc;height: 100%;">
										<div class="type widget box" style="background-color: #96B5CF;color: white;text-align: center;height: 100%;">
											<div class="nav_yw active_yw">
												<img src="../../../style/images/left_menu_1.png" width="40" height="40">
												<h6>年计划</h6>
											</div>
											<div class="nav_yw">
												<img src="../../../style/images/left_menu_2.png" width="40" height="40">
												<h6>月计划</h6>
											</div>
											<div class="nav_yw">
												<img src="../../../style/images/left_menu_3.png" width="40" height="40">
												<h6>生产调度</h6>
											</div>
											<div class="nav_yw">
												<img src="../../../style/images/left_menu_4.png" width="40" height="40">
												<h6>内部验收</h6>
											</div>
											<div class="nav_yw">
												<img src="../../../style/images/left_menu_5.png" width="40" height="40">
												<h6>外部验收</h6>
											</div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-10" style="padding:0px;border:0px solid #307ecc;height: 100%;">
										<div class="widget box" style="border:1px solid #307ecc;height: 100%;">
											<div class="widget-content">
												<div class="tabbable">
													<ul class="nav nav-tabs" id="myTab3">
														<li class="active">
															<a data-toggle="tab" href="#cxtj">
																<i class="pink ace-icon fa fa-tachometer bigger-110"></i>
																查询条件
															</a>
														</li>
													</ul>
								
													<div class="tab-content" style="border:0px;border-top: 1px solid #307ECC;">
														<div id="cxtj" class="tab-pane in active">
															<div id="njh">
																<div>
																	<div class="con_h">业务类型</div>
																	<div id="njh_ywlx">
																		<dmtag:selectmemumanager table="t_sc_technology" cssStyle="width:180px" checkdiv="radiotreediv" checktree="radiotreedemo"
																		name="radiotree" checkboxvalue="techid" checkboxtext="techname" onclick="showRadioMenu();" treewidth="180px" treeheight="300px"
																		parentid="parentid" parameter="text like '123%'"></dmtag:selectmemumanager>
																		<div id="njh_gx">
																		</div>
																		<!-- <p><input type="checkbox" value="YW007" name="ywlx"/>反井工程</p> -->
																		<p style="display:none" class="ckff">采矿方法：
																			<dmtag:sourcemanager sourcename="CKFF" name="njh_minemethod" 
																				cssClass="form-control" onchange="loadModel();"></dmtag:sourcemanager>
																		</p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">月份</div>
																	<div id="njh_month">
																		<p><span class="_month" value="-">全部</span></p>
																		<p><span class="_month" value="01">1月</span>&nbsp;&nbsp;<span class="_month" value="02">&nbsp;2月</span>&nbsp;&nbsp;<span class="_month" value="03">&nbsp;3月</span>&nbsp;&nbsp;<span class="_month" value="04">&nbsp;4月</span></p>
																		<p><span class="_month" value="05">5月</span>&nbsp;&nbsp;<span class="_month" value="06">&nbsp;6月</span>&nbsp;&nbsp;<span class="_month" value="07">&nbsp;7月</span>&nbsp;&nbsp;<span class="_month" value="08">&nbsp;8月</span></p>
																		<p><span class="_month" value="09">9月</span>&nbsp;&nbsp;<span class="_month" value="10">10月</span>&nbsp;&nbsp;<span class="_month" value="11">11月</span>&nbsp;&nbsp;<span class="_month" value="12">12月</span></p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">区队</div>
																	<div id="njh_qd">
																		<!-- <span class="_qd">500采区</span>&nbsp;&nbsp;<span class="_qd">700采区</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">设备</div>
																	<select id="njh_devclass" class="form-control" onchange="reloadDevName();"></select>
																	<select id="njh_devname" class="form-control" onchange="loadDevCon();"></select>
																	<div id="njh_dev">
																		<!-- <span class="_dev">BM-01</span>&nbsp;&nbsp;<span class="_dev">BM-01</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">标识工程</div>
																	<div id="njh_flagprj">
																		<input type="checkbox" value="" name="flagproject"/>全部
																		<input type="checkbox" value="BSGC002" name="flagproject"/>重点工程
																		<input type="checkbox" value="BSGC003" name="flagproject"/>反井工程
																	</div>
																</div>
															</div>
															<div id="yjh" style="display:none;">
																<div>
																	<div class="con_h">业务类型</div>
																	<div id="yjh_ywlx">
																		<p><input type="checkbox" value="YW001" name="ywlx"/>掘进&nbsp;&nbsp;<input type="checkbox" value="YW002" name="ywlx"/>采（出）矿</p>
																		<p><input type="checkbox" value="YW003" name="ywlx"/>支护&nbsp;&nbsp;<input type="checkbox" value="YW004" name="ywlx"/>充填</p>
																		<p><input type="checkbox" value="YW005" name="ywlx"/>安装&nbsp;&nbsp;<input type="checkbox" value="YWZSK" name="ywlx"/>中深孔</p>
																		<!-- <p><input type="checkbox" value="YW007" name="ywlx"/>反井工程</p> -->
																		<p style="display:none" class="ckff">采矿方法：
																			<dmtag:sourcemanager sourcename="CKFF" name="yjh_minemethod" 
																				cssClass="form-control" onchange="loadModel();"></dmtag:sourcemanager>
																		</p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">月份</div>
																	<div id="yjh_month">
																		<p><span class="_month" value="01">1月</span>&nbsp;&nbsp;<span class="_month" value="02">&nbsp;2月</span>&nbsp;&nbsp;<span class="_month" value="03">&nbsp;3月</span>&nbsp;&nbsp;<span class="_month" value="04">&nbsp;4月</span></p>
																		<p><span class="_month" value="05">5月</span>&nbsp;&nbsp;<span class="_month" value="06">&nbsp;6月</span>&nbsp;&nbsp;<span class="_month" value="07">&nbsp;7月</span>&nbsp;&nbsp;<span class="_month" value="08">&nbsp;8月</span></p>
																		<p><span class="_month" value="09">9月</span>&nbsp;&nbsp;<span class="_month" value="10">10月</span>&nbsp;&nbsp;<span class="_month" value="11">11月</span>&nbsp;&nbsp;<span class="_month" value="12">12月</span></p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">区队</div>
																	<div id="yjh_qd">
																		<!-- <span class="_qd">500采区</span>&nbsp;&nbsp;<span class="_qd">700采区</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">设备</div>
																	<select id="yjh_devclass" class="form-control" onchange="reloadDevName();"></select>
																	<select id="yjh_devname" class="form-control" onchange="loadDevCon();"></select>
																	<div id="yjh_dev">
																		<!-- <span class="_dev">BM-01</span>&nbsp;&nbsp;<span class="_dev">BM-01</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">班组</div>
																	<select id="yjh_teamtype" class="form-control" onchange="loadTeamCon();"></select>
																	<div id="yjh_team">
																		<!-- <span class="_team">掘进班组</span>&nbsp;&nbsp;<span class="_team">支护班组</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">标识工程</div>
																	<div id="yjh_flagprj">
																		<input type="checkbox" value="" name="flagproject"/>全部
																		<input type="checkbox" value="BSGC002" name="flagproject"/>重点工程
																		<input type="checkbox" value="BSGC003" name="flagproject"/>反井工程
																	</div>
																</div>
															</div>
															<div id="scrb" style="display:none;">
																<div>
																	<div class="con_h">业务类型</div>
																	<div id="scrb_ywlx">
																		<p><input type="checkbox" value="YW001" name="ywlx"/>掘进&nbsp;&nbsp;<input type="checkbox" value="YW002" name="ywlx"/>采（出）矿</p>
																		<p><input type="checkbox" value="YW003" name="ywlx"/>支护&nbsp;&nbsp;<input type="checkbox" value="YW004" name="ywlx"/>充填</p>
																		<p><input type="checkbox" value="YW005" name="ywlx"/>安装&nbsp;&nbsp;<input type="checkbox" value="YW006" name="ywlx"/>中深孔</p>
																		<!-- <p><input type="checkbox" value="YW007" name="ywlx"/>反井工程</p> -->
																		<p style="display:none" class="ckff">采矿方法：
																			<dmtag:sourcemanager sourcename="CKFF" name="scrb_minemethod" 
																				cssClass="form-control" onchange="loadModel();"></dmtag:sourcemanager>
																		</p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">日期</div>
																	<div id="scrb_month">
																		<input type="text" id="daytime" class="Wdate" style="height:30px;" value="<%=DateUtil.getYear() + "-" + DateUtil.getMonth()+"-"+DateUtil.getDay() %>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:changedayvalue()})"/>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">周次</div>
																	<div id="scrb_week">	
																		<select id="weektime" onchange="changeweekvalue(this.value)" onclick="clearday(this.value)"></select>
																	</div>
																	<div id="startendAll" style="display:none;">起止时间：<div id="startend"></div></div>
																</div>
																<hr>
																<div>
																	<div class="con_h">开始日期</div>
																	<div id="scrb_start">
																		<input type="text" id="daystart" class="Wdate" style="height:30px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:changedatevalue()})"/>
																	</div>
																	<div class="con_h">结束日期</div>
																	<div id="scrb_end">
																		<input type="text" id="dayend" class="Wdate" style="height:30px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:changedatevalue()})"/>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">区队</div>
																	<div id="scrb_qd">
																		<!-- <span class="_qd">500采区</span>&nbsp;&nbsp;<span class="_qd">700采区</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">设备</div>
																	<select id="scrb_devclass" class="form-control" onchange="reloadDevName();"></select>
																	<select id="scrb_devname" class="form-control" onchange="loadDevCon();"></select>
																	<div id="scrb_dev">
																		<!-- <span class="_dev">BM-01</span>&nbsp;&nbsp;<span class="_dev">BM-01</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">班组</div>
																	<select id="scrb_teamtype" class="form-control" onchange="loadTeamCon();"></select>
																	<div id="scrb_team">
																		<!-- <span class="_team">掘进班组</span>&nbsp;&nbsp;<span class="_team">支护班组</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">标识工程</div>
																	<div id="scrb_flagprj">
																		<input type="checkbox" value="" name="flagproject"/>全部
																		<input type="checkbox" value="BSGC002" name="flagproject"/>重点工程
																		<input type="checkbox" value="BSGC003" name="flagproject"/>反井工程
																	</div>
																</div>
															</div>
															<div id="nbys" style="display:none;">
																<div>
																	<div class="con_h">业务类型</div>
																	<div id="nbys_ywlx">
																		<p><input type="checkbox" value="YW001" name="ywlx"/>掘进&nbsp;&nbsp;<input type="checkbox" value="YW002" name="ywlx"/>采（出）矿</p>
																		<p><input type="checkbox" value="YW003" name="ywlx"/>支护&nbsp;&nbsp;<input type="checkbox" value="YW004" name="ywlx"/>充填</p>
																		<p><input type="checkbox" value="YW005" name="ywlx"/>安装&nbsp;&nbsp;<input type="checkbox" value="YWZSK" name="ywlx"/>中深孔</p>
																		<!-- <p><input type="checkbox" value="YW007" name="ywlx"/>反井工程</p> -->
																		<p style="display:none" class="ckff">采矿方法：
																			<dmtag:sourcemanager sourcename="CKFF" name="nbys_minemethod" 
																				cssClass="form-control" onchange="loadModel();"></dmtag:sourcemanager>
																		</p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">月份</div>
																	<div id="nbys_month">
																		<p><span class="_month" value="01">1月</span>&nbsp;&nbsp;<span class="_month" value="02">&nbsp;2月</span>&nbsp;&nbsp;<span class="_month" value="03">&nbsp;3月</span>&nbsp;&nbsp;<span class="_month" value="04">&nbsp;4月</span></p>
																		<p><span class="_month" value="05">5月</span>&nbsp;&nbsp;<span class="_month" value="06">&nbsp;6月</span>&nbsp;&nbsp;<span class="_month" value="07">&nbsp;7月</span>&nbsp;&nbsp;<span class="_month" value="08">&nbsp;8月</span></p>
																		<p><span class="_month" value="09">9月</span>&nbsp;&nbsp;<span class="_month" value="10">10月</span>&nbsp;&nbsp;<span class="_month" value="11">11月</span>&nbsp;&nbsp;<span class="_month" value="12">12月</span></p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">区队</div>
																	<div id="nbys_qd">
																		<!-- <span class="_qd">500采区</span>&nbsp;&nbsp;<span class="_qd">700采区</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">设备</div>
																	<select id="nbys_devclass" class="form-control" onchange="reloadDevName();"></select>
																	<select id="nbys_devname" class="form-control" onchange="loadDevCon();"></select>
																	<div id="nbys_dev">
																		<!-- <span class="_dev">BM-01</span>&nbsp;&nbsp;<span class="_dev">BM-01</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">班组</div>
																	<select id="nbys_teamtype" class="form-control" onchange="loadTeamCon();"></select>
																	<div id="nbys_team">
																		<!-- <span class="_team">掘进班组</span>&nbsp;&nbsp;<span class="_team">支护班组</span> -->
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">标识工程</div>
																	<div id="nbys_flagprj">
																		<input type="checkbox" value="" name="flagproject"/>全部
																		<input type="checkbox" value="BSGC002" name="flagproject"/>重点工程
																		<input type="checkbox" value="BSGC003" name="flagproject"/>反井工程
																	</div>
																</div>
															</div>
															<div id="wbys" style="display:none;">
																<div>
																	<div class="con_h">业务类型</div>
																	<div id="wbys_ywlx">
																		<p><input type="checkbox" value="YW001" name="ywlx"/>掘进&nbsp;&nbsp;<input type="checkbox" value="YW002" name="ywlx"/>采（出）矿</p>
																		<p><input type="checkbox" value="YW003" name="ywlx"/>支护&nbsp;&nbsp;<input type="checkbox" value="YW004" name="ywlx"/>充填</p>
																		<p><input type="checkbox" value="YW005" name="ywlx"/>安装&nbsp;&nbsp;<input type="checkbox" value="YWZSK" name="ywlx"/>中深孔</p>
																		<!-- <p><input type="checkbox" value="YW007" name="ywlx"/>反井工程</p> -->
																		<p style="display:none" class="ckff">采矿方法：
																			<dmtag:sourcemanager sourcename="CKFF" name="wbys_minemethod" 
																				cssClass="form-control" onchange="loadModel();"></dmtag:sourcemanager>
																		</p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">月份</div>
																	<div id="wbys_month">
																		<p><span class="_month" value="01">1月</span>&nbsp;&nbsp;<span class="_month" value="02">&nbsp;2月</span>&nbsp;&nbsp;<span class="_month" value="03">&nbsp;3月</span>&nbsp;&nbsp;<span class="_month" value="04">&nbsp;4月</span></p>
																		<p><span class="_month" value="05">5月</span>&nbsp;&nbsp;<span class="_month" value="06">&nbsp;6月</span>&nbsp;&nbsp;<span class="_month" value="07">&nbsp;7月</span>&nbsp;&nbsp;<span class="_month" value="08">&nbsp;8月</span></p>
																		<p><span class="_month" value="09">9月</span>&nbsp;&nbsp;<span class="_month" value="10">10月</span>&nbsp;&nbsp;<span class="_month" value="11">11月</span>&nbsp;&nbsp;<span class="_month" value="12">12月</span></p>
																	</div>
																</div>
																<hr>
																<div>
																	<div class="con_h">标识工程</div>
																	<div id="wbys_flagprj">
																		<input type="checkbox" value="" name="flagproject"/>全部
																		<input type="checkbox" value="BSGC002" name="flagproject"/>重点工程
																		<input type="checkbox" value="BSGC003" name="flagproject"/>反井工程
																	</div>
																</div>
															</div>
															
														</div>
								
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-10" style="padding:0px;border:0px solid #307ecc;">
									<div class="col-xs-12" style="padding:0px;">
										<div class="col-xs-12 col-sm-9" id="modelcol">
											<div class="col-xs-12">
												<div class="widget-box ui-sortable-handle" style="margin-top:2px;border:0px solid #307ecc;">
													<div class="col-xs-12 col-sm-1" style="text-align: left;">
														<span onclick="slide('left')" class="left"><i class="ace-icon fa fa-angle-double-left bigger-200"></i></span>
													</div>
													<div class="col-xs-12 col-sm-10" style="text-align: left;">
														<span id="deptname"></span>&nbsp;<i class="ace-icon fa fa-search" id="deptsearch"></i>&nbsp;&nbsp;
														<input type="checkbox" value="GCLX001" id="jhmodeltype" name="modeltype"/>井巷工程模型
														<input type="checkbox" value="GCLX002" id="ccmodeltype" name="modeltype"/>采场模型
													</div>
													<div class="col-xs-12 col-sm-1" style="text-align: right;">
														<span onclick="slide('right')" class="right"><i class="ace-icon fa fa-angle-double-right bigger-200"></i></span>
													</div>
												</div>
											</div>
											<div class="col-xs-12" style="margin:2px 0px;">
												<div class="col-xs-12 col-sm-6" style="text-align: left;">
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="allPro">全部工程</button>
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="impPro" style="display:none">重点工程</button>
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="yearPlanPro" style="display:none">年计划工程对比</button>
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="yearPlanPro2" style="display:none">年计划工程对比</button>
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="mthPlanPro" style="display:none">月计划工程对比</button>
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="mthPlanPro2" style="display:none">月计划工程对比</button>
													<button type="button" class="btn btn-primary btn-xs ladda-button" data-style="slide-right" data-size="l" id="mthcaaPro" style="display:none">与内部验收工程对比</button>
												</div>
												<div class="col-xs-12 col-sm-6 kjbtn" style="text-align: right;margin-top:6px;">
													<div><img src="./xz.png" onclick="orbit(this)" title="旋转" /></div>&nbsp;&nbsp;
													<div><img src="./yd.png" onclick="pan(this)" title="平移"/></div>&nbsp;&nbsp;
													<div><img src="./fw.png" onclick="zoomToExtents(this)" title="复位视图"/></div>&nbsp;&nbsp;
													<div><img src="./bottom.png" onclick="xZPlane(this)" title="从上往下看"/></div>&nbsp;&nbsp;
													<div><img src="./back.png" onclick="yxPlane(this)" title="从前向后看"/></div>&nbsp;&nbsp;
													<div><img src="./left.png" onclick="yzPlane(this)" title="从右向左看"/></div>&nbsp;&nbsp;
													<div><img src="./ch.png" onclick="select(this)" title="选择"/></div>
												</div>
											</div>
											<div class="col-xs-12" style="padding:0px;">
												<div style="width: 100%; height: 550px;border:1px solid #307ecc;">
													<object id="DimineWindow"
														classid="CLSID:224DB674-8091-4F6B-9762-B622895002CA"
														codebase="../../../upfile/dimine/DimineX.cab#version=1,0,0,2"
														style="width: 100%; height: 100%">
													</object>
												</div>
											</div>
										</div>
										
										<div class="col-xs-12 col-sm-3" id="rightslide" style="position: relative;height: 615px;">
											<table id="propertiesvalue" class="dtable" style="position: absolute;top: 0;"></table>
											<table id="report" class="dtable" style="position: absolute;bottom: 0;">
												<tr>
													<th width="100%">统计报表列表</th>
												</tr>
												<tr>
													<td><a class="yearrpt">年计划查看</a></td>
												</tr>
												<tr>
													<td><a class="monthrpt">月计划查看</a></td>
												</tr>
											</table>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="widget-box ui-sortable-handle" style="margin-top:2px;border:0px solid #307ecc;">
											<iframe id="datagrid_ifr" name="datagrid_ifr" src="<%=request.getContextPath()%>/biz/bp/yearplan/threedimensional/bizmain.jsp" 
												frameborder="0" scrolling="no" style="width:100%;height:400px;"></iframe>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->


			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script src="<%=request.getContextPath()%>/plugins/components/jquery/dist/jquery.js"></script>
		<!-- <![endif]-->
	
		<!--[if IE]>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.1x/dist/jquery.js"></script>
		<![endif]-->
	
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='<%=request.getContextPath()%>/plugins/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
		</script>

		<script src="<%=request.getContextPath()%>/plugins/components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/select2/dist/js/select2.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/typeahead.js/dist/typeahead.jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery.gritter/js/jquery.gritter.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jquery-ui/jquery-ui.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/demojs/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="<%=request.getContextPath()%>/webpage/main/pub.js"></script>
   		<script src="<%=request.getContextPath()%>/plugins/jquery/jquery.cookie.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jqueryui-touch-punch/jquery.ui.touch-punch.min.js"></script>
   		<script src="<%=request.getContextPath()%>/plugins/components/bootbox.js/bootbox.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jqgrid/js/jquery.jqgrid.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/components/jqgrid/js/i18n/grid.locale-<s:text name="grid.lang" />.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/pub/biz/grid_<s:text name="dimine.lang" />.js"></script>
	   	<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/owin.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/botree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/widget/radiotree.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/plugins/jquery/jquery-json.js"></script>
	   	<script type="text/javascript">
	   		var year = "<%=com.dimine.sys.util.DateUtil.getYear()%>";
	   		var time_month='<%=DateUtil.getMonth() %>';
	   		$(function() {
				//页面脚本
				$('#yearvalue').ace_spinner({value:year,min:2000,max:2100,step:1, on_sides: true, icon_up:'ace-icon fa fa-plus smaller-75', icon_down:'ace-icon fa fa-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'})
				.closest('.ace-spinner')
				.on('changed.fu.spinbox', function(){
					loadModel();
				});
				$.fn.zTree.init($("#radiotreedemo"), radiosetting, zNodes);
			});
			$("#leftslide").height(1006);
			/*$(window).resize(function() {
				$("#leftslide").height($(window).height());
			});*/
			var deptid="<%=deptid%>";
			var port = "<%=request.getServerPort()%>";
			var host = "<%=request.getLocalAddr()%>";
			var now = "<%=DateUtil.getYear() + "-" + DateUtil.getMonth()+"-"+DateUtil.getDay() %>";
			var data_week = null;
		</script>
	   	<script src="./pub.js"></script>
	   	<script src="./dimine.js"></script>
	   	<!-- <script src="./yjh.js"></script>
	   	<script src="./scdd.js"></script>
	   	<script src="./nwbys.js"></script> -->
		<!-- ace scripts -->
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace.min.js"></script>
		<script src="<%=request.getContextPath()%>/plugins/assets/js/ace-elements.min.js"></script>
	</body>
</html>
