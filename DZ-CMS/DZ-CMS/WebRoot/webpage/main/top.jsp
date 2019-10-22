<%@page import="com.dimine.sys.data.ResourceManager"%>
<%@page import="com.dimine.base.util.WebUtil"%>
<%@page import="com.dimine.security.entity.LoginUserEntity"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
LoginUserEntity loginuser = WebUtil.getLoginUser(session);	
String username = "";
if(loginuser != null){
username = loginuser.getUsername();
}
 %>
<!-- #section:basics/navbar.layout -->
<div>
 	<div class="top_banner">
	<!-- 	<div class="logo"></div> -->
	</div>
</div>
<div id="navbar" class="navbar navbar-default">
	<div class="navbar-container" id="navbar-container">
		<!-- #section:basics/sidebar.mobile.toggle -->
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>
		</button>

		<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-tasks"></i>
								<span class="badge badge-grey"></span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-check"></i>
									<span class="tomatter_count"></span> 待办
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar tomatter_list"></ul>
								</li>

								<li class="dropdown-footer">
									<a href="javascript:void(0);" onclick="view_db();">
										查看所有待办
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="purple">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-bell icon-animated-bell"></i>
								<span class="badge badge-important"></span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-exclamation-triangle"></i>
									<span class="alarm_count"></span> 告警
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar navbar-pink alarm_list"></ul>
								</li>

								<li class="dropdown-footer">
									<a href="javascript:void(0)" onclick="openwaring()">
										查看所有告警
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
								<span class="badge badge-success"></span>
							</a>

							<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="ace-icon fa fa-envelope-o"></i>
									<span class="message_count"></span> 公告
								</li>

								<li class="dropdown-content">
									<ul class="dropdown-menu dropdown-navbar message_list"></ul>
								</li>

								<li class="dropdown-footer">
									<a href="javascript:void(0)" onclick="openmessage()" >
										查看所有公告
										<i class="ace-icon fa fa-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>

						<!-- #section:basics/navbar.user_menu -->
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info">
									<small><s:text name="dmmes.home.welcome" />,</small> <%=username%>
								</span>

								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="javascript:void(0)" onclick="openShortcut();" >
										<i class="ace-icon fa fa-cog"></i>
										<s:text name="dmmes.home.shortcutkey" />
									</a>
								</li>

								<li>
									<a href="#" onclick="updatePassword()">
										<i class="ace-icon fa fa-user"></i>
										<s:text name="dmmes.home.modifypassword" />
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="<%=request.getContextPath()%>/main/logout.action">
										<i class="ace-icon fa fa-power-off"></i>
										<s:text name="dmmes.home.signout" />
									</a>
								</li>
							</ul>
						</li>

						<!-- /section:basics/navbar.user_menu -->
					</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
			</div>
			<div>
				 <div id="doSelectTopfuncsinfo" style="overflow:hidden;white-space:nowrap;"></div>
			</div>
			<!-- /.navbar-container -->
		</div>
		
		<%@ include file="updatepwd.jsp"%>