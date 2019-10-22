<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../../pub/biz/headinfo.jsp" %>
<div id="sys_modelmapp_info" style="display:none">
	<form id="sys_modelmapp_form" method="post" class="form-horizontal">
		<input type="hidden" id="mid" name="mid"/>
		<input type="hidden" id="modelid" name="modelid"/>
		
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="ecolname">excel列名称</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<div id="ecolnameT" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="mfield">映射字段</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<div id="mfieldT" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="isnull">是否为空</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="checkbox" checked="checked" id="isnull" name="isnull" class="FormElement ace ace-switch ace-switch-lt"><span class="lbl"></span> 
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="isscode">是否转码</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="checkbox" id="isscode" name="isscode" class="FormElement ace ace-switch ace-switch-lt" onclick="hiddencodetext()"><span class="lbl"></span> 
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group" id="isdicttypeid" style="display:none">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="dicttypeid">转码列的父类</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<div id="dicttypeidT" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="dtype">数据类型</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<div id="dtypeT" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>

	</form>
</div>