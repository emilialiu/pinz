<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="../../../pub/biz/headinfo.jsp"%>
<div id="sys_modelentitymapp_info">
	<form id="sys_modelentitymapp_form" method="post"
		class="form-horizontal">

		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="tablename">
				表名称
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="tablename" id="tablename"
						class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="entityclass">
				实体类名
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="entityclass" id="entityclass"
						class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="isauto">
				自动生成列值
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<select class="chosen-select form-control" id="isauto" onchange="changeDbkey(this.value)" data-placeholder="Choose a State...">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-xs-12 col-sm-3 no-padding-right"
				for="dbkey">
				自动键列
			</label>
			<div class="col-xs-12 col-sm-9">
				<div class="col-sm-6">
					<!-- class="clearfix" -->
					<input type="text" name="dbkey" id="dbkey" class="col-xs-12" />
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>

	</form>
</div>